public class App {
    public static void main(String[] args) {
        Graph city = new GraphNonOriente();

        Depot depot = new Depot("Central");
        Restaurant pizza = new Restaurant("Luigi's", 15);
        Restaurant sushi = new Restaurant("Sakura", 10);
        Client client1 = new Client("Mme. Dupont", "12 rue de la Paix");
        Client client2 = new Client("M. Martin", "5 avenue Victor Hugo");
        Client client3 = new Client("Mme. Lopez", "3 place Bellecour");
        LocationNode carrefour1 = new LocationNode("Carrefour Nord") {};
        LocationNode carrefour2 = new LocationNode("Carrefour Sud") {};

        city.addNode(depot);
        city.addNode(pizza);
        city.addNode(sushi);
        city.addNode(client1);
        city.addNode(client2);
        city.addNode(client3);
        city.addNode(carrefour1);
        city.addNode(carrefour2);

        city.addEdge(depot, carrefour1, 4);
        city.addEdge(depot, carrefour2, 6);
        city.addEdge(carrefour1, pizza, 3);
        city.addEdge(carrefour1, sushi, 5);
        city.addEdge(carrefour2, sushi, 2);
        city.addEdge(carrefour2, client2, 4);
        city.addEdge(pizza, client1, 7);
        city.addEdge(sushi, client3, 8);
        city.addEdge(carrefour1, client2, 6);
        city.addEdge(carrefour2, client3, 3);

        DeliveryManager manager = new DeliveryManager(city);

        manager.planifierLivraison(depot, pizza, client1);
        manager.planifierLivraison(depot, sushi, client3);
        manager.planifierLivraison(depot, sushi, client2);
    }
}