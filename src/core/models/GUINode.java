package core.models;

import java.awt.*;
import java.util.List;

public class GUINode {
    private Point coord = new Point();
    private int id;
    private List<GUINode> path;
    private int h;
    public void setHeuristicValue(int h){
        this.h = h;
    }
    public int getHeuristicValue(){
        return h;
    }
    public GUINode(){}

    public GUINode(int id){
        this.id = id;
    }

    public GUINode(Point p){
        this.coord = p;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setCoord(int x, int y){
        coord.setLocation(x, y);
    }

    public Point getCoord(){
        return coord;
    }

    public void setPath(List<GUINode> path) {
        this.path = path;
    }

    public List<GUINode> getPath() {
        return path;
    }

    public int getX(){
        return (int) coord.getX();
    }

    public int getY(){
        return (int) coord.getY();
    }

    public int getId(){
        return id-1;
    }

    @Override
    public String toString() {
        return "Node " + id;
    }
}
