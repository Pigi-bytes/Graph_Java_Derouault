public class Restaurant extends LocationNode {
    private int preparation;

    public Restaurant(String label, int prepTime) {
        super(label);
        this.preparation = prepTime;
    }

    public int getPreparation() { 
        return preparation; 
    }
    
    @Override
    public String toString() {
        return super.toString() + " Resto";
    }
}