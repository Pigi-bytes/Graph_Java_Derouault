import java.io.IOException;
import java.util.Scanner;

/**
 * Classe principale de l'application de gestion de graphes de livraison.
 */
public class App {
    /**
     * Constructeur privé pour empêcher l'instanciation de la classe utilitaire.
     */
    private App() {
        // Constructeur privé
    }

    /**
     * Point d'entrée du programme.
     *
     * @param args Arguments de la ligne de commande (non utilisés)
     * @throws IOException            Si une erreur d'entrée/sortie survient lors du
     *                                chargement ou de la sauvegarde du graphe
     * @throws ClassNotFoundException Si la classe du graphe sérialisé n'est pas
     *                                trouvée
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Voulez-vous (s)auvegarder ou (c)harger le graphe ? [s/c] : ");
        String choix = scanner.nextLine().trim().toLowerCase();
        Graph city;

        if (choix.equals("c")) {
            city = Graph.loadGraph("city.ser");
            System.out.println("Graphe chargé :\n" + city.toAdjacencyListString());
        } else {
            city = new GraphNonOriente();

            Depot depot = new Depot("DepotA");
            Restaurant resto1 = new Restaurant("Resto1", 12);
            Restaurant resto2 = new Restaurant("Resto2", 8);

            Client clientA = new Client("ClientA", "1 rue A");
            Client clientB = new Client("ClientB", "2 rue B");
            Client clientC = new Client("ClientC", "3 rue C");
            Client clientD = new Client("ClientD", "4 rue D");

            LocationNode carrefour1 = new Carrefour("Carrefour1");
            LocationNode carrefour2 = new Carrefour("Carrefour2");

            city.addNode(depot);
            city.addNode(resto1);
            city.addNode(resto2);
            city.addNode(clientA);
            city.addNode(clientB);
            city.addNode(clientC);
            city.addNode(clientD);
            city.addNode(carrefour1);
            city.addNode(carrefour2);
            city.addEdge(depot, carrefour1, 5);
            city.addEdge(carrefour1, resto1, 3);
            city.addEdge(carrefour1, clientA, 4);
            city.addEdge(carrefour1, carrefour2, 2);
            city.addEdge(carrefour2, resto2, 2);
            city.addEdge(carrefour2, clientB, 3);
            city.addEdge(resto1, clientC, 6);
            city.addEdge(resto2, clientD, 7);
            city.addEdge(clientA, clientB, 5);

            System.out.println("Graphe créé :\n" + city.toAdjacencyListString());
            city.saveGraph("city.ser");
            System.out.println("Graphe sauvegardé dans city.ser");

        }

        Depot depotUsed = (Depot) city.getNode("DepotA");
        Restaurant resto1Used = (Restaurant) city.getNode("Resto1");
        Restaurant resto2Used = (Restaurant) city.getNode("Resto2");
        Client clientAUsed = (Client) city.getNode("ClientA");
        Client clientBUsed = (Client) city.getNode("ClientB");
        Client clientCUsed = (Client) city.getNode("ClientC");
        Client clientDUsed = (Client) city.getNode("ClientD");
        DeliveryManager manager = new DeliveryManager(city);

        manager.planifierLivraison(depotUsed, resto1Used, clientAUsed);

        System.out.println("");
        manager.planifierLivraison(depotUsed, resto2Used, clientBUsed);

        System.out.println("");
        manager.planifierLivraison(depotUsed, resto1Used, clientCUsed);

        System.out.println("");
        manager.planifierLivraison(depotUsed, resto2Used, clientDUsed);

        System.out.println("");
        NetworkAnalyzer analyzer = new NetworkAnalyzer(city);
        System.out.println("Le graphe est connexe ? " + analyzer.estConnexe());
        System.out.println("Ponts du graphe : " + analyzer.trouverLesPonts());

        System.out.println("");

        try {
            city.getNode("jlb lfjklndfljknkl");
        } catch (NodeNotFoundException e) {
            System.out.println("Test NodeNotFoundException : " + e.getMessage());
        }

        Client clientPerdu = new Client("clientPerdu", "5 rue E");
        city.addNode(clientPerdu);

        System.out.println("\nTest NetworkNotConnectedException :");
        manager.planifierLivraison(depotUsed, resto1Used, clientPerdu);
    }
}
