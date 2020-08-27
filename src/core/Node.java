package core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

//represents a node in the search tree
//implements Comparable to be used in PriorityQueue ordering
public class Node implements Comparable<Node> {
    private UUID id;
    //the corresponding state in state space
    private State state;
    //node parent which led us to this node
    private Node parent;
    //the cost from initial state to this node i.e. g(n)
    private double pathCost;
    //total estimated cost i.e. f(n)
    private double totalCost;
    //the action applied to get to this node
    private IAction action;
    //the node Depth;
    private int depth;
    //the order of picking the node;
    private int visitingOrder;
    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double c){
        totalCost = c;
    }
    //initial state ctor
    public Node(State state) {
        id= randomUUID();
        this.state = state;
        pathCost = 0.0;
        totalCost = Double.MAX_VALUE;
        visitingOrder = 0;
    }
    public UUID getId(){
        return id;
    }
    public Node(State state, Node parent, IAction action, double pathCost) {
        this(state);
        this.parent = parent;
        this.action = action;
        this.pathCost = pathCost;
        this.totalCost = pathCost;//when total cost is not provided treat the pathCost as the totalCost in order to be used correctly when using PriorityQueue as it deals with totalCost.
    }

    public Node(State state, Node parent, IAction action, double pathCost, double totalCost) {
        this(state, parent, action, pathCost);
        this.totalCost = totalCost;
    }

    public double getPathCost() {
        return pathCost;
    }

    public boolean isRootNode() {
        return parent == null;
    }

    public Node getParent() {
        return parent;
    }

    public int getVisitingOrder(){
        return visitingOrder;
    }
    public void setVisitingOrder(int visitingOrder){
        this.visitingOrder = visitingOrder;
    }
    public IAction getAction() {
        return action;
    }

    public State getState() {
        return state;
    }

    public List<Node> getPathFromRoot() {
        List<Node> path = new ArrayList<Node>();
        Node current = this;
        while (!current.isRootNode()) {
            path.add(0, current);
            current = current.getParent();
        }
        // ensure the root node is added
        path.add(0, current);
        return path;
    }

    @Override
    public String toString() {
        return (parent == null?"initial State ":parent)+
                "action: "+(action==null?"no action":action.getName())+
                ", pathCost: "+
                pathCost+
                ", state: \n"+
                getState() + "\n";
    }

    @Override
    public boolean equals(Object o) { //two nodes are said to be equal when they hold same state
        Node t = (Node) o;
        return t.state.equals(state);
    }
    //this used when only when building search tree diagram..
    public boolean isSimilarTo(Node n) {//two nodes are said to be similar when they hold same info.
        return this.parent == n.parent &&
                this.pathCost == n.getPathCost() &&
                this.totalCost == n.getTotalCost() &&
                this.state.equals(n.getState());
    }
    @Override
    public int compareTo(Node node) {
        if (getTotalCost() > node.getTotalCost())
            return 1;
        if (getTotalCost() < node.getTotalCost())
            return -1;
        return 0;
    }
}
