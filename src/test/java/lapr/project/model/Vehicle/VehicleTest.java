/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

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
        boolean bol = xmlImport.importVehicles(object, filename1);
        assertTrue(bol);
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

    /**
     * Ensures the method determineInitialVelocity calculates correctly the initial velocity of the vehicle
     */
    @Test
    public void ensureInitialVelocityIsCorrect() {

        List<VelocityLimit> velocityLimitListTest = new ArrayList<>();
        velocityLimitListTest.add(new VelocityLimit("Highway", new Measurable(110, Unit.KILOMETERS_PER_HOUR)));
        velocityLimitListTest.add(new VelocityLimit("Road", new Measurable(80, Unit.KILOMETERS_PER_HOUR)));

        List<Gears> gearsTest = new ArrayList<>();
        gearsTest.add(new Gears(1, 4.5f));
        gearsTest.add(new Gears(2, 3.5f));
        gearsTest.add(new Gears(3, 2.7f));
        gearsTest.add(new Gears(4, 1.6f));
        gearsTest.add(new Gears(5, 1.2f));
        gearsTest.add(new Gears(6, 0.9f));

        List<Regime> regimes25Test = new ArrayList<>();
        regimes25Test.add(new Regime(115, 125, 900, 1499, 500));
        regimes25Test.add(new Regime(125, 120, 1500, 2499, 450));
        regimes25Test.add(new Regime(120, 105, 2500, 3499, 520));
        regimes25Test.add(new Regime(105, 90, 3500, 4499, 550));
        regimes25Test.add(new Regime(90, 80, 4500, 5500, 650));

        List<Regime> regimes50Test = new ArrayList<>();
        regimes50Test.add(new Regime(185, 195, 900, 1499, 380));
        regimes50Test.add(new Regime(195, 190, 1500, 2499, 350));
        regimes50Test.add(new Regime(190, 180, 2500, 3499, 360));
        regimes50Test.add(new Regime(180, 150, 3500, 4499, 400));
        regimes50Test.add(new Regime(150, 135, 4500, 5500, 520));

        List<Regime> regimes100Test = new ArrayList<>();
        regimes100Test.add(new Regime(305, 325, 900, 1499, 380));
        regimes100Test.add(new Regime(325, 315, 1500, 2499, 350));
        regimes100Test.add(new Regime(315, 290, 2500, 3499, 360));
        regimes100Test.add(new Regime(290, 220, 3500, 4499, 400));
        regimes100Test.add(new Regime(220, 205, 4500, 5500, 520));

        List<Throttle> throttlesTest = new ArrayList<>();
        throttlesTest.add(new Throttle(25, regimes25Test));
        throttlesTest.add(new Throttle(50, regimes50Test));
        throttlesTest.add(new Throttle(100, regimes100Test));

        Vehicle vehicleTest = new Vehicle("Toyota", "Vehicle 1", VehicleType.Car, 1, Vehicle.MotorType.COMBUSTION, Fuel.Diesel,
                new Measurable(1500, Unit.KILOGRAM), new Measurable(7500, Unit.KILOGRAM), 0.320f,
                new Measurable(1.9, Unit.METER_SQUARED), 0.01f, new Measurable(0.6, Unit.METER),
                velocityLimitListTest, new Energy(900, 5500, 4f, gearsTest, throttlesTest));

        Measurable expected = new Measurable(5.654866, Unit.KILOMETERS_PER_HOUR);
        Measurable result = vehicleTest.determineInitialVelocity();

        assertEquals(expected.getQuantity(), result.getQuantity(), 0.00001);

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
