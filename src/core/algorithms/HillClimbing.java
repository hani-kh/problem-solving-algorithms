package core.algorithms;

import core.*;

import java.util.Collection;

public class HillClimbing extends SearchAlgorithm {

    public Node chooseSuccessorNode(Node currentNode, Problem p) { //returns the best successor of current node
        Collection<State> nextStates;
        State maxState = currentNode.getState();
        double maxFitness = p.getHeuristicFunction().getH(currentNode.getState());

        for(IAction action :p.getActions())
        {
            nextStates = action.apply(currentNode.getState());
            for (State s :nextStates){
                double stateFitness = p.getHeuristicFunction().getH(s);
                if( stateFitness > maxFitness){
                    maxFitness = stateFitness;
                    maxState = s;
                }
            }
        }
        Node bestSuccessor  =  new Node(maxState);
        bestSuccessor.setTotalCost(maxFitness);
        return bestSuccessor;
    }

    @Override
    public Node search(Problem p) {
        Node currentNode = new Node(p.getInitialState());
        System.out.println("initial:\n"+currentNode.getState());
        currentNode.setTotalCost(p.getHeuristicFunction().getH(p.getInitialState()));

        int round = 1;

        while (true){

            Node highestNeigbour = chooseSuccessorNode(currentNode,p);
            if(highestNeigbour.getTotalCost() <= currentNode.getTotalCost()){
                return currentNode;
            }
            currentNode = highestNeigbour;
            System.out.println("Selected Node for round["+round+"]:\n"+currentNode.getState()+currentNode.getTotalCost());
            round++;

        }
    }
}
