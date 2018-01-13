/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Team Fonseca
 */
public class RoadNetworkTest {
    
    public RoadNetworkTest() {
    }

    /**
     * Test of setId method, of class RoadNetwork.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "iddif";
        RoadNetwork instance = new RoadNetwork("id","desc");
        instance.setId(id);
        assertEquals(instance.getId(),id);
    }

    /**
     * Test of addNode method, of class RoadNetwork.
     */
    @Test
    public void testAddNode() {
        System.out.println("addNode");
        Node node = new Node("node");
        RoadNetwork instance = new RoadNetwork();
        boolean result = instance.addNode(node);
        assertTrue(result);
    }

    /**
     * Test of getId method, of class RoadNetwork.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        RoadNetwork instance = new RoadNetwork("id","desc");
        String expResult = "id";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class RoadNetwork.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        RoadNetwork instance = new RoadNetwork("id","desc");
        String expResult = "desc";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class RoadNetwork.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        RoadNetwork instance = new RoadNetwork("id","desc");
        RoadNetwork instance2 = new RoadNetwork("id","desc");
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        assertTrue(instance.equals(instance2));
    }
    
    @Test
    public void testHashCode() {
        
        RoadNetwork instance = new RoadNetwork("id","desc");
        int expResult = 3243412;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of addSection method, of class RoadNetwork.
     */
    @Test
    public void testAddSection() {
        
        Collection<Segment> collection = new ArrayList<>();
        collection.add(new Segment(0, 0, 0, 50, 0, 0, 120, 0));
        collection.add(new Segment(1, 0, 0, 100, 0, 0, 90, 0));
        collection.add(new Segment(2, 0, 0, 75, 0, 0, 70, 0));
        collection.add(new Segment(3, 0, 0, 100, 0, 0, 100, 0));
        
        System.out.println("addSection");
        Node n1 = new Node("n1");
        Node n2 = new Node("n2");
        Section section = new Section (n1,n2, Direction.BIDIRECTIONAL, collection, new Road("A01", "A01", "toll highway"), new ArrayList<>());
        RoadNetwork instance = new RoadNetwork();
        boolean expResult = true;
        boolean result = instance.addSection(n1, n2, section);
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveAllRoads method, of class RoadNetwork.
     */
    @Test
    public void testRetrieveAllRoads() {
        System.out.println("retrieveAllRoads");
        RoadNetwork instance = new RoadNetwork("Alpha", "Description");
        List<Road> expResult = new ArrayList<>();
        List<Road> result = instance.retrieveAllRoads();
        assertEquals(expResult, result);
    }


    
}
