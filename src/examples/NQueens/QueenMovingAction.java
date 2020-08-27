package examples.NQueens;

import core.IAction;
import core.State;

import java.util.ArrayList;
import java.util.Collection;

public class QueenMovingAction implements IAction {
    @Override
    public Collection<State> apply(State s) {

        NQueensBoard temp=(NQueensBoard) s;
        Collection<State> nextStates = new ArrayList<>();
        for(int i=0;i<temp.getSize();i++)
        {
            int []t = new int [temp.getSize()];
            for(int k=0;k<i;k++)
                t[k]=temp.board[k];

            for(int k=i+1;k<temp.getSize();k++)
                t[k] = temp.board[k];
            for(int j = 1;j<temp.getSize();j++){
                t[i] = (temp.board[i]+j) %temp.getSize();
                ((ArrayList<State>) nextStates).add(new NQueensBoard(t));
            }
        }
        return nextStates;
    }

    @Override
    public String getName() {
        return "Moving Queen To Next Row";
    }
}
