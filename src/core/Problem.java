package core;

import java.util.List;

//this class describes a problem in general
// extend it for a specific problem description
// it needs a state space i.e. State and a search strategy i.e. Search Algorithm
public abstract class Problem {
    //returns the initial state of problem
    public abstract State getInitialState();

    // returns true when the the state passed is a goal state or when ever we reached a goal
    public abstract boolean isGoal(State state);

    // returns a list of actions (operators) that can be applied to any state of the problem
    public abstract List<IAction> getActions();

    // returns the cost of applying the action a on state a to get state ss
    public abstract double getActionCost(State s, IAction a, State ss);

    // returns the heuristic function to be used by search strategy
    // by default problem don't use it so when called by problem it will throw.
    // to make problem use it, just override it and provide a valid h function.
    public HeuristicFunction getHeuristicFunction(){
        throw new UnsupportedOperationException("this problem doesn't use Heuristics");
    }
    //used to initiate the search strategy to be used to solve the problem and run the the algorithm sa on this problem..
    //it returns the goal Node as result which satisfy the isGoal method
    public Node solve (SearchAlgorithm sa){
        return sa.search(this);
    }
}
