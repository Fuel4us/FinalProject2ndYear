package lapr.project.model;

import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.utils.Graph.Graph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.*;

public class ProjectTest {

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
                new RoadNetwork<>(false, new Graph<>(false)), new ArrayList<>());

        boolean result = Objects.equals(p1, p1);

        assertEquals(true, result);

        //objects with the same name but different descriptions
        Project p2 = new Project("Name 1", "Description 2",
                new RoadNetwork<>(false, new Graph<>(false)), new ArrayList<>());

        result = Objects.equals(p1, p2);

        assertEquals(true, result);
    }

}