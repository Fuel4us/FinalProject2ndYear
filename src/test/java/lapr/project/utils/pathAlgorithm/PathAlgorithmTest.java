package lapr.project.utils.pathAlgorithm;

import lapr.project.model.*;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class to the class PathAlgorithm
 */
public class PathAlgorithmTest {

    /**
     * Ensures the method fastestPath() returns the correct Analysis
     * @throws Exception
     */
    @Test
    public void ensureFastestPathReturnsCorrectAnalysis() throws Exception {

        RoadNetwork roadNetworkTest = new RoadNetwork("1", "the road network");

        Node nodeTest1 = new Node("n01");
        Node nodeTest2 = new Node("n02");
        Node nodeTest3 = new Node("n03");
        Node nodeTest4 = new Node("n04");

        roadNetworkTest.addNode(nodeTest1);
        roadNetworkTest.addNode(nodeTest2);
        roadNetworkTest.addNode(nodeTest3);
        roadNetworkTest.addNode(nodeTest4);

        List<Double> tollFaresRoadTest = new ArrayList<>();
        tollFaresRoadTest.add(0.15);
        tollFaresRoadTest.add(0.25);
        tollFaresRoadTest.add(0.35);

        Road roadTest1 = new Road("A01", "A01", "gantry toll highway");
        Road roadTest2 = new Road("A02", "A02", "toll highway", tollFaresRoadTest);
        Road roadTest3 = new Road("E01", "E01", "regular road");

        Segment segmentTest1 = new Segment(0, 250, 50, 2.5, 10, 2.5, 120, 50);
        Segment segmentTest3 = new Segment(0, 50, 250, 25, -30, 1.75, 90, 50);
        Segment segmentTest5 = new Segment(0, 250, 50, 50, 10, 2.5, 90, 0);
        Segment segmentTest6 = new Segment(0, 50, 400, 1, 10, 2.5, 90, 0);

        List<Segment> segmentsTest1 = new ArrayList<>();
        segmentsTest1.add(segmentTest1);

        List<Segment> segmentsTest2 = new ArrayList<>();
        segmentsTest2.add(segmentTest3);

        List<Segment> segmentsTest3 = new ArrayList<>();
        segmentsTest3.add(segmentTest5);
        segmentsTest3.add(segmentTest6);

        List<Double> tollFaresSectionTest = new ArrayList<>();
        tollFaresSectionTest.add(0.25);
        tollFaresSectionTest.add(0.35);
        tollFaresSectionTest.add(0.40);

        Section sectionTest1 = new Section(nodeTest1, nodeTest2, Direction.BIDIRECTIONAL, segmentsTest1, roadTest1, tollFaresSectionTest);
        Section sectionTest2 = new Section(nodeTest2, nodeTest3, Direction.BIDIRECTIONAL, segmentsTest2, roadTest2, new ArrayList<>());
        Section sectionTest3 = new Section(nodeTest3, nodeTest4, Direction.BIDIRECTIONAL, segmentsTest3, roadTest3, new ArrayList<>());

        roadNetworkTest.addSection(nodeTest1, nodeTest2, sectionTest1);
        roadNetworkTest.addSection(nodeTest2, nodeTest3, sectionTest2);
        roadNetworkTest.addSection(nodeTest3, nodeTest4, sectionTest3);

        List<VelocityLimit> velocityLimitListTest = new ArrayList<>();
        velocityLimitListTest.add(new VelocityLimit("highway", new Measurable(110, Unit.KILOMETERS_PER_HOUR)));
        velocityLimitListTest.add(new VelocityLimit("road", new Measurable(80, Unit.KILOMETERS_PER_HOUR)));

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

        Vehicle vehicle1 = new Vehicle("Toyota", "Vehicle 1", VehicleType.Car, 1, Vehicle.MotorType.COMBUSTION, Fuel.Diesel, new Measurable(1500, Unit.KILOGRAM),
                new Measurable(7500, Unit.KILOGRAM), 0.320f, new Measurable(1.9, Unit.METER_SQUARED), 0.01f, new Measurable(0.6, Unit.METER),
                velocityLimitListTest, new Energy(900, 5500, 4f, gearsTest, throttlesTest));

        Vehicle vehicle2 = new Vehicle("BMW", "Vehicle 2", VehicleType.Car, 1, Vehicle.MotorType.NONCOMBUSTION, Fuel.Electric, new Measurable(2000, Unit.KILOGRAM),
                new Measurable(0, Unit.KILOGRAM), 0.320f, new Measurable(1.9, Unit.METER_SQUARED), 0.01f, new Measurable(0.6, Unit.METER),
                velocityLimitListTest, new Energy(900, 5500, 4f, gearsTest, throttlesTest));

        List<Vehicle> vehiclesTest = new ArrayList<>();
        vehiclesTest.add(vehicle1);
        vehiclesTest.add(vehicle2);

        Project projectTest = new Project("project 1","project 1", "the project", roadNetworkTest, vehiclesTest);

        List<Section> sectionsExpected = new ArrayList<>();
        sectionsExpected.add(sectionTest1);
        sectionsExpected.add(sectionTest2);
        sectionsExpected.add(sectionTest3);

        Measurable load1 = new Measurable(500, Unit.KILOMETER);

        Analysis analysisResult = PathAlgorithm.fastestPath(projectTest, nodeTest1, nodeTest4, vehicle1, load1);

        Analysis analysisExpected = new Analysis(projectTest, "N10 - Fastest Path",
                sectionsExpected, new Measurable(808941, Unit.KILOJOULE), new Measurable(0.943, Unit.HOUR), new Measurable(4, Unit.EUROS));

        assertEquals(analysisExpected.getAlgorithmName(), analysisResult.getAlgorithmName());
        assertEquals(analysisExpected.getRequestingInstance(), analysisResult.getRequestingInstance());
        assertEquals(analysisExpected.getBestPath(), analysisResult.getBestPath());
        assertEquals(analysisExpected.getExpendedEnergy().getQuantity(), analysisResult.getExpendedEnergy().getQuantity(), 2000);
        assertEquals(analysisExpected.getTravelTime().getQuantity(), analysisResult.getTravelTime().getQuantity(), 0.1);
        assertEquals(analysisExpected.getTravelCost().getQuantity(), analysisResult.getTravelCost().getQuantity(), 0.1);

        assertThrows(IllegalArgumentException.class, () -> PathAlgorithm.fastestPath(projectTest, nodeTest1, nodeTest4, vehicle2, load1));

    }

