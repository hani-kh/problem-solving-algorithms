package examples.tsp;

import core.State;

import java.util.List;

public class TspState implements State {

    public int graphSize; //holds the max length, n-vertices
    public int graphCurrentStateIndex; //holds the current vertex INDEX value {0,1,2,3,4,5,6}
    public int[][] theGraph; //hold the graph into 2D array

    public List<Integer> route;

    public TspState() {
    }

    public TspState(int[][] x, int y, int z, List<Integer> route) {
        graphCurrentStateIndex = y;
        graphSize = z;
        theGraph = x;
        this.route = route;
    }

    @Override
    //checks whether the state calling is a goal state or not
    public boolean isGoal() {
        for (int i = 1; i < graphSize; i++) {
            if (!route.contains(i)) {
                return false;
            }
        }
        return graphCurrentStateIndex == 0;
    }

    @Override
    public boolean equals(Object s) {

        /*
        local temp variable to hold S's value.
        The element (s, which is form State type)
        has to be explicitly cast back to its original type:
        */
        TspState temp = (TspState) s;
        if (route.size() != temp.route.size()) {
            return false;
        }
        for (int i = 0; i < route.size(); i++) {
            if (route.get(i) != temp.route.get(i))
                return false;
        }
        if (graphCurrentStateIndex != temp.graphCurrentStateIndex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "State:\n" + this.graphCurrentStateIndex;
    }
}
