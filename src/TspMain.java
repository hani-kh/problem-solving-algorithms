import core.HeuristicFunction;
import core.Node;
import core.Problem;
import core.SearchAlgorithm;
import core.algorithms.Astar;
import examples.tsp.TspProblem;

public class TspMain {
    public static void main(String [] args){
        Problem p = new TspProblem();
        SearchAlgorithm ucs = new Astar(true);
        Node s = p.solve(ucs);
        System.out.println(s);

    }
}
