public class DeliveryManager {
    private final Graph grapheVille;
    private final DijkstraAlgorithm algo;

    public DeliveryManager(Graph graphe) {
        this.grapheVille = graphe;
        this.algo = new DijkstraAlgorithm();
    }

    /**
     * Planifie la livraison d'un repas du dépôt au client via le restaurant
     * Affiche les trajets et les temps estimés
     * 
     * @param depart dépôt de départ
     * @param resto  restaurant de préparation
     * @param client client destinataire
     */
    public void planifierLivraison(Depot depart, Restaurant resto, Client client) {
        try {
            chemin trajet1 = algo.findShortestPath(grapheVille, depart, resto);
            int cout1 = trajet1.cout;
            java.util.List<Node> chemin1 = trajet1.chemin;

            System.out.println("Vers le restaurant (" + cout1 + " min)");
            afficherChemin(chemin1);

            chemin trajet2 = algo.findShortestPath(grapheVille, resto, client);
            int cout2 = trajet2.cout;
            java.util.List<Node> chemin2 = trajet2.chemin;

            System.out.println("Vers le client (" + cout2 + " min)");
            afficherChemin(chemin2);

            System.out.println("");
            System.out.println("Trajet total       : " + (cout1 + cout2) + " min");
            System.out.println("Préparation repas  : " + resto.getPreparation() + " min");
            System.out.println("TEMPS TOTAL ESTIMÉ : " + (cout1 + resto.getPreparation() + cout2) + " min");
        } catch (NetworkNotConnectedException e) {
            System.out.println("ERREUR : " + e.getMessage());
        }
    }

    /**
     * Affiche le chemin sous forme de labels séparés par des flèches
     * 
     * @param chemin liste ordonnée des noeuds à afficher
     */
    private void afficherChemin(java.util.List<Node> chemin) {
        for (int i = 0; i < chemin.size(); i++) {
            System.out.print(chemin.get(i).getLabel());
            if (i < chemin.size() - 1)
                System.out.print(" -> ");
        }
        System.out.println();
    }
}