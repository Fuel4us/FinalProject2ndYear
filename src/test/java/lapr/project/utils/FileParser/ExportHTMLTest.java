package lapr.project.utils.FileParser;

import lapr.project.model.Analysis;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.*;
import lapr.project.model.Vehicle.*;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit tests of class ExportHTML
 */
public class ExportHTMLTest {

    /**
     * Test of method exportDataFromAnalysis
     */
    @Test
    public void exportDataFromAnalysisTest() throws IOException {

        Collection<Segment> segments1 = new ArrayList<>();
        segments1.add(new Segment(0, 0, 0, 50, 0, 0, 120, 0));
        segments1.add(new Segment(1, 0, 0, 100, 0, 0, 90, 0));
        segments1.add(new Segment(2, 0, 0, 75, 0, 0, 70, 0));
        segments1.add(new Segment(3, 0, 0, 100, 0, 0, 100, 0));

        Collection<Segment> segments2 = new ArrayList<>();
        segments2.add(new Segment(0, 0, 0, 50, 0, 0, 120, 0));
        segments2.add(new Segment(1, 0, 0, 100, 0, 0, 90, 0));
        segments2.add(new Segment(2, 0, 0, 75, 0, 0, 70, 0));
        segments2.add(new Segment(3, 0, 0, 100, 0, 0, 100, 0));

        RoadNetwork roadNetworkTest1 = new RoadNetwork(true);

        Node node1 = new Node("n01");
        Node node2 = new Node("n02");
        Node node3 = new Node("n03");
        Node node4 = new Node("n04");

        Section sectionTest1 = new Section(node1, node2, Direction.BIDIRECTIONAL, segments1, new Road("A01", "A01", "toll highway"), new ArrayList<>());
        Section sectionTest2 = new Section(node3, node4, Direction.BIDIRECTIONAL, segments2, new Road("E02", "E02", "regular road"), new ArrayList<>());

        Collection<Section> bestPath = new LinkedList<>();
        bestPath.add(sectionTest1);
        bestPath.add(sectionTest2);
        roadNetworkTest1.addSection(node1, node2, sectionTest1);
        roadNetworkTest1.addSection(node3, node4, sectionTest2);


        List<VelocityLimit> velocityLimitList = new ArrayList<>();
        velocityLimitList.add(new VelocityLimit("Highway", new Measurable(110, Unit.KILOMETERS_PER_HOUR)));
        velocityLimitList.add(new VelocityLimit("Road", new Measurable(80, Unit.KILOMETERS_PER_HOUR)));

        //vehicle with velocity limits - highway
        Vehicle vehicleTest1 = new Vehicle("name1", "description", VehicleType.Car, 1,
                Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(0, Unit.KILOGRAM),
                new Measurable(0, Unit.KILOGRAM), 0f, new Measurable(0, Unit.METER_SQUARED),
                0f, new Measurable(0, Unit.METER),
                velocityLimitList, new Energy(0, 0, 0f, new ArrayList<>(),
                new ArrayList<>()));

        List<Vehicle> vehicles = new LinkedList<>();
        vehicles.add(vehicleTest1);

        Project project = new Project("name1", "Ola", roadNetworkTest1, vehicles);

        Analysis analysis = new Analysis(project, "N10", bestPath, new Measurable(300, Unit.KILOJOULE), new Measurable(3, Unit.HOUR), new Measurable(50, Unit.EUROS));

        ExportHTML exportHTML = new ExportHTML(analysis);
        File outputFile = new File("src\\main\\resources\\htmlFileStructure\\outputTestHTML.html");
        exportHTML.exportDataFromAnalysis(outputFile);
    }

}