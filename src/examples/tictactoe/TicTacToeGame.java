package examples.tictactoe;

import core.Game;
import core.IAction;
import core.Player;
import core.State;
import core.algorithms.AlphaBetaSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides an implementation of the Tic-tac-toe game which can be used for
 * experiments with the Minimax algorithm.
 *
 * @author Ruediger Lunde
 */
public class TicTacToeGame extends Game {

    private State initialState = new TicTacToeState();

    private List<Player> players;

    public TicTacToeGame() {
        Player p1 = new AlphaBetaSearch(this, true);
        Player p2 = new HumanPlayer();

        players = new ArrayList<>() {{
            add(p1);
            add(p2);
        }};
    }

    @Override
    public State getInitialState() {
        return initialState;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public Player getPlayer() {
        if (getCurrentPlayer().isMaxPlayer()) {
            setCurrentPlayer(players.get(1));
        } else {
            setCurrentPlayer(players.get(0));
        }
        return getCurrentPlayer();
    }

    @Override
    public List<IAction> getActions(State state) {
        ArrayList<IAction> res = new ArrayList<>();
        TicTacToeState t = (TicTacToeState) state;
        int[][] positions = t.getUnMarkedPositions();
        for (int i = 0; i < 9; i++) {
            if (positions[i][0] == -1)
                break;

            res.add(new MarkCell(positions[i][0], positions[i][1]));
        }
        return res;
    }

    @Override
    public State getResult(State state, IAction action) {
        ArrayList<State> res = (ArrayList<State>) action.apply(state);
        return res.get(0);
    }

    @Override
    public boolean isTerminal(State state) {

        return state.isGoal();
    }

    @Override
    public double Evaluate(State state) {
        TicTacToeState t = (TicTacToeState) state;
        double result = t.getUtility();
        if (result != -1) {
            if (!getCurrentPlayer().isMaxPlayer() && t.playerToMove == TicTacToeState.O)
                result = 1 - result;
        } else {
            throw new IllegalArgumentException("State is not terminal.");
        }
        return result;
    }
}
