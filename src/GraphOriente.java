public class GraphOriente extends Graph {
    public void addEdge(Node source, Node destination, int weight) {
        Edge newEdge = new Edge(source, destination, weight);
        if (edges.contains(newEdge)) {
            return;
        }

        addNode(source);
        addNode(destination);
        edges.add(newEdge);
    }

    public void removeEdge(Node source, Node destination) {
        Edge edgeToRemove = null;
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getDestination().equals(destination)) {
                edgeToRemove = edge;
                break;
            }
        }

        if (edgeToRemove != null) {
            edges.remove(edgeToRemove);
        }
    }

    public boolean isOriente() {
        return true;
    }

    /**
     * Calcule le degré sortant (nombre d'arêtes sortantes) du noeud
     *
     * @param node noeud ciblé
     * @return nombre d'arêtes ayant node comme source
     */
    public int outDegree(Node node) {
        int count = 0;
        for (Edge e : edges) {
            if (node.equals(e.getSource()))
                count++;
        }
        return count;
    }

    /**
     * Calcule le degré entrant (nombre d'arêtes entrantes) du noeud
     *
     * @param node noeud ciblé
     * @return nombre d'arêtes ayant node comme destination
     */
    public int inDegree(Node node) {
        int count = 0;
        for (Edge e : edges) {
            if (node.equals(e.getDestination()))
                count++;
        }
        return count;
    }

    public int degree(Node node) {
        return inDegree(node) + outDegree(node);
    }
}