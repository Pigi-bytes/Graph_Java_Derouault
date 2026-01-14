public class Restaurant extends LocationNode {
    private final int preparation;

    public Restaurant(String label, int prepTime) {
        super(label);
        this.preparation = prepTime;
    }

    /**
     * Retourne le temps de préparation du restaurant
     * @return temps de préparation en minutes
     */
    public int getPreparation() { 
        return preparation; 
    }
    
    @Override
    /**
     * Retourne une représentation textuelle du restaurant
     * 
     * @return chaîne représentant le restaurant
     */
    public String toString() {
        return super.toString();
    }
}