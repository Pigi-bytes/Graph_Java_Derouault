import java.util.ArrayList;
import java.util.List;

public class GraphNonOriente extends Graph {

    @Override
    public void addEdge(Node source, Node destination, int weight) {
        Edge newEdge = new Edge(source, destination, weight);
        Edge reverseEdge = new Edge(destination, source, weight);

        // si AB ou BA existe déjà, on ne duplique pas
        if (edges.contains(newEdge) || edges.contains(reverseEdge)) {
            return;
        }

        addNode(source);
        addNode(destination);
        edges.add(newEdge);
    }

    @Override
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

            // si la réciproque existe (BA), on la supprime aussi
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

    @Override
    public boolean isOriente() {
        return false;
    }

    @Override
    public int degree(Node node) {
        int count = 0;
        for (Edge e : edges) {
            if (node.equals(e.getSource()) || node.equals(e.getDestination())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            
            // Si le noeud est la source, le voisin est la destination
            if (edge.getSource().equals(node)) {
                neighbors.add(edge.getDestination());
            } 

            // Si le noeud est la destination, le voisin est la source (car non orienté)
            else if (edge.getDestination().equals(node)) {
                neighbors.add(edge.getSource());
            }
        }
        return neighbors;
    }

}
