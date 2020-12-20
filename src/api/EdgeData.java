package api;
import java.io.Serializable;
import java.util.Objects;

public class EdgeData implements edge_data , Serializable, Comparable {

    private int src;
    private int dest;
    private double weight;
    private String Info;
    private int Tag;

    // Basic constructors , getters and setters

    public EdgeData(int src, int dest,double weight) {
        this.src = src;
        this.dest=dest;
        this.weight=weight;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.Info;
    }

    @Override
    public void setInfo(String s) {
        this.Info=s;
    }

    @Override
    public int getTag() {
        return this.Tag;
    }

    @Override
    public void setTag(int t) {
        this.Tag=t;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeData edgeData = (EdgeData) o;
        return src == edgeData.src &&
                dest == edgeData.dest &&
                Double.compare(edgeData.weight, weight) == 0 &&
                Tag == edgeData.Tag &&
                Objects.equals(Info, edgeData.Info);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}