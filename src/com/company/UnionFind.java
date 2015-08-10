package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * CS350 - Lots Of Topics
 * Problem 1.(b)
 * <p>
 * Implementation of pseudocode for Union-Find.
 *
 * @author Kathleen Tran
 */
public class UnionFind {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final ArrayList<Vertex> vertices = new ArrayList<>();
    private static UnionFind unionFind;
    private static int[] makeVertices;
    private static int numberOfVertices;
    private static int i;
    private long starting;

    /**
     * Allocates data and tests the correctness and performance of the code.
     *
     * @param args an array of integers used to populate the vertices
     */
    public static void main(String[] args) {
        createVertices();
        unionFind = new UnionFind(makeVertices);
        unionFind.displayAllVertices();
        unionFind.startTime();

        try {
            unionFind.init();
            createMappings();
            displayPartitions();

            System.out.println("\nTotal running time: " + unionFind.endTimer() + " s");
        } catch (StackOverflowError ex) {
            System.err.println("An expected error that is associated with utilizing the RNG to map vertices has occurred. " +
                    "Please just re-run the program.");
            System.exit(1);
        }
    }

    /**
     * Creates a set of vertices of some user-specified size that is filled with random integers.
     */
    private static void createVertices() {
        System.out.print("How many vertices would you like to begin with?: ");
        numberOfVertices = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        makeVertices = new int[numberOfVertices];
        i = 0;
        while (i < numberOfVertices) {
            int vertex = random.nextInt(50);
            makeVertices[i] = vertex;
            i = i + 1;
        }
    }

    /**
     * Randomly maps vertices a user-defined number of times.
     */
    private static void createMappings() {
        System.out.print("How many times would you like to create mappings?: ");
        int numberOfMappings = scanner.nextInt();
        scanner.nextLine();

        i = 0;
        while (i < numberOfMappings) {
            int mapChild = random.nextInt(numberOfVertices - 1);
            int mapParent = random.nextInt(numberOfVertices - 1);
            unionFind.union(vertices.get(mapChild), vertices.get(mapParent));
            i = i + 1;
        }
    }

    /**
     * Displays all forests and displays total number of partitions.
     */
    private static void displayPartitions() {
        int partitionCount = 0;
        for (i = 0; i < numberOfVertices; ++i) {
            if (!vertices.get(i).getParent().isDisplayed()) {
                unionFind.displayForest(vertices.get(i));
                vertices.get(i).getParent().setDisplayed(true);
                partitionCount = partitionCount + 1;
            }
        }
        System.out.println("\nThere exists " + partitionCount + " partitions.");
    }

    /**
     * Adds command line arguments to the container of vertices.
     *
     * @param args list of integers
     */
    public UnionFind(int[] args) {
        for (int arg : args)
            vertices.add(new Vertex(arg));
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

    /**
     * Starts the counter.
     *
     * @return starting time
     */
    public long startTime() {
        starting = System.currentTimeMillis();
        return starting;
    }

    /**
     * Calculates the time between when the counter was started and ended.
     *
     * @return time representative of duration between the call to the startTime
     * method and the call to this method
     */
    public double endTimer() {
        long ending = System.currentTimeMillis();
        return ((ending - starting) / 1000.0);
    }
}
