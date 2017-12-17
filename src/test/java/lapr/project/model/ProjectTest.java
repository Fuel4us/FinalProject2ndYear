package lapr.project.model;

import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.Vehicle.Vehicle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class ProjectTest {

    /**
     * Ensures the getters for the attributes name, description, roadNetwork and vehicles are
     * returning successfully
     *
     * @throws Exception
     */
    @Test
    public void ensureGettersReturnSuccessfully() throws Exception {

        String nameTest = "Name 1";
        String descriptionTest = "Description 1";
        RoadNetwork roadNetworkTest = new RoadNetwork(false);
        List<Vehicle> vehiclesTest = new ArrayList<>();

        Project p1 = new Project(nameTest, descriptionTest, roadNetworkTest, vehiclesTest);

        assertEquals(p1.getName(), nameTest);
        assertEquals(p1.getDescription(), descriptionTest);
        assertEquals(p1.getRoadNetwork(), roadNetworkTest);
        assertEquals(p1.getVehicles(), vehiclesTest);
    }


    /**
     * Ensures hashCode() returns the correct number of its calculation
     * @throws Exception
     */
    @Test
    public void ensureHashCodeCalculatesSuccessfully() throws Exception {

        //name not null
        Project p1 = new Project("Name 1", "Description 1",
                new RoadNetwork(false), new ArrayList<>());

        int expected = p1.getName().hashCode();
        int result = p1.hashCode();

        assertEquals(expected, result);

        //name null
        Project p2 = new Project(null, "Description 2",
                new RoadNetwork(false), new ArrayList<>());

        expected = 0;
        result = p2.hashCode();

        assertEquals(expected, result);
    }

    /**
     * Ensures equals() returns true:
     * - in the first condition
     * - in the final return
     * @throws Exception
     */
    @Test
    public void ensureEqualsReturnsTrue() throws Exception {

        //same objects
        Project p1 = new Project("Name 1", "Description 1",
                new RoadNetwork(false), new ArrayList<>());

        boolean result = Objects.equals(p1, p1);

        assertEquals(true, result);

        //objects with the same name but different descriptions
        Project p2 = new Project("Name 1", "Description 2",
                new RoadNetwork(false), new ArrayList<>());

        result = Objects.equals(p1, p2);

        assertEquals(true, result);

        //objects with both names null
        Project p3 = new Project(null, "Description 3",
                new RoadNetwork(false), new ArrayList<>());

        Project p4 = new Project(null, "Description 4",
                new RoadNetwork(false), new ArrayList<>());

        result = Objects.equals(p3, p4);

        assertEquals(true, result);
    }

    /**
     * Ensures equals() returns false:
     * - in the second condition
     * - in the final return
     * @throws Exception
     */
    @Test
    public void ensureEqualsReturnsFalse() throws Exception {

        //object p2 is null
        Project p1 = new Project("Name 1", "Description 1",
                new RoadNetwork(false), new ArrayList<>());

        Project p2 = null;

        boolean result = Objects.equals(p1, p2);

        assertEquals(false, result);

        //object p3 has the name null but the p4 doesn't
        Project p3 = new Project(null, "Description 3",
                new RoadNetwork(false), new ArrayList<>());

        Project p4 = new Project("Name 4", "Description 4",
                new RoadNetwork(false), new ArrayList<>());

        result = Objects.equals(p3, p4);

        assertEquals(false, result);
    }

}