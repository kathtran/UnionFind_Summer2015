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
//            kruskals.init();

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
