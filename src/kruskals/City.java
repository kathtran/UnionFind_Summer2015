package kruskals;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kathleen Tran
 */
public class City {
    private Map<City, Integer> edgeList = new HashMap<>();
    private String name;
    private boolean visited = false;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<City, Integer> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(Map<City, Integer> edgeList) {
        this.edgeList = edgeList;
    }

    public void addToEdgeList(City city, int distance) {
        edgeList.put(city, distance);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void displayEdgeList() {
        for (Map.Entry<City, Integer> entry : edgeList.entrySet())
            System.out.println("CITY: " + entry.getKey().getName() + " & DISTANCE: " + entry.getValue());
    }
}
