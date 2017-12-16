/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.File;
import lapr.project.model.Vehicle.Vehicle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author goncalo
 */
public class XMLImporterTest {
    
    private XMLImporter xmlImporter;

    /**
     * Test of importVehicle method, of class XMLImporter.
     */
    @Test
    public void testImportVehicle() throws Exception {
        
        String file = "TestSet01_vehicles.XMLs";
        
        xmlImporter = new XMLImporter(new File(file));
        
        Vehicle vehicle = xmlImporter.importVehicle();
        
        assertTrue(vehicle != null);
    }
    
}
