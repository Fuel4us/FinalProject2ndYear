package lapr.project.utils.Graph;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by anily on 14/12/2017.
 */
public class EdgeTest {

    Edge<String, String> instance;

    @Before
    public void setUp() throws Exception {
        instance = new Edge<>();
    }

    /**
     * Test of getElement method, of class Edge.
     */
    @Test
    public void testGetElement() {
        System.out.println("getElement");

        String expResult = null;
        assertEquals(expResult, instance.getElement());

        Edge<String, String> instance1 = new Edge<>("edge1", 1.0, null, null);
        expResult = "edge1";
        assertEquals(expResult, instance1.getElement());
    }

    /**
     * Test of setElement method, of class Edge.
     */
    @Test
    public void testSetElement() {
        System.out.println("setElement");

        String eInf = "edge1";
        instance.setElement(eInf);

        assertEquals("edge1", instance.getElement());
    }

    /**
     * Test of getWeight method, of class Edge.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");

        double expResult = 0.0;
        assertEquals(expResult, instance.getWeight(), 0.0);
    }

    /**
     * Test of setWeight method, of class Edge.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        double ew = 2.0;
        instance.setWeight(ew);

        double expResult = 2.0;
        assertEquals(expResult, instance.getWeight(), 2.0);
    }

    /**
     * Test of getVOrig method, of class Edge.
     */
    @Test
    public void testGetVOrig() {
        System.out.println("getVOrig");

        Object expResult = null;
        assertEquals(expResult, instance.getVOrig());

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);
        assertEquals(vertex1.getElement(), otherEdge.getVOrig());
    }

    /**
     * Test of setVOrig method, of class Edge.
     */
    @Test
    public void testSetVOrig() {
        System.out.println("setVOrig");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        assertEquals(vertex1.getElement(), instance.getVOrig());
    }

    /**
     * Test of getVDest method, of class Edge.
     */
    @Test
    public void testGetVDest() {
        System.out.println("getVDest");

        Object expResult = null;
        assertEquals(expResult, instance.getVDest());

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);
        assertEquals(vertex1.getElement(), otherEdge.getVDest());
    }

    /**
     * Test of setVDest method, of class Edge.
     */
    @Test
    public void testSetVDest() {
        System.out.println("setVDest");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVDest(vertex1);
        assertEquals(vertex1.getElement(), instance.getVDest());
    }

    /**
     * Test of getEndpoints method, of class Edge.
     */
    @Test
    public void testGetEndpoints() {
        System.out.println("getEndpoints");

        List<String> expResult = null;
        List<String> result = instance.getEndpoints();
        assertEquals(expResult, result);

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        instance.setVDest(vertex1);

        List<String> expResult1 = new LinkedList<>();
        expResult1.add("Vertex1");
        expResult1.add("Vertex1");
        assertEquals(expResult1, instance.getEndpoints());
    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        assertFalse("should not be equal to null", instance.equals(null));

        assertTrue("should be equal to itself", instance.equals(instance));

        assertTrue("should be equal to a clone", instance.equals(instance.clone()));

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);

        assertFalse("should not be equal to otherEdge", instance.equals(otherEdge));
    }

    /**
     * Test of compareTo method, of class Edge.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);

        int expResult = -1;
        int result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);

        otherEdge.setWeight(0.0);
        expResult = 0;
        result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);

        instance.setWeight(2.0);
        expResult = 1;
        result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class Edge.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);

        Edge<String,String> instClone = otherEdge.clone();

        assertTrue("element should be equal", otherEdge.getElement() == instClone.getElement());
        assertTrue("weight should be equal", otherEdge.getWeight() == instClone.getWeight());

        List<String> expResult = otherEdge.getEndpoints();
        assertEquals(expResult, instClone.getEndpoints());
    }

    /**
     * Test of toString method, of class Edge.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        instance.setElement("edge1");
        instance.setWeight(1.0);
        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        instance.setVDest(vertex1);

        String expResult = "(edge1) - 1.0 - Vertex1";
        String result = instance.toString().trim();
        assertEquals(expResult, result);

        System.out.println(instance);
    }


}
