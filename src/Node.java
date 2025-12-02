import java.util.Objects;

public class Node {
    private final String label;

    public Node(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public boolean equals(Object otherNode) {
        if (this == otherNode) return true; 
        if (otherNode == null || getClass() != otherNode.getClass()) return false;
        Node node = (Node) otherNode;
        return Objects.equals(label, node.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}