    /**
     * Ensures the algorithm theoreticalEfficientPath returns the correct Analysis
     */
    @Test
    public void ensureTheoreticalEfficientPathReturnsCorrectAnalysis() {

        RoadNetwork roadNetworkTest = new RoadNetwork();

        Node nodeTest1 = new Node("n01");
        Node nodeTest2 = new Node("n02");
        Node nodeTest3 = new Node("n03");

        roadNetworkTest.addNode(nodeTest1);
        roadNetworkTest.addNode(nodeTest2);
        roadNetworkTest.addNode(nodeTest3);

        Collection<Segment> segmentsTest1 = new ArrayList<>();
        segmentsTest1.add(new Segment(0, 0, 100, 5, 30, 1.5, 120, 0));
        Collection<Segment> segmentsTest2 = new ArrayList<>();
        segmentsTest2.add(new Segment(0, 100, 250, 3.5, -10, 1, 100, 0));

        List<Double> tollFaresRoadTest = new ArrayList<>();
        tollFaresRoadTest.add(0.15);
        tollFaresRoadTest.add(0.25);
        tollFaresRoadTest.add(0.35);

        Section sectionTest1 = new Section(nodeTest1, nodeTest2, Direction.BIDIRECTIONAL, segmentsTest1,
                new Road("A01", "A01", "toll highway", tollFaresRoadTest), new ArrayList<>());

        Section sectionTest2 = new Section(nodeTest2, nodeTest3, Direction.BIDIRECTIONAL, segmentsTest2,
                new Road("A01", "A01", "toll highway", tollFaresRoadTest), new ArrayList<>());

        roadNetworkTest.addSection(nodeTest1, nodeTest2, sectionTest1);
        roadNetworkTest.addSection(nodeTest2, nodeTest3, sectionTest2);

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

        List<Vehicle> vehiclesTest = new ArrayList<>();
        vehiclesTest.add(vehicleTest);

        Project projectTest = new Project("name", "name", "description", roadNetworkTest, vehiclesTest);

        List<Section> sectionsExpected = new ArrayList<>();
        sectionsExpected.add(sectionTest1);
        sectionsExpected.add(sectionTest2);

        Analysis expected = new Analysis(projectTest, "N11 - Theoretical Most Energy Efficient Path", sectionsExpected,
                new Measurable(86287, Unit.KILOJOULE), new Measurable(0.0789763, Unit.HOUR), new Measurable(1.275, Unit.EUROS));

        Analysis result = PathAlgorithm.theoreticalEfficientPath(projectTest, nodeTest1, nodeTest3, vehicleTest, new Measurable(5, Unit.METERS_PER_SECOND_SQUARED),
                new Measurable(-2.5, Unit.METERS_PER_SECOND_SQUARED), new Measurable(500, Unit.KILOGRAM));

        assertEquals(expected.getAlgorithmName(), result.getAlgorithmName());
        assertEquals(expected.getRequestingInstance(), result.getRequestingInstance());
        assertEquals(expected.getBestPath(), result.getBestPath());
        assertEquals(expected.getExpendedEnergy().getQuantity(), result.getExpendedEnergy().getQuantity(), 500);
        assertEquals(expected.getTravelTime().getQuantity(), result.getTravelTime().getQuantity(), 0.01);
        assertEquals(expected.getTravelCost().getQuantity(), result.getTravelCost().getQuantity(), 0.01);

    }

