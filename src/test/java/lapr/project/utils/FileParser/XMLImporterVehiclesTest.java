/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.FileParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.Vehicle.Energy;
import lapr.project.model.Vehicle.Fuel;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.model.Vehicle.VehicleType;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Test class for XMLImporterVehicles class
 * 
 * @author Pedro and Gonçalo
 */
public class
XMLImporterVehiclesTest {

    List<Vehicle> instance = new ArrayList<>();
    XMLImporterVehicles obj = new XMLImporterVehicles();

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
     * Test of importVehicles method, of class X MLImporterVehicles.
     */
    @Test
    public void testImportVehicles() {

        System.out.println("importVehicles");
        Project object = new Project("test", "description", new RoadNetwork(), new ArrayList<>());
        XMLImporterVehicles instance = new XMLImporterVehicles();
        boolean expResult = true;
        String filename1 = "src/test/resources/TestSet02_Vehicles_v2.xml";
        boolean result = instance.importVehicles(object, filename1);
        assertEquals(expResult, result);
        assertEquals(object.getVehicles().get(0), new Vehicle("Pick_up", "", VehicleType.Car, 0, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(1.0, Unit.KILOGRAM), new Measurable(1.0, Unit.KILOMETERS_PER_HOUR), 1f, new Measurable(1, Unit.METER_SQUARED), 1f, new Measurable(0, Unit.METER), new ArrayList<>(), new Energy(0, 0, 0, new ArrayList<>(), new ArrayList<>())));
        assertFalse(instance.importVehicles(object, ""));
    }

    @Test
    public void testAddName() {
        
        Vehicle v = new Vehicle("Citroen", "bla", VehicleType.Car, 0, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(1.0, Unit.KILOGRAM), new Measurable(1.0, Unit.KILOMETERS_PER_HOUR), 1f, new Measurable(1, Unit.METER_SQUARED), 1f, new Measurable(0, Unit.METER), new ArrayList<>(), new Energy(0, 0, 0, new ArrayList<>(), new ArrayList<>()));
        Vehicle v2 = new Vehicle("Citroen", "bla", VehicleType.Car, 0, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(1.0, Unit.KILOGRAM), new Measurable(1.0, Unit.KILOMETERS_PER_HOUR), 1f, new Measurable(1, Unit.METER_SQUARED), 1f, new Measurable(0, Unit.METER), new ArrayList<>(), new Energy(0, 0, 0, new ArrayList<>(), new ArrayList<>()));
        Project object = new Project("test", "description", new RoadNetwork(), new ArrayList<>());
        
        instance.add(v2);
       
        String result = obj.addName(instance ,v.getName());
        String expResult = ("Citroen1");
        
        assertEquals(expResult, result);
        
    }

}
