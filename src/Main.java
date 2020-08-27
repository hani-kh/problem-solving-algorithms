import core.Node;
import core.Problem;
import core.SearchAlgorithm;
import core.algorithms.Astar;
import core.algorithms.BFS;
import examples.hanoi.HanoiProblem;

public class Main {
    public static void main(String[] arg) {
        Problem hp = new HanoiProblem();
        SearchAlgorithm bfs = new BFS(true);
        Node solution = hp.solve(bfs);
        System.out.println(solution);
        bfs.printStates();
        bfs.printSolution(solution);
        System.out.println("BFS Nodes: "+ bfs.developedNodes);
        SearchAlgorithm astar = new Astar(true);
        Node astarSolution = hp.solve(astar);
        System.out.println(astarSolution);
        astar.printStates();
        astar.printSolution(astarSolution);
        System.out.println("Astar Nodes: "+ astar.developedNodes);


        ///////Manhattan Heuristic Example for 8-puzzle///////
//        ManhattanHeuristic manhattan = new ManhattanHeuristic(new PuzzleBoard(new int[][]{{1,2,3},{4,5,6},{7,8,0}},3));
//        System.out.println(manhattan.getH(new PuzzleBoard(new int[][]{{2,1,3},{5,4,0},{6,7,8}},3)));
//        /////////////////////////////////////////////////
    }
}