    /**
     * Ensures the algorithm efficientPathEnergySavingMode returns the correct Analysis
     */
    @Test
    public void ensureEfficientPathEnergySavingModeReturnsCorrectAnalysis() {

        RoadNetwork roadNetworkTest = new RoadNetwork();

        Node nodeTest1 = new Node("n01");
        Node nodeTest2 = new Node("n02");
        Node nodeTest3 = new Node("n03");

        roadNetworkTest.addNode(nodeTest1);
        roadNetworkTest.addNode(nodeTest2);
        roadNetworkTest.addNode(nodeTest3);

        Collection<Segment> segmentsTest1 = new ArrayList<>();
        segmentsTest1.add(new Segment(0, 0, 100, 5, 30, 1.5, 120, 0));
        Collection<Segment> segmentsTest2 = new ArrayList<>();
        segmentsTest2.add(new Segment(0, 100, 250, 3.5, -10, 1, 100, 0));

        List<Double> tollFaresRoadTest = new ArrayList<>();
        tollFaresRoadTest.add(0.15);
        tollFaresRoadTest.add(0.25);
        tollFaresRoadTest.add(0.35);

        Section sectionTest1 = new Section(nodeTest1, nodeTest2, Direction.BIDIRECTIONAL, segmentsTest1,
                new Road("A01", "A01", "toll highway", tollFaresRoadTest), new ArrayList<>());

        Section sectionTest2 = new Section(nodeTest2, nodeTest3, Direction.BIDIRECTIONAL, segmentsTest2,
                new Road("A01", "A01", "toll highway", tollFaresRoadTest), new ArrayList<>());

        roadNetworkTest.addSection(nodeTest1, nodeTest2, sectionTest1);
        roadNetworkTest.addSection(nodeTest2, nodeTest3, sectionTest2);

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

        List<Vehicle> vehiclesTest = new ArrayList<>();
        vehiclesTest.add(vehicleTest);

        Project projectTest = new Project("name","name", "description", roadNetworkTest, vehiclesTest);

        List<Section> sectionsExpected = new ArrayList<>();
        sectionsExpected.add(sectionTest1);
        sectionsExpected.add(sectionTest2);

        Analysis expected = new Analysis(projectTest, "N12 - Efficient Path in Energy Saving Mode", sectionsExpected,
                new Measurable(74027, Unit.KILOJOULE), new Measurable(0.112198, Unit.HOUR), new Measurable(1.275, Unit.EUROS));

        Analysis result = PathAlgorithm.efficientPathEnergySavingMode(projectTest, nodeTest1, nodeTest3, vehicleTest, new Measurable(5, Unit.METERS_PER_SECOND_SQUARED),
                new Measurable(-1, Unit.METERS_PER_SECOND_SQUARED), new Measurable(500, Unit.KILOGRAM));

        assertEquals(expected.getAlgorithmName(), result.getAlgorithmName());
        assertEquals(expected.getRequestingInstance(), result.getRequestingInstance());
        assertEquals(expected.getBestPath(), result.getBestPath());
        assertEquals(expected.getExpendedEnergy().getQuantity(), result.getExpendedEnergy().getQuantity(), 500);
        assertEquals(expected.getTravelTime().getQuantity(), result.getTravelTime().getQuantity(), 0.01);
        assertEquals(expected.getTravelCost().getQuantity(), result.getTravelCost().getQuantity(), 0.01);

    }

