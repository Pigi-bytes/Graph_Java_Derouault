/**
 * Représente un restaurant dans le graphe.
 */
public class Restaurant extends LocationNode {
    /**
     * Temps de préparation du restaurant (en minutes).
     */
    private final int preparation;

    /**
     * Construit un restaurant avec un label et un temps de préparation.
     * @param label nom du restaurant
     * @param prepTime temps de préparation en minutes
     */
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
        return "Restaurant " + super.toString() + " (" + this.getPreparation() + " min)" ;
    }
}