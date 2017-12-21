/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.Vehicle;

import java.util.ArrayList;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Segment;
import lapr.project.utils.FileParser.XMLImporterVehicles;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
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
public class VehicleTest {
    
    private final Vehicle instance = new Vehicle("Pick_up", "", VehicleType.Car, 0, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(1.0, Unit.KILOGRAM), new Measurable(1.0, Unit.KILOMETERS_PER_HOUR), 1f, new Measurable(1, Unit.METER_SQUARED), 1f, new Measurable(0, Unit.METER), new ArrayList<>(), new Energy(0, 0, 0, new ArrayList<>(), new ArrayList<>()));
    
    public VehicleTest() {
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
     * Test of getVehicleClass method, of class Vehicle.
     */
    @Test
    public void testGetVehicleClass() {
        System.out.println("getVehicleClass");
        int expResult = 0;
        int result = instance.getVehicleClass();
        assertEquals(expResult, result);
        }

    /**
     * Test of retrieveMaxVelocity method, of class Vehicle.
     */
    @Test
    public void testRetrieveMaxVelocity() {
        System.out.println("retrieveMaxVelocity");
        String roadTypology ="road";
        Project object = new Project("test", "description", new RoadNetwork(), new ArrayList<>());
        XMLImporterVehicles xmlImport = new XMLImporterVehicles();
        String filename1 = "src/test/resources/TestSet02_Vehicles_v2.xml";
        xmlImport.importVehicles(object, filename1);
        Measurable expResult = new Measurable(80.0, Unit.KILOMETERS_PER_HOUR);
        Measurable result = object.getVehicles().get(0).retrieveMaxVelocity(roadTypology);
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of determineEnergyExpenditure method, of class Vehicle.
//     */
//    @Test
//    public void testDetermineEnergyExpenditure() {
//        System.out.println("determineEnergyExpenditure");
//        RoadNetwork roadNetwork = null;
//        Segment segment = null;
//        Vehicle instance = new Vehicle();
//        Measurable expResult = null;
//        Measurable result = instance.determineEnergyExpenditure(roadNetwork, segment);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    /**
     * Test of equals method, of class Vehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Vehicle testVehicle1 = new Vehicle("Pick_u", "", VehicleType.Car, 0, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(1.0, Unit.KILOGRAM), new Measurable(1.0, Unit.KILOMETERS_PER_HOUR), 1f, new Measurable(1, Unit.METER_SQUARED), 1f, new Measurable(0, Unit.METER), new ArrayList<>(), new Energy(0, 0, 0, new ArrayList<>(), new ArrayList<>()));
        Vehicle testVehicle2 = new Vehicle("Pick_up", "", VehicleType.Car, 0, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(1.0, Unit.KILOGRAM), new Measurable(1.0, Unit.KILOMETERS_PER_HOUR), 1f, new Measurable(1, Unit.METER_SQUARED), 1f, new Measurable(0, Unit.METER), new ArrayList<>(), new Energy(0, 0, 0, new ArrayList<>(), new ArrayList<>()));
        boolean expResult = false;
        boolean result = instance.equals(testVehicle1);
        assertEquals(expResult, result);
        assertTrue(instance.equals(testVehicle2));
    }

//    /**
//     * Test of hashCode method, of class Vehicle.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        Vehicle instance = new Vehicle();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Vehicle.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Vehicle instance = new Vehicle();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getName method, of class Vehicle.
//     */
//    @Test
//    public void testGetName() {
//        System.out.println("getName");
//        Vehicle instance = new Vehicle();
//        String expResult = "";
//        String result = instance.getName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMotorType method, of class Vehicle.
//     */
//    @Test
//    public void testGetMotorType() {
//        System.out.println("getMotorType");
//        Vehicle instance = new Vehicle();
//        Vehicle.MotorType expResult = null;
//        Vehicle.MotorType result = instance.getMotorType();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
