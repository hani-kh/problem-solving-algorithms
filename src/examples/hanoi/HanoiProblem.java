package examples.hanoi;

import core.HeuristicFunction;
import core.IAction;
import core.Problem;
import core.State;

import java.util.ArrayList;
import java.util.List;

public class HanoiProblem extends Problem {
    @Override
    public State getInitialState() {
        HanoiState s = new HanoiState();
        s.tablets = new int[]{0, 0, 0}; // all tablets in first tower.
        return s;

    }

    @Override
    public boolean isGoal(State state) {
        return state.isGoal();
    }

    @Override
    public List<IAction> getActions() {
        int[] sidesToMove = new int[]{-1, 1};
        List<IAction> res = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                res.add(new MoveTablet(i,sidesToMove[j]));
            }
        }
        return res;
    }
    public HeuristicFunction getHeuristicFunction(){
        return new HanoiHeurisic();
    }
    @Override
    public double getActionCost(State s, IAction a, State ss) {
        return 1;
    }
}
