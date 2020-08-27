package core;

import java.util.ArrayList;
import java.util.List;

//used to print search tree..
// it's not important to dive into details..
public class GenericTreeNode{
    public Node value;
    public ArrayList<GenericTreeNode> children = new ArrayList<>();
    public GenericTreeNode parent;
    public int getChildrenCount(){
        return children.size();
    }
    public List<GenericTreeNode> getChildren(){
        return children;
    }
    public boolean isRightMostChild(){
        return parent.getChildren().get(parent.getChildrenCount()-1).value.isSimilarTo(this.value);
    }
    public boolean isLeftMostChild(){
        return parent.getChildren().get(0).value.isSimilarTo(this.value);
    }
    public String toString(){
        return value.getState().toString();
    }
}