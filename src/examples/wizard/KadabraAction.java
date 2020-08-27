package examples.wizard;

import core.IAction;
import core.State;

import java.util.ArrayList;
import java.util.Collection;

public class KadabraAction implements IAction {

    private char fix(char c, long h) {
        int chr = (c - '0');
        chr += h;
        chr %= 10;
        return (char) (chr + '0');
    }

    @Override
    public Collection<State> apply(State s) {
        WizardState currentState = (WizardState) s;
        int n = currentState.currentStateNumber.length();
        StringBuilder t = new StringBuilder();
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            if (j % 2 == 0) {
                t.append(fix(currentState.currentStateNumber.charAt(i), currentState.H));
            } else {
                t.append(currentState.currentStateNumber.charAt(i));
            }
        }
        WizardState successorState = new WizardState(currentState.A, currentState.H, t.toString());
        return new ArrayList<State>() {{
            add(successorState);
        }};
    }

    @Override
    public String getName() {
        return "Kadabra";
    }
}
