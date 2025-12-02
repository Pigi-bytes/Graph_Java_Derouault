import java.util.Objects;

public class Edge {
    private final Node source;
    private final Node destination;
    private final int weight;

    public Edge(Node nodeA, Node nodeB, int weight) {
        this.source = nodeA;
        this.destination = nodeB;
        this.weight = weight;
    }
    
    @Override
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

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, weight);
    }

    @Override
    public boolean equals(Object otherEdge) {
        if (this == otherEdge) return true;
        if (otherEdge == null || getClass() != otherEdge.getClass()) return false;
        Edge edge = (Edge) otherEdge;

        return weight == edge.weight &&
               Objects.equals(source, edge.source) &&
               Objects.equals(destination, edge.destination);
    }
}