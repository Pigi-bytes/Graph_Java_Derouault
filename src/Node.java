import java.io.Serializable;
import java.util.Objects;

public class Node implements Serializable, Cloneable {
    private final String label;

    /**
     * Crée un noeud avec le label fourn
     *
     * @param label label du noeud
     */
    public Node(String label) {
        this.label = label;
    }

    /**
     * Retourne le label du noeud
     *
     * @return string contenant le label du noeud
     */
    public String getLabel() {
        return label;
    }

    /**
     * Représentation du noeud (son label)
     *
     * @return string représentant le noeud
     */
    @Override
    public String toString() {
        return label;
    }

    /**
     * Deux noeud sont égaux si leurs labels sont égaux
     *
     * @param otherNode objet à comparer
     * @return true si otherNode est un Node avec le même label
     */
    @Override
    public boolean equals(Object otherNode) {
        if (this == otherNode) return true; 
        if (otherNode == null || getClass() != otherNode.getClass()) return false;
        Node node = (Node) otherNode;
        return Objects.equals(label, node.label);
    }

    /**
     * Code de hachage basé sur le label
     *
     * @return int code de hachage
     */
    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public Node clone() throws CloneNotSupportedException {
        return (Node) super.clone();
    }
}

