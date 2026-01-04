import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Graph {
    protected final Set<Node> nodes = new HashSet<>();
    protected final Set<Edge> edges = new HashSet<>();

    /**
     * Constructeur protégé pour les sous-classes
     */
    protected Graph() {}

    /**
     * Ajoute un noeud au graphe
     *
     * @param node le noeud à ajouter
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    public abstract void addEdge(Node source, Node destination, int weight);

    /**
     * Ajoute une arête non pondérée (poids = 1)
     *
     * @param source      noeud source
     * @param destination noeud destination
     */
    public void addEdge(Node source, Node destination) {
        addEdge(source, destination, 1);
    }

    /**
     * Supprime un noeud et toutes les arêtes connecté
     *
     * @param node noeud à supprimer
     */
    public void removeNode(Node node) {
        if (!nodes.contains(node)) {
            return;
        }

        Set<Edge> edgesToRemove = new HashSet<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) || edge.getDestination().equals(node)) {
                edgesToRemove.add(edge);
            }
        }

        edges.removeAll(edgesToRemove);

        nodes.remove(node);
    }

    /**
     * Supprime un noeud identifier par son label et toutes les arêtes connecté
     *
     * @param node noeud à supprimer
     */
    public void removeNode(String label) {
        for (Node node : this.nodes) {
            if (node.getLabel().equals(label)) {
                removeNode(node);
                return;
            }
        }
    }

    /**
     * Supprime l'arête (source vers destination)
     * Pour un graphe non orienté, la reciproce est également supprimée
     *
     * @param source      noeud source
     * @param destination noeud destination
     */
    public abstract void removeEdge(Node source, Node destination);

    /**
     * Supprime l'arête identifier par les labels de (source vers destination)
     * Pour un graphe non orienté, la reciproce est également supprimée
     *
     * @param source      noeud source
     * @param destination noeud destination
     */
    public void removeEdge(String sourceLabel, String destinationLabel) {
        Node source = null;
        Node destination = null;

        for (Node node : nodes) {
            if (node.getLabel().equals(sourceLabel)) {
                source = node;
            }
            if (node.getLabel().equals(destinationLabel)) {
                destination = node;
            }
        }

        if (source != null && destination != null) {
            removeEdge(source, destination);
        }
    }

    /**
     * Retourne une représentation en liste d'adjacence
     * 
     * Format :
     * source: destination1(poids) destination2(poids)
     *
     * @return string représentant la liste d'adjacence
     */
    public String toAdjacencyListString() {
        String chaine = "";

        for (Node node : this.nodes) {
            chaine += node.getLabel() + ": ";

            for (Edge edge : this.edges) {
                if (edge.getSource().equals(node)) {
                    chaine += edge.getDestination() + "(" + edge.getWeight() + ") ";
                }
            }
            chaine += "\n";
        }
        return chaine;
    }

    /**
     * Retourne la liste d'arêtes
     * 
     * Format :
     * source destination poids
     *
     * Les noeud isolés sont listés seuls
     *
     * @return string représentant la liste d'arêtes
     */
    public String toEdgeListString() {
        String chaine = "";
        Set<Node> nodesInEdges = new HashSet<>();

        for (Edge edge : this.edges) {
            Node source = edge.getSource();
            Node destination = edge.getDestination();

            nodesInEdges.add(source);
            nodesInEdges.add(destination);

            chaine += source + " " + destination + " " + edge.getWeight() + "\n";
        }

        for (Node node : this.nodes) {
            if (!nodesInEdges.contains(node)) {
                chaine += node + "\n";
            }
        }
        return chaine;
    }

    /**
     * Parcours en profondeur (DFS) récursif à partir d'un noeud
     *
     * @param start noeud de départ (doit appartenir au graphe)
     * @return liste des noeud visités dans l'ordre du parcours
     */
    public List<Node> dfs(Node start) {
        List<Node> result = new ArrayList<>();

        for (Node n : nodes) {
            if (n.equals(start)) {
                start = n;
            }
        }

        Set<Node> visited = new HashSet<>();
        dfsVisit(start, visited, result);
        return result;
    }

    /**
     * Méthode auxiliaire récursive pour DFS
     *
     * @param u       noeud courant
     * @param visited ensemble des noeud déjà visités
     * @param result  liste des noeud visités
     */
    private void dfsVisit(Node u, Set<Node> visited, List<Node> result) {
        visited.add(u);
        result.add(u);

        for (Node v : getNeighbors(u)) {
            if (!visited.contains(v)) {
                dfsVisit(v, visited, result);
            }
        }
    }

    /**
     * Calcule le degré du noeud
     *
     * @param node noeud ciblé
     * @return degré du noeud
     */
    public abstract int degree(Node node);

    /**
     * @return true si le graphe est orienté
     */
    public abstract boolean isOriente();

    /**
     * Récupère la liste des voisins d'un noeud.
     *
     * @param node le noeud dont on cherche les voisins
     * @return liste des noeuds voisins
     */
    public abstract List<Node> getNeighbors(Node node);

    /**
     * Représentation texte du graphe
     *
     * @return string descriptive
     */
    @Override
    public String toString() {
        return "Graph [nodes=" + nodes + ", edges=" + edges + "] + orientation ;" + isOriente();
    }

}