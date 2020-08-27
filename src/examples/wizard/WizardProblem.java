package examples.wizard;

import core.IAction;
import core.Problem;
import core.State;

import java.util.ArrayList;
import java.util.List;

public class WizardProblem extends Problem {
    private long A, H;
    private String number;

    public WizardState MinimumNumberState;
    public WizardState GoalState;

    public WizardProblem(long A, long H, String number) {
        this.A = A;
        this.H = H;
        this.number = number;
    }


    @Override
    public State getInitialState() {
        WizardState s = new WizardState(A, H, number);
        MinimumNumberState = s;
        return s;
    }

    @Override
    public boolean isGoal(State state) {

        WizardState w = (WizardState) state;
        if (Integer.parseInt(w.currentStateNumber) < Integer.parseInt(MinimumNumberState.currentStateNumber)) {
            MinimumNumberState = w;
        }
        if (GoalState != null && w.equals(GoalState)) {
            return true;
        }
        return false;
    }

    @Override
    public List<IAction> getActions() {
        return new ArrayList<>() {
            {
                add(new AbraAction());
                add(new KadabraAction());
            }
        };
    }

    @Override
    public double getActionCost(State s, IAction a, State ss) {
        return 1;
    }

}
