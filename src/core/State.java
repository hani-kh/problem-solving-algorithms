package core;

//implement this interface to define the state space of a problem
public interface State {
    //checks whether the state calling is a goal state or not
    boolean isGoal();
    // the way to show the state in the output
    String toString();
    // checks calling state with passed state s for equality
    boolean equals(Object s);
}
