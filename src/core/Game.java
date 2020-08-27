package core;
import java.util.List;
import core.*;
public abstract class Game {

    private Player currentPlayer;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public abstract State getInitialState();

    public abstract List<Player> getPlayers();

    public abstract Player getPlayer();

    public abstract List<IAction> getActions(State state);

    public abstract State getResult(State state, IAction action);

    public abstract boolean isTerminal(State state);

    public abstract double Evaluate(State state);

    public void startGame(){
        State s = getInitialState();

        while (!isTerminal(s)){
            System.out.println(s);
            Player p  = getPlayer();
            IAction a = p.makeDecision(s);
            s = getResult(s,a);
        }
        System.out.println(s);
    }
}