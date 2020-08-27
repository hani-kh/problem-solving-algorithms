package examples.Bridge;

import core.IAction;
import core.State;

import java.util.ArrayList;
import java.util.Collection;

public class MoveTwoPersons implements IAction {

    int firstPersonToMove;
    int secondPersonToMove;
    int side;

    public MoveTwoPersons(int firstPersonToMove, int secondPersonToMove, int side) {
        this.firstPersonToMove = firstPersonToMove;
        this.secondPersonToMove = secondPersonToMove;
        this.side = side;
    }

    @Override
    public Collection<State> apply(State s) {
        BridgeState t = (BridgeState) s;
        ArrayList<State> res = new ArrayList<>();
        if (t.personsSide[firstPersonToMove] != side && t.personsSide[secondPersonToMove] != side && t.lanternSide != side) {
            BridgeState newState = new BridgeState();
            newState.personsSide = new int[t.personsSide.length];
            for (int i = 0; i < t.personsSide.length; i++) {
                newState.personsSide[i] = t.personsSide[i];
            }
            newState.personsSide[firstPersonToMove] = side;
            newState.personsSide[secondPersonToMove] = side;
            newState.lanternSide = side;
            res.add(newState);
        }
        return res;
    }

    @Override
    public String getName() {
        String res = "Move ";
        if (firstPersonToMove == 0) {
            res += "Son";
        }
        if (firstPersonToMove == 1) {
            res += "Father";
        }
        if (firstPersonToMove == 2) {
            res += "Mother";
        }
        if (firstPersonToMove == 3) {
            res += "GrandFather";
        }
        res += "&";
        if (secondPersonToMove == 0) {
            res += "Son";
        }
        if (secondPersonToMove == 1) {
            res += "Father";
        }
        if (secondPersonToMove == 2) {
            res += "Mother";
        }
        if (secondPersonToMove == 3) {
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
