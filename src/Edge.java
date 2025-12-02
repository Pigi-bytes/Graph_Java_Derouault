import java.util.Objects;

public class Edge {
    private Node source;
    private Node destination;
    private int weight;

    public Edge(Node nodeA, Node nodeB, int weight) {
        this.source = nodeA;
        this.destination = nodeB;
        this.weight = weight;
    }
    
    public String toString() {
        return "" + source + destination + ":" + weight;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public int hashCode() {
        return Objects.hash(source, destination, weight);
    }

    public boolean equals(Object otherEdge) {
        if (this == otherEdge) return true;
        if (otherEdge == null) return false;
        Edge edge = (Edge) otherEdge;

        return weight == edge.weight &&
               Objects.equals(source, edge.source) &&
               Objects.equals(destination, edge.destination);
    }
}