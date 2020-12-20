package tests;

import api.DWGraph_DS;
import api.NodeData;
import api.directed_weighted_graph;
import api.node_data;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_DS_test {
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
    void test1() {
        directed_weighted_graph test = myTestGraph();
        node_data a = new NodeData(5);
        node_data b = new NodeData(9);
        test.addNode(a);
        test.addNode(b);
        test.addNode(b);
        test.removeNode(10);
        test.removeNode(2);
        test.removeNode(0);
        int s = test.nodeSize();
        assertEquals(5,s);
    }

    @Test
    void test2() {
        directed_weighted_graph test = new DWGraph_DS();
        node_data a = new NodeData(0);
        node_data b = new NodeData(1);
        node_data c = new NodeData(2);
        node_data d = new NodeData(3);
        test.addNode(a);
        test.addNode(b);
        test.addNode(c);
        test.addNode(d);
        test.connect(a.getKey(),b.getKey(),1);
        test.connect(a.getKey(),c.getKey(),2);
        test.connect(a.getKey(),d.getKey(),3);
        test.connect(a.getKey(),b.getKey(),1);
        int e_size =  test.edgeSize();
        assertEquals(3, e_size);
        double w03 = test.getEdge(a.getKey(),d.getKey()).getWeight();
        assertEquals(w03, 3);
    }

    @Test
    void test3(){
        directed_weighted_graph test = new DWGraph_DS();
        node_data a = new NodeData(0);
        node_data b = new NodeData(1);
        node_data c = new NodeData(2);
        node_data d = new NodeData(3);
        test.addNode(a);
        test.addNode(b);
        test.addNode(c);
        test.addNode(d);
        test.connect(a.getKey(),b.getKey(),1);
        test.connect(a.getKey(),c.getKey(),2);
        test.connect(a.getKey(),d.getKey(),3);
        test.connect(a.getKey(),b.getKey(),1);
        Collection<node_data> v = test.getV();
        Iterator<node_data> iter = v.iterator();
        while (iter.hasNext()) {
            node_data n = iter.next();
            assertNotNull(n);
        }
    }

    @Test
    void test4 (){
        directed_weighted_graph test = new DWGraph_DS();
        node_data a = new NodeData(0);
        node_data b = new NodeData(1);
        node_data c = new NodeData(2);
        node_data d = new NodeData(3);
        test.addNode(a);
        test.addNode(b);
        test.addNode(c);
        test.addNode(d);
        test.connect(a.getKey(),b.getKey(),1);
        test.connect(a.getKey(),c.getKey(),2);
        test.connect(a.getKey(),d.getKey(),3);
        test.removeEdge(0,1);
        test.removeEdge(2,1);
        test.connect(0,1,1);
        double w = test.getEdge(0,1).getWeight();
        assertEquals(w,1);
    }

    @Test
    void test5(){
        directed_weighted_graph test = new DWGraph_DS();
        node_data a = new NodeData(0);
        node_data b = new NodeData(1);
        node_data c = new NodeData(2);
        node_data d = new NodeData(3);
        test.addNode(a);
        test.addNode(b);
        test.addNode(c);
        test.addNode(d);
        test.connect(0,1,1);
        test.connect(0,2,2);
        test.connect(0,3,3);
        test.removeNode(4);
        test.removeNode(0);
        int e = test.edgeSize();
        assertEquals(0,e);
        assertEquals(3,test.nodeSize());
    }

    @Test
    void test6(){
        directed_weighted_graph test = new DWGraph_DS();
        node_data a = new NodeData(0);
        node_data b = new NodeData(1);
        node_data c = new NodeData(2);
        node_data d = new NodeData(3);
        test.addNode(a);
        test.addNode(b);
        test.addNode(c);
        test.addNode(d);
        test.connect(a.getKey(),b.getKey(),1);
        test.connect(a.getKey(),c.getKey(),2);
        test.connect(a.getKey(),d.getKey(),3);
        test.removeEdge(0,3);
        assertEquals(test.getEdge(0,3),null);
    }


}