/**
 * Représente un client dans le graphe.
 */
public class Client extends LocationNode {
    /**
     * Adresse du client.
     */
    private final String adress;

    /**
     * Construit un client avec un label et une adresse.
     * 
     * @param label  Nom du client
     * @param adress Adresse du client
     */
    public Client(String label, String adress) {
        super(label);
        this.adress = adress;
    }
    
    /**
     * Retourne l'adresse du client
     * @return l'adresse du client
     */
    public String getAdress() { 
        return adress; 
    }

    @Override
    /**
     * Retourne une représentation textuelle du client
     * @return chaîne représentant le client
     */
    public String toString() {
        return super.toString() + " ; " + this.adress;
    }
}