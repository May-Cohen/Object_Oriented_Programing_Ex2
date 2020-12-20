package api;
import java.io.*;
import java.util.*;
import com.google.gson.*;

public class DWGraph_Algo implements dw_graph_algorithms {

    private directed_weighted_graph WG;
    private HashMap<Integer, node_data> Pred; // will help us for DijkstraAlgorithm
    private HashMap<Integer, Double> distances; //  will help us for DijkstraAlgorithm

    @Override
    public void init(directed_weighted_graph g) {
        this.WG = g;
        this.Pred = new HashMap<>();
        this.distances = new HashMap<>();
        for (node_data i : WG.getV()) {
            i.setInfo("false");
            Pred.put(i.getKey(), null);
            distances.put(i.getKey(), Double.MAX_VALUE);
        }
    }

    @Override
    public directed_weighted_graph getGraph() {
        return this.WG;
    }

    @Override
    public directed_weighted_graph copy() {
        directed_weighted_graph toReturn = new DWGraph_DS();
        for (node_data add : WG.getV()) {
            toReturn.addNode(add);
        }
        return toReturn;
    }

    // This method will traverse the graph, mark the nodes and return true if the algorithm visit all of the nodes

    public boolean BFS(directed_weighted_graph W) {
        int src=0;
        for (node_data a : W.getV()){
            src = a.getKey();
        }
        W.getNode(src);
        init(W);
        Queue<node_data> qu = new LinkedList();
        for (node_data i : W.getV()) {
            i.setInfo("WHITE");
            distances.put(i.getKey(),Double.MAX_VALUE);
            Pred.put(i.getKey(),null);
        }
        node_data source = W.getNode(src);
        source.setInfo("GRAY");
        distances.put(src,0.0);
        qu.add(W.getNode(src));
        while (qu.isEmpty() == false) {
           NodeData i = (NodeData) qu.poll();
            for (int j : i.getNeighbors().keySet()) {
                node_data temp = W.getNode(j);
                if (temp.getInfo().equals("WHITE")) {
                    temp.setInfo("GRAY");
                    distances.put(j, (distances.get(i.getKey())+1));
                    Pred.put(j,i);
                    qu.add(temp);
                }
            }
            i.setInfo("BLACK");
        }
        // check if we visited all the vertices
        for (node_data Q : W.getV()) {
            if (Q.getInfo().equals("WHITE")){
                return false;
            }
        }
        return true;
    }

    // This method reverse all the edges in the graph and creates new graph

    public directed_weighted_graph EdgeReverse (){
        directed_weighted_graph edgeRevers = new DWGraph_DS();
        for (node_data p : WG.getV()){
            edgeRevers.addNode(p);
        }
        for (node_data i : edgeRevers.getV()){
            for (edge_data e : edgeRevers.getE(i.getKey())){
                int dest = i.getKey();
                int src = e.getDest();
                edgeRevers.connect(src,dest,e.getWeight());
            }
        }
        return edgeRevers;
    }

    // This method checks if the BFS traversal of both graph returns true (means that the graph is strongly connected)

    @Override
    public boolean isConnected() {
        boolean a = BFS(WG);
        init(WG);
        directed_weighted_graph q = EdgeReverse();
        boolean b = BFS(q);
        if (a==true && b==true){
            return true;
        }
        return false;
    }

    public void DijkstraAlgorithm(int keySource) {
        init(WG);
        node_data key = WG.getNode(keySource);
        PriorityQueue<node_data> Pq = new PriorityQueue<>((n1 , n2)-> (int) (distances.get(n1.getKey())-distances.get(n2.getKey())));
        // (n1 , n2)-> (int) (distances.get(n1.getKey())-distances.get(n2.getKey())) this lambda expression replace the
        // CompareTo methods in the classes or the Comparator.
        distances.put(key.getKey(), 0.0);
        Pq.add(key);
        while (!Pq.isEmpty()) {
            node_data temp0 = Pq.remove();
            NodeData temp =(NodeData)temp0;
            Set<node_data> Ni = new HashSet<>();
            Set<Integer> a = temp.getNeighbors().keySet();
            for (int i: a) {
                node_data t = WG.getNode(i);
                Ni.add(t);
            }
            for (node_data i : Ni) {
                if (i.getInfo().equals("false")) {
                    double distance = (distances.get(temp.getKey()))+(WG.getEdge(temp.getKey(),i.getKey())).getWeight();
                    if (distances.get(i.getKey()) > distance) {
                        distances.put(i.getKey() ,distance);
                        Pred.put(i.getKey(), temp);
                        Pq.remove(i);
                        Pq.add(i);
                    }
                }
            }
            temp.setInfo("true");
        }
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (WG.getNode(src) == null || WG.getNode(dest) == null) {
            return -1;
        }
        init(WG);
        DijkstraAlgorithm(src);
        node_data temp = WG.getNode(dest);
        if (distances.get(temp.getKey())== Double.MAX_VALUE) {
            return -1;
        }
        return distances.get(temp.getKey());
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        if (WG.getNode(src) == null || WG.getNode(dest) == null) {
            return null;
        }
        LinkedList<node_data> ans = new LinkedList<>();
        init(WG);
        DijkstraAlgorithm(dest);
        node_data w = WG.getNode(src);
        while (w != WG.getNode(dest) && w != null) {
            ans.add(w);
            w = Pred.get(w.getKey());
        }
        ans.add(w);
        if (w == null) {
            return null;
        }
        return ans;
    }

    @Override
    public boolean save(String file) {
        Gson gson = new GsonBuilder().registerTypeAdapter(node_data.class , new InterfaceAddapter()).
                registerTypeAdapter(geo_location.class , new InterfaceAddapter()).
                registerTypeAdapter(edge_data.class, new InterfaceAddapter()).create();
        try {
            FileWriter wr = new FileWriter(file);
            gson.toJson(WG, wr);
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean load(String file) {
        Gson gson = new GsonBuilder().registerTypeAdapter(node_data.class , new InterfaceAddapter()).
        registerTypeAdapter(geo_location.class , new InterfaceAddapter()).
                registerTypeAdapter(edge_data.class, new InterfaceAddapter()).create();
        try {
            BufferedReader re = new BufferedReader(new FileReader(file));
            this.WG=gson.fromJson(re, DWGraph_DS.class);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void loadGame(String file) {
        Gson gson = new GsonBuilder().registerTypeAdapter(node_data.class , new InterfaceAddapter()).
                registerTypeAdapter(geo_location.class , new InterfaceAddapter()).
                registerTypeAdapter(edge_data.class, new InterfaceAddapter()).create();
        BufferedReader re = new BufferedReader(new StringReader(file));
        this.WG=gson.fromJson(re, DWGraph_DS.class);
    }

}
