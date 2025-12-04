import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Graph {
    private final Set<Node> nodes = new HashSet<>();
    private final Set<Edge> edges = new HashSet<>();
    private final boolean estOriente;

    /**
     * Construit un graphe
     *
     * @param estOriente true si le graphe est orienté, false sinon
     */
    public Graph(boolean estOriente) {
        this.estOriente = estOriente;
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
     * Ajoute une arête pondérée entre source et destination
     * Pour un graphe non orienté, l'arête reciproce est aussi ajoutée
     *
     * @param source noeud source
     * @param destination noeud destination
     * @param weight poids de l'arête
     */
    public void addEdge(Node source, Node destination, int weight) {
        Edge newEdge = new Edge(source, destination, weight);

        if (source.equals(destination)) {
            return;
        }

        if (edges.contains(newEdge)) {
            return;
        }

        addNode(source);
        addNode(destination);

        edges.add(newEdge);

        if (!estOriente) {
            edges.add(new Edge(destination, source, weight));
        }
    }

    /** 
     * Ajoute une arête non pondérée (poids = 1)
     *
     * @param source noeud source
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
        for (Node node : nodes) {
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
     * @param source noeud source
     * @param destination noeud destination
     */
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
            
            if (!estOriente) {
                Edge reverseEdge = null;
                for (Edge edge : edges) {
                    if (edge.getSource().equals(destination) && edge.getDestination().equals(source)) {
                        reverseEdge = edge;
                        break;
                    }
                }
                if (reverseEdge != null) {
                    edges.remove(reverseEdge);
                }
            }
        }
    }

    /** 
     * Supprime l'arête identifier par les labels de (source vers destination)
     * Pour un graphe non orienté, la reciproce est également supprimée
     *
     * @param source noeud source
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
                    chaine +=  edge.getDestination() + "(" + edge.getWeight() + ") ";
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
                start =  n;
            }
        }

        Set<Node> visited = new HashSet<>();
        dfsVisit(start, visited, result);
        return result;
    }

    /** 
     * Méthode auxiliaire récursive pour DFS
     *
     * @param u noeud courant
     * @param visited ensemble des noeud déjà visités
     * @param result liste des noeud visités
     */
    private void dfsVisit(Node u, Set<Node> visited, List<Node> result) {
        visited.add(u);
        result.add(u);

        for (Edge e : edges) {
            if (e.getSource().equals(u)) {
                Node v = e.getDestination();
                if (!visited.contains(v)) {
                    dfsVisit(v, visited, result);
                }
            }
        }
    }


    /** 
     * Calcule le degré sortant (nombre d'arêtes sortantes) du noeud
     *
     * @param node noeud ciblé
     * @return nombre d'arêtes ayant node comme source
     */
    public int outDegree(Node node) {
        for (Node n : nodes) {
            if (n.equals(node)) {
                node = n;
            }
        }

        int count = 0;
        for (Edge e : edges) {
            if (e.getSource().equals(node)) {
                count++;
            }
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
        for (Node n : nodes) {
            if (n.equals(node)) {
                node = n;
            }
        }

        int count = 0;
        for (Edge e : edges) {
            if (e.getDestination().equals(node)) {
                count++;
            }
        }
        
        return count;
    }

    /** 
     * Calcule le degré du noeud
     *
     * @param node noeud ciblé
     * @return degré du noeud
     */
    public int degree(Node node) {
        if (!estOriente) {
            // pour graphe non orienté, outDegree donne le degré (car on stocke les deux directions)
            return outDegree(node);
        } else {
            return inDegree(node) + outDegree(node);
        }
    }

    /** 
     * @return true si le graphe est orienté
     */
    public boolean getEstOriente() {
        return estOriente;
    }

    /** 
     * @return code de hachage basé sur les noeuds, arêtes et l'orientation
     */
    @Override
    public int hashCode() {
        return Objects.hash(nodes, edges, estOriente);
    }

    /** 
     * Compare deux graphes en vérifiant noeuds, arêtes et orientation.
     *
     * @param obj objet à comparer
     * @return true si obj est un Graph équivalent
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Graph other = (Graph) obj;
        
        return estOriente == other.estOriente &&
            Objects.equals(nodes, other.nodes) &&
            Objects.equals(edges, other.edges);
    }

    /** 
     * Représentation texte du graphe 
     *
     * @return string descriptive
     */
    @Override
    public String toString() {
        return "Graph [nodes=" + nodes + ", edges=" + edges + ", estOriente=" + estOriente + "]";
    }
    
}