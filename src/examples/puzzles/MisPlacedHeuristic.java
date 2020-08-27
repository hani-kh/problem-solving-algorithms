package examples.puzzles;

import core.HeuristicFunction;
import core.State;

//the number of tiles which is not in it's goal location.
public class MisPlacedHeuristic implements HeuristicFunction {

    private PuzzleBoard goal;

    public MisPlacedHeuristic(State goal) {
        this.goal = (PuzzleBoard) goal;
    }

    @Override
    public double getH(State s) {
        PuzzleBoard casted = (PuzzleBoard) s;

        double misplaced = 0;
        for (int i = 0; i < casted.puzzelBoardSize; i++) {
            for (int j = 0; j < casted.puzzelBoardSize; j++) {
                if(goal.tiles[i][j] == 0){ //ignore blank tile
                    continue;
                }
                if (casted.tiles[i][j] != goal.tiles[i][j]) { // if tile is not in its goal location.
                    misplaced++;
                }
            }
        }
        return misplaced;
    }

}
