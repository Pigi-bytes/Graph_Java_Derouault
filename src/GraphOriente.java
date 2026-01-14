import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation d'un graphe orienté
 */
public class GraphOriente extends Graph {
    
    @Override
    /**
     * Ajoute une arête orientée entre deux noeuds avec un poids donné
     * @param source noeud source
     * @param destination noeud destination
     * @param weight poids de l'arête
     */
    public void addEdge(Node source, Node destination, int weight) {
        Edge newEdge = new Edge(source, destination, weight);
        if (edges.contains(newEdge)) {
            return;
        }

        addNode(source);
        addNode(destination);
        edges.add(newEdge);
    }

    @Override
    /**
     * Supprime l'arête orientée entre deux noeuds
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
        }
    }

    @Override
    /**
     * Indique si le graphe est orienté (toujours vrai ici)
     * @return true
     */
    public boolean isOriente() {
        return true;
    }

    /**
     * Calcule le degré sortant (nombre d'arêtes sortantes) du noeud
     *
     * @param node noeud ciblé
     * @return nombre d'arêtes ayant node comme source
     */
    public int outDegree(Node node) {
        int count = 0;
        for (Edge e : edges) {
            if (node.equals(e.getSource()))
                count++;
        }
        return count;
    }

    /**
     * Calcule le degré entrant (nombre d'arêtes entrantes) du noeud
     *
     * @param node noeud ciblé
     * @return nombre d'arêtes ayant node comme destination
     */
    public int inDegree(Node node) {
        int count = 0;
        for (Edge e : edges) {
            if (node.equals(e.getDestination()))
                count++;
        }
        return count;
    }

    @Override
    /**
     * Retourne la liste des voisins accessibles depuis un noeud
     * @param node noeud dont on veut les voisins
     * @return liste des noeuds voisins
     */
    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            // On ne regarde que si le noeud est la SOURCE
            if (edge.getSource().equals(node)) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    @Override
    /**
     * Calcule le degré total (entrant + sortant) d'un noeud
     * @param node noeud ciblé
     * @return somme des degrés entrant et sortant
     */
    public int degree(Node node) {
        return inDegree(node) + outDegree(node);
    }

    @Override
    /**
     * Retourne l'arête orientée reliant deux noeuds
     * @param source noeud source
     * @param destination noeud destination
     * @return arête correspondante ou null
     */
    public Edge getEdge(Node source, Node destination) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getDestination().equals(destination)) {
                return edge;
            }
        }
        return null;
    }
}