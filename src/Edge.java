import java.io.Serializable;
import java.util.Objects;

/**
 * Représente une arête pondéré du graph
 */
public class Edge implements Serializable, Cloneable {

    /**
     * Noeud source de l'arête.
     */
    private final Node source;
    /**
     * Noeud destination de l'arête.
     */
    private final Node destination;
    /**
     * Poids de l'arête.
     */
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
     * Retourne le noeud source de l'arête.
     * @return le noeud source
     */
    public Node getSource() {
        return source;
    }

    /**
     * Retourne le noeud destination de l'arête.
     * @return le noeud destination
     */
    public Node getDestination() {
        return destination;
    }

    /**
     * Retourne le poids de l'arête.
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

    /**
     * Clone l'arête avec de nouveaux noeuds source et destination.
     * @param newSource nouveau noeud source
     * @param newDestination nouveau noeud destination
     * @return nouvelle arête clonée
     */
    public Edge clone(Node newSource, Node newDestination) {
        return new Edge(newSource, newDestination, this.weight);
    }
}