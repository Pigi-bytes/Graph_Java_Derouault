import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GraphFile {

    public enum format {
        EDGE,
        ADJACENCY
    }

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

        Graph graph = new Graph(estOriente);

        List<String> dataLines = lines.subList(1, lines.size());
        if (FormatType == format.EDGE) {
            parseEdgeList(graph, dataLines);
        } else {
            parseAdjacencyList(graph, dataLines);
        }
        
        return graph;
    }

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

    private static void parseAdjacencyList(Graph graph, List<String> lines) {
        for (String line : lines) {

            // 1. Diviser en deux : "SOURCE : " et "DEST1(W1) DEST2(W2)..."
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


    public static void exportGraph(Graph graph, String filePath, format formatType) throws IOException {
        int orientationFlag = graph.getEstOriente() ? 1 : 0;

        String file = formatType.name() + " " + orientationFlag + "\n";
    
        if (formatType == format.EDGE) {
            file += graph.toEdgeListString();
        } else {
            file += graph.toAdjacencyListString();
        }

        Files.write(Paths.get(filePath), file.getBytes());    
    }
}