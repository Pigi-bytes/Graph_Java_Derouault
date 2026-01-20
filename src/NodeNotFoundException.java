/**
 * Exception levée lorsqu'un noeud n'est pas trouvé dans le graphe.
 */
public class NodeNotFoundException extends RuntimeException {
    /**
     * Construit une exception avec un message explicite.
     * @param message le message d'erreur
     */
    public NodeNotFoundException(String message) {
        super(message);
    }
}