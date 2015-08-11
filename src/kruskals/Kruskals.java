package kruskals;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * CS350 - Lots Of Topics
 * Problem 2
 * <p>
 * Implementation of Kruskal's Algorithm.
 * Testing via application against provided data set.
 *
 * @author Kathleen Tran
 */
public class Kruskals {
    private static final Map<Vertex, Vertex> cities = new HashMap<>();
    private static final File file = new File("city-pairs.txt");

    private static String[] allCities;
    private static FileReader fr;
    private static BufferedReader br;
    private static int i;
    private static final Kruskals kruskals = new Kruskals();

    private long starting;

    public static void main(String[] args) {
        try {
            readCities();
            kruskals.init();

            for (String city : allCities) {
                System.out.println(city);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO-related error has occurred!");
            System.exit(1);
        }
    }

    /**
     * Reads in data (city-pairs) from external file.
     *
     * @throws FileNotFoundException if file cannot be located.
     */
    private static void readCities() throws IOException {
        allCities = new String[29];
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        String line = br.readLine();
        if (line != null)
            allCities[0] = line.split(" ")[0];

        i = 1;
        while (line != null && i < 29) {
            while (allCities[i - 1].equals(line.split(" ")[0])) {
                line = br.readLine();
            }
            allCities[i] = line.split(" ")[0];
            line = br.readLine();
            i = i + 1;
        }
        br.close();
        fr.close();
    }

    /**
     * Adds cities to the array list.
     */
    private void createVerticesFromCities() {
        for (String city : allCities)
            cities.put(new Vertex(city), null);
    }

    private void mapCitiesToCities() throws IOException {
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        String line = br.readLine();

        while (line != null) {


        }
    }

    /**
     * Initializes vertices by setting each vertex's parent pointer to itself.
     */
    private void init() {
        for (Map.Entry<Vertex, Vertex> city : cities.entrySet())
            city.getKey().setParent(city.getKey());
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
        System.out.println("City " + a.getCity() + " has been mapped to city " + b.getCity() + ".");
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
