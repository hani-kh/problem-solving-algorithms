package examples.tictactoe;

import core.IAction;
import core.Player;
import core.State;

import java.util.Scanner;

public class HumanPlayer implements Player {
    @Override
    public boolean isMaxPlayer() {
        return false;
    }

    @Override
    public IAction makeDecision(State s) {
        System.out.println("Your turn: here is the Board:");
        System.out.println(s);
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        return new MarkCell(row,col);
    }
}
