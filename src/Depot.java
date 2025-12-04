public class Depot extends LocationNode {

    private int capacity;

    public Depot(String id, String name, String address, int capacity) {
        super(id, name, address);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String getType() {
        return "Depot";
    }
}
