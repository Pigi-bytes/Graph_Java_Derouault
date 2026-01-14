import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation d'un graphe non orienté
 */
public class GraphNonOriente extends Graph {

    @Override
    /**
     * Ajoute une arête non orientée entre deux noeuds avec un poids donné
     * @param source noeud source
     * @param destination noeud destination
     * @param weight poids de l'arête
     */
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
    /**
     * Supprime l'arête (et sa réciproque) entre deux noeuds
     * @param source noeud source
     * @param destination noeud destination
     */
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
    /**
     * Indique si le graphe est orienté (toujours faux ici)
     * @return false
     */
    public boolean isOriente() {
        return false;
    }

    @Override
    /**
     * Calcule le degré d'un noeud
     * @param node noeud dont on veut le degré
     * @return degré du noeud
     */
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
    /**
     * Retourne la liste des voisins d'un noeud
     * @param node noeud dont on veut les voisins
     * @return liste des noeuds voisins
     */
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

    @Override
    /**
     * Retourne l'arête reliant deux noeuds (dans un sens ou l'autre)
     * @param source noeud source
     * @param destination noeud destination
     * @return arête correspondante ou null
     */
    public Edge getEdge(Node source, Node destination) {
        for (Edge edge : edges) {
            // On vérifie A vers B
            if (edge.getSource().equals(source) && edge.getDestination().equals(destination)) {
                return edge;
            }
            // On vérifie B vers A (car non orienté)
            if (edge.getSource().equals(destination) && edge.getDestination().equals(source)) {
                return edge;
            }
        }
        return null;
    }

}
