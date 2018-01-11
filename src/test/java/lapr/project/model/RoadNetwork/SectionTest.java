package lapr.project.model.RoadNetwork;

import lapr.project.model.Vehicle.*;
import lapr.project.utils.EnergyExpenditureAccelResults;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
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

        Section sectionTest1 = new Section(node1, node2, Direction.BIDIRECTIONAL, segments1, new Road("A01", "A01", "toll highway"), new ArrayList<>());
        Section sectionTest2 = new Section(node3, node4, Direction.BIDIRECTIONAL, segments2, new Road("E02", "E02", "regular road"), new ArrayList<>());
        roadNetworkTest1.addSection(node1, node2, sectionTest1);
        roadNetworkTest1.addSection(node3, node4, sectionTest2);

        List<VelocityLimit> velocityLimitList = new ArrayList<>();
        velocityLimitList.add(new VelocityLimit("Highway", new Measurable(110, Unit.KILOMETERS_PER_HOUR)));
        velocityLimitList.add(new VelocityLimit("Road", new Measurable(80, Unit.KILOMETERS_PER_HOUR)));

        //vehicle with velocity limits - highway
        Vehicle vehicleTest1 = new Vehicle("name1", "description", VehicleType.Car, 1,
                Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(0, Unit.KILOGRAM),
                new Measurable(0, Unit.KILOGRAM), 0f, new Measurable(0, Unit.METER_SQUARED), 0f,
                new Measurable(0, Unit.METER), velocityLimitList, new Energy(0, 0, 0f, new ArrayList<>(),
                new ArrayList<>()));

        double expected = (double) 50/110 + (double) 100/90 + (double) 75/70 + (double) 100/100;
        double result = sectionTest1.calculateTotalMinimumTimeInterval(roadNetworkTest1, vehicleTest1);

        assertEquals(expected, result, 0.0001);

        //vehicle without velocity limits - highway
        Vehicle vehicleTest2 = new Vehicle("name2", "description", VehicleType.Car, 1,
                Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(0, Unit.KILOGRAM),
                new Measurable(0, Unit.KILOGRAM), 0f, new Measurable(0, Unit.METER_SQUARED),
                0f, new Measurable(0, Unit.METER),
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

    /**
     * Ensures the method calculateEnergyExpenditureAccel returns the correct EnergyExpenditureAccelResults
     */
    @Test
    public void ensureEnergyExpenditureAccelResultsAreCorrect() {

        RoadNetwork roadNetworkTest = new RoadNetwork();

        Node nodeTest1 = new Node("n01");
        Node nodeTest2 = new Node("n02");

        roadNetworkTest.addNode(nodeTest1);
        roadNetworkTest.addNode(nodeTest2);

        Collection<Segment> segmentsTest = new ArrayList<>();
        segmentsTest.add(new Segment(0, 0, 100, 5, 30, 1.5, 120, 0));
        segmentsTest.add(new Segment(1, 100, 250, 3.5, -10, 1, 100, 0));

        List<Double> tollFaresRoadTest = new ArrayList<>();
        tollFaresRoadTest.add(0.15);
        tollFaresRoadTest.add(0.25);
        tollFaresRoadTest.add(0.35);

        Section sectionTest = new Section(nodeTest1, nodeTest2, Direction.BIDIRECTIONAL, segmentsTest,
                new Road("A01", "A01", "toll highway", tollFaresRoadTest), new ArrayList<>());

        roadNetworkTest.addSection(nodeTest1, nodeTest2, sectionTest);

        List<VelocityLimit> velocityLimitListTest = new ArrayList<>();
        velocityLimitListTest.add(new VelocityLimit("Highway", new Measurable(110, Unit.KILOMETERS_PER_HOUR)));
        velocityLimitListTest.add(new VelocityLimit("Road", new Measurable(80, Unit.KILOMETERS_PER_HOUR)));

        List<Gears> gearsTest = new ArrayList<>();
        gearsTest.add(new Gears(1, 4.5f));
        gearsTest.add(new Gears(2, 3.5f));
        gearsTest.add(new Gears(3, 2.7f));
        gearsTest.add(new Gears(4, 1.6f));
        gearsTest.add(new Gears(5, 1.2f));
        gearsTest.add(new Gears(6, 0.9f));

        List<Regime> regimes25Test = new ArrayList<>();
        regimes25Test.add(new Regime(115, 125, 900, 1499, 500));
        regimes25Test.add(new Regime(125, 120, 1500, 2499, 450));
        regimes25Test.add(new Regime(120, 105, 2500, 3499, 520));
        regimes25Test.add(new Regime(105, 90, 3500, 4499, 550));
        regimes25Test.add(new Regime(90, 80, 4500, 5500, 650));

        List<Regime> regimes50Test = new ArrayList<>();
        regimes50Test.add(new Regime(185, 195, 900, 1499, 380));
        regimes50Test.add(new Regime(195, 190, 1500, 2499, 350));
        regimes50Test.add(new Regime(190, 180, 2500, 3499, 360));
        regimes50Test.add(new Regime(180, 150, 3500, 4499, 400));
        regimes50Test.add(new Regime(150, 135, 4500, 5500, 520));

        List<Regime> regimes100Test = new ArrayList<>();
        regimes100Test.add(new Regime(305, 325, 900, 1499, 380));
        regimes100Test.add(new Regime(325, 315, 1500, 2499, 350));
        regimes100Test.add(new Regime(315, 290, 2500, 3499, 360));
        regimes100Test.add(new Regime(290, 220, 3500, 4499, 400));
        regimes100Test.add(new Regime(220, 205, 4500, 5500, 520));

        List<Throttle> throttlesTest = new ArrayList<>();
        throttlesTest.add(new Throttle(25, regimes25Test));
        throttlesTest.add(new Throttle(50, regimes50Test));
        throttlesTest.add(new Throttle(100, regimes100Test));

        Vehicle vehicleTest = new Vehicle("Toyota", "Vehicle 1", VehicleType.Car, 1, Vehicle.MotorType.COMBUSTION, Fuel.Diesel,
                new Measurable(1500, Unit.KILOGRAM), new Measurable(7500, Unit.KILOGRAM), 0.320f,
                new Measurable(1.9, Unit.METER_SQUARED), 0.01f, new Measurable(0.6, Unit.METER),
                velocityLimitListTest, new Energy(900, 5500, 4f, gearsTest, throttlesTest));

        EnergyExpenditureAccelResults expected = new EnergyExpenditureAccelResults(new Measurable(82600, Unit.KILOJOULE),
                new Measurable(5.6548668, Unit.KILOMETERS_PER_HOUR), new Measurable(0.09383, Unit.HOUR), new Measurable(1.275, Unit.EUROS));

        EnergyExpenditureAccelResults results = sectionTest.calculateEnergyExpenditureAccel(roadNetworkTest, new Measurable(5.6548668, Unit.KILOMETERS_PER_HOUR), vehicleTest,
                new Measurable(500, Unit.KILOGRAM), new Measurable(5, Unit.METERS_PER_SECOND_SQUARED),
                new Measurable(-2.5, Unit.METERS_PER_SECOND_SQUARED), nodeTest2);

        assertEquals(expected.getEnergyExpenditure().getQuantity(), results.getEnergyExpenditure().getQuantity(), 100);
        assertEquals(expected.getFinalVelocity().getQuantity(), results.getFinalVelocity().getQuantity(), 0.001);
        assertEquals(expected.getTimeSpent().getQuantity(), results.getTimeSpent().getQuantity(), 0.001);
        assertEquals(expected.getTollCosts().getQuantity(), results.getTollCosts().getQuantity(), 0.01);

    }
}