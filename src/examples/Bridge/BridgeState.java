package examples.Bridge;

import core.State;

public class BridgeState implements State {

    int[] crossCost = new int[]{ 1, 2, 5, 8 };
    int[] personsSide;
    int lanternSide;

    public BridgeState(int[] cost, int lanternOnTheRightSide) {
        this.crossCost = cost;
        this.lanternSide = lanternOnTheRightSide;
    }

    public BridgeState() {

    }

    @Override
    public boolean isGoal() {
        for (int i = 0; i < personsSide.length; i++)
            if (personsSide[i] != 1) {
                return false;
            }
        return true;
    }

    @Override
    public boolean equals(Object s) {
        BridgeState t = (BridgeState) s;
        for (int i = 0; i < personsSide.length; i++) {
            if (this.personsSide[i] != t.personsSide[i]) {
                return false;
            }
        }
        if (this.lanternSide != t.lanternSide) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String res = "\n";
        for (int i = 0; i < personsSide.length; i++) {
            res += personsSide[i] + "  ";
        }
        return res;
    }
}