    /**
     * Ensures the algorithm efficientPathPolynomialInterpolation returns the correct Analysis
     */
    @Test
    public void ensureEfficientPathPolynomialInterpolationReturnsCorrectAnalysis() {

        RoadNetwork roadNetworkTest = new RoadNetwork();

        Node nodeTest1 = new Node("n01");
        Node nodeTest2 = new Node("n02");
        Node nodeTest3 = new Node("n03");

        roadNetworkTest.addNode(nodeTest1);
        roadNetworkTest.addNode(nodeTest2);
        roadNetworkTest.addNode(nodeTest3);

        Collection<Segment> segmentsTest1 = new ArrayList<>();
        segmentsTest1.add(new Segment(0, 0, 100, 5, 30, 1.5, 120, 0));
        Collection<Segment> segmentsTest2 = new ArrayList<>();
        segmentsTest2.add(new Segment(0, 100, 250, 3.5, -10, 1, 100, 0));

        List<Double> tollFaresRoadTest = new ArrayList<>();
        tollFaresRoadTest.add(0.15);
        tollFaresRoadTest.add(0.25);
        tollFaresRoadTest.add(0.35);

        Section sectionTest1 = new Section(nodeTest1, nodeTest2, Direction.BIDIRECTIONAL, segmentsTest1,
                new Road("A01", "A01", "toll highway", tollFaresRoadTest), new ArrayList<>());

        Section sectionTest2 = new Section(nodeTest2, nodeTest3, Direction.BIDIRECTIONAL, segmentsTest2,
                new Road("A01", "A01", "toll highway", tollFaresRoadTest), new ArrayList<>());

        roadNetworkTest.addSection(nodeTest1, nodeTest2, sectionTest1);
        roadNetworkTest.addSection(nodeTest2, nodeTest3, sectionTest2);

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

        List<Vehicle> vehiclesTest = new ArrayList<>();
        vehiclesTest.add(vehicleTest);

        Project projectTest = new Project("name","name", "description", roadNetworkTest, vehiclesTest);

        List<Section> sectionsExpected = new ArrayList<>();
        sectionsExpected.add(sectionTest1);
        sectionsExpected.add(sectionTest2);

        Analysis expected = new Analysis(projectTest, "N13 - Efficient Path with Polynomial Interpolation", sectionsExpected,
                new Measurable(74027, Unit.KILOJOULE), new Measurable(0.112198, Unit.HOUR), new Measurable(1.275, Unit.EUROS));

        Analysis result = PathAlgorithm.efficientPathPolynomialInterpolation(projectTest, nodeTest1, nodeTest3, vehicleTest, new Measurable(5, Unit.METERS_PER_SECOND_SQUARED),
                new Measurable(-1, Unit.METERS_PER_SECOND_SQUARED), new Measurable(500, Unit.KILOGRAM), true);

        assertEquals(expected.getAlgorithmName(), result.getAlgorithmName());
        assertEquals(expected.getRequestingInstance(), result.getRequestingInstance());
        assertEquals(expected.getBestPath(), result.getBestPath());
        assertEquals(expected.getExpendedEnergy().getQuantity(), result.getExpendedEnergy().getQuantity(), 500);
        assertEquals(expected.getTravelTime().getQuantity(), result.getTravelTime().getQuantity(), 0.01);
        assertEquals(expected.getTravelCost().getQuantity(), result.getTravelCost().getQuantity(), 0.01);

    }
}