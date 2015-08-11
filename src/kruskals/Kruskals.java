package kruskals;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    private static String[] allCities;
    private static FileReader fr;
    private static BufferedReader br;
    private static int i;
    private static final Kruskals kruskals = new Kruskals();

    private long starting;

    public static void main(String[] args) {
        try {
            readCities();

            for (String city : allCities) {
                System.out.println(city);
            }
            System.out.println("\nCities printed from ARRAY. Now printing from MAP\n\n");
            addCitiesToMap();
            for (Map.Entry<String, City> entry : cities.entrySet())
                System.out.println(entry.getKey());
            System.out.println("\nCities printed from MAP. Now printing from EDGELIST\n\n");
            addEdgesToCities();
            for (Map.Entry<String, City> entry : cities.entrySet()) {
                System.out.print("CITY : " + entry.getKey() + "\nEDGE LIST : \n");
                entry.getValue().displayEdgeList();
            }
//            kruskals.init();

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
