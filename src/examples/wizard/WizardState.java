package examples.wizard;

import core.State;

public class WizardState implements State {

    public String currentStateNumber; // holds the current number the wizard has
    public long A, H;

    public WizardState(long a, long h, String s) {
        A = a;
        H = h;
        currentStateNumber = s;
    }

    public WizardState(WizardState s) {
        A = s.A;
        H = s.H;
        currentStateNumber = s.currentStateNumber;
    }

    @Override
    //checks whether the state calling is a goal state or not
    // in this problem the goal state is not pre-defined
    public boolean isGoal() {
        return false;
    }

    @Override
    public boolean equals(Object s) {

        /*
        local temp variable to hold S's value.
        The element (s, which is form State type)
        has to be explicitly cast back to its original type:
        */
        WizardState temp = (WizardState) s;
        return currentStateNumber.equals(temp.currentStateNumber);
    }

    @Override
    public String toString() {
        return this.currentStateNumber;
    }
}
