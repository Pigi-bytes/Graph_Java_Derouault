public class Client extends LocationNode {
    private final String adress;

    public Client(String label, String adress) {
        super(label);
        this.adress = adress;
    }
    
    public String getAdress() { 
        return adress; 
    }

    @Override
    public String toString() {
        return "Client " + super.toString() + " ; " + this.adress;
    }
}