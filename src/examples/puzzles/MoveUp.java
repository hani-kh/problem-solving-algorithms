package examples.puzzles;

import core.IAction;
import core.State;

import java.util.ArrayList;
import java.util.Collection;

public class MoveUp implements IAction {


    @Override
    public Collection<State> apply(State s) {

        PuzzleBoard temp = (PuzzleBoard) s;
        Collection<State> nextStates = new ArrayList<>();

        /*
         * elementZeroXs and elementZeroYs to hold
         * the coordinates of the element (0) on the Puzzel Board
         * and we set them to value of -1 to make sure they are out of
         * Puzzel board
         */
        int elementZeroXs = -1, elementZeroYs = -1;

        //find zero (Blank position) and save it.
        for (int i = 0; i < temp.puzzelBoardSize; i++) {
            for (int j = 0; j < temp.puzzelBoardSize; j++) {
                if (temp.tiles[i][j] == 0) {
                    elementZeroXs = i;
                    elementZeroYs = j;
                }
            }
        }
        if (elementZeroXs > 0) {
            //we can move upwards if Zero wasn't on the upper edge of the puzzle board
            //and in this case we need to save the new board tiles configuration.
            int[][] localMovedUpTiles = new int[temp.puzzelBoardSize][temp.puzzelBoardSize];

            for (int i = 0; i < temp.puzzelBoardSize; i++) {
                for (int j = 0; j < temp.puzzelBoardSize; j++) {
                    //copying (temp)'s puzzle board into (localMovedUpTiles)
                    localMovedUpTiles[i][j] = temp.tiles[i][j];
                }
            }

            //moving up Zero in rows but in the same column.
            localMovedUpTiles[elementZeroXs - 1][elementZeroYs] = 0;
            //Swapping Zero with whatever value was in its old location.
            localMovedUpTiles[elementZeroXs][elementZeroYs] = temp.tiles[elementZeroXs - 1][elementZeroYs];

            //now to export this configuration as a new possible state
            //we create a State object to hold the new info we came up with in that local config.
            State movedUpState = new PuzzleBoard(localMovedUpTiles,temp.puzzelBoardSize);
            //then exporting the new movedUpState to our collection of next States
            ((ArrayList<State>) nextStates).add(movedUpState);
        }
        return nextStates;
    }

    @Override
    public String getName() {
        return "Move Up";
    }
}
