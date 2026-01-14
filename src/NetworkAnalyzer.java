import java.util.*;

public class NetworkAnalyzer {
    private final Graph graph;

    public NetworkAnalyzer(Graph graph) {
        this.graph = graph;
    }

    /**
     * Vérifie si le graphe est connexe (DFS)
     * 
     * @return true si connexe, false sinon
     */
    public boolean estConnexe() {
        Node start = graph.nodes.iterator().next();
        List<Node> visited = graph.dfs(start);
        return visited.size() == graph.nodes.size();
    }

    /**
     * Détecte les ponts du graphe (arêtes critiques)
     * 
     * @return liste des arêtes qui sont des ponts
     */
    public List<Edge> trouverLesPonts() {
        List<Edge> bridges = new ArrayList<>();
        List<Edge> edgesCopy = new ArrayList<>(graph.edges);

        for (Edge edge : edgesCopy) {
            graph.edges.remove(edge); // On retire l'arête

            if (!estConnexe()) {
                bridges.add(edge);
            }

            graph.edges.add(edge); // On la remet
        }
        return bridges;
    }
}