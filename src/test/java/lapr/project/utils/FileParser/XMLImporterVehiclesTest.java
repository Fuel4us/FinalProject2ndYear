/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.FileParser;

import java.util.ArrayList;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
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
public class XMLImporterVehiclesTest {
    
    public XMLImporterVehiclesTest() {
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
     * Test of importVehicles method, of class XMLImporterVehicles.
     */
    @Test
    public void testImportVehicles() {
        
        System.out.println("importVehicles");
        Project object = new Project("test","description",new RoadNetwork(),new ArrayList<>());
        XMLImporterVehicles instance = new XMLImporterVehicles();
        boolean expResult = true;
        boolean result = instance.importVehicles(object, "src/test/resources/TestSet02_Vehicles.xml");
        assertEquals(expResult, result);
    }
    
}
