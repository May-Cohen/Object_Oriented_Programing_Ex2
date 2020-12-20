package tests;
import api.*;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_Algo_test {
    private directed_weighted_graph myTestGraph() {
        directed_weighted_graph Gt1 = new DWGraph_DS();
        node_data a = new NodeData(0);
        node_data b = new NodeData(1);
        node_data c = new NodeData(2);
        node_data d = new NodeData(3);
        node_data e = new NodeData(4);
        Gt1.addNode(a);
        Gt1.addNode(b);
        Gt1.addNode(c);
        Gt1.addNode(d);
        Gt1.addNode(e);
        Gt1.connect(a.getKey(), b.getKey(), 10.0);
        Gt1.connect(a.getKey(), e.getKey(), 5.0);
        Gt1.connect(b.getKey(), c.getKey(), 1.0);
        Gt1.connect(b.getKey(), e.getKey(), 2.0);
        Gt1.connect(c.getKey(), d.getKey(), 4.0);
        Gt1.connect(d.getKey(), c.getKey(), 6.0);
        Gt1.connect(d.getKey(), a.getKey(), 7.0);
        Gt1.connect(e.getKey(), d.getKey(), 2.0);
        Gt1.connect(e.getKey(), b.getKey(), 3.0);
        Gt1.connect(e.getKey(), c.getKey(), 9.0);
        return Gt1;
    }

    @Test
    void shortestPathDist() {
        directed_weighted_graph g0 = myTestGraph();
        dw_graph_algorithms ag0 = new DWGraph_Algo();
        ag0.init(g0);
        assertTrue(ag0.isConnected());
        double d = ag0.shortestPathDist(0,2);
        assertEquals(9, d);
    }

    @Test
    void shortestPath() {
        directed_weighted_graph a = myTestGraph();
        dw_graph_algorithms b = new DWGraph_Algo();
        b.init(a);
        assertTrue(b.isConnected());
        assertEquals(7,b.shortestPathDist(0,3));
        LinkedList<node_data> c = new LinkedList<>();
        c.add(a.getNode(0));
        c.add(a.getNode(4));
        c.add(a.getNode(3));
        LinkedList<node_data> d = new LinkedList<>(b.shortestPath(0,3));
        assertEquals(c,d);
    }

    @Test
    void save_load() {
        directed_weighted_graph g0 = myTestGraph();
        dw_graph_algorithms ag0 = new DWGraph_Algo();
        ag0.init(g0);
        String str = "g0.json";
        ag0.save(str);
        directed_weighted_graph g1 = myTestGraph();
        ag0.load(str);
        assertEquals(g0,g1);
        g0.removeNode(0);
        assertNotEquals(g0,g1);
    }

    @Test
    void isConnected(){
        directed_weighted_graph aa = new DWGraph_DS();
        node_data a = new NodeData(0);
        node_data b = new NodeData(1);
        node_data c = new NodeData(2);
        node_data d = new NodeData(3);
        node_data e = new NodeData(4);
        aa.addNode(a);
        aa.addNode(b);
        aa.addNode(c);
        aa.addNode(d);
        aa.addNode(e);
        aa.connect(a.getKey(),b.getKey(),0);
        aa.connect(b.getKey(),c.getKey(),0);
        aa.connect(c.getKey(),e.getKey(),0);
        aa.connect(e.getKey(),c.getKey(),0);
        aa.connect(c.getKey(),d.getKey(),0);
        aa.connect(d.getKey(),a.getKey(),0);
        dw_graph_algorithms ag0 = new DWGraph_Algo();
        ag0.init(aa);
        assertTrue(ag0.isConnected());
    }



}