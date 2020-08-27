package core.algorithms;

import core.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class DFS extends SearchAlgorithm {
    // Stack is the data structure for implementing DFS Container (i.e. LIFO).
    private Stack<Node> frontier = new Stack<>();
    //store visited (explored states).
    private List<State> explored = new ArrayList<>();
    //stores the result of applying an action to a state while expanding.
    private Collection<State> nextStates = new ArrayList<>();
    //counter for visited nodes will increased each time a node is picked and will stored in node picked.
    private int visitingOrder = 0;

    //default ctor. (Use TreeSearch Mode)
    public DFS() {

    }

    //ctor for telling algo to run in TreeSearch(false) or GraphSearch(true) mode
    public DFS(boolean useGraphSearch) {
        this.useGraphSeach = useGraphSearch;
    }

    @Override
    public Node search(Problem p) {
        Node startNode = new Node(p.getInitialState()); //create start node
        frontier.add(startNode); // add start node to frontier
        initGraph(startNode);//used for initiating the search tree diagram.
        // start searching
        while (!(frontier.isEmpty())) {
            //pick
            Node currentNode = frontier.pop();
            //set the order of picking this node
            currentNode.setVisitingOrder(++visitingOrder);
            //check wither the mode is GraphSearch and the state of node is processed before.
            //if it is then don't process it again. (i.e. continue to next node in frontier.
            if (useGraphSeach && explored.contains(currentNode.getState())) {
                continue;
            }
            //check if state of node picked is the goal state
            if (p.isGoal(currentNode.getState())) {
                return currentNode;
            }
            //mark node as visited (i.e. add it to explored)
            explored.add(currentNode.getState());
            //Expand
            for (IAction action : p.getActions()) {// for each action defined in problem we need to apply this action to the current state
                //apply action to state and get next states.
                nextStates = action.apply(currentNode.getState());
                for (State s : nextStates) {// for each state in the result of applying action to the current state.
                    // get the action cost.
                    double actionCost = p.getActionCost(currentNode.getState(), action, s);
                    // compute the pathCost g(x).
                    double pathCost = actionCost + currentNode.getPathCost();
                    // create a new node and fill information.
                    Node child = new Node(s, currentNode, action, pathCost);
                    frontier.push(child);// add the new node to frontier.
                    //add node and it's parent to search tree.
                    // this only for printing the search tree diagram.
                    addNodeEdge(currentNode, child, action);
                }
            }
        }
        //No result Node found and algo. faild to find a goal return nothing!
        return null;
    }
}
