package com.company;

import java.util.ArrayList;

/**
 * CS350 - Lots Of Topics
 * Problem 1.(b)
 * <p>
 * Implementation of pseudocode for Union-Find.
 *
 * @author Kathleen Tran
 */
public class UnionFind {
    private static ArrayList<Vertex> vertices = new ArrayList<>();

    /**
     * Allocates data and tests the correctness and performance of the code.
     *
     * @param args an array of integers used to populate the vertices
     */
    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(args);
        unionFind.displayAllVertices();

        unionFind.init();
        unionFind.union(vertices.get(8), vertices.get(3));
        unionFind.union(vertices.get(10), vertices.get(1));
        unionFind.union(vertices.get(7), vertices.get(0));
        unionFind.union(vertices.get(11), vertices.get(12));
        unionFind.union(vertices.get(10), vertices.get(13));
        unionFind.union(vertices.get(6), vertices.get(12));
        unionFind.union(vertices.get(1), vertices.get(12));
        unionFind.union(vertices.get(4), vertices.get(3));
        unionFind.union(vertices.get(5), vertices.get(1));
        unionFind.displayForest(vertices.get(11));
        unionFind.displayForest(vertices.get(1));
        unionFind.displayForest(vertices.get(8));
    }

    /**
     * Adds command line arguments to the container of vertices.
     *
     * @param args list of integers
     */
    public UnionFind(String[] args) {
        for (String arg : args)
            vertices.add(new Vertex(Integer.parseInt(arg)));
    }

    /**
     * Initializes vertices by setting each vertex's parent pointer to itself.
     */
    public void init() {
        for (Object element : vertices) {
            Vertex vertex = (Vertex) element;
            vertex.setParent(vertex);
        }
    }

    /**
     * Finds the canonical representative of the given vertex.
     *
     * @param vertex some vertex
     * @return the canonical representative of the given vertex
     */
    public Vertex find(Vertex vertex) {
        if (vertex.equals(vertex.getParent()))
            return vertex;
        return find(vertex.getParent());
    }

    /**
     * Joins two vertices to create a new forest. The second parameter
     * becomes the parent of the first parameter.
     *
     * @param a some vertex
     * @param b some vertex
     */
    public void union(Vertex a, Vertex b) {
        Vertex aRep = find(a);
        Vertex bRep = find(b);
        a.setParent(b);
        System.out.println("Vertex " + a.getValue() + " has been mapped to vertex " + b.getValue() + ".");
    }

    /**
     * Displays all vertices provided by the command line arguments.
     */
    public void displayAllVertices() {
        System.out.print("Vertices: ");
        for (Object element : vertices)
            System.out.print(((Vertex) element).getValue() + " ");
        System.out.print("\n");
    }

    /**
     * Displays all vertices within a given vertex's forest.
     * This is the wrapper method.
     *
     * @param vertex some vertex
     */
    public void displayForest(Vertex vertex) {
        Vertex parent = find(vertex);
        System.out.print("Display forest for vertex " + vertex.getValue() + ": " +
                "\n\tCanonical representative: " + parent.getValue() +
                "\n\tThe rest of the forest: ");
        displayForestRecursively(parent);
        System.out.print("\n");
    }

    /**
     * Displays all vertices within a given vertex's forest.
     * This is the recursive method.
     *
     * @param parent the parent vertex
     */
    private void displayForestRecursively(Vertex parent) {
        for (Object vertex : vertices) {
            Vertex v = (Vertex) vertex;
            if (v.equals(parent))
                continue;
            if (find(v).equals(parent))
                System.out.print(v.getValue() + " ");
        }
    }
}
