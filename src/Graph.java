import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe abstraite représentant un graphe
 */
public abstract class Graph implements Serializable, Cloneable {
    /**
     * Ensemble des noeuds du graphe
     */
    protected final Set<Node> nodes = new HashSet<>();
    /**
     * Ensemble des arêtes du graphe
     */
    protected final Set<Edge> edges = new HashSet<>();

    /**
     * Constructeur protégé pour les sous-classes
     */
    protected Graph() {
    }

    /**
     * Ajoute un noeud au graphe
     *
     * @param node le noeud à ajouter
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * Ajoute une arête pondérée entre deux noeuds.
     * @param source noeud source
     * @param destination noeud destination
     * @param weight poids de l'arête
     */
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
     * @param label noeud à supprimer
     */
    public void removeNode(String label) {
        Node node = getNode(label);
        if (node != null) {
            removeNode(node);
        }
    }

    /**
     * Supprime l'arête (source vers destination)
     * Pour un graphe non orienté, la reciproce est également supprimée
     *
     * @param source noeud source
     * @param destination noeud destination
     */
    public abstract void removeEdge(Node source, Node destination);

    /**
     * Supprime l'arête identifier par les labels de (source vers destination)
     * Pour un graphe non orienté, la reciproce est également supprimée
     *
     * @param sourceLabel noeud source
     * @param destinationLabel noeud destination
     */
    public void removeEdge(String sourceLabel, String destinationLabel) {
        Node source = getNode(sourceLabel);
        Node destination = getNode(destinationLabel);

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
     * Récupère un noeud à partir de son label.
     *
     * @param label le label du noeud recherché
     * @return le noeud correspondant ou null s'il n'existe pas
     */
    public Node getNode(String label) {
        for (Node node : nodes) {
            if (node.getLabel().equals(label)) {
                return node;
            }
        }
        throw new NodeNotFoundException("Node with label '" + label + "' not found.");
    }

    /**
     * Calcule le degré du noeud
     *
     * @param node noeud ciblé
     * @return degré du noeud
     */
    public abstract int degree(Node node);

    /**
     * Indique si le graphe est orienté.
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
     * Récupère l'arête connectant deux noeuds
     * 
     * @param source      noeud de départ
     * @param destination noeud d'arrivée
     * @return l'objet Edge correspondant ou null
     */
    public abstract Edge getEdge(Node source, Node destination);

    /**
     * Représentation texte du graphe
     *
     * @return string descriptive
     */
    @Override
    public String toString() {
        return "Graph [nodes=" + nodes + ", edges=" + edges + "] + orientation ;" + isOriente();
    }

    /**
    * Clone profond du graphe
    * @return une copie indépendante du graphe
    */
    @Override
    public Graph clone() throws CloneNotSupportedException {
        Graph cloned = (Graph) super.clone();
        cloned.nodes.clear();
        cloned.edges.clear();

        java.util.Map<Node, Node> nodeMap = new java.util.HashMap<>();
        for (Node node : this.nodes) {
            Node clonedNode = node.clone();
            nodeMap.put(node, clonedNode);
            cloned.addNode(clonedNode);
        }

        // Deep clone des arêtes
        for (Edge edge : this.edges) {
            Node clonedSource = nodeMap.get(edge.getSource());
            Node clonedDestination = nodeMap.get(edge.getDestination());
            cloned.addEdge(clonedSource, clonedDestination, edge.getWeight());
        }
        return cloned;
    }

    /**
     * Sauvegarde le graphe dans un fichier (sérialisation)
     * @param filename nom du fichier
     * @throws IOException en cas d'erreur d'écriture
     */
    public void saveGraph(String filename) throws IOException {
        try (ObjectOutputStream graph = new ObjectOutputStream(new FileOutputStream(filename))) {
            graph.writeObject(this);
        }
    }

    /**
     * Charge un graphe depuis un fichier (sérialisation)
     * @param filename nom du fichier
     * @return le graphe chargé
     * @throws IOException en cas d'erreur de lecture
     * @throws ClassNotFoundException si la classe n'est pas trouvée
     */
    public static Graph loadGraph(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream graph = new ObjectInputStream(new FileInputStream(filename))) {
            return (Graph) graph.readObject();
        }
    }

}