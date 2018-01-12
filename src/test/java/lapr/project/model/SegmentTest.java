package lapr.project.model;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * JUnit tests of an instance {@link Segment}
 */
public class SegmentTest {

//    /**
//     * Test of method printDataFromSegment
//     * @throws IOException
//     */
//    @Test
//    public void ensureExportHtmlCorrectly() throws IOException {
//        int index = 1;
//        double initialHeight = 0.1;
//        double finalHeight = 5;
//        double length = 4;
//        double windAngle = 60;
//        double windSpeed = 1;
//        double maxVelocity = 40;
//        double minVelocity = 20;
//        Segment segment = new Segment(index, initialHeight, finalHeight, length, windAngle, windSpeed, maxVelocity, minVelocity);
//
//        FileWriter fillFile = new FileWriter("src\main\resources\htmlFileStructure\outputSegmentTest.html", true);
//        StringTemplateGroup groupSegment = new StringTemplateGroup("src\\main\\resources\\htmlFileStructure");
//        StringTemplate segmentTemplate = groupSegment.getInstanceOf("html_structure_segment");
//
//        segment.printDataFromSegment(segmentTemplate, fillFile);
//
//    }
    
    /**
     * Test of getID method, of class Section.
     */
    @Test
    public void testgetMinVelocity() {
        Segment test = new Segment(0,0.0,0.0,0.0,0.0,0.0,0.0,1.0);
        double expResult = 1.0;
        assertTrue(Double.compare(expResult, test.getMinVelocity())==0);
    }


}