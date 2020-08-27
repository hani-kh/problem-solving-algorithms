package examples.puzzles;

import core.State;

public class PuzzleBoard implements State {

    //the size of the puzzle Board (i.e. N-Puzzle has a size of N)
    public int puzzelBoardSize;
    //holds the tiles placement at a certain moment (State)
    public int[][] tiles;

    public PuzzleBoard(int[][] tiles, int size) {

        this.puzzelBoardSize = size;
        this.tiles = tiles;
    }

    @Override
    public boolean isGoal() {
        return false; //let problem decide.
    }

    @Override
    public boolean equals(Object s) {
        //Downcast (s) to PuzzleBoard State
        PuzzleBoard tempState = (PuzzleBoard) s;

        for (int i = 0; i < puzzelBoardSize; i++) {
            for (int j = 0; j < puzzelBoardSize; j++) {
                if (tiles[i][j] != tempState.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        String res = "\n";
        for (int i = 0; i < puzzelBoardSize; i++) {
            for (int j = 0; j < puzzelBoardSize; j++) {
                res += tiles[i][j] + " ";
            }
            res += "\n";
        }
        return res;
    }
}
