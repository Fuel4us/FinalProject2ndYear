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

}