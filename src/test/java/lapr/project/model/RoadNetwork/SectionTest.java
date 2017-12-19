package lapr.project.model.RoadNetwork;

import lapr.project.model.Vehicle.*;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        roadNetworkTest1.addNode(node1);
        roadNetworkTest1.addNode(node2);
        roadNetworkTest1.addNode(node3);
        roadNetworkTest1.addNode(node4);

        Section sectionTest1 = new Section(node1, node2, Direction.BIDIRECTIONAL, segments1, new Road("A01", "A01", "toll highway"));
        Section sectionTest2 = new Section(node3, node4, Direction.BIDIRECTIONAL, segments2, new Road("E02", "E02", "regular road"));
        roadNetworkTest1.addSection(node1, node2, sectionTest1);
        roadNetworkTest1.addSection(node3, node4, sectionTest2);

        List<VelocityLimit> velocityLimitList = new ArrayList<>();
        velocityLimitList.add(new VelocityLimit("Highway", new Measurable(110, Unit.KILOMETERSPERHOUR)));
        velocityLimitList.add(new VelocityLimit("Road", new Measurable(80, Unit.KILOMETERSPERHOUR)));

        //vehicle with velocity limits - highway
        Vehicle vehicleTest1 = new Vehicle("name1", "description", VehicleType.Car, 1,
                Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(0, Unit.KILOGRAM),
                new Measurable(0, Unit.KILOGRAM), 0f, 0f, 0f, 0f,
                velocityLimitList, new Energy(0, 0, 0f, new ArrayList<>(),
                new ArrayList<>()));

        double expected = (double) 50/110 + (double) 100/90 + (double) 75/70 + (double) 100/100;
        double result = sectionTest1.calculateTotalMinimumTimeInterval(roadNetworkTest1, vehicleTest1);

        assertEquals(expected, result, 0.0001);

        //vehicle without velocity limits - highway
        Vehicle vehicleTest2 = new Vehicle("name2", "description", VehicleType.Car, 1,
                Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(0, Unit.KILOGRAM),
                new Measurable(0, Unit.KILOGRAM), 0f, 0f, 0f, 0f,
                new ArrayList<>(), new Energy(0, 0, 0f, new ArrayList<>(),
                new ArrayList<>()));

        expected = (double) 50/120 + (double) 100/90 + (double) 75/70 + (double) 100/100;
        result = sectionTest1.calculateTotalMinimumTimeInterval(roadNetworkTest1, vehicleTest2);

        assertEquals(expected, result, 0.0001);

        //vehicle with velocity limits - road
        expected = (double) 50/80 + (double) 100/80 + (double) 75/70 + (double) 100/80;
        result = sectionTest2.calculateTotalMinimumTimeInterval(roadNetworkTest1, vehicleTest1);

        assertEquals(expected, result, 0.0001);

        //vehicle without velocity limits - road
        expected = (double) 50/120 + (double) 100/90 + (double) 75/70 + (double) 100/100;
        result = sectionTest2.calculateTotalMinimumTimeInterval(roadNetworkTest1, vehicleTest2);

        assertEquals(expected, result, 0.0001);

    }

}