package kruskals;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kathleen Tran
 */
public class Vertex extends City {
    private Map<String, Vertex> edgeList = new HashMap<>();
    private Vertex forest;
    private int distance;
    private boolean displayed;

    public Vertex(String name) {
        super(name);
        forest = null;
        distance = 0;
        displayed = false;
    }

    public Map<String, Vertex> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(Map<String, Vertex> edgeList) {
        this.edgeList = edgeList;
    }

    public Vertex getForest() {
        return forest;
    }

    public void setForest(Vertex forest) {
        this.forest = forest;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }
}
