
import java.util.LinkedList;

/**
 * A node class, for each location in our Graph
 */
public class Node {
    // Fields
    public LinkedList<Edge> edgeList;
    private String locationName;
    private Boolean hasCoffee;

    /**
     * Constructor method
     * @param locationName
     * @param hasCoffee
     */
    public Node(String locationName, Boolean hasCoffee) {
        this.edgeList = new LinkedList<Edge>();
        this.hasCoffee = hasCoffee;
        this.locationName = locationName;
    }

    /**
     * Add a neighbor to the lit
     * @param street
     */
    public void addStreet(Edge street) {
        this.edgeList.addLast(street);
    }

    /**
     * Pass in a random int, get back a Node, always same if int is same
     * @param randValue
     * @return
     */
    public Edge getRandStreet(int randValue) {
        int listLength = edgeList.size();

        // return a null if there are no streets
        if (listLength == 0) {
            return null;
        }

        // otherwise, return a value
        return this.edgeList.get(randValue % listLength);
    }

    /**
     * Getter for the locationName variable, which is private
     * @return
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Getter for whether or not the location has coffee
     * @return
     */
    public Boolean getHasCoffee() {
        return hasCoffee;
    }
}
