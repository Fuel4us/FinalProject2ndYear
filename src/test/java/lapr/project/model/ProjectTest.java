package lapr.project.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

import static org.junit.Assert.*;
import org.junit.Before;

public class ProjectTest {

    List<Project> listProject = new ArrayList<>();
    Project p1;
    Project p2;
    Vehicle v1 = new Vehicle("Pick_up", "", VehicleType.Car, 0, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(1.0, Unit.KILOGRAM), new Measurable(1.0, Unit.KILOMETERS_PER_HOUR), 1f, new Measurable(1, Unit.METER_SQUARED), 1f, new Measurable(0, Unit.METER), new ArrayList<>(), new Energy(0, 0, 0, new ArrayList<>(), new ArrayList<>()));
    Vehicle v2 = new Vehicle("Pick_up2", "", VehicleType.Car, 0, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(1.0, Unit.KILOGRAM), new Measurable(1.0, Unit.KILOMETERS_PER_HOUR), 1f, new Measurable(1, Unit.METER_SQUARED), 1f, new Measurable(0, Unit.METER), new ArrayList<>(), new Energy(0, 0, 0, new ArrayList<>(), new ArrayList<>()));
    List<Vehicle> vehicleList = new ArrayList<>();
    RoadNetwork rn = new RoadNetwork();

    @Before
    public void setUp() throws Exception {

        vehicleList.add(v1);
        vehicleList.add(v2);
        this.p1 = new Project("Alpha", "Alpha", "Alpha test network", rn, vehicleList);
        this.p2 = new Project("Alpha", "Alpha", "Beta test network", rn, vehicleList);
    }

    /**
     * Ensures the getters for the attributes name, description, roadNetwork and
     * vehicles are returning successfully
     *
     * @throws Exception
     */
    @Test
    public void ensureGettersReturnSuccessfully() throws Exception {
        assertEquals(p1.getName(), "Alpha");
        assertEquals(p1.getId(),"Alpha identification");
        assertEquals(p1.getDescription(), "Alpha test network");
        assertEquals(p1.getRoadNetwork(), rn);
        assertEquals(p1.getVehicles(), vehicleList);
    }

    /**
     * Ensures hashCode() returns the correct number of its calculation
     *
     * @throws Exception
     */
    @Test
    public void ensureHashCodeCalculatesSuccessfully() throws Exception {

        // name not null
        int expected = p1.getName().hashCode();
        int result = p1.hashCode();

        assertEquals(expected, result);

        // name null
        expected = 63357246;
        result = p2.hashCode();

        assertEquals(expected, result);
    }

    /**
     * Ensures equals() returns true: - in the first condition - in the final
     * return
     *
     * @throws Exception
     */
    @Test
    public void ensureEqualsReturnsTrue() throws Exception {

        //same objects
        boolean result = Objects.equals(p1, p1);
        assertEquals(true, result);

        //objects with the same name but different descriptions
        result = Objects.equals(p1, p2);
        assertEquals(true, result);

        //objects with both names null
        Project p3 = new Project(null, null, "Description 3",
                new RoadNetwork(false), new ArrayList<>());

        Project p4 = new Project(null, null, "Description 4",
                new RoadNetwork(false), new ArrayList<>());

        result = Objects.equals(p3, p4);

        assertEquals(true, result);
        
        // class
        Class<? extends Project> resultClass = p3.getClass();
        Class<? extends Project> expResult = p4.getClass();
        assertEquals(expResult, resultClass);
        
    }

    /**
     * Ensures equals() returns false: - in the second condition - in the final
     * return
     *
     * @throws Exception
     */
    @Test
    public void ensureEqualsReturnsFalse() throws Exception {

        Project p3 = new Project(null,null, "Description 3",
                new RoadNetwork(false), new ArrayList<>());

        Project p4 = new Project("Name4", "Name 4", "Description 4",
                new RoadNetwork(false), new ArrayList<>());

        //object p2 is null
        boolean result = Objects.equals(p1, p3);
        assertEquals(false, result);

        //object p3 has the name null but the p4 doesn't
        result = Objects.equals(p3, p4);
        assertEquals(false, result);
    }

    /**
     * Test of addNameIfEquals method, of class Project.
     */
    @Test
    public void testAddNameIfEquals() {

        Project p3;
        p3 = new Project("Alpha","Alpha", "", null, null);
        List<Project> listProjectTest = new ArrayList<>();
        listProjectTest.add(p3);

        String result = p3.addNameIfEquals(listProjectTest, p3.getName());
        String expResult = ("Alpha1");

        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Project.
     */
    @Test
    public void testEquals() {
        Project instance = new Project();
        Project instance2 = new Project();
        assertEquals(instance,instance2);
        assertNotEquals(v1,instance);
    }

    /**
     * Test of setName method, of class Project.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "testProject";
        Project instance = new Project();
        instance.setName(name);
        assertEquals(instance.getName(),name);
    }

    /**
     * Test of setDescription method, of class Project.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "description";
        Project instance = new Project();
        instance.setDescription(description);
        assertEquals(instance.getDescription(),description);
    }

    /**
     * Test of cloneProject method, of class Project.
     * @throws java.lang.Exception
     */
    @Test
    public void testCloneProject() throws Exception {
        System.out.println("cloneProject");
        Project instance = new Project();
        Project expResult = new Project(instance.getId()+" (Copy)", instance.getName()+" (Copy)",instance.getDescription() + " (Copy)",instance.getRoadNetwork(),instance.getVehicles());
        Project result = instance.cloneProject();
        assertEquals(expResult, result);
    }

    @Test
    public void ensureToStringEqualsName() throws Exception {
        Project project = new Project("sample", "sample", "description", null, null);
        assert project.toString().equals(project.getId());
    }

}
