package core.algorithms;

import core.Game;
import core.IAction;
import core.Player;
import core.State;

public class MinimaxSearch implements Player {

    private Game game;

    private boolean isMaxPlayer;

    public MinimaxSearch(Game game, boolean isMaxPlayer) {
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
        if (isMaxPlayer()) {
            double resultValue = Double.NEGATIVE_INFINITY;
            for (IAction action : game.getActions(state)) {
                double value = minValue(game.getResult(state, action));
                if (value > resultValue) {
                    result = action;
                    resultValue = value;
                }
            }
        }else{
            double resultValue = Double.POSITIVE_INFINITY;
            for (IAction action : game.getActions(state)) {
                double value = minValue(game.getResult(state, action));
                if (value < resultValue) {
                    result = action;
                    resultValue = value;
                }
            }
        }
        return result;
    }

    public double maxValue(State state) { // returns an utility value
        if (game.isTerminal(state))
            return game.Evaluate(state);
        double value = Double.NEGATIVE_INFINITY;
        for (IAction action : game.getActions(state))
            value = Math.max(value,
                    minValue(game.getResult(state, action)));
        return value;
    }

    public double minValue(State state) { // returns an utility value
        if (game.isTerminal(state))
            return game.Evaluate(state);
        double value = Double.POSITIVE_INFINITY;
        for (IAction action : game.getActions(state))
            value = Math.min(value,
                    maxValue(game.getResult(state, action)));
        return value;
    }
}
