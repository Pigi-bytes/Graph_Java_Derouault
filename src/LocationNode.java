public abstract class LocationNode extends Node {

    private String name;
    private String address;

    public LocationNode(String id, String name, String address) {
        super(id);
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public abstract String getType();
}
