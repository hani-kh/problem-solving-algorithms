/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.Bridge;

import core.IAction;
import core.Problem;
import core.State;

import java.util.ArrayList;
import java.util.List;

public class BridgeProblem extends Problem {

    @Override
    public State getInitialState() {
        BridgeState s = new BridgeState();
        s.personsSide = new int[]{0, 0, 0, 0};
        s.lanternSide = 0;
        return s;
    }

    @Override
    public boolean isGoal(State state) {
        return state.isGoal();
    }

    @Override
    public List<IAction> getActions() {
        int[] sidesToMove = new int[]{0, 1};
        List<IAction> res = new ArrayList<>();
        for (int j = 0; j < 2; j++) {

            for (int i = 0; i < 4; i++) {
                res.add(new MoveOnePerson(i,sidesToMove[j]));
                for (int k = i + 1; k < 4; k++) {
                    res.add(new MoveTwoPersons(i,k,sidesToMove[j]));
                }
            }
        }
        return res;
    }

    @Override
    public double getActionCost(State s, IAction a, State ss) {

        var crossTime = Double.MIN_VALUE;
        BridgeState t = (BridgeState) s;
        BridgeState tt = (BridgeState) ss;
        for (int i = 0; i < 4; i++) {
            if (t.personsSide[i] != tt.personsSide[i]) {
                crossTime = Math.max(crossTime, t.crossCost[i]);
            }
        }
        return crossTime;
    }
}
