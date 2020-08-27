package core;

import java.util.Collection;

//implement this interface to define an action
public interface IAction {
    //returns a collection of states as a result of applying the action to the state s
    Collection<State> apply(State s);
    //retuns actionName.. useful in some places
    String getName();
}
