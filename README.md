# Graph Java DEROUAULT

Ce projet a été réalisé dans le cadre des TPs de Programmation Orientée Objet (POO) en Java 
Il consiste à modéliser, implémenter et manipuler des graphes orientés ou non orientés

## Fonctionnalités
  - Création, modification et sauvegarde de graphes
  - Ajout/suppression de sommets et d’arêtes
  - Planification de livraisons (dépôts, restaurants, clients, carrefours)
  - Analyse de la connectivité et recherche de ponts dans le graphe
  - Sauvegarde et chargement des graphs, serialiser sous la forme d'une adjacency liste ou une edge Liste

## Prérequis

- Java (JDK 8 ou supérieur)

## Setup
```bash
git clone https://github.com/Pigi-bytes/Graph_Java_Derouault.git
cd Graph_Java_Derouault
```

## Compilation et exécution

Pour compiler :

```sh
javac -d bin src/*.java
```

Pour exécuter :

```sh
java -cp bin App
```

En une seule ligne :

```sh
javac -d bin src/*.java && java -cp bin App
```

## Diagramme de class interactif
Séparé en deux pour la lisibilité.

```mermaid
classDiagram

class Node {
    - String label
    + Node(String label)
    + getLabel() String
    + toString() String
    + equals(Object) boolean
    + hashCode() int
    + clone() Node
}
class LocationNode {
    + LocationNode(String label)
}
LocationNode --|> Node

class Depot {
    + Depot(String label)
    + toString() String
}
Depot --|> LocationNode

class Restaurant {
    - int preparation
    + Restaurant(String label, int prepTime)
    + getPreparation() int
    + toString() String
}
Restaurant --|> LocationNode

class Client {
    - String adress
    + Client(String label, String adress)
    + getAdress() String
    + toString() String
}
Client --|> LocationNode

class Carrefour {
    + Carrefour(String label)
}
Carrefour --|> LocationNode

class Edge {
    - Node source
    - Node destination
    - int weight
    + Edge(Node, Node, int)
    + toString() String
    + getSource() Node
    + getDestination() Node
    + getWeight() int
    + hashCode() int
    + equals(Object) boolean
    + clone(Node, Node) Edge
}
Edge "1" --> "1" Node : source
Edge "1" --> "1" Node : destination

class NodeNotFoundException {
    + NodeNotFoundException(String message)
}
NodeNotFoundException --|> RuntimeException

class NetworkNotConnectedException {
    + NetworkNotConnectedException(String message)
}
NetworkNotConnectedException --|> Exception


```

```mermaid
classDiagram

class Graph {
    Set~Node~ nodes
    Set~Edge~ edges
    + addNode(Node)
    + addEdge(Node, Node, int)
    + addEdge(Node, Node)
    + removeNode(Node)
    + removeNode(String)
    + removeEdge(Node, Node)
    + removeEdge(String, String)
    + toAdjacencyListString() String
    + toEdgeListString() String
    + dfs(Node) List~Node~
    + getNode(String) Node
    + degree(Node) int
    + isOriente() boolean
    + getNeighbors(Node) List~Node~
    + getEdge(Node, Node) Edge
    + toString() String
    + clone() Graph
    + saveGraph(String) void
    + static loadGraph(String) Graph
}
Graph "1" o-- "*" Node : nodes
Graph "1" o-- "*" Edge : edges

class GraphNonOriente {
    + GraphNonOriente()
    + addEdge(Node, Node, int)
    + removeEdge(Node, Node)
    + isOriente() boolean
    + degree(Node) int
    + getNeighbors(Node) List~Node~
    + getEdge(Node, Node) Edge
}
GraphNonOriente --|> Graph

class GraphOriente {
    + GraphOriente()
    + addEdge(Node, Node, int)
    + removeEdge(Node, Node)
    + isOriente() boolean
    + outDegree(Node) int
    + inDegree(Node) int
    + getNeighbors(Node) List~Node~
    + degree(Node) int
    + getEdge(Node, Node) Edge
}
GraphOriente --|> Graph

class GraphFile {
    + static importGraph(String) Graph
    + static exportGraph(Graph, String, format)
}
class format {
    <<enum>>
    EDGE
    ADJACENCY
}
GraphFile ..> format : utilise

class DijkstraAlgorithm {
    + findShortestPath(Graph, Node, Node) Map~String,Object~
}

class DeliveryManager {
    - Graph grapheVille
    - DijkstraAlgorithm algo
    + DeliveryManager(Graph graphe)
    + planifierLivraison(Depot, Restaurant, Client)
    - afficherChemin(List~Node~)
}
DeliveryManager "1" --> "1" Graph
DeliveryManager "1" --> "1" DijkstraAlgorithm

class NetworkAnalyzer {
    - Graph graph
    + NetworkAnalyzer(Graph graph)
    + estConnexe() boolean
    + trouverLesPonts() List~Edge~
}
NetworkAnalyzer "1" --> "1" Graph

class App {
    + main(String[] args)
}
```
