/**
 * A directed edge
 */
public class Edge {
    // fields
    Node from;
    Node to;
    String streetName;
    boolean exitNode;

    /**
     * Construct for Edge class
     */
    public Edge(Node from, Node to, String streetName, boolean exitNode) {
        this.from = from;
        this.to = to;
        this.streetName = streetName;
        this.exitNode = exitNode;
    }

}
