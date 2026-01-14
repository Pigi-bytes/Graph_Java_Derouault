import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implémente l'algorithme de Dijkstra pour le graphe
 */
public class DijkstraAlgorithm {
    /**
     * Trouve le plus court chemin entre deux noeuds dans un graphe
     * 
     * @param graph Graphe à parcourir
     * @param start Noeud de départ
     * @param end   Noeud d'arrivée
     * @return Un objet chemin contenant la liste des noeuds et le coût total
     * @throws NetworkNotConnectedException Si aucun chemin n'existe entre start et
     *                                      end
     */
    public chemin findShortestPath(Graph graph, Node start, Node end) throws NetworkNotConnectedException {
        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Node> precedent = new HashMap<>();
        List<Node> aVisiter = new ArrayList<>();

        for (Node n : graph.nodes) {
            distance.put(n, Integer.MAX_VALUE);
            aVisiter.add(n);
        }
        distance.put(start, 0);

        while (!aVisiter.isEmpty()) {
            Node courant = NodeLePlusProche(aVisiter, distance);

            if (courant == null || distance.get(courant) == Integer.MAX_VALUE) {
                break;
            }
            if (courant.equals(end)) {
                break;
            }

            aVisiter.remove(courant);

            for (Node voisin : graph.getNeighbors(courant)) {
                if (!aVisiter.contains(voisin)) {
                    continue;
                }
                Edge route = graph.getEdge(courant, voisin);
                if (route != null) {
                    int nouvelleDistance = distance.get(courant) + route.getWeight();
                    if (nouvelleDistance < distance.get(voisin)) {
                        distance.put(voisin, nouvelleDistance);
                        precedent.put(voisin, courant);
                    }
                }
            }
        }

        int endDistance = distance.getOrDefault(end, Integer.MAX_VALUE);
        if (endDistance == Integer.MAX_VALUE) {
            throw new NetworkNotConnectedException("Impossible d'atteindre la destination.");
        }

        List<Node> chemin = new ArrayList<>();
        Node courant = end;
        while (courant != null) {
            chemin.add(0, courant);
            courant = precedent.get(courant);
        }

        return new chemin(endDistance, chemin);
    }

    private Node NodeLePlusProche(List<Node> nodes, Map<Node, Integer> distances) {
        Node nodeLePlusProche = null;
        int distanceLaPlusCourte = Integer.MAX_VALUE;

        for (Node node : nodes) {
            int dist = distances.get(node);
            if (dist < distanceLaPlusCourte) {
                distanceLaPlusCourte = dist;
                nodeLePlusProche = node;
            }
        }
        return nodeLePlusProche;
    }

}