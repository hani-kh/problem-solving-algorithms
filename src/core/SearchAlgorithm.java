package core;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.geom.Point2;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.graphicGraph.GraphicGraph;
import org.graphstream.ui.graphicGraph.GraphicNode;
import org.graphstream.ui.graphicGraph.stylesheet.StyleConstants;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;
import org.graphstream.ui.view.Camera;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public abstract class SearchAlgorithm {
    protected boolean useGraphSeach = false;
    public int developedNodes =0;
    //the search strategy .. return the goal node regarding the problem p;
    // it search for the goal using problem definition going from initial state of p applying actions to reach the goal state..
    public abstract Node search(Problem p);

    // all below related to printing Tree
    // its not important to dive in details..
    public Graph graph = new SingleGraph("Search Tree");
    public SpriteManager aSpriteManager = new SpriteManager(graph);
    public Viewer viewer;
    //Code For GraphStream Extension///////////////////////////
    protected static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    public void printSolution(Node solution){
        while (solution != null) {
            org.graphstream.graph.Node n = graph.getNode(solution.getId().toString());
            n.setAttribute("ui.class", "marked");
            solution = solution.getParent();
        }
    }
    public void printStates(){




        for(org.graphstream.graph.Node n : graph){
            SpriteManager aSpriteManager = new SpriteManager(graph);
            String[] parts = n.getAttribute("state").toString().split("\n");
            double c = -1;
            for (String ss : parts) {

                Sprite aSprite = aSpriteManager.addSprite(randomUUID().toString());
                aSprite.attachToNode(n.getId()); //this sets where the Label will be (adjust to fit your purpose)
//                GraphicGraph gn = viewer.getGraphicGraph();
//                GraphicNode nn = gn.getNode(n.getId());
//                double nx = nn.getX();
//                double ny = nn.getY();
                //System.out.println(nx+" "+ny);
                c++;
                // aSprite.setPosition(StyleConstants.Units.PX, nx+c, ny+c,-1);
                aSprite.addAttribute("ui.label", ss);
                aSprite.setPosition(StyleConstants.Units.PX, c * 15 + 1, 20, -90);
                aSprite.addAttribute("ui.style", "fill-mode: none; text-size:15;");
            }
        }
//        sleep();
//        sleep();
//        sleep();
//        sleep();
       // viewer.disableAutoLayout();

    }
    public void initGraph(Node node) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.addAttribute("ui.stylesheet", styleSheet);
        viewer = graph.display();
        /*HierarchicalLayout hl = new HierarchicalLayout();
        viewer.enableAutoLayout(hl);*/
        View view = viewer.getDefaultView();
        //view.getCamera().setViewPercent(0.5);
        org.graphstream.graph.Node n = graph.addNode(node.getId().toString());
        n.setAttribute("state", node.getState().toString());
        //setLabelForNode(n,node.getState().toString());
        ((Component) view).addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                e.consume();
                viewer.disableAutoLayout();
                int i = e.getWheelRotation();
                double factor = Math.pow(1.25, i);
                Camera cam = view.getCamera();
                double zoom = cam.getViewPercent() * factor;
                Point2 pxCenter = cam.transformGuToPx(cam.getViewCenter().x, cam.getViewCenter().y, 0);
                Point3 guClicked = cam.transformPxToGu(e.getX(), e.getY());
                double newRatioPx2Gu = cam.getMetrics().ratioPx2Gu / factor;
                double x = guClicked.x + (pxCenter.x - e.getX()) / newRatioPx2Gu;
                double y = guClicked.y - (pxCenter.y - e.getY()) / newRatioPx2Gu;
                cam.setViewCenter(x, y, 0);
                cam.setViewPercent(zoom);
            }
        });
    }
    public void addNodeEdge(Node p, Node c,IAction action) {

        org.graphstream.graph.Node pn = graph.addNode(p.getId().toString());
        org.graphstream.graph.Node cn = graph.addNode(c.getId().toString());
        cn.setAttribute("state", c.getState().toString());
        Edge e = graph.addEdge(pn.getId() + "" + cn.getId(), pn, cn);
        e.setAttribute("label",action.getName()+" (g:"+c.getPathCost()+")");

        //setLabelForNode(cn,p.getState().toString());
    }

    protected String styleSheet =
            "node {" +
                    "	fill-color: yellow;" +
                    "   size:25px;" +
                    "text-alignment: center;" +
                    "}" +
            "edge.label {" +
                    "   size:25px;" +
                    "text-alignment: center;" +
                    "}" +
                    "node.marked {" +
                    "	fill-color: red;" +
                    "}";
    //////////////////*****///////////////
    private List<GenericTreeNode> allNodes = new ArrayList<>();
    private GenericTreeNode searchTree = new GenericTreeNode();
    public GenericTreeNode getNode(Node n){
        for(GenericTreeNode node:allNodes){
            if(n.isSimilarTo(node.value))
                return node;
        }
        return null;
    }
    public GenericTreeNode getSearchTree(){
        return searchTree;
    }

    //used to add node to search tree..
    public void addToSearchTree(Node parent, Node child){
        GenericTreeNode node = getNode(parent);
        GenericTreeNode tt = new GenericTreeNode();
        tt.value = child;
        tt.parent = node;
        node.children.add(tt);
        allNodes.add(tt);
    }
    //used to initiate search tree..
    public void initSearchTree(Node n){
        searchTree.value = n;
        searchTree.parent = null;
        allNodes.add(searchTree);
    }
    // used to get a string representing search tree..
    public String generateSearchTree(){
        return generateSearchTreeString(searchTree,"",false,false,new StringBuilder());
    }
    private String generateSearchTreeString(GenericTreeNode node, String prefix, boolean isRightMost, boolean isLeftMost, StringBuilder sb) {
        int halfSize = (node.getChildrenCount() + 1) / 2;
        List<GenericTreeNode> children = new ArrayList<>(node.getChildren());
        for (int i = children.size() - 1; i >= halfSize; i--) {
            GenericTreeNode child = children.get(i);
            generateSearchTreeString(child,
                    prefix + (isRightMost && !isLeftMost ? "    " : "│   "),
                    child.isRightMostChild() ? true : false,
                    child.isLeftMostChild() ? true : false,
                    sb);
        }
        sb.append(prefix).
                append(isRightMost && isLeftMost ? "└── " : "").
                append(isRightMost && !isLeftMost ? "┌── " : "").
                append(isLeftMost && !isRightMost ? "└── " : "").
                append(!isRightMost && !isLeftMost ? "├── " : "").
                append("["+node.value.getState().toString()+",g="+node.value.getPathCost()+",f="+node.value.getTotalCost()+",vo="+node.value.getVisitingOrder()+"]").
                append("\n");
        for (int i = halfSize - 1; i >= 0; i--) {
            GenericTreeNode child = children.get(i);
            generateSearchTreeString(children.get(i),
                    prefix + (isLeftMost ? "    " : "│   "),
                    child.isRightMostChild() ? true : false,
                    child.isLeftMostChild() ? true : false,
                    sb);
        }
        return sb.toString();
    }
}
