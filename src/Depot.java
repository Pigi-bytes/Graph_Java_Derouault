public class Depot extends LocationNode {
    public Depot(String label) {
        super(label);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (Depot)";
    }
}