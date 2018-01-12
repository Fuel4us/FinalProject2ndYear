/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.List;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Pedro
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


//    /**
//     * Test of retrieveAllRoads method, of class RoadNetwork.
//     */
//    @Test
//    public void testRetrieveAllRoads() {
//        System.out.println("retrieveAllRoads");
//        RoadNetwork instance = new RoadNetwork();
//        List<Road> expResult = null;
//        List<Road> result = instance.retrieveAllRoads();
//        assertEquals(expResult, result);
//    }

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


    
}
