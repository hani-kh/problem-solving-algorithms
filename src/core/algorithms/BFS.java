package core.algorithms;

import core.*;

import java.util.*;

public class BFS extends SearchAlgorithm {
    // Queue is the data structure for implementing BFS Container (i.e. FIFO).
    private Queue<Node> frontier = new LinkedList<>();
    //store visited (explored states).
    private List<State> explored = new ArrayList<>();
    //stores the result of applying an action to a state while expanding.
    private Collection<State> nextStates = new ArrayList<>();
    //counter for visited nodes will increased each time a node is picked and will stored in node picked.
    private int visitingOrder = 0;

    //default ctor. (Use TreeSearch Mode)
    public BFS() {

    }

    //ctor for telling algo to run in TreeSearch(false) or GraphSearch(true) mode
    public BFS(boolean useGraphSearch) {
        this.useGraphSeach = useGraphSearch;
    }

    @Override
    public Node search(Problem p) {
        Node startNode = new Node(p.getInitialState()); //create start node
        frontier.add(startNode); // add start node to frontier
        initGraph(startNode); //used for initiating the search tree diagram.
        // start searching
        while (!(frontier.isEmpty())) {
            //pick
            Node currentNode = frontier.poll();
            //set the order of picking this node
            currentNode.setVisitingOrder(++visitingOrder);
            //check wither the the mode is GraphSearch and state of node is processed before.
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
            for (IAction action : p.getActions()) {// for each action defined in problem we need to apply this action to the current state.
                //apply action to state and get next states.
                nextStates = action.apply(currentNode.getState());
                developedNodes += nextStates.size();
                for (State s : nextStates) {// for each state in the result of applying action to the current state.
                    // get the action cost.
                    double actionCost = p.getActionCost(currentNode.getState(), action, s);
                    // compute the pathCost g(x).
                    double pathCost = actionCost + currentNode.getPathCost();
                    // create a new node and fill information.
                    Node child = new Node(s, currentNode, action, pathCost);
                    // add the new node to frontier.
                    frontier.add(child);
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
