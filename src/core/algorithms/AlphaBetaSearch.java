package core.algorithms;

import core.Game;
import core.IAction;
import core.Player;
import core.State;

public class AlphaBetaSearch implements Player {


    Game game;
    private boolean isMaxPlayer;

    public AlphaBetaSearch(Game game, boolean isMaxPlayer) {
        this.game = game;
        this.isMaxPlayer = isMaxPlayer;
    }

    @Override
    public boolean isMaxPlayer() {
        return isMaxPlayer;
    }

    @Override
    public IAction makeDecision(State state) {

        IAction result = null;

        //Player player = game.getCurrentPlayer();
        if (isMaxPlayer()) {
            double resultValue = Double.NEGATIVE_INFINITY;
            for (IAction action : game.getActions(state)) {
                double value = minValue(game.getResult(state, action),
                        Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
                if (value > resultValue) {
                    result = action;
                    resultValue = value;
                }
            }
        }else{
            double resultValue = Double.POSITIVE_INFINITY;
            for (IAction action : game.getActions(state)) {
                double value = maxValue(game.getResult(state, action),
                        Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
                if (value < resultValue) {
                    result = action;
                    resultValue = value;
                }
            }
        }
        return result;
    }

    public double maxValue(State state, double alpha, double beta) {

        if (game.isTerminal(state))
            return game.Evaluate(state);
        double value = Double.NEGATIVE_INFINITY;
        for (IAction action : game.getActions(state)) {
            value = Math.max(value, minValue( //
                    game.getResult(state, action), alpha, beta));
            if (value >= beta)
                return value;
            alpha = Math.max(alpha, value);
        }
        return value;
    }

    public double minValue(State state, double alpha, double beta) {

        if (game.isTerminal(state))
            return game.Evaluate(state);
        double value = Double.POSITIVE_INFINITY;
        for (IAction action : game.getActions(state)) {
            value = Math.min(value, maxValue( //
                    game.getResult(state, action), alpha, beta));
            if (value <= alpha)
                return value;
            beta = Math.min(beta, value);
        }
        return value;
    }

}
