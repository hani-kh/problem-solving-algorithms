package examples.puzzles;


import core.HeuristicFunction;
import core.IAction;
import core.Problem;
import core.State;

import java.util.ArrayList;
import java.util.List;

public class PuzzleProblem extends Problem {
    private PuzzleBoard goalState;

    @Override
    public State getInitialState() {
        PuzzleBoard initialState = new PuzzleBoard(new int[][]{
                {0, 1, 2},
                {4, 5, 3},
                {7, 8, 6}
        }, 3);
        return initialState;
    }

    public State getGoalState() {
        if (goalState != null) {
            return goalState; //once getGoalState is called this will be assigned
            // just to avoid multi creation for goal each time we call this function
            // and hence save some memory..
        }
        else {
            PuzzleBoard temp = new PuzzleBoard(new int[][]{
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 0}
            }, 3);
            //Initial state array is given by user
            return goalState = temp;
        }
    }

    @Override
    public boolean isGoal(State state) {
        return state.equals(getGoalState());
    }

    @Override
    public List<IAction> getActions() {
        return new ArrayList<IAction>() {{
            add(new MoveUp());
            add(new MoveDown());
            add(new MoveLeft());
            add(new MoveRight());
        }};
    }

    @Override
    public double getActionCost(State s, IAction a, State ss) {
        //moving in any direction (Up, down, left, right) costs 1 per step
        return 1;
    }
    @Override
    public HeuristicFunction getHeuristicFunction() {
        return new ManhattanHeuristic(getGoalState());
    }
}