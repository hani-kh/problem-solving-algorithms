package examples.tsp;

import core.HeuristicFunction;
import core.State;

//#cost of min arc * num of missing arcs;
public class H2 implements HeuristicFunction {

    @Override
    public double getH(State s) {
        TspState t = (TspState) s;
        double mn = Double.POSITIVE_INFINITY;
        for (int i = 0; i < t.graphSize; i++) {
            if (t.route.size() >0 && i == t.route.get(t.route.size()-1)) {
                continue;
            }
            if (t.theGraph[t.graphCurrentStateIndex][i] != 0) {
                mn = Math.min(mn, t.theGraph[t.graphCurrentStateIndex][i]);
            }
        }
        return mn;
    }
}
