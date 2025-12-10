public class StreetSegment extends Edge {

    public StreetSegment(Node source, Node destination, int weight) {
        super(source, destination, weight);
    }

    public int getWeight() {
        return super.getWeight();
    }

    @Override
    public String toString() {
        return "StreetSegment [" + getSource() + " -> " + getDestination() + ", weight=" + getWeight() + "]";
    }
}