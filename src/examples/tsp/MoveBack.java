package examples.tsp;

import core.IAction;
import core.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MoveBack implements IAction {
    @Override
    public Collection<State> apply(State s) {
        //Inputs: a state, Outputs: collection of states (valid moves)
        TspState currentState = (TspState) s;

        //maximum moves (links to other cities) are Graphsize-1...
        //where our current state (city) could be linked to all other vertices
        ArrayList<State> nextStates = new ArrayList<>();

        if (currentState.route.size() == currentState.graphSize - 1) {
            if ((currentState.theGraph[currentState.graphCurrentStateIndex][0] != 0)) {
                TspState newState = new TspState();
                newState.graphCurrentStateIndex = 0;
                newState.graphSize = currentState.graphSize;
                newState.theGraph = currentState.theGraph;
                List<Integer> tRoute = new ArrayList<>();
                for (Integer city : currentState.route) {
                    tRoute.add((city));
                }
                tRoute.add(currentState.graphCurrentStateIndex);
                newState.route = tRoute;

                nextStates.add(newState);
            }
        }
        return nextStates;
    }

    @Override
    public String getName() {
        return "Move Back";
    }
}
