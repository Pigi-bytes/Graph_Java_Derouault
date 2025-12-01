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
        return "Edge [nodeA=" + source + ", nodeB=" + destination + ", weight=" + weight + "]";
    }
}