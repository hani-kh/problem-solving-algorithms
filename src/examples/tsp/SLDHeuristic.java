package examples.tsp;

import core.HeuristicFunction;
import core.State;

//SLD of straight line distance stores h value for all states for graph problem in a map.
public class SLDHeuristic implements HeuristicFunction {
    private double []sldMap;
    public SLDHeuristic(double [] map){
        sldMap = map;
    }
    @Override
    public double getH(State s) {
        TspState casted = (TspState)s;
        return  sldMap[casted.graphCurrentStateIndex];
    }
}
