package examples.tsp;

import core.HeuristicFunction;
import core.State;

//#cost of max arc * num of missing arcs;
public class H7 implements HeuristicFunction {

    @Override
    public double getH(State s) {
        TspState t = (TspState) s;
        double mn = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < t.graphSize; i++) {
            if (t.route.size() >0 && i == t.route.get(t.route.size()-1)) {
                continue;
            }
            if (t.theGraph[t.graphCurrentStateIndex][i] != 0) {
                mn = Math.max(mn, t.theGraph[t.graphCurrentStateIndex][i]);
            }
        }
        return mn;
    }
}
