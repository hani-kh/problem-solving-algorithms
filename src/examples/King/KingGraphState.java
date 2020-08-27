package examples.King;

import core.State;

public class KingGraphState implements State {

    public int graphSize; //holds the max length, n-vertices
    public int graphCurrentStateIndex; //holds the current vertex INDEX value {0,1,2,3,4,5,6}
    public int[][] theGraph; //hold the graph into 2D array

    public KingGraphState() {
    }

    public KingGraphState(int[][] x, int y, int z) {
        graphCurrentStateIndex = y;
        graphSize = z;
        theGraph = x;
    }

    @Override
    //checks whether the state calling is a goal state or not
    public boolean isGoal() {
        return graphCurrentStateIndex == 7;
    }

    @Override
    public boolean equals(Object s) {

        /*
        local temp variable to hold S's value.
        The element (s, which is form State type)
        has to be explicitly cast back to its original type:
        */
        KingGraphState temp = (KingGraphState) s;
        if (graphCurrentStateIndex != temp.graphCurrentStateIndex) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {

         if(graphCurrentStateIndex == 1){
             return  "S";
         }else if(graphCurrentStateIndex == 7){
             return "G";
         }
         return graphCurrentStateIndex+"";
    }
}
