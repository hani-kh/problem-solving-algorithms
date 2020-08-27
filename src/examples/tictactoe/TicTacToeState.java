package examples.tictactoe;

import core.State;

import java.util.Arrays;
import java.util.Objects;

/**
 * A state of the Tic-tac-toe game is characterized by a board containing
 * symbols X and O, the next player to move, and an utility information.
 *
 * @author Ruediger Lunde
 */
public class TicTacToeState implements State {
    public static final String O = "O";
    public static final String X = "X";
    public static final String EMPTY = "-";
    //
    public String[] board = new String[]{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY, EMPTY};

    public String playerToMove = X;
    private double utility = -1; // 1: win for X, 0: win for O, 0.5: draw

    public String getPlayerToMove() {
        return playerToMove;
    }

    public boolean isEmpty(int col, int row) {
        return Objects.equals(board[getAbsPosition(col, row)], EMPTY);
    }

    public String getValue(int col, int row) {
        return board[getAbsPosition(col, row)];
    }

    public double getUtility() {
        return utility;
    }


    public void analyzeUtility() {
        if (lineThroughBoard()) {
            utility = (Objects.equals(playerToMove, O) ? 1 : 0); //x if computer starts o if human starts
        } else if (getNumberOfMarkedPositions() == 9) {
            utility = 0.5;
        }
    }

    public boolean lineThroughBoard() {
        return (isAnyRowComplete() || isAnyColumnComplete() || isAnyDiagonalComplete());
    }

    private boolean isAnyRowComplete() {
        for (int row = 0; row < 3; row++) {
            String val = getValue(0, row);
            if (!Objects.equals(val, EMPTY) && Objects.equals(val, getValue(1, row)) && Objects.equals(val, getValue(2, row))) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnyColumnComplete() {
        for (int col = 0; col < 3; col++) {
            String val = getValue(col, 0);
            if (!Objects.equals(val, EMPTY) && Objects.equals(val, getValue(col, 1)) && Objects.equals(val, getValue(col, 2))) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnyDiagonalComplete() {
        String val = getValue(0, 0);
        if (!Objects.equals(val, EMPTY) && Objects.equals(val, getValue(1, 1)) && Objects.equals(val, getValue(2, 2))) {
            return true;
        }
        val = getValue(0, 2);
        if (!Objects.equals(val, EMPTY) && Objects.equals(val, getValue(1, 1)) && Objects.equals(val, getValue(2, 0))) {
            return true;
        }
        return false;
    }

    public int getNumberOfMarkedPositions() {
        int retVal = 0;
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                if (!(isEmpty(col, row))) {
                    retVal++;
                }
            }
        }
        return retVal;
    }

    public int[][] getUnMarkedPositions() {
        int[][] result = new int[9][2];

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 2; j++) {
                result[i][j] = -1;
            }
        int idx = 0;
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                if (isEmpty(col, row)) {
                    result[idx][0] = row;
                    result[idx++][1] = col;
                }
            }
        }
        return result;
    }

    @Override
    public TicTacToeState clone() {
        TicTacToeState copy = new TicTacToeState();
        copy.playerToMove = playerToMove;
        copy.utility = utility;
        copy.board = Arrays.copyOf(board, board.length);

        return copy;
    }


    public boolean equals(Object anObj) {
        if (anObj != null && anObj.getClass() == getClass()) {
            TicTacToeState anotherState = (TicTacToeState) anObj;
            for (int i = 0; i < 9; i++) {
                if (!Objects.equals(board[i], anotherState.board[i]))
                    return false;
            }
            return true;
        }
        return false;
    }


    @Override
    public boolean isGoal() {
        return getUtility() != -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                builder.append(getValue(col, row)).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    //
    // PRIVATE METHODS
    //

    public int getAbsPosition(int col, int row) {
        return row * 3 + col;
    }
}
