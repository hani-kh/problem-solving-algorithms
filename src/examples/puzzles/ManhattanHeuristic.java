package examples.puzzles;

import core.HeuristicFunction;
import core.State;

//Manhattan calculate the total steps for moving each tile to it's goal location.
public class ManhattanHeuristic implements HeuristicFunction {
    private PuzzleBoard goal;
    public ManhattanHeuristic(State goal) {
        this.goal = (PuzzleBoard) goal;
    }
    @Override
    public double getH(State s) {
        PuzzleBoard casted = (PuzzleBoard) s;
        double manahattanCount = 0;
        //the two next for looping on goal state to reach the same tile and pick its location
        for (int i = 0; i < casted.puzzelBoardSize; i++) {
            for (int j = 0; j < casted.puzzelBoardSize; j++) {
                if(goal.tiles[i][j] == 0){  //ignore blank tile
                    continue;
                }
                //the two next for looping on each tile in state s;
                for (int k = 0; k < goal.puzzelBoardSize; k++) {
                    for (int l = 0; l < casted.puzzelBoardSize; l++) {
                        if (goal.tiles[i][j] == casted.tiles[k][l]) {
                            double rowDef = Math.abs(i - k);//calc. the row dif..
                            double colDef = Math.abs(j - l);// calc. the col dif..
                            manahattanCount += rowDef + colDef; //add the sum of dif(manhattan for this tile) to overall total.
                        }
                    }
                }
            }
        }
        return  manahattanCount;
    }
}



