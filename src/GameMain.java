import core.Game;
import core.Player;
import core.algorithms.AlphaBetaSearch;
import examples.tictactoe.TicTacToeGame;

public class GameMain {
    public static void main(String[] args){
        Game g = new TicTacToeGame();
        g.setCurrentPlayer(g.getPlayers().get(0));
        g.startGame();
    }
}
