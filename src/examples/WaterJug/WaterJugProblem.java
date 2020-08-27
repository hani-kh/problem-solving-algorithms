/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.WaterJug;

import core.IAction;
import core.Problem;
import core.State;

import java.util.ArrayList;
import java.util.List;


public class WaterJugProblem extends Problem {

    @Override
    public State getInitialState() {
        WaterState ws = new WaterState();
        ws.jugsSizes = new int[]{
                8, 5, 3
        };

        return ws;
    }

    @Override
    public boolean isGoal(State state) {
        return state.isGoal();
    }

    @Override
    public List<IAction> getActions() {
        return new ArrayList<IAction>() {
            {
                add(new OneTwo());
                add(new OneThree());
                add(new TwoOne());
                add(new TwoThree());
                add(new ThreeOne());
                add(new ThreeTwo());
            }
        };
    }

    @Override
    public double getActionCost(State s, IAction a, State ss) {
        return 1;
    }

}
