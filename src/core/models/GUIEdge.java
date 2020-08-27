package core.models;

public class GUIEdge {
    private GUINode one;
    private GUINode two;
    private int weight = 1;

    public GUIEdge(GUINode one, GUINode two){
        this.one = one;
        this.two = two;
    }

    public GUINode getNodeOne(){
        return one;
    }

    public GUINode getNodeTwo(){
        return two;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }

    public boolean hasNode(GUINode GUINode){
        return one== GUINode || two== GUINode;
    }

    public boolean equals(GUIEdge GUIEdge) {
        return (one == GUIEdge.one && two == GUIEdge.two) || (one == GUIEdge.two && two == GUIEdge.one) ;
    }

    @Override
    public String toString() {
        return "Edge ~ "
                + getNodeOne().getId() + " - "
                + getNodeTwo().getId();
    }
}
