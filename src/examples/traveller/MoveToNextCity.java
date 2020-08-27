package examples.traveller;

import core.IAction;
import core.State;

import java.util.ArrayList;
import java.util.Collection;

public class MoveToNextCity implements IAction {

    @Override
    public Collection<State> apply(State s) {

        //Inputs: a state, Outputs: collection of states (valid moves)
        GraphState currentState = (GraphState) s;

        //maximum moves (links to other cities) are Graphsize-1...
        //where our current state (city) could be linked to all other vertices
        ArrayList<State> nextStates = new ArrayList<>();

        //if we're not in the final state (graphSize-1) and
        for(int i=0;i<currentState.graphSize;i++){
            //if current state != final state number and for each 1 in the matrix(which represent a link)
            if ((currentState.theGraph[currentState.graphCurrentStateIndex][i]!=0)) {

                //pass all fields on with each link found between our temp state...
                //and other vertices and then add it to out (nextStates) collection...
                //and repeat until we reach the end of the horizontal axis of (theGraph) array...
                // which holds the 1's in represents of links with other vertices.
                GraphState newState = new GraphState();
                newState.graphCurrentStateIndex=i;
                newState.graphSize=currentState.graphSize;
                newState.theGraph=currentState.theGraph;

                nextStates.add(newState);
            }

        }

        return nextStates;
    }

    @Override
    public String getName() {
        return "MoveToNextCity";
    }
}
