**Directed weighted graph, and the Pokémons's Challenge**

- This project contains:
  
  Part 1: 
  
    1. [General information about the project's structure](#1)
    2. [Main algorithms in the project](#2)
       
  Part 2:
  
    3. [The Pokemon's Challenge](#3)


**Prat 1: Directed weighted graph implementation**
___

<div id = '1'/>

**General information about the project's structure**
___

This part of the project is an implementation of directed weighted graph. 
- Each graph is built from HashMap of Integers (represents the keys of the nodes) and node_data (the actual node \ vertex).
- Each vertex/node is built from int key, int Tag , String info , double weight , geo_location location (we will use these
variables in some algorithms in the project) and HashMap of Integers (represents the destination node key) and edge_data 
(the information about the edge between the node we work on, and the destination node).
- Each Edge is built from int src , int dest (represents the keys of the nodes this edge is built from), double weight , 
String info and int Tag. 
  
The main classes are: 
  * **DWGraph_DS**: this class implements `directed_weighted_graph` and represent the structure of the graph. This class
    implements the following basic methods:

      * `getNode(int key)`: this method gets the node id and returns its information.
      * `getEdge(int src, int dest)`: this method gets the node id of the source node and the node id of the destination
        node and returns the information of the edge between them.
      * `addNode(node_data n)`: this method adds the given node to the graph
      * `connect(int src, int dest, double w)`: this method creating an edge between the two given nodes and weight.
      * `getV()`: this method returns a pointer for collection of all the nodes in the graph.
      * `getE(int node_id)`: this method returns a pointer for collection of all the edges in the graph.
      * `removeNode(int key)`: this method remove the node with the given key from the graph and returns it.
      * `removeEdge(inr src, int dest)`: this method remove the edge between the two nodes with given key froms the 
        graph and returns it.
      * `nodeSize()`: this method returns the number of nodes in the graph. 
      * `edgeSize()`: this method returns the number of edges in the graph.
      * `getMC()`: this method returns the number of changes that made on the graph.
      

  * **DWGraph_Algo**: this class implements `dw_graph_algorithms` and represent the algorithms we can preform on the graph.
    This class implements the following basic methods:

      * `init(directed_weighted_graph g)`:this method init the graph on which this set of algorithms operates on.
      * `getGraph()`: this method the graph of which the class works on.
      * `copy()`: this method compute a deep copy of the graph.
      * `isConnected()`: this method returns true in the graph is super-connected (if there is a valid path from each 
        node to another).
      * `shortestPathDist(int src, int dest)`: this method returns the length of the shortest path from the source node
        anf the destination node.
      * `shortestPath(int src, int dest)`: this method returns a list of the shortest path between the two nodes.
      * `save(String file)`: this method saves this weighted graph to given file name (Json format).
      * `load(String file)`: this method load a graph to this graph algorithm from given Json format file.
    
    
 * **NodeData and EdgeData**:
    NodeData implements `node_data` and represents the structure of node in the graph.
    EdgeData implements `edge_data` and represents the structure od edge in the graph.
    both contacting getters and setters of the class variables.
   
    
  * **GeoLocation**:
      * `x()`: this method returns the x value of the location.
      * `y()`: this method returns the y value of the location.
      * `z()`: this method returns the z value of the location.
      * `distance(geo_location g)`: this method returns the distance between the location of the node we work on and 
        the given location of the node.
    

<div id = '2'/>

**Main algorithms in the project**
___

  1. **Dijkstra algorithm**: This algorithm get the key of the source node.
     The algorithm goes through all the neighbors of the key value and check the distance (by the weights of the edges) between 
     the source node to each other.
     The algorithm uses three main values:
        * boolean value - use the info value of the node. The default info is "false". The algorithm marks the info value with "true" if we went over all its neighbors.
        * HashMap<Integer,double> distances - the default value value for each vertex in the HashMap is infinity. The algorithm store
          there the distance between the nodes. After all nodes marked we can retrieve the information and use it in other methods in the project.
        * HashMap<Integer,node_data> Pred - to determine the predecessor of the node in during the traverse.
        This algorithm helped to check the shortest path between between two nodes (vertexes).
     
  2. **EdgeReverse()**: method that built to create the "reverse graph" means that we change the direction of each edge
     in the graph.

  3. **BFS algorithm**: This algorithm traverse the graph and mark each node with color:
        * White - unvisited node.
        * Gray - the node the algorithm works on (the algorithm didn't go through all his neighbors).
        * Black - the algorithm went through all the node's neighbors. 
      
        This algorithm also uses the distances and pred HashMaps.
        This algorithm helped us to determine if the graph is strongly connected: we first ran the method on the regular graph 
        and then on the "reverse" graph we created by the `EdgeReverse()` method. If in both we got that the graph is connected
            (the algorithm passed over all the vertices in the graph) then the graph is strongly connected.




**Prat 2: The Pokémon's Challenge**
___

<div id = '3'/>

**The Pokémon's Challenge**
___

**General information about the game**

In this game we need to "pick up" all the Pokémons on the game board with the agents (each level have different number of agents).
we pass level when we collected all the Pokémons before the time is ending.
The aim of the game is to pass all the levels with the lowest score (the lowest number of the moves).
In this part we used the implementation of the weighted directed graph to build the game track, and the algorithms (from the first part) 
to determine the path they need to go throw. 

**General idea**

We need to locate the agents in the vertex on the graph who have the shortest distance from the Pokémon (we want to collect).
for each edge the Pokémon is located on we need to take the closest vertex (source vertex of the edge - to avoid an attempt to
traverse the edge in the opposite direction).



