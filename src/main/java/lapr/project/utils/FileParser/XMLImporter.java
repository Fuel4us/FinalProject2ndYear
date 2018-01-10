package lapr.project.utils.FileParser;

import lapr.project.model.RoadNetwork.*;
import lapr.project.model.Vehicle.*;
import lapr.project.utils.Graph.Edge;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles importation of instances of {@link RoadNetwork} and {@link Vehicle}s by parsing a XML file
 */
public class XMLImporter implements FileParser {

    private File roadsFile;
    private File vehiclesFile;

    private RoadNetwork roadNetwork;

    //Used to handle repeated vehicles
    private int id = 1;

    /**
     * Constructor to update an existing road network
     * @param roadsFile xml file
     * @param roadNetwork the road network already created
     */
    public XMLImporter(File roadsFile, RoadNetwork roadNetwork) {
        this.roadsFile = roadsFile;
        this.roadNetwork = roadNetwork;
    }

    /**
     * Constructor to create a new road network and read vehicles
     * @param roadsFile xmlFile
     * @param vehiclesFile
     */
    public XMLImporter(File roadsFile, File vehiclesFile) {
        this.roadsFile = roadsFile;
        this.vehiclesFile = vehiclesFile;
    }


    //RoadNetwork import methods

    /**
     * Reads RoadNetwork from file
     * @param newNetwork true if the road network is to be created as new
     * @return the road network updated
     */
    public RoadNetwork importNetwork(boolean newNetwork) throws IOException, SAXException, ParserConfigurationException {

        completeNetworkInformationDOMParsing(newNetwork);

        return roadNetwork;
    }

    /**
     * Creates document in order to complete information in the RoadNetwork
     * @param newNetwork true if the road network is to be created as new
     */
    private void completeNetworkInformationDOMParsing(boolean newNetwork) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(roadsFile);

