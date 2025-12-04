import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public void removeNode(Node node) {
        if (!nodes.contains(node)) {
            return;
        }
        
        Set<Edge> edgesToRemove = new HashSet<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) || edge.getDestination().equals(node)) {
                edgesToRemove.add(edge);
            }
        }

        edges.removeAll(edgesToRemove);
        
        nodes.remove(node);
    }

    public void removeNode(String label) {
        for (Node node : nodes) {
            if (node.getLabel().equals(label)) {
                removeNode(node);
                return;
            }
        }
    }

    public void removeEdge(Node source, Node destination) {
        Edge edgeToRemove = null;
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getDestination().equals(destination)) {
                edgeToRemove = edge;
                break;
            }
        }
        
        if (edgeToRemove != null) {
            edges.remove(edgeToRemove);
            
            if (!estOriente) {
                Edge reverseEdge = null;
                for (Edge edge : edges) {
                    if (edge.getSource().equals(destination) && edge.getDestination().equals(source)) {
                        reverseEdge = edge;
                        break;
                    }
                }
                if (reverseEdge != null) {
                    edges.remove(reverseEdge);
                }
            }
        }
    }

    public void removeEdge(String sourceLabel, String destinationLabel) {
        Node source = null;
        Node destination = null;
        
        for (Node node : nodes) {
            if (node.getLabel().equals(sourceLabel)) {
                source = node;
            }
            if (node.getLabel().equals(destinationLabel)) {
                destination = node;
            }
        }
        
        if (source != null && destination != null) {
            removeEdge(source, destination);
        }
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


    public List<Node> dfs(Node start) {
        List<Node> result = new ArrayList<>();

        for (Node n : nodes) {
            if (n.equals(start)) {
                start =  n;
            }
        }

        Set<Node> visited = new HashSet<>();
        dfsVisit(start, visited, result);
        return result;
    }

    private void dfsVisit(Node u, Set<Node> visited, List<Node> result) {
        visited.add(u);
        result.add(u);

        for (Edge e : edges) {
            if (e.getSource().equals(u)) {
                Node v = e.getDestination();
                if (!visited.contains(v)) {
                    dfsVisit(v, visited, result);
                }
            }
        }
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