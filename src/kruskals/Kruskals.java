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

    private static String[] allCities;
    private static FileReader fr;
    private static BufferedReader br;
    private static int i;
    private static final Kruskals kruskals = new Kruskals();

    private long starting;

    public static void main(String[] args) {
        try {
            readCities();                          // Load cities

//            for (String city : allCities) {
//                System.out.println(city);
//            }
//            System.out.println("\nCities printed from ARRAY. Now printing from MAP\n\n");
            addCitiesToMap();                      // Add cities as vertices to the graph
//            for (Map.Entry<String, City> entry : cities.entrySet())
//                System.out.println(entry.getKey());
//            System.out.println("\nCities printed from MAP. Now printing from EDGELIST\n\n");
            addEdgesToCities();                    // Add connecting cities and corresponding distances to existing vertices
            Collections.sort(weightedEdges);       // Sort edges shortest to longest distances
//            for (Object edge : weightedEdges)
//                System.out.println(edge);
//            for (Map.Entry<String, City> entry : cities.entrySet()) {
//                System.out.print("<--------- CITY : " + entry.getKey() + " --------->\n");
//                entry.getValue().displayEdgeList();
//                System.out.println();
//            }
            kruskals.init();                       // Mark each city (vertex) as its own forest

            City endOne;
            City endTwo;
            // For each possible distance, starting with the shortest
            for (Object edge : weightedEdges) {
                // For each city out of the 29 total cities
                for (Map.Entry<String, City> city : cities.entrySet()) {
                    // For each connected city
                    for (Map.Entry<City, Integer> connectedCity : city.getValue().getEdgeList().entrySet()) {
                        // If the distance between the two cities is the one that we are looking for
                        // AND they are not already within the same forest, UNION them
                        if (connectedCity.getValue() == Integer.parseInt((String) edge) &&
                                !find(connectedCity.getKey()).equals(find(city.getValue()))) {
                            union(city.getValue(), cities.get(connectedCity.getKey().getName()));
                        }
                    }
                }
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

    private static void addCitiesToMap() {
        for (String city : allCities)
            cities.put(city, new City(city));
    }

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
     * Mark each vertex in the set of vertices as its own forest.
     */
    private void init() {
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
