package kruskals;

import java.io.*;
import java.util.*;

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
    private static final File file = new File("city-pairs.txt");
    private static final Map<String, City> cities = new HashMap<>();
    private static final ArrayList weightedEdges = new ArrayList<>();
    private static final ArrayList edgesOfMST = new ArrayList<>();

    private static String[] allCities;
    private static FileReader fr;
    private static BufferedReader br;
    private static int totalEdges = 0;
    private static int totalDistance = 0;
    private static int partitionCount = 0;
    private static final Kruskals kruskals = new Kruskals();

    public static void main(String[] args) {
        try {
            readCities();                          // Load cities
            addCitiesToMap();                      // Add cities as vertices to the graph
            addEdgesToCities();                    // Add connecting cities and corresponding distances to existing vertices
            Collections.sort(weightedEdges);       // Sort edges shortest to longest distances
            init();                                // Mark each city (vertex) as its own forest
            findMST();
            findNumberOfPartitions();
            System.out.println("\nTOTAL NUMBER OF EDGES: " + totalEdges);
            System.out.println("TOTAL DISTANCE: " + totalDistance + "\n");
            System.out.println("There exists " + partitionCount + " partition(s)");
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO-related error has occurred!");
            System.exit(1);
        }
    }

    /**
     * Finds the minimum spanning tree.
     */
    private static void findMST() {
        for (Object edge : weightedEdges) {                                  // For each possible distance, starting with the shortest
            for (Map.Entry<String, City> city : cities.entrySet()) {         // For each city out of the 29 total cities
                for (Map.Entry<City, Integer> connectedCity : city.getValue().getEdgeList().entrySet()) {
                    if ((!find(city.getValue()).equals(find(connectedCity.getKey()))) && connectedCity.getValue() == (int) edge) {
                        edgesOfMST.add(connectedCity.getValue());
                        union(cities.get(city.getKey()), cities.get(connectedCity.getKey().getName()));
                        System.out.println(city.getKey() + " " + connectedCity.getKey().getName() + " " + connectedCity.getValue());
                    }
                }
            }
        }
        for (Object distance : edgesOfMST)
            totalDistance += (int) distance;
        totalEdges = edgesOfMST.size();
    }


    /**
     * Find the number of partitions that exist.
     */
    private static void findNumberOfPartitions() {
        for (Map.Entry<String, City> entry : cities.entrySet()) {
            if (!find(entry.getValue()).isDisplayed()) {
//                System.out.println(find(entry.getValue()).getName());
                find(entry.getValue()).setDisplayed(true);
                partitionCount += 1;
            }
        }
    }

    /**
     * Reads in data (city-pairs) from external file.
     *
     * @throws IOException IO error
     */
    private static void readCities() throws IOException {
        allCities = new String[29];
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        String line = br.readLine();
        if (line != null)
            allCities[0] = line.split(" ")[0];

        int i = 1;
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
     * Adds cities to the graph as vertices.
     */
    private static void addCitiesToMap() {
        for (String city : allCities)
            cities.put(city, new City(city));
    }

    /**
     * Adds connected cities and distances to each existing city vertex.
     *
     * @throws IOException IO error
     */
    private static void addEdgesToCities() throws IOException {
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        String line = br.readLine();

        while (line != null) {
            cities.get(line.split(" ")[0]).addToEdgeList(cities.get(line.split(" ")[1]), Integer.parseInt(line.split(" ")[2]));
            weightedEdges.add(Integer.parseInt(line.split(" ")[2]));
            line = br.readLine();
        }
        br.close();
        fr.close();
    }

    /**
     * Displays all cities and their connections/distances.
     */
    public static void printOutEverything() {
        for (Map.Entry<String, City> city : cities.entrySet()) {
            System.out.println("\n<--------- CITY : " + city.getKey() + " --------->");
            for (Map.Entry<City, Integer> connectedCity : city.getValue().getEdgeList().entrySet())
                System.out.println("Connected to " + connectedCity.getKey().getName() + " via " + connectedCity.getValue() + " miles.");
        }
    }

    /**
     * Mark each vertex in the set of vertices as its own forest.
     */
    private static void init() {
        for (Map.Entry<String, City> entry : cities.entrySet())
            entry.getValue().setForest(entry.getValue());
    }

    /**
     * Finds the canonical representative of the forest.
     *
     * @param city some city
     * @return the canonical representative of the given city
     */
    private static City find(City city) {
        if (city.equals(city.getForest()))
            return city;
        return find(city.getForest());
    }

    /**
     * Joins two forests.
     *
     * @param here  some city
     * @param there some other city
     */
    private static void union(City here, City there) {
        City thisForest = find(here);
        City thatForest = find(there);
        thisForest.setForest(thatForest);
    }
}
