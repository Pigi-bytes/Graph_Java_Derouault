public class Restaurant extends LocationNode {

    private int preparationTime; // minutes

    public Restaurant(String id, String name, String address, int preparationTime) {
        super(id, name, address);
        this.preparationTime = preparationTime;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    @Override
    public String getType() {
        return "Restaurant";
    }
}
