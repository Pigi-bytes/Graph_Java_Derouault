import java.util.Objects;

public class Node {
    private String label;

    public Node(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String toString() {
        return label;
    }

    public boolean equals(Object otherNode) {
        if (this == otherNode) return true; 
        if (otherNode == null) return false;
        Node node = (Node) otherNode;
        return Objects.equals(label, node.label);
    }

    public int hashCode() {
        return Objects.hash(label);
    }
}

