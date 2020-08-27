package examples.NQueens;

import core.HeuristicFunction;
import core.State;

public class NQueensNumberOfNonAttacksHeuristic implements HeuristicFunction {
    @Override
    public double getH(State s) {
        NQueensBoard casted = (NQueensBoard)s;
        int numOfAttacks = casted.getCoupleAttacks();
        int total = (casted.getSize() *(casted.getSize()-1))/2;
        return total-numOfAttacks;
    }
}
