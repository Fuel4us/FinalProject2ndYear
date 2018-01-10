package lapr.project.utils.FileParser;

import lapr.project.model.RoadNetwork.*;
import lapr.project.utils.Graph.Edge;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lapr.project.model.Project;

import org.xml.sax.SAXException;

/**
 * Handles importation of a RoadNetwork through a XML file
 */
public class XMLImporterRoads implements FileParser {

    @Override
    public boolean importVehicles(Project project, String filename) {
        return false;
    }

    private final File file;

    private RoadNetwork roadNetwork;

    /**
     * Constructor to update an existing road network
     * @param file xml file
     * @param roadNetwork the road network already created
     */
    public XMLImporterRoads(File file, RoadNetwork roadNetwork) {
        this.file = file;
        this.roadNetwork = roadNetwork;
    }

    /**
     * Constructor to create a new road network
     *
     * @param file xmlFile
     */
    public XMLImporterRoads(File file) {
        this.file = file;
    }

    /**
     * Reads RoadNetwork from file
     * @param newNetwork true if the road network is to be created as new
     * @return the road network updated
     * @throws Exception
     */
    public RoadNetwork importNetwork(boolean newNetwork) throws IOException, SAXException, ParserConfigurationException {

        completeNetworkInformationDOMParsing(newNetwork);

        return roadNetwork;
    }

    /**
     * Creates document in order to complete information in the RoadNetwork
     * @param newNetwork true if the road network is to be created as new
     * @throws Exception
     */
    private void completeNetworkInformationDOMParsing(boolean newNetwork) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

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

            Node node = roads.item(i);

            if (node instanceof Element) {

                Element element = (Element) node;

                String id = element.getAttributes().item(0).getTextContent();
                String name = element.getElementsByTagName("road_name").item(0).getTextContent();
                String typology = element.getElementsByTagName("typology").item(0).getTextContent();
                List<Double> tollFaresList = new ArrayList<>();

                if (element.getElementsByTagName("class").getLength() > 0) {

                    NodeList tollFares = element.getElementsByTagName("class");

                    for (int j = 0; j < tollFares.getLength(); j++) {

                        Node tollFaresNode = tollFares.item(j);

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

            Node node = sections.item(i);

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

            Node node = nodeList.item(i);

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

}