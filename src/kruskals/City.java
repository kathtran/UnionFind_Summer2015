package kruskals;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kathleen Tran
 */
public class City {
    private String name;
    private City forest;
    private Map<City, Integer> edgeList = new HashMap<>();
    private boolean visited = false;
    private boolean displayed = false;

    public City(String name) {
        this.name = name;
    }

    /**
     * Adds an entry into the edge list.
     *
     * @param city     some city
     * @param distance the distance between this city and the supplied city
     */
    public void addToEdgeList(City city, int distance) {
        edgeList.put(city, distance);
    }

    /**
     * Displays all entries in the edge list.
     */
    public void displayEdgeList() {
        for (Map.Entry<City, Integer> entry : edgeList.entrySet())
            System.out.println("Connected to " + entry.getKey().getName() + " via " + entry.getValue() + " miles.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getForest() {
        return forest;
    }

    public void setForest(City forest) {
        this.forest = forest;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Map<City, Integer> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(Map<City, Integer> edgeList) {
        this.edgeList = edgeList;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }
}
