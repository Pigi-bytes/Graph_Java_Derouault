import java.util.HashSet;
import java.util.Set;

public class Graph {
    private final Set<Node> nodes = new HashSet<>();
    private final Set<Edge> edges = new HashSet<>();
    private final boolean estOriente;

    public Graph(boolean estOriente) {
        this.estOriente = estOriente;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node source, Node destination, int weight) {
        Edge newEdge = new Edge(source, destination, weight);

        if (source.equals(destination)) {
            return;
        }

        if (edges.contains(newEdge)) {
            return;
        }

        addNode(source);
        addNode(destination);

        edges.add(newEdge);

        if (!estOriente) {
            edges.add(new Edge(destination, source, weight));
        }
    }

    public void addEdge(Node source, Node destination) {
        addEdge(source, destination, 1);
    }

    public String toString() {
        return "Graph [nodes=" + nodes + ", edges=" + edges + ", directed=" + estOriente + "]";
    }

    
}