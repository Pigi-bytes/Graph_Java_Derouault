import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Utilitaires pour l'import/export de graphes depuis/vers des fichiers
 */
public class GraphFile {
    /**
     * Constructeur privé pour empêcher l'instanciation de la classe utilitaire.
     */
    private GraphFile() {
        // Constructeur privé
    }

    /**
     * Types de format supportés pour l'import/export, utilisé pour les flags
     */
    /**
     * Types de format supportés pour l'import/export.
     */
    public enum format {
        /** Format liste d'arêtes. */
        EDGE,
        /** Format liste d'adjacence. */
        ADJACENCY
    }

    /**
     * Importe un graphe depuis un fichier
     *
     * Première ligne attendue : "EDGE [ORIENTE]" ou "ADJACENCY [ORIENTE]" avec
     * [ORIENTE] = 1 ou 0
     *
     * @param filePath chemin du fichier à lire
     * @return un Graph construit à partir du contenu du fichier
     * @throws IOException si le fichier ne peut pas être lu ou si le flag
     *                     d'orientation est invalide
     */
    public static Graph importGraph(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        String configLine = lines.get(0).trim().toUpperCase();
        String[] config = configLine.split(" ");

        format FormatType = format.valueOf(config[0]);

        boolean estOriente;
        int orientationInt = Integer.parseInt(config[1]);
        switch (orientationInt) {
            case 1 -> estOriente = true;
            case 0 -> estOriente = false;
            default -> throw new IOException("Format orienté doit etre 1 ou 0");
        }

        Graph graph = estOriente ? new GraphOriente() : new GraphNonOriente();

        List<String> dataLines = lines.subList(1, lines.size());
        if (FormatType == format.EDGE) {
            parseEdgeList(graph, dataLines);
        } else {
            parseAdjacencyList(graph, dataLines);
        }

        return graph;
    }

    /**
     * Parse une liste d'arêtes
     *
     * Chaque ligne :
     * - "LABEL" : ajoute un noeud isolé
     * - "SOURCE DESTINATION WEIGHT" : ajoute une arête de SOURCE vers DESTINATION
     * avec le poids WEIGHT
     *
     * @param graph objet Graph à remplir
     * @param lines lignes du fichier correspondant au format EDGE
     */
    private static void parseEdgeList(Graph graph, List<String> lines) {
        for (String line : lines) {
            // Séparer les éléments : "A B 5" -> ["A", "B", "5"]
            String[] parts = line.split(" ");

            if (parts.length == 1) {
                graph.addNode(new Node(parts[0]));
                continue;
            }

            Node source = new Node(parts[0]);
            Node destination = new Node(parts[1]);
            int weight = Integer.parseInt(parts[2]);

            graph.addEdge(source, destination, weight);
        }
    }

    /**
     * Parse une liste d'adjacence
     *
     * Chaque ligne :
     * - "SOURCE: DESTINATION1(WEIGHT1) DESTINATION2(WEIGHT2) ..."
     * - "SOURCE:" si aucun voisin
     *
     * @param graph objet Graph à remplir
     * @param lines lignes du fichier correspondant au format ADJACENCY
     */
    private static void parseAdjacencyList(Graph graph, List<String> lines) {
        for (String line : lines) {

            // Diviser en deux : "SOURCE : " et "DESTINATION1(W1) DESTINATION2(W2)..."
            String[] parts = line.split(":", 2);

            Node source = new Node(parts[0]);
            String edgeData = parts[1].trim();

            if (edgeData.isEmpty()) {
                graph.addNode(source);
                continue;
            }

            String[] edges = edgeData.split(" ");

            for (String edge : edges) {
                int open = edge.indexOf('(');
                int close = edge.indexOf(')');

                int weight = Integer.parseInt(edge.substring(open + 1, close));

                Node destination = new Node(edge.substring(0, open));

                graph.addEdge(source, destination, weight);

            }
        }
    }

    /**
     * Exporte un Graph vers un fichier
     *
     * Écrit d'abord la ligne de configuration "[FORMAT] [ORIENTE]" puis
     * le contenu selon le format choisi
     * 
     * @param graph      graphe à exporter
     * @param filePath   chemin du fichier de sortie
     * @param formatType format d'export (EDGE ou ADJACENCY)
     * @throws IOException si l'écriture échoue
     */
    public static void exportGraph(Graph graph, String filePath, format formatType) throws IOException {
        int orientationFlag = graph.isOriente() ? 1 : 0;

        String file = formatType.name() + " " + orientationFlag + "\n";

        if (formatType == format.EDGE) {
            file += graph.toEdgeListString();
        } else {
            file += graph.toAdjacencyListString();
        }

        Files.write(Paths.get(filePath), file.getBytes());
    }
}