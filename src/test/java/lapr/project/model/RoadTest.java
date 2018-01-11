package lapr.project.model;

import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class RoadTest {

    /**
     * Ensures the method equals() returns true:
     * - same objects
     * - same content
     * @throws Exception
     */
    @Test
    public void ensureEqualsReturnsTrueSuccessfully() throws Exception {

        //same objects
        Road roadTest1 = new Road("id1", "name1", "typology1", new ArrayList<>());

        boolean result = Objects.equals(roadTest1, roadTest1);

        assert result;

        //same content, different objects
        Road roadTest2 = new Road("id1", "name1", "typology1", new ArrayList<>());

        result = Objects.equals(roadTest1, roadTest2);

        assert result;

    }

    /**
     * Ensures the method equals() returns false:
     * - different classes
     * - difference for each attribute
     * @throws Exception
     */
    @Test
    public void ensureEqualsReturnsFalseSuccessfully() throws Exception {

        Road roadTest1 = new Road("id1", "name1", "typology1", new ArrayList<>());

        Object roadTest2 = new Object();

        boolean result = Objects.equals(roadTest1, roadTest2);
        assertFalse(result);

        Road roadTest3 = new Road("id2", "name1", "typology1", new ArrayList<>());
        Road roadTest4 = new Road("id1", "name2", "typology1", new ArrayList<>());
        Road roadTest5 = new Road("id1", "name1", "typology3", new ArrayList<>());

        List<Double> list = new ArrayList<>();
        list.add(0d);

        Road roadTest6 = new Road("id1", "name1", "typology1", list);

        result = Objects.equals(roadTest1, roadTest3);
        assertFalse(result);

        result = Objects.equals(roadTest1, roadTest4);
        assertFalse(result);

        result = Objects.equals(roadTest1, roadTest5);
        assertFalse(result);

        result = Objects.equals(roadTest1, roadTest6);
        assertFalse(result);

    }

    /**
     * Ensures the method hashCode() returns the correct value
     * @throws Exception
     */
    @Test
    public void ensureHashCodeReturnsCorrectValue() throws Exception {

        String id = "id";
        String name = "name";
        String typology = "typology";
        List<Double> tollFare = new ArrayList<>();

        Road roadTest = new Road(id, name, typology, tollFare);

        int expected = -135370504;

        int result = roadTest.hashCode();

        assertEquals(expected, result);

    }

    /**
     * Test of getTypology method, of class Road.
     */
    @Test
    public void testGetTypology() {
        System.out.println("getTypology");
        Road instance = new Road("id","name","typo",new ArrayList<>());
        String expResult = "typo";
        String result = instance.getTypology();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Road.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Road instance = new Road("id","name","typo",new ArrayList<>());
        String expResult = "name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveVehicleClassRespectiveTollFare method, of class Road.
     */
    @Test
    public void testRetrieveVehicleClassRespectiveTollFare() {
        System.out.println("retrieveVehicleClassRespectiveTollFare");
        Vehicle vehicle = new Vehicle("Pick_up", "", VehicleType.Car, 1, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(1.0, Unit.KILOGRAM), new Measurable(1.0, Unit.KILOMETERS_PER_HOUR), 1f, new Measurable(1, Unit.METER_SQUARED), 1f, new Measurable(0, Unit.METER), new ArrayList<>(), new Energy(0, 0, 0, new ArrayList<>(), new ArrayList<>()));
        List<Double> toll = new ArrayList<>();
        toll.add(10.0);
        Road instance = new Road("id","name","typo",toll);
        double expResult = 10.0;
        double result = instance.retrieveVehicleClassRespectiveTollFare(vehicle);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of equals method, of class Road.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = (Object)(new Road("id","name","typo",new ArrayList<>()));
        Road instance = new Road("id","name","typo",new ArrayList<>());
        boolean result = instance.equals(o);
        Road instance2 = new Road("i","name","typo",new ArrayList<>());
        assertTrue(result);
        assertFalse(instance2.equals(o));
    }

    /**
     * Test of hashCode method, of class Road.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Road instance = new Road("id","name","typo",new ArrayList<>());;
        int expResult = -842041843;
        int result = instance.hashCode();
        System.out.println(result);
        assertEquals(expResult, result);
    }

}