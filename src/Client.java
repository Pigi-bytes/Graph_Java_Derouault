public class Client extends LocationNode {
    private final String adress;

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
        return "Client " + super.toString() + " ; " + this.adress;
    }
}