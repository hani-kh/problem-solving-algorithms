package examples.hanoi;

import core.State;

public class HanoiState implements State {
    public int[] tablets;

    @Override
    public boolean isGoal() {

        for (int i = 0; i < 3; i++) {
            if (tablets[i] != 2) {
                return false;
            }
        }

        return true;

    }

    @Override
    public boolean equals(Object s) {

        HanoiState t = (HanoiState) s;

        for (int i = 0; i < 3; i++) {
            if (t.tablets[i] != this.tablets[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {

        String res = "";
        for (int i = 0; i < 3; i++) { //loop each tower..
            res += "Tower " + i + ":";
            for (int j = 2; j >= 0; j--) { // loop each tablet..
                if (tablets[j] == i) { // if tablet in the tower add it
                    if (j == 0) {
                        res += "S ";
                    } else if (j == 1) {
                        res += "M ";
                    } else {
                        res += "L ";
                    }
                }
            }
            res += "\n";// new line for new tower..
        }
        return res;
    }

}
