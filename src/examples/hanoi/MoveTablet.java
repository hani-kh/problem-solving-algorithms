package examples.hanoi;

import core.IAction;
import core.State;

import java.util.ArrayList;
import java.util.Collection;

public class MoveTablet implements IAction {
    int indexOfTablet;
    int sideToMove;

    public MoveTablet(int indexOfTablet, int sideToMove) {
        this.indexOfTablet = indexOfTablet;
        this.sideToMove = sideToMove;
    }

    @Override
    public Collection<State> apply(State s) {

        HanoiState t = (HanoiState) s;
        ArrayList<State> res = new ArrayList<>();


        boolean canMove = true;
        if (t.tablets[indexOfTablet] + sideToMove >= 0 && t.tablets[indexOfTablet] + sideToMove <= 2) {
            for (int j = 0; j < indexOfTablet; j++) {
                if (t.tablets[j] == t.tablets[indexOfTablet]) {
                    canMove = false;
                }
            }
            if (canMove) {

                HanoiState child = new HanoiState();

                child.tablets = new int[t.tablets.length];
                for (int k = 0; k < 3; k++) {
                    child.tablets[k] = t.tablets[k];
                }
                child.tablets[indexOfTablet] += sideToMove;

                boolean isValid = true;
                for (int j = 0; j < indexOfTablet; j++) {

                    if (child.tablets[indexOfTablet] == child.tablets[j]) {
                        isValid = false;
                    }
                }
                if (isValid) {

                    res.add(child);
                }
            }
        }
        return res;

    }

    @Override
    public String getName() {
        String res = "Move ";
        if (indexOfTablet == 0) {
            res += "Small";
        } else if (indexOfTablet == 1) {
            res += "Medium";
        } else if (indexOfTablet == 2) {
            res += "Large";
        }
        res += " Tablet ";
        if (sideToMove > 0) {
            res += "Right";
        } else {
            res += "Left";
        }
        return res;
    }
}
