package com.company;

/**
 * @author Kathleen Tran
 */
public class Vertex extends Data {
    private Vertex parent;

    public Vertex() {
        parent = null;
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
}
