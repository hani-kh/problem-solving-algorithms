package core.gui;

import core.models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;


public class GraphPanel extends JPanel implements MouseListener, MouseMotionListener {

    private DrawUtils drawUtils;

    private GUIGraph graph;

    private GUINode selectedNode = null;
    private GUINode hoveredNode = null;
    private GUIEdge hoveredEdge = null;

    private List<GUINode> path = null;

    private Point cursor;

    public GraphPanel(GUIGraph graph){
        this.graph = graph;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setPath(List<GUINode> path) {
        this.path = path;
        hoveredEdge = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        drawUtils = new DrawUtils(graphics2d);

        if(graph.isSolved()){
            drawUtils.drawPath(path);
        }

        if(selectedNode != null && cursor != null){
            GUIEdge e = new GUIEdge(selectedNode, new GUINode(cursor));
            drawUtils.drawEdge(e);
        }

        for(GUIEdge edge : graph.getGUIEdges()){
            if(edge == hoveredEdge)
                drawUtils.drawHoveredEdge(edge);
            drawUtils.drawEdge(edge);
        }

        for(GUINode node : graph.getGUINodes()){
            if(node == selectedNode || node == hoveredNode)
                drawUtils.drawHalo(node);
            if(graph.isSource(node))
                drawUtils.drawSourceNode(node);
            else if(graph.isDestination(node))
                drawUtils.drawDestinationNode(node);
            else
                drawUtils.drawNode(node);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        GUINode selected = null;
        for(GUINode node : graph.getGUINodes()) {
            if(DrawUtils.isWithinBounds(e, node.getCoord())){
                selected = node;
                break;
            }
        }

        if(selected!=null) {
            if(e.isAltDown()){
                setHValueFromInput(selected);
                return;
            }
            if(e.isControlDown() && e.isShiftDown()){
                graph.deleteNode(selected);
                graph.setSolved(false);
                repaint();
                return;
            } else if(e.isControlDown() && graph.isSolved()){
                path = selected.getPath();
                repaint();
                return;
            } else if(e.isShiftDown()){
                if(SwingUtilities.isLeftMouseButton(e)){
                    if(!graph.isDestination(selected))
                        graph.setSource(selected);
                    else
                        JOptionPane.showMessageDialog(null, "Destination can't be set as Source");
                } else if(SwingUtilities.isRightMouseButton(e)) {
                    if(!graph.isSource(selected))
                        graph.setDestination(selected);
                    else
                        JOptionPane.showMessageDialog(null, "Source can't be set as Destination");
                }else
                    return;

                graph.setSolved(false);
                repaint();
                return;
            }
        }

        if(hoveredEdge!=null){
            if(e.isControlDown() && e.isShiftDown()){
                graph.getGUIEdges().remove(hoveredEdge);
                hoveredEdge = null;
                graph.setSolved(false);
                repaint();
                return;
            }

            String input = JOptionPane.showInputDialog("Enter weight for " + hoveredEdge.toString()
                                                        + " : ");
            try {
                int weight = Integer.parseInt(input);
                if (weight > 0) {
                    hoveredEdge.setWeight(weight);
                    graph.setSolved(false);
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Weight should be positive");
                }
            } catch (NumberFormatException nfe) {}
            return;
        }

        for(GUINode node : graph.getGUINodes()) {
            if(DrawUtils.isOverlapping(e, node.getCoord())){
                JOptionPane.showMessageDialog(null, "Overlapping Node can't be created");
                return;
            }
        }

        graph.addNode(e.getPoint());
        graph.setSolved(false);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (GUINode node : graph.getGUINodes()) {
            if(selectedNode !=null && node!= selectedNode && DrawUtils.isWithinBounds(e, node.getCoord())){
                GUIEdge new_edge = new GUIEdge(selectedNode, node);
                graph.addEdge(new_edge);
                graph.setSolved(false);
            }
        }
        selectedNode = null;
        hoveredNode = null;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        hoveredNode = null;

        for (GUINode node : graph.getGUINodes()) {
            if(selectedNode ==null && DrawUtils.isWithinBounds(e, node.getCoord())){
                selectedNode = node;
            } else if(DrawUtils.isWithinBounds(e, node.getCoord())) {
                hoveredNode = node;
            }
        }

        if(selectedNode !=null){
            if(e.isControlDown()){
                selectedNode.setCoord(e.getX(), e.getY());
                cursor = null;
                repaint();
                return;
            }

            cursor = new Point(e.getX(), e.getY());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if(e.isControlDown()){
            hoveredNode = null;
            for (GUINode node : graph.getGUINodes()) {
                if(DrawUtils.isWithinBounds(e, node.getCoord())) {
                    hoveredNode = node;
                }
            }
        }

        hoveredEdge = null;

        for (GUIEdge edge : graph.getGUIEdges()) {
            if(DrawUtils.isOnEdge(e, edge)) {
                hoveredEdge = edge;
            }
        }

        repaint();
    }

    public void reset(){
        graph.clear();
        selectedNode = null;
        hoveredNode = null;
        hoveredEdge = null;
        repaint();
    }

    public void setHValueFromInput(GUINode node){
        String input = JOptionPane.showInputDialog("Enter h for " + node.toString()
                + " : ");
        try {
            int h = Integer.parseInt(input);
            if (h >= 0) {
                node.setHeuristicValue(h);
                graph.setSolved(false);
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Weight should be positive");
            }
        } catch (NumberFormatException nfe) {}
    }
}
