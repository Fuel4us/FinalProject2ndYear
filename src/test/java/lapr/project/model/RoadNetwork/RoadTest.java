package lapr.project.model.RoadNetwork;

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
     *
     * @throws Exception
     */
    @Test
    public void ensureEqualsReturnsFalseSuccessfully() throws Exception {
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
        List<Float> tollFare = new ArrayList<>();

        Road roadTest = new Road(id, name, typology, tollFare);

        int expected = -135370504;

        int result = roadTest.hashCode();

        assertEquals(expected, result);

    }

}