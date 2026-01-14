public class Restaurant extends LocationNode {
    private final int preparation;

    public Restaurant(String label, int prepTime) {
        super(label);
        this.preparation = prepTime;
    }

    public int getPreparation() { 
        return preparation; 
    }
    
    @Override
    public String toString() {
        return "Restaurant " + super.toString() + " (" + this.getPreparation() + " min)" ;
    }
}