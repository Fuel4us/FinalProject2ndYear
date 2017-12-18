package lapr.project.model.RoadNetwork;

import com.sun.media.sound.SoftEnvelopeGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Represents the test class for the Section class
 */
public class SectionTest {

    /**
     * Ensures the method calculateTotalMinimumTimeInterval() calculates the time correctly
     * @throws Exception
     */
    @Test
    public void ensureTotalMinimumTimeIntervalIsCorrectlyCalculated() throws Exception {

        Collection<Segment> segments = new ArrayList<>();
        segments.add(new Segment(0, 0, 0, 50, 0, 0, 120, 50));
        segments.add(new Segment(0, 0, 0, 100, 0, 0, 90, 50));
        segments.add(new Segment(0, 0, 0, 75, 0, 0, 90, 50));
        segments.add(new Segment(0, 0, 0, 100, 0, 0, 120, 50));

        Section sectionTest = new Section(new Node("n01"), new Node("n02"), Direction.BIDIRECTIONAL, segments, new Road("E01", "E01", "Typology"));

        double expected = (double) 50/100 + (double) 100/90 + (double) 75/90 + (double) 100/100;
        double result = sectionTest.calculateTotalMinimumTimeInterval(100);

        assertEquals(expected, result, 0.0001);

    }

}