package examples.NQueens;

import core.HeuristicFunction;
import core.IAction;
import core.Problem;
import core.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NQueensProblem extends Problem {
    private State initial;
    public NQueensProblem(int sz){
        initial = generateBoard(sz);
    }
    public NQueensProblem(State initial){
        this.initial = initial;
    }
    @Override
    public State getInitialState() {
        return initial;
    }
    public void setInitialState(State s){
        this.initial =s;
    }
    @Override
    public boolean isGoal(State state) {
       return state.isGoal();
    }

    @Override
    public List<IAction> getActions() {
        return new ArrayList<>(){{add(new QueenMovingAction());}};
    }

    @Override
    public double getActionCost(State s, IAction a, State ss) {
        return 1;
    }
    @Override
    public HeuristicFunction getHeuristicFunction(){
        return new NQueensNumberOfNonAttacksHeuristic();
    }
    public NQueensBoard generateBoard(int size){//generate random Board of entered size
        int[] start = new int[size];
        Random gen = new Random(System.nanoTime());

        for(int i=0; i<size; i++){
            start[i] = gen.nextInt(size);
        }
        return new NQueensBoard(start);
    }
}
