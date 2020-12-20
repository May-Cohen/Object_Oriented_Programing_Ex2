package api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DWGraph_DS implements directed_weighted_graph {

    private HashMap<Integer,node_data> Graph;
    private int countOfGraphChanges;
    private int countOfEdgeChanges;

    // Basic constructors , getters and setters

    public DWGraph_DS() {
        this.Graph= new HashMap<>();
        this.countOfEdgeChanges = 0;
        this.countOfEdgeChanges = 0;
    }

    public DWGraph_DS(HashMap<Integer,node_data> g , int Cogc , int Coec) {
        this.Graph=g;
        this.countOfEdgeChanges=Coec;
        this.countOfGraphChanges=Cogc;
    }

    @Override
    public node_data getNode(int key) {
        return Graph.get(key);
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        NodeData node = (NodeData) getNode(src);
        HashMap<Integer,edge_data> NodeEdge = node.getNeighbors();
        if (!NodeEdge.containsKey(dest)){
            return null;
        }
        return NodeEdge.get(dest);
    }

    @Override
    public void addNode(node_data n) {
        if (Graph.containsKey(n.getKey())) {
            return;
        }
        else {
            Graph.put(n.getKey(), n);
            countOfGraphChanges++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (w < 0) {
            return;
        }
        NodeData node1 = (NodeData) getNode(src);
        HashMap<Integer,edge_data> temp = node1.getNeighbors();
        if (temp.get(dest)!=null){
            return;
        }
        else{
            EdgeData edge = new EdgeData(src,dest,w);
           temp.put(dest,edge);
           countOfEdgeChanges++;
        }
    }

    @Override
    public Collection<node_data> getV() {
        return Graph.values();
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
        NodeData node = (NodeData) getNode(node_id);
        return node.getNeighbors().values();
    }

    @Override
    public node_data removeNode(int key) {
        if (Graph.keySet().contains(key) == false) {
            return null;
        }
        NodeData a = (NodeData) getNode(key);
        Set<Integer> e = new HashSet<>(a.getNeighbors().keySet());
        for (int temp : e) {
            a.getNeighbors().remove(temp);
            NodeData i = (NodeData) getNode(temp);
            i.getNeighbors().remove(key);
            countOfEdgeChanges--;
            countOfGraphChanges++;
        }
        countOfGraphChanges++; // the change of the node removal in the next line
        return Graph.remove(key);
    }

    @Override
    public edge_data removeEdge(int src, int dest) {
        NodeData temp = (NodeData) getNode(src);
        edge_data ans =temp.getNeighbors().get(dest);
        temp.getNeighbors().remove(dest);
        countOfEdgeChanges--;
        countOfGraphChanges++;
        return ans;
    }

    @Override
    public int nodeSize() {
        return Graph.size();
    }

    @Override
    public int edgeSize() {
        return countOfEdgeChanges;
    }

    @Override
    public int getMC() {
        return countOfGraphChanges++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DWGraph_DS wGraph_ds = (DWGraph_DS) o;
        return countOfEdgeChanges == wGraph_ds.countOfEdgeChanges &&
                Graph.equals(wGraph_ds.Graph);
    }


}
