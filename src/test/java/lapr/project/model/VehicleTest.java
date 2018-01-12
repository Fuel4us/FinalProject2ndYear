/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author TEAM Fonseca
 */
public class VehicleTest {

    Vehicle instance = new Vehicle("Pick_up", "", VehicleType.Car, 0, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(1.0, Unit.KILOGRAM), new Measurable(1.0, Unit.KILOMETERS_PER_HOUR), 1f, new Measurable(1, Unit.METER_SQUARED), 1f, new Measurable(0, Unit.METER), new ArrayList<>(), new Energy(0, 0, 0, new ArrayList<>(), new ArrayList<>()));

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

    @Test
    public void VehicleTest() {
        Vehicle vehicleTest = new Vehicle();
        String expResult = null;
        String result = vehicleTest.getName();
        assertEquals(expResult, result);
    }

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
     * Ensures the method determineInitialVelocity calculates correctly the
     * initial velocity of the vehicle
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

    /**
     * Test of hashCode method, of class Vehicle.
     */
    @Test
    public void testHashCode() {
        int expResult = instance.hashCode();
        int result = 1086622844;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Vehicle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Pick_up - .";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Vehicle.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Pick_up";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMotorType method, of class Vehicle.
     */
    @Test
    public void testGetMotorType() {
        System.out.println("getMotorType");
        Vehicle.MotorType expResult = Vehicle.MotorType.COMBUSTION;
        Vehicle.MotorType result = instance.getMotorType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMass method, of class Vehicle.
     */
    @Test
    public void testGetMass() {
        System.out.println("getMass");
        Measurable expResult = new Measurable(1.0, Unit.KILOGRAM);
        Measurable result = instance.getMass();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxLoad method, of class Vehicle.
     */
    @Test
    public void testGetMaxLoad() {
        System.out.println("getMaxLoad");
        Measurable expResult = new Measurable(1.0, Unit.KILOMETERS_PER_HOUR);
        Measurable result = instance.getMaxLoad();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFrontalArea method, of class Vehicle.
     */
    @Test
    public void testGetFrontalArea() {
        System.out.println("getFrontalArea");
        Measurable expResult = new Measurable(1, Unit.METER_SQUARED);
        Measurable result = instance.getFrontalArea();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWheelSize method, of class Vehicle.
     */
    @Test
    public void testGetWheelSize() {
        System.out.println("getWheelSize");
        Measurable expResult = new Measurable(0, Unit.METER);
        Measurable result = instance.getWheelSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVelocityLimitList method, of class Vehicle.
     */
    @Test
    public void testGetVelocityLimitList() {
        System.out.println("getVelocityLimitList");
        List<VelocityLimit> expResult = new ArrayList<>();
        List<VelocityLimit> result = instance.getVelocityLimitList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnergy method, of class Vehicle.
     */
    @Test
    public void testGetEnergy() {
        System.out.println("getEnergy");
        Vehicle vehicleTestEnergy = new Vehicle();
        Energy expResult = null;
        Energy result = vehicleTestEnergy.getEnergy();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasValidLoad, of class Vehicle.
     */
    @Test
    public void testHasValidLoad() {
        assertTrue(instance.hasValidLoad(new Measurable(0.0, Unit.KILOGRAM)));
    }

    /**
     * Test of calculateAccelerationForce, of class Vehicle.
     */
    @Test
    public void testCalculateAccelerationForce() {
        assertEquals(instance.calculateAccelerationForce(new Measurable(0.0, Unit.KILOGRAM), new Measurable(0.0, Unit.MILES_PER_HOUR)), new Measurable((new Measurable(1.0, Unit.KILOGRAM).getQuantity() + new Measurable(0.0, Unit.KILOGRAM).getQuantity()) * new Measurable(0.0, Unit.MILES_PER_HOUR).getQuantity(), Unit.NEWTON));
    }

}
