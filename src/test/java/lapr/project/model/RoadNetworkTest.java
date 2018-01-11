/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author TOSHIBA
 */
public class RoadNetworkTest {
    
    public RoadNetworkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
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
    
}
