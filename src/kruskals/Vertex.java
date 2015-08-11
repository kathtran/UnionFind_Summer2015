package kruskals;

/**
 * @author Kathleen Tran
 */
public class Vertex extends City {
    private Vertex parent;
    private int distance;
    private boolean displayed;

    public Vertex() {
        parent = null;
        distance = 0;
        displayed = false;
    }

    public Vertex(String name) {
        super(name);
        parent = null;
        distance = 0;
        displayed = false;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
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
