package examples.King;

import core.IAction;
import core.Problem;
import core.State;

import java.util.ArrayList;
import java.util.List;

public class KingProblem extends Problem {
    @Override
    public State getInitialState() {
        KingGraphState s = new KingGraphState();
        s.graphSize = 8; //total graph size (how many vertices in it).
        s.graphCurrentStateIndex = 1; //in this case it's the same as the initial state.
        s.theGraph = new int[][]{
           //dummy, S, 2, 3, 4, 5, 6, G
                {0, 0, 0, 0, 0, 0, 0, 0},//dummy
                {0, 0, 5, 3, 0, 0, 0, 0},//1(S)
                {0, 0, 0, 0, 6, 0, 0, 0},//2
                {0, 0, 0, 0, 0, 0, 0, 0},//3
                {0, 0, 0, 0, 0, 0, 0, 0},//4
                {0, 0, 0, 8, 0, 0, 0, 0},//5
                {0, 0, 0, 0, 4, 2, 0, 0},//6
                {0, 10, 0, 0, 0, 3, 0, 0},//7(G)


        };
        return s;

    }

    @Override
    public boolean isGoal(State state) {
        return state.isGoal();
    }

    @Override
    public List<IAction> getActions() {
        return new ArrayList<IAction>() {
            {
                add(new DirectionalMove());
                add(new SwitchRoad());
            }
        };
    }

    @Override
    public double getActionCost(State s, IAction a, State ss) {
        if (a.getName() == "DirectionalMove") {
            return 0;
        } else {
            KingGraphState t = (KingGraphState) s;
            KingGraphState tt = (KingGraphState) ss;
            return tt.theGraph[tt.graphCurrentStateIndex][t.graphCurrentStateIndex];
        }
    }

}
