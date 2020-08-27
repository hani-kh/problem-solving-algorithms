package examples.tsp;

import core.HeuristicFunction;
import core.IAction;
import core.Problem;
import core.State;

import java.util.ArrayList;
import java.util.List;

public class TspProblem extends Problem {
    private TspState initialState;
    // private TspState goalState;
   // private double[] sldMap;

    public TspProblem() {
        TspState s = new TspState();
        s.graphSize = 5; //total graph size (how many vertices in it).
        s.graphCurrentStateIndex = 0; //in this case it's the same as the initial state.
        s.theGraph = new int[][]{
                {0, 20, 15, 7, 0},
                {20, 0, 6, 6, 3},
                {15, 6, 0, 5, 5},
                {7, 6, 5, 0, 10},
                {0, 3, 5, 10, 0}
        };
        s.route = new ArrayList<>();
        initialState = s;
    }

    @Override
    public State getInitialState() {
        return initialState;
    }

    @Override
    public boolean isGoal(State state) {
        return state.isGoal();
        //return  state.isGoal();
    }

    @Override
    public List<IAction> getActions() {
        return new ArrayList<IAction>() {
            {
                add(new TspMove());
                add(new MoveBack());
            }
        };
    }

    @Override
    public double getActionCost(State s, IAction a, State ss) {

//        TspState tt = (TspState)ss;
//        var cost = 0;
//        for(int i=1;i<tt.route.size();i++){
//            cost+=tt.theGraph[tt.route.get(i-1)][tt.route.get(i)];
//        }
//        if(tt.route.size() == tt.graphSize-1){
//            cost+=tt.theGraph[tt.route.get(tt.route.size()-1)][0];
//        }
//        return cost;
        TspState t = (TspState) s;
        TspState tt = (TspState) ss;
        return t.theGraph[t.graphCurrentStateIndex][tt.graphCurrentStateIndex];

    }

    @Override
    public HeuristicFunction getHeuristicFunction() {
        return new H7();
    }

}
