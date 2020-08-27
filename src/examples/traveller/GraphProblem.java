package examples.traveller;

import core.HeuristicFunction;
import core.IAction;
import core.Problem;
import core.State;
import core.models.GUIEdge;
import core.models.GUIGraph;
import core.models.GUINode;

import java.util.ArrayList;
import java.util.List;

public class GraphProblem extends Problem {
    private GraphState initialState;
    private GraphState goalState;
    private double [] sldMap;
    public GraphProblem(){
        GraphState s = new GraphState();
        s.graphSize = 4; //total graph size (how many vertices in it).
        s.graphCurrentStateIndex = 0; //in this case it's the same as the initial state.
        s.theGraph = new int[][]{
                {0, 1, 1, 0},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {0, 0, 1, 0}
        };
        initialState = s;
    }
    public GraphProblem(GUIGraph graph){
        int graphSize = graph.getGUINodes().size();
        int[][] theGraph = new int[graphSize][graphSize];
        for (GUIEdge edge:graph.getGUIEdges()){
            theGraph[edge.getNodeOne().getId()][edge.getNodeTwo().getId()] = edge.getWeight();
            theGraph[edge.getNodeTwo().getId()][edge.getNodeOne().getId()] = edge.getWeight();

        }

        for (int i = 0;i<graphSize;i++)
        {
            System.out.println();
            for(int j=0;j<graphSize;j++){
                System.out.print(theGraph[i][j]+" ");
            }
        }
        initialState = new GraphState();
        goalState = new GraphState();

        initialState.graphCurrentStateIndex = graph.getSource().getId();
        initialState.theGraph = theGraph;
        initialState.graphSize = graphSize;



        goalState.graphCurrentStateIndex = graph.getDestination().getId();
        goalState.graphSize = graphSize;
        goalState.theGraph = theGraph;
        sldMap = new double[graphSize];
        for(GUINode node:graph.getGUINodes()){
            sldMap[node.getId()] = node.getHeuristicValue();
        }
    }
    @Override
    public State getInitialState() {
        return initialState;
    }

    @Override
    public boolean isGoal(State state) {
        return state.equals(goalState);
        //return  state.isGoal();
    }

    @Override
    public List<IAction> getActions() {
        return new ArrayList<IAction>() {
            {
                add(new MoveToNextCity());
            }
        };
    }

    @Override
    public double getActionCost(State s, IAction a, State ss) {

        GraphState t = (GraphState)s;
        GraphState tt = (GraphState)ss;
        return  t.theGraph[t.graphCurrentStateIndex][tt.graphCurrentStateIndex];
    }
    @Override
    public HeuristicFunction getHeuristicFunction(){
        return  new SLDHeuristic(sldMap);
    }

}
