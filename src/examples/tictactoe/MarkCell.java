package examples.tictactoe;

import core.IAction;
import core.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class MarkCell implements IAction {
    int col, row;

    public MarkCell(int row, int col) {

        this.row = row;
        this.col = col;
    }

    @Override
    public Collection<State> apply(State s) {
        TicTacToeState t = ((TicTacToeState) s).clone();

        if (t.getUtility() == -1 && Objects.equals(t.getValue(col, row), t.EMPTY)) {
            t.board[t.getAbsPosition(col, row)] = t.playerToMove;
            t.analyzeUtility();
            t.playerToMove = (Objects.equals(t.playerToMove, t.X) ? t.O : t.X);
        }
        return new ArrayList<>(){{add(t);}};
    }

    @Override
    public String getName() {
        return "Mark Cell[" + row + "," + col + "]";
    }
}
