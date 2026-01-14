import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DijkstraAlgorithm {

    public Map<String, Object> findShortestPath(Graph graph, Node start, Node end) throws NetworkNotConnectedException {
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

        Map<String, Object> resultat = new HashMap<>();
        resultat.put("cout", endDistance);

        List<Node> chemin = new ArrayList<>();
        Node courant = end;
        while (courant != null) {
            chemin.add(0, courant);
            courant = precedent.get(courant);
        }
        resultat.put("chemin", chemin);

        return resultat;
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