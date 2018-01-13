/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Project;
import lapr.project.model.Node;
import lapr.project.model.RoadNetwork;
import lapr.project.model.Vehicle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TOSHIBA
 */
public class BestPathControllerTest {
    
    public BestPathControllerTest() {
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
     * Test of getAllVehicles method, of class BestPathController.
     */
    @Test
    public void testGetAllVehicles() {
        System.out.println("getAllVehicles");
        BestPathController instance = new BestPathController(new Project("test","test"
                , "description", new RoadNetwork(), new ArrayList<>()));
        List<Vehicle> expResult = new ArrayList<>();
        List<Vehicle> result = instance.getAllVehicles();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllNodes method, of class BestPathController.
     */
    @Test
    public void testGetAllNodes() {
        System.out.println("getAllNodes");
        BestPathController instance = new BestPathController(new Project("test","test"
                , "description", new RoadNetwork(), new ArrayList<>()));
        List<Node> expResult = new ArrayList<>();
        List<Node> result = instance.getAllNodes();
        assertEquals(expResult, result);
    }
    
}
