import java.util.Map;

public class DeliveryManager {
    private final Graph grapheVille;
    private final DijkstraAlgorithm algo;

    public DeliveryManager(Graph graphe) {
        this.grapheVille = graphe;
        this.algo = new DijkstraAlgorithm();
    }


    public void planifierLivraison(Depot depart, Restaurant resto, Client client) {
        Map<String, Object> trajet1 = algo.findShortestPath(grapheVille, depart, resto);
        int cout1 = (int) trajet1.get("cout");
        java.util.List<Node> chemin1 = (java.util.List<Node>) trajet1.get("chemin");

        if (cout1 == -1) {
            System.out.println("ERREUR : Impossible de rejoindre le restaurant depuis le dépôt");
            return;
        }

        System.out.println("Vers le restaurant (" + cout1 + " min)");
        afficherChemin(chemin1);

        Map<String, Object> trajet2 = algo.findShortestPath(grapheVille, resto, client);
        int cout2 = (int) trajet2.get("cout");
        java.util.List<Node> chemin2 = (java.util.List<Node>) trajet2.get("chemin");
        
        if (cout2 == -1) {
            System.out.println("ERREUR : Impossible de rejoindre le client depuis le restaurant.");
            return;
        }

        System.out.println("Vers le client (" + cout2 + " min)");
        afficherChemin(chemin2);

        System.out.println("");
        System.out.println("Trajet total       : " + (cout1 + cout2) + " min");
        System.out.println("Préparation repas  : " + resto.getPreparation() + " min");
        System.out.println("TEMPS TOTAL ESTIMÉ : " + (cout1 + resto.getPreparation() + cout2) + " min");

    }

    private void afficherChemin(java.util.List<Node> chemin) {
        for (int i = 0; i < chemin.size(); i++) {
            System.out.print(chemin.get(i).getLabel());
            if (i < chemin.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
}