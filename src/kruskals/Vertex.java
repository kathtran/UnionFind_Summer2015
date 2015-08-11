package kruskals;

/**
 * @author Kathleen Tran
 */
public class Vertex extends City {
    private Vertex parent;
    private boolean displayed;

    public Vertex() {
        parent = null;
        displayed = false;
    }

    public Vertex(String name) {
        super(name);
        displayed = false;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }
}
