package lapr.project.model.RoadNetwork;

import java.io.File;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

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


}