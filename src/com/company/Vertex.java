package com.company;

/**
 * @author Kathleen Tran
 */
public class Vertex extends Data {
    private Vertex parent;
    private boolean displayed;

    public Vertex() {
        parent = null;
        displayed = false;
    }

    public Vertex(int value) {
        super(value);
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
