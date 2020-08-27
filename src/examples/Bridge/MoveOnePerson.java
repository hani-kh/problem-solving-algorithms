package examples.Bridge;

import core.IAction;
import core.State;

import java.util.ArrayList;
import java.util.Collection;

public class MoveOnePerson implements IAction {

    int personToMove;
    int side;

    public MoveOnePerson(int personToMove, int side) {
        this.personToMove = personToMove;
        this.side = side;
    }

    @Override
    public Collection<State> apply(State s) {
        BridgeState t = (BridgeState) s;
        ArrayList<State> res = new ArrayList<>();
        if (t.personsSide[personToMove] != side && t.lanternSide != side) {
            BridgeState newState = new BridgeState();
            newState.personsSide = new int[t.personsSide.length];
            for (int i = 0; i < t.personsSide.length; i++) {
                newState.personsSide[i] = t.personsSide[i];
            }
            newState.personsSide[personToMove] = side;
            newState.lanternSide = side;
            res.add(newState);
        }
        return res;
    }

    @Override
    public String getName() {
        String res = "Move ";
        if (personToMove == 0) {
            res += "Son";
        }
        if (personToMove == 1) {
            res += "Father";
        }
        if (personToMove == 2) {
            res += "Mother";
        }
        if (personToMove == 3) {
            res += "GrandFather";
        }
        res += " ";
        if (side > 0) {
            res += "Right";
        } else {
            res += "Left";
        }
        return res;
    }
}
