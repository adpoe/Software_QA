import java.util.LinkedList;

/**
 * Graph, containing all of our locations.
 * Using a LinkedList so we can index into the Graph to get a starting location consistently.
 */
public class Graph {
    // container for nodes
    public LinkedList<Node> elements;

    /**
     * Constructor method
     */
    public Graph(LinkedList<Node> allNodes) {
        this.elements = new LinkedList<Node>();
        for (Node node : allNodes) {
            this.elements.addLast(node);
        }
    }
    
    /**
     * Get a randNum at most the size of total number of nodes created
     * @param randNum
     * @return
     */
    public Node getEntryPoint(int randNum) {
        int graphSize = this.elements.size();
        return this.elements.get(randNum % graphSize);
    }

}
