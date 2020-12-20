package api;
import java.io.Serializable;
import java.util.HashMap;

public class NodeData implements node_data , Serializable {
    private int key;
    private geo_location location;
    private double weight;
    private String Info;
    private int Tag;
    // The key is the key of the destination node, the value is the information about the edge
    private HashMap<Integer, edge_data> Neighbors;

    // Basic constructors , getters and setters

    public NodeData(int key) {
        this.key = key;
        this.location = new GeoLocation();
        this.Neighbors = new HashMap<Integer,edge_data>();
        this.Info="false";
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public geo_location getLocation() {
        return location;
    }

    @Override
    public void setLocation(geo_location p) {
        this.location=p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.Info;
    }

    @Override
    public void setInfo(String s) {
        this.Info = s;
    }

    @Override
    public int getTag() {
        return this.Tag;
    }

    @Override
    public void setTag(int t) {
        this.Tag = t;
    }

    public HashMap<Integer, edge_data> getNeighbors(){
        return this.Neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeData nodeInfo = (NodeData) o;
        return  key == nodeInfo.key &&
                Info == nodeInfo.Info &&
                weight == nodeInfo.weight &&
                Double.compare(nodeInfo.Tag, Tag) == 0 &&
                location.equals(nodeInfo.location);

    }

    @Override
    public String toString() {
        return "NodeData{" +
                "key=" + key +
                ", location=" + location +
                ", weight=" + weight +
                ", Info='" + Info + '\'' +
                ", Tag=" + Tag +
                ", Neighbors=" + Neighbors +
                '}';
    }
}