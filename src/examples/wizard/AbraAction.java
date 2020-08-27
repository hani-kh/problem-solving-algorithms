package examples.wizard;

import core.IAction;
import core.State;

import java.util.ArrayList;
import java.util.Collection;

public class AbraAction implements IAction {

    @Override
    public Collection<State> apply(State s) {
        WizardState currentState = (WizardState) s;
        int n = currentState.currentStateNumber.length();
        int turns = (int) currentState.A % n;
        StringBuilder t = new StringBuilder();
        int cut = Math.max(0, n - turns);
        for (int i = cut; i < n; i++) t.append(currentState.currentStateNumber.charAt(i));
        for (int i = 0; i < cut; i++) t.append(currentState.currentStateNumber.charAt(i));
        WizardState successorState = new WizardState(currentState.A, currentState.H, t.toString());
        return new ArrayList<State>() {{
            add(successorState);
        }};
    }

    @Override
    public String getName() {
        return "Abra";
    }
}
