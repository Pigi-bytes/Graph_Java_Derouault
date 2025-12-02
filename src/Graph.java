import java.util.HashSet;
import java.util.Objects;
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

    public String toAdjacencyListString() {
        String chaine = "";

        for (Node node : this.nodes) {
            chaine += node.getLabel() + ": ";
            
            for (Edge edge : this.edges) {
                if (edge.getSource().equals(node)) {
                    chaine +=  edge.getDestination() + "(" + edge.getWeight() + ") ";
                }
            }
            chaine += "\n";
        }
        return chaine;
    }

    public String toEdgeListString() {
        String chaine = "";
        Set<Node> nodesInEdges = new HashSet<>();

        for (Edge edge : this.edges) {
            Node source = edge.getSource();
            Node destination = edge.getDestination();

            nodesInEdges.add(source);
            nodesInEdges.add(destination);

            chaine += source + " " + destination + " " + edge.getWeight() + "\n";
        }

        for (Node node : this.nodes) {
            if (!nodesInEdges.contains(node)) {
                chaine += node + "\n";
            }
        }
        return chaine;
    }

    public boolean getEstOriente() {
        return estOriente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, edges, estOriente);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Graph other = (Graph) obj;
        
        return estOriente == other.estOriente &&
            Objects.equals(nodes, other.nodes) &&
            Objects.equals(edges, other.edges);
    }

    @Override
    public String toString() {
        return "Graph [nodes=" + nodes + ", edges=" + edges + ", estOriente=" + estOriente + "]";
    }
    
}