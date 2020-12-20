package api;

import java.util.Objects;

public class GeoLocation implements geo_location {
    private double x;
    private double y;
    private double z;

    public GeoLocation(){
        this.x=0;
        this.y=0;
        this.z=0;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(geo_location g) {
        double p1 = Math.pow((this.x- g.x()),2);
        double p2 = Math.pow((this.y- g.y()),2);
        double p3 = Math.pow((this.z- g.z()),2);
        double p4 = p1+p2+p3;
        double ans = Math.sqrt(p4);
        return ans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoLocation that = (GeoLocation) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.z, z) == 0;
    }

}
