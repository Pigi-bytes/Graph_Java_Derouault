/**
 * Représente un noeud localisable dans le graphe (dépôt, restaurant, client,
 * carrefour).
 */
public abstract class LocationNode extends Node {
    /**
     * Construit un noeud localisable avec un label.
     * @param label Nom du noeud
     */
    public LocationNode(String label) {
        super(label);
    }

}