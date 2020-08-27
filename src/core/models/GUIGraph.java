package core.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIGraph {
    private int count = 1;
    private List<GUINode> GUINodes = new ArrayList<>();
    private List<GUIEdge> GUIEdges = new ArrayList<>();

    private GUINode source;
    private GUINode destination;

    private boolean solved = false;

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setGUINodes(List<GUINode> GUINodes){
        this.GUINodes = GUINodes;
    }

    public List<GUINode> getGUINodes(){
        return GUINodes;
    }

    public void setGUIEdges(List<GUIEdge> GUIEdges){
        this.GUIEdges = GUIEdges;
    }

    public List<GUIEdge> getGUIEdges(){
        return GUIEdges;
    }

    public boolean isNodeReachable(GUINode GUINode){
        for(GUIEdge GUIEdge : GUIEdges)
            if(GUINode == GUIEdge.getNodeOne() || GUINode == GUIEdge.getNodeTwo())
                return true;

        return false;
    }

    public void setSource(GUINode GUINode){
        if(GUINodes.contains(GUINode))
            source = GUINode;
    }

    public void setDestination(GUINode GUINode){
        if(GUINodes.contains(GUINode))
            destination = GUINode;
    }

    public GUINode getSource(){
        return source;
    }

    public GUINode getDestination(){
        return destination;
    }

    public boolean isSource(GUINode GUINode){
        return GUINode == source;
    }

    public boolean isDestination(GUINode GUINode){
        return GUINode == destination;
    }

    public void addNode(Point coord){
        GUINode GUINode = new GUINode(coord);
        addNode(GUINode);
    }

    public void addNode(GUINode GUINode){
        GUINode.setId(count++);
        GUINodes.add(GUINode);
        if(GUINode.getId()==0)
            source = GUINode;
    }

    public void addEdge(GUIEdge new_GUI_edge){
        boolean added = false;
        for(GUIEdge GUIEdge : GUIEdges){
            if(GUIEdge.equals(new_GUI_edge)){
                added = true;
                break;
            }
        }
        if(!added)
            GUIEdges.add(new_GUI_edge);
    }

    public void deleteNode(GUINode GUINode){
        List<GUIEdge> delete = new ArrayList<>();
        for (GUIEdge GUIEdge : GUIEdges){
            if(GUIEdge.hasNode(GUINode)){
                delete.add(GUIEdge);
            }
        }
        for (GUIEdge GUIEdge : delete){
            GUIEdges.remove(GUIEdge);
        }
        GUINodes.remove(GUINode);
    }

    public void clear(){
        count = 1;
        GUINodes.clear();
        GUIEdges.clear();
        solved = false;

        source = null;
        destination = null;
    }

}
