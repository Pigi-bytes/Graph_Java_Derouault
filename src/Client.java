public class Client extends LocationNode {

    private String phoneNumber;

    public Client(String id, String name, String address, String phoneNumber) {
        super(id, name, address);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getType() {
        return "Client";
    }
}
