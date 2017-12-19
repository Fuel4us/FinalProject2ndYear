/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.FileParser;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.Vehicle.Vehicle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author goncalo
 */
public class XMLImporterVehiclesTest {
    
    private final String filename;
    
    public XMLImporterVehiclesTest() {
        this.filename="src/test/resources/TestSet02_Vehicles.xml";
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
        boolean result = instance.importVehicles(object, filename);
        assertEquals(expResult, result);
    }
    
}
