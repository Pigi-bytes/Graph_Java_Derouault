/**
 * Exception levée lorsque le réseau n'est pas connexe.
 */
public class NetworkNotConnectedException extends Exception {
    /**
     * Construit une exception avec un message explicite.
     * @param message le message d'erreur
     */
    public NetworkNotConnectedException(String message) {
        super(message);
    }
}