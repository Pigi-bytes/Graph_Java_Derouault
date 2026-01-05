import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DijkstraAlgorithm {

public Map<String, Object> findShortestPath(Graph graph, Node start, Node end) {

    Map<Node, Integer> distance = new HashMap<>();
    Map<Node, Node> precedent = new HashMap<>();
    List<Node> aVisiter = new ArrayList<>();

    // Tous les noeuds sont à l'infini sauf le départ
    for (Node n : graph.nodes) {
        distance.put(n, Integer.MAX_VALUE);
        aVisiter.add(n);
    }
    distance.put(start, 0);

    // Tant qu'il reste des noeuds à visiter
    while (!aVisiter.isEmpty()) {
        // On cherche le noeud le plus proche parmi ceux à visiter
        Node courant = NodeLePlusProche(aVisiter, distance);

        // Si on ne peut plus avancer ou qu'on est arrivé, on arrête
        if (courant == null || distance.get(courant) == Integer.MAX_VALUE) {
            break;
        }
        if (courant.equals(end)) {
            break;
        }

        aVisiter.remove(courant);

        // On regarde les voisins du noeud courant
        for (Node voisin : graph.getNeighbors(courant)) {

            if (!aVisiter.contains(voisin)) {
                continue;
            }

            Edge route = graph.getEdge(courant, voisin);

            if (route != null) {
                int nouvelleDistance = distance.get(courant) + route.getWeight();
                // Si on trouve un chemin plus court, on met à jour
                if (nouvelleDistance < distance.get(voisin)) {
                    distance.put(voisin, nouvelleDistance);
                    precedent.put(voisin, courant);
                }
            }
        }
    }

    // On reconstruit le chemin en partant de la fin
    List<Node> chemin = new ArrayList<>();
    if (distance.get(end) != Integer.MAX_VALUE) {
        Node courant = end;
        while (courant != null) {
            chemin.add(0, courant);
            courant = precedent.get(courant);
        }
    }

    Map<String, Object> resultat = new HashMap<>();
    resultat.put("chemin", chemin);

    // Si le chemin est pas possible on renvoie -1.
    int endDistance = distance.getOrDefault(end, Integer.MAX_VALUE);
    resultat.put("cout", endDistance == Integer.MAX_VALUE ? -1 : endDistance);

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