public class App {
    public static void main(String[] args) throws Exception {
        Node nA = new Node("A");
        Node nB = new Node("B");
        Node nC = new Node("C");
        Node nD = new Node("D");
        Node nE = new Node("E");
        Node nF = new Node("F");
        Node nZ = new Node("Z");

        Graph complexGraph = new GraphOriente();

        complexGraph.addNode(nA);
        complexGraph.addNode(nB);
        complexGraph.addNode(nC);
        complexGraph.addNode(nD);
        complexGraph.addNode(nE);
        complexGraph.addNode(nF);
        complexGraph.addNode(nZ);

        complexGraph.addEdge(nA, nB, 10);
        complexGraph.addEdge(nB, nC, 3);
        complexGraph.addEdge(nC, nA, 7);

        complexGraph.addEdge(nE, nF, 2);
        complexGraph.addEdge(nF, nE, 4); 

        complexGraph.addEdge(nC, nD, 50);
        complexGraph.addEdge(nD, nF, 1);

        complexGraph.addEdge(nB, nE, 15);
        complexGraph.addEdge(nF, nD, 20);

        System.out.println(complexGraph);

        GraphFile.exportGraph(complexGraph, "edge_export.txt", GraphFile.format.EDGE);
        GraphFile.exportGraph(complexGraph, "adj_export.txt", GraphFile.format.ADJACENCY);

        Graph importAdj = GraphFile.importGraph("adj_export.txt");
        System.out.println(importAdj.toAdjacencyListString());

        Graph importEdge = GraphFile.importGraph("edge_export.txt");
        System.out.println(importEdge);
        System.out.println(importAdj);

        System.out.println(importEdge.equals(importAdj));
        System.out.println(importEdge.equals(complexGraph));

        
    }
}