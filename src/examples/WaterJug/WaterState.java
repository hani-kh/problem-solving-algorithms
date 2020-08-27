/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.WaterJug;

import core.State;

public class WaterState implements State {

    public int[] jugs = {8, 0, 0};
    public int[] jugsSizes;

    public WaterState(int[] jugs, int[] jugsSizes) {
        this.jugsSizes = jugsSizes;
        this.jugs = jugs;
    }

    public WaterState() {

    }

    @Override
    public boolean isGoal() {
        return jugs[0] == 4 && jugs[1] == 4 && jugs[2] == 0;
    }

    @Override
    public boolean equals(Object s) {
        WaterState s1 = (WaterState) s;
        for (int i = 0; i < this.jugs.length; i++) {
            if (s1.jugs[i] != this.jugs[i])
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < 3; i++) {
            res += jugs[i] + ", ";
        }
        res = "[ " + res + "]";
        return res;
    }

}
