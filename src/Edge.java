import java.util.Objects;

public class Edge {
    private final Node source;
    private final Node destination;
    private final int weight;

    /**
     * Crée une arête de nodeA vers nodeB avec un weight donné
     *
     * @param nodeA noeud source
     * @param nodeB noeud destination
     * @param weight poids de l'arête
     */
    public Edge(Node nodeA, Node nodeB, int weight) {
        this.source = nodeA;
        this.destination = nodeB;
        this.weight = weight;
    }
    
    /**
     * Représentation textuelle de l'arête.
     *
     * @return string décrivant l'arête
     */
    @Override
    public String toString() {
        return "" + source + destination + ":" + weight;
    }

    /**
     * @return le noeud source
     */
    public Node getSource() {
        return source;
    }

    /**
     * @return le noeud destination
     */
    public Node getDestination() {
        return destination;
    }

    /**
     * @return le poids (weight) de l'arête
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @return code de hachage basé sur la source et la destination
     * 
     * !! Le poids n'est pas facteur d'unicité !!
     * 
     * 
     */
    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    /**
     * Deux arêtes sont égales si elles relient le même noeud source au même noeud destination
     * 
     * !! Le poids n'est pas facteur d'unicité !!
     *
     * @param otherEdge objet à comparer
     * @return true si otherEdge est une arête équivalente
     */
    @Override
    public boolean equals(Object otherEdge) {
        if (this == otherEdge) return true;
        if (otherEdge == null || getClass() != otherEdge.getClass()) return false;
        Edge edge = (Edge) otherEdge;

        return Objects.equals(source, edge.source) &&
               Objects.equals(destination, edge.destination);
    }
}