        if (newNetwork) {
            createRoadNetwork(doc);
        }
        addNodes(doc);
        List<Road> roadList = addRoads(doc);
        addSections(roadList, doc);
    }

    private void createRoadNetwork(Document doc) {
        String id = doc.getElementsByTagName("Network").item(0).getAttributes().item(0).getTextContent();
        String description = doc.getElementsByTagName("Network").item(0).getAttributes().item(1).getTextContent();

        roadNetwork = new RoadNetwork(id, description);
    }

    /**
     * Adds nodes from the file in the RoadNetwork graph
     * @param doc the document
     */
    private void addNodes(Document doc) {

        NodeList nodes = doc.getElementsByTagName("node");

        for (int i = 0; i < nodes.getLength(); i++) {

            org.w3c.dom.Node node = nodes.item(i);

            if (node instanceof Element) {

                Element element = (Element) node;

                lapr.project.model.RoadNetwork.Node roadNetworkNode =
                        new lapr.project.model.RoadNetwork.Node(element.getAttribute("id"));

                List<lapr.project.model.RoadNetwork.Node> duplicateNodes = roadNetwork.getVertices().stream()
                        .filter(node1 -> node1.getId().equals(roadNetworkNode.getId()))
                        .collect(Collectors.toList());

                if (duplicateNodes.isEmpty()) {
                    roadNetwork.addNode(roadNetworkNode);
                }

            }

        }

    }

    /**
     * Adds roads from the file in the RoadNetwork graph
     * @param doc the document
     * @return the list of roads imported from the file
     */
    private List<Road> addRoads(Document doc) {

        List<Road> roadList = new ArrayList<>();

        NodeList roads = doc.getElementsByTagName("road");

        for (int i = 0; i < roads.getLength(); i++) {

            org.w3c.dom.Node node = roads.item(i);

            if (node instanceof Element) {

                Element element = (Element) node;

                String id = element.getAttributes().item(0).getTextContent();
                String name = element.getElementsByTagName("road_name").item(0).getTextContent();
                String typology = element.getElementsByTagName("typology").item(0).getTextContent();
                List<Double> tollFaresList = new ArrayList<>();

                if (element.getElementsByTagName("class").getLength() > 0) {

                    NodeList tollFares = element.getElementsByTagName("class");

                    for (int j = 0; j < tollFares.getLength(); j++) {

                        org.w3c.dom.Node tollFaresNode = tollFares.item(j);

                        if (tollFaresNode instanceof Element) {

                            Double tollFare = Double.parseDouble(tollFaresNode.getTextContent());

                            tollFaresList.add(tollFare);

                        }

                    }

                }

                List<Road> duplicateRoads = roadNetwork.retrieveAllRoads().stream()
                        .filter(road -> road.getName().equals(name) || road.getId().equals(id))
                        .collect(Collectors.toList());

                if (duplicateRoads.isEmpty()) {
                    roadList.add(new Road(id, name, typology, tollFaresList));
                }

            }

        }

        return roadList;

    }

    /**
     * Adds sections from the file in the RoadNetwork graph
     * @param roadList the list of roads
     * @param doc the document
     */
    private void addSections(List<Road> roadList, Document doc) {

        NodeList sections = doc.getElementsByTagName("road_section");

        for (int i = 0; i < sections.getLength(); i++) {

            org.w3c.dom.Node node = sections.item(i);

            if (node instanceof Element) {

                Element element = (Element) node;

                lapr.project.model.RoadNetwork.Node beginningNode = null;
                lapr.project.model.RoadNetwork.Node endingNode = null;

                for (lapr.project.model.RoadNetwork.Node roadNetworkNode : roadNetwork.vertices()) {

                    String nodeId = roadNetworkNode.toString();

                    if (element.getAttribute("begin").equals(nodeId)) {
                        beginningNode = roadNetworkNode;
                    }

                    if (element.getAttribute("end").equals(nodeId)) {
                        endingNode = roadNetworkNode;
                    }

                }

                // if the nodes in the file don't exist, we don't add the section
                if (beginningNode == null || endingNode == null) {
                    continue;
                }

                Road road = null;
                for (Road roadNetworkRoad : roadList) {

                    if (roadNetworkRoad.getId().equals(element.getElementsByTagName("road_id").item(0).getTextContent())) {
                        road = roadNetworkRoad;
                        break;
                    }

                }

                // if the road in the file doesn't exist, we don't add the section
                if (road == null) {
                    continue;
                }

                List<Double> tollFare = new ArrayList<>();
                if (road.getTypology().equalsIgnoreCase("gantry toll highway")) {
                    NodeList nodeList = element.getElementsByTagName("toll").item(0).getChildNodes();
                    for (int j = 0; j < nodeList.getLength(); j++) {
                        if (nodeList.item(j) instanceof Element) {
                            Element elementClass = (Element) nodeList.item(j);

                            tollFare.add(Double.parseDouble(elementClass.getTextContent()));
                        }
                    }
                }

                Direction direction = null;
                if (element.getElementsByTagName("direction").item(0).getTextContent().equals("bidirectional")) {
                    direction = Direction.BIDIRECTIONAL;
                }
                if (element.getElementsByTagName("direction").item(0).getTextContent().equals("direct")) {
                    direction = Direction.DIRECT;
                }
                if (element.getElementsByTagName("direction").item(0).getTextContent().equals("reverse")) {
                    direction = Direction.REVERSE;
                }

                // if the direction is invalid, we don't add the section
                if (direction == null) {
                    continue;
                }

                lapr.project.model.RoadNetwork.Node finalBeginningNode = beginningNode;
                lapr.project.model.RoadNetwork.Node finalEndingNode = endingNode;
                List<Section> duplicateSections = roadNetwork.getEdges().stream()
                        .filter(nodeSectionEdge -> nodeSectionEdge.getElement().getBeginningNode().equals(finalBeginningNode)
                                && nodeSectionEdge.getElement().getEndingNode().equals(finalEndingNode))
                        .map(Edge::getElement).collect(Collectors.toList());

                if (duplicateSections.isEmpty()) {
                    List<Segment> segmentList = addSegments(element.getElementsByTagName("segment_list").item(0).getChildNodes());

                    Section section = new Section(beginningNode, endingNode, direction, segmentList, road, tollFare);
                    roadNetwork.addSection(beginningNode, endingNode, section);
                }

            }

        }

    }

    /**
     * Creates a list with all the segments belonging to that section
     * @param nodeList the node list of segments
     * @return list of segments
     */
    private List<Segment> addSegments(NodeList nodeList) {
        List<Segment> segmentList = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {

            org.w3c.dom.Node node = nodeList.item(i);

            if (node instanceof Element) {

                Element element = (Element) node;

                int id = Integer.parseInt(element.getAttribute("id"));
                Measurable initialHeight = new Measurable(Double.parseDouble(
                        element.getElementsByTagName("init_height").item(0).getTextContent()), Unit.METER);
                Measurable finalHeight = new Measurable(Double.parseDouble(
                        element.getElementsByTagName("final_height").item(0).getTextContent()), Unit.METER);
                Measurable length = new Measurable(Double.parseDouble(
                        element.getElementsByTagName("length").item(0).getTextContent().split(" ")[0]), Unit.KILOMETER);
                Measurable maxVelocity = new Measurable(Double.parseDouble(
                        element.getElementsByTagName("max_velocity").item(0).getTextContent().split(" ")[0]), Unit.KILOMETERS_PER_HOUR);
                Measurable minVelocity = new Measurable(Double.parseDouble(
                        element.getElementsByTagName("min_velocity").item(0).getTextContent().split(" ")[0]), Unit.KILOMETERS_PER_HOUR);
                Measurable windAngle = new Measurable(Double.parseDouble(
                        element.getElementsByTagName("wind_direction").item(0).getTextContent()), Unit.DEGREE);
                Measurable windSpeed = new Measurable(Double.parseDouble(
                        element.getElementsByTagName("wind_speed").item(0).getTextContent().split(" ")[0]), Unit.METERS_PER_SECOND);

                segmentList.add(new Segment(id, initialHeight.getQuantity(), finalHeight.getQuantity(), length.getQuantity(),
                        windAngle.getQuantity(), windSpeed.getQuantity(), maxVelocity.getQuantity(), minVelocity.getQuantity()));

            }

        }

        return segmentList;
    }


    //Vehicle import methods


    @Override
    public List<Vehicle> importVehicles() throws ParserConfigurationException, IOException, SAXException {

        if (vehiclesFile == null) {
            throw new IllegalArgumentException("The use of this method requires an instance constructed with a vehiclesFile argument");
        }

        //<editor-fold desc="Variable Initialization">
        String name;
        String description;
        VehicleType vehicleType = null;
        int newTollClass = 0;
        Vehicle.MotorType motorTypeValue = null;
        Fuel fuel = null;
        Measurable mass = null;
        Measurable load = null;
        Measurable frontalArea = null;
        Measurable wheel = null;
        float rrc = 0;
        float dragCoefficient = 0;
        Energy energy = null;
        List<VelocityLimit> velocityLimits = new ArrayList<>();

        List<Vehicle> vehicles = new ArrayList<>();
        //</editor-fold>

        // Initiate parser
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(vehiclesFile);

        doc.getDocumentElement().normalize();

        // Get attributes
        NodeList vehicleList = doc.getElementsByTagName("vehicle");

        // Get vehicle nodes
        for (int temp = 0; temp < vehicleList.getLength(); temp++) {
            org.w3c.dom.Node vehicleNode = vehicleList.item(temp);
            Element nameElement = (Element) vehicleNode;
            // Name & Description
            name = addName(vehicles, nameElement.getAttribute("name"));
            description = nameElement.getAttribute("description");

                /*
                Get vehicle attributes
                 */
            if (vehicleNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                NodeList vehicleAttributes = vehicleNode.getChildNodes();

                for (int i = 0; i < vehicleAttributes.getLength(); i++) {
                    org.w3c.dom.Node attribute = vehicleAttributes.item(i);

                    if (attribute.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

                            /*
                             Type of vehicle
                             */
                        if (attribute.getNodeName().equalsIgnoreCase("type")) {
                            vehicleType = addVehicleType(attribute);
                        }

                            /*
                             Toll_class
                             */
                        else if (attribute.getNodeName().equalsIgnoreCase("toll_class")) {
                            newTollClass = Integer.parseInt(attribute.getTextContent());
                        }

                            /*
                             Motorization type ENUM
                             */
                        else if (attribute.getNodeName().equalsIgnoreCase("motorization")) {
                            motorTypeValue = addMotorization(attribute);
                        }

                            /*
                             Fuel
                             */
                        else if (attribute.getNodeName().equalsIgnoreCase("fuel")) {
                            fuel = addFuel(attribute);
                        }

                            /*
                             Mass from Measurable
                             */
                        else if (attribute.getNodeName().equalsIgnoreCase("mass")) {
                            mass = readMeasurable(attribute);

                        }

                            /*
                             Load from Measurable
                             */
                        else if (attribute.getNodeName().equalsIgnoreCase("load")) {
                            load = readMeasurable(attribute);
                        }

                            /*
                             Drag
                             */
                        else if (attribute.getNodeName().equalsIgnoreCase("drag")) {
                            dragCoefficient = Float.parseFloat(attribute.getTextContent());
                        }

                            /*
                             Frontal Area
                             */
                        else if (attribute.getNodeName().equalsIgnoreCase("frontal_area")) {
                            double frontalAreaQuantity = Double.parseDouble(attribute.getTextContent());
                            frontalArea = new Measurable(frontalAreaQuantity, Unit.METER_SQUARED);
                        }
                            /*
                             RollingReleaseCoefficient
                             */
                        else if (attribute.getNodeName().equalsIgnoreCase("rrc")) {
                            rrc = Float.parseFloat(attribute.getTextContent());
                        }

                            /*
                             Wheel size
                             */
                        else if (attribute.getNodeName().equalsIgnoreCase("wheel_size")) {
                            double wheelSizeQuantity = Double.parseDouble(attribute.getTextContent());
                            wheel = new Measurable(wheelSizeQuantity, Unit.METER);
                        }

                            /*
                             VelocityLimitList
                             */
                        else if (attribute.getNodeName().equalsIgnoreCase("velocity_limit_list")) {
                            velocityLimits = readVelocityLimits(attribute);
                        }

                            /*
                             Energy
                             */
                        else if (attribute.getNodeName().equalsIgnoreCase("energy")) {
                            energy = addEnergy(attribute);
                        }

                    }
                }

            }

                /*
                Create Vehicle
                 */
            Vehicle vehicle = new Vehicle(name, description, vehicleType, newTollClass, motorTypeValue, fuel, mass, load, dragCoefficient, frontalArea, rrc, wheel, velocityLimits, energy);
            vehicles.add(vehicle);
        }

        return vehicles;
    }

    private VehicleType addVehicleType(org.w3c.dom.Node attribute) {
        String newVehicleType = attribute.getTextContent();
        VehicleType[] typeOfVehicle = VehicleType.values();
        for (VehicleType type : typeOfVehicle) {
            if (newVehicleType.equalsIgnoreCase(type.toString())) {
                return type;
            }
        }
        return VehicleType.Car;
    }

    private Vehicle.MotorType addMotorization(org.w3c.dom.Node attribute) {
        if (attribute.getTextContent().equalsIgnoreCase("combustion")) {
            return Vehicle.MotorType.COMBUSTION;
        } else if (attribute.getTextContent().equalsIgnoreCase("electric")) {
            return Vehicle.MotorType.NONCOMBUSTION;
        }
        return Vehicle.MotorType.COMBUSTION;
    }

    private Fuel addFuel(org.w3c.dom.Node attribute) {
        String newFuel = attribute.getTextContent();
        Fuel[] fuelEnum = Fuel.values();
        for (Fuel fuelType : fuelEnum) {
            if (newFuel.equalsIgnoreCase(fuelType.toString())) {
                return fuelType;
            }
        }
        return Fuel.Gasoline;
    }

    private Measurable readMeasurable(org.w3c.dom.Node attribute) {
        String line = attribute.getTextContent();
        String[] unitSplit = line.split(" ");
        double quantity = Double.parseDouble(unitSplit[0]);
        String unit = unitSplit[1];
        if (unit.equalsIgnoreCase("kg")) {
            return new Measurable(quantity, Unit.KILOGRAM);
        } else if (unit.equalsIgnoreCase("g")) {
            return new Measurable(quantity, Unit.GRAM);
        }
        return new Measurable(quantity, Unit.KILOGRAM);
    }

    private List<VelocityLimit> readVelocityLimits(org.w3c.dom.Node attribute) {

        String segmentType = "";

        List<VelocityLimit> velocityLimits = new ArrayList<>();

        NodeList velocityLimitList = attribute.getChildNodes();
        for (int i = 0; i < velocityLimitList.getLength(); i++) {
            org.w3c.dom.Node velocityLimitListNode = velocityLimitList.item(i);

            NodeList velocityLimit = velocityLimitListNode.getChildNodes();
            for (int j = 0; j < velocityLimit.getLength(); j++) {
                org.w3c.dom.Node velocityLimitNode = velocityLimit.item(j);

                /*
                Read Segment Type
                 */
                if (velocityLimitNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    if (velocityLimitNode.getNodeName().equalsIgnoreCase("segment_type")) {
                        segmentType = velocityLimitNode.getTextContent();
                    }

                    /*
                    Read Limit
                     */
                    else if (velocityLimitNode.getNodeName().equalsIgnoreCase("limit")) {

                        String string = velocityLimitNode.getTextContent().replaceAll("\\s+", "");
                        String[] stringSplit = string.split(" ");
                        double limit;

                        //Read Unit, if unit is present after the limit quantity
                        if (stringSplit.length == 2) {
                            limit = Double.parseDouble(stringSplit[0]);
                            String newVelocity = stringSplit[1];

                            if (newVelocity.equalsIgnoreCase("km/h")) {
                                Measurable newVelocityLimitValue = new Measurable(limit, Unit.KILOMETERS_PER_HOUR);
                                velocityLimits.add(new VelocityLimit(segmentType, newVelocityLimitValue));

                            } else if (newVelocity.equalsIgnoreCase("mp/h")) {

                                Measurable newVelocityLimitValue = new Measurable(limit, Unit.MILES_PER_HOUR);
                                velocityLimits.add(new VelocityLimit(segmentType, newVelocityLimitValue));

                            } else if (newVelocity.equalsIgnoreCase("m/s")) {

                                Measurable newVelocityLimitValue = new Measurable(limit, Unit.METERS_PER_SECOND);
                                velocityLimits.add(new VelocityLimit(segmentType, newVelocityLimitValue));

                            }

                            //Assume limit is expressed in the file in km/h
                        } else if (stringSplit.length == 1) {
                            limit = Double.parseDouble(stringSplit[0]);

                            Measurable newVelocityLimitValue = new Measurable(limit, Unit.KILOMETERS_PER_HOUR);
                            velocityLimits.add(new VelocityLimit(segmentType, newVelocityLimitValue));
                        }
                    }
                }
            }
        }
        return velocityLimits;
    }

    private Energy addEnergy(org.w3c.dom.Node attribute) {

        int minRpm = 0;
        int maxRpm = 0;
        float finalDriveRatio = 0f;
        List<Gears> gearBox = new ArrayList<>();
        List<Throttle> throttles = new ArrayList<>();

        NodeList energyList = attribute.getChildNodes();
        for (int j = 0; j < energyList.getLength(); j++) {
            org.w3c.dom.Node energyNode = energyList.item(j);
            if (energyNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

                /*
                Min RPM
                 */
                if (energyNode.getNodeName().equalsIgnoreCase("min_rpm")) {
                    minRpm = Integer.parseInt(energyNode.getTextContent());
                }
                /*
                Max RPM
                 */
                else if (energyNode.getNodeName().equalsIgnoreCase("max_rpm")) {
                    maxRpm = Integer.parseInt(energyNode.getTextContent());
                }
                /*
                Final Drive ratio
                 */
                else if (energyNode.getNodeName().equalsIgnoreCase("final_drive_ratio")) {
                    finalDriveRatio = Float.parseFloat(energyNode.getTextContent());
                }
                /*
                Gear list
                 */
                else if (energyNode.getNodeName().equalsIgnoreCase("gear_list")) {
                    gearBox = addGearList(energyNode);
                }
                /*
                Throttle List
                 */
                else if (energyNode.getNodeName().equalsIgnoreCase("throttle_list")) {
                    throttles = addThrottleList(energyNode);
                    break;
                }

            }

        }
        return new Energy(minRpm, maxRpm, finalDriveRatio, gearBox, throttles);
    }

    private List<Gears> addGearList(org.w3c.dom.Node energyNode) {

        List<Gears> gears = new ArrayList<>();

        NodeList gearListList = energyNode.getChildNodes();
        for (int k = 1; k < gearListList.getLength(); k++) {
            org.w3c.dom.Node gearListNode = gearListList.item(k);

            /*
            ID
             */
            if (gearListNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element gearElement = (Element) gearListNode;
                int newGearId = Integer.parseInt(gearElement.getAttribute("id"));

                NodeList gearList = gearListNode.getChildNodes();
                for (int l = 1; l < gearList.getLength(); l++) {
                    org.w3c.dom.Node gearNode = gearList.item(l);

                    /*
                    Ratio
                     */
                    if (gearNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                        if (gearNode.getNodeName().equalsIgnoreCase("ratio")) {
                            float newRatio = Float.parseFloat(gearNode.getTextContent());
                            Gears newGear = new Gears(newGearId, newRatio);
                            gears.add(newGear);
                        }
                    }
                }
            }

        }
        return gears;
    }

    private List<Throttle> addThrottleList(org.w3c.dom.Node energyNode) {

        int throttleId, torqueLow = 0, torqueHigh = 0, rpmLow = 0, rpmHigh = 0;
        List<Throttle> throttles = new ArrayList<>();
        double sfc = 0;

        NodeList throttleList = energyNode.getChildNodes();
        for (int i = 0; i < throttleList.getLength(); i++) {

            org.w3c.dom.Node throttleNode = throttleList.item(i);
            if (throttleNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

                Element throttleElement = (Element) throttleNode;
                throttleId = Integer.parseInt(throttleElement.getAttribute("id"));
                NodeList regimeList = throttleNode.getChildNodes();

                List<Regime> regimes = new ArrayList<>();

                for (int j = 0; j < regimeList.getLength(); j++) {
                    org.w3c.dom.Node regimeNode = regimeList.item(j);
                    NodeList regimeChildList = regimeNode.getChildNodes();


                    if (regimeChildList.getLength() != 0) {

                        //Read regime
                        for (int k = 0; k < regimeChildList.getLength(); k++) {
                            org.w3c.dom.Node regimeChild = regimeChildList.item(k);

                            if (regimeChild.getNodeName().equalsIgnoreCase("torque_low")) {

                                torqueLow = Integer.parseInt(regimeChild.getTextContent());

                            } else if (regimeChild.getNodeName().equalsIgnoreCase("torque_high")) {

                                torqueHigh = Integer.parseInt(regimeChild.getTextContent());

                            } else if (regimeChild.getNodeName().equalsIgnoreCase("rpm_low")) {

                                rpmLow = Integer.parseInt(regimeChild.getTextContent());

                            } else if (regimeChild.getNodeName().equalsIgnoreCase("rpm_high")) {

                                rpmHigh = Integer.parseInt(regimeChild.getTextContent());

                            } else if (regimeChild.getNodeName().equalsIgnoreCase("SFC")) {
                                sfc = Double.parseDouble(regimeChild.getTextContent());
                                break;
                            }

                        }

                        //Add regime
                        Regime newRegime;
                        if (sfc != 0) {
                            newRegime = new Regime(torqueLow, torqueHigh, rpmLow, rpmHigh, sfc);
                        } else {
                            newRegime = new Regime(torqueLow, torqueHigh, rpmLow, rpmHigh);
                        }
                        regimes.add(newRegime);
                    }

                }

                Throttle throttle = new Throttle(throttleId, regimes);
                throttles.add(throttle);

            }

        }

        return throttles;
    }

    /**
     * If we get the name search for that name. save the spot we started. start searching after that
     * @param list
     * @param name
     * @return
     */
    private String addName(List<Vehicle> list, String name) {

        for (Vehicle v : list) {
            if (v.getName().equalsIgnoreCase(name)) {
                name += id;
                id++;
            }
        }

        return name;

    }


}
