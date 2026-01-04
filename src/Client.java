public class Client extends LocationNode {
    private String adress;

    public Client(String label, String adress) {
        super(label);
    }
    
    public String getAdress() { 
        return adress; 
    }

    @Override
    public String toString() {
        return super.toString() + " Client";
    }
}