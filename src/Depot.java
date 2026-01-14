public class Depot extends LocationNode {
    public Depot(String label) {
        super(label);
    }
    
    @Override
    /**
     * Retourne une représentation textuelle du dépôt
     * @return chaîne représentant le dépôt
     */
    public String toString() {
        return super.toString() + " (Depot)";
    }
}