package core;
//implement this interface to define a heuristic function..
public interface HeuristicFunction {
    //returns the estimated cost to reach goal from State s.
    double getH(State s);
}
