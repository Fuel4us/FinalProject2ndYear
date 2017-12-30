package lapr.project.utils.FileParser;

import lapr.project.model.RoadNetwork.Road;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.model.RoadNetwork.Segment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lapr.project.utils.ClassCast;
import org.xml.sax.SAXException;

/**
 * Handles importation of a RoadNetwork through a XML file
 */
public class XMLImporterRoads {

    private final File file;
    private RoadNetwork roadNetwork;

    /**
     * Constructor of class
     *
     * @param file xmlFile
     */
    public XMLImporterRoads(File file, boolean directed) {
        this.file = file;
        this.roadNetwork = new RoadNetwork(directed);
    }

    /**
     * Reads RoadNetwork from file
     *
     * @return the road network updated
     * @throws Exception
     */
    public RoadNetwork importNetwork() throws JAXBException, IOException, SAXException, ParserConfigurationException {  

        completeNetworkInformationDOMParsing();

        return roadNetwork;
    }

    /**
     * Creates document in order to complete information in the RoadNetwork
     * @throws Exception
     */
    private void completeNetworkInformationDOMParsing() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

        List<lapr.project.model.RoadNetwork.Node> nodeList = addNodes(doc);
        List<Road> roadList = addRoads(doc);
        addSections(roadList, nodeList, doc);
    }

    /**
     * Adds roads from the file in the RoadNetwork graph
     * @param doc the document
     * @return the list of roads imported from the file
     */
    public List<Road> addRoads(Document doc) {

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

                roadList.add(new Road(id, name, typology, tollFaresList));

            }

        }

        return roadList;

    }

    /**
     * Adds nodes from the file in the RoadNetwork graph
     * @param doc the document
     * @return a Node list
     */
    private List<lapr.project.model.RoadNetwork.Node> addNodes(Document doc) {

        List<lapr.project.model.RoadNetwork.Node> nodeList = new ArrayList<>();

        NodeList nodes = doc.getElementsByTagName("node");

        for (int i = 0; i < nodes.getLength(); i++) {

            org.w3c.dom.Node node = nodes.item(i);

            if (node instanceof Element) {

                Element element = (Element) node;

                lapr.project.model.RoadNetwork.Node roadNetworkNode =
                        new lapr.project.model.RoadNetwork.Node(element.getAttribute("id"));

                roadNetwork.addNode(roadNetworkNode);

                nodeList.add(roadNetworkNode);

            }

        }

        return nodeList;

    }

    /**
     * Adds sections from the file in the RoadNetwork graph
     * @param roadList the list of roads
     * @param roadNetworkNodeList the node list
     * @param doc the document
     */
    private void addSections(List<Road> roadList, List<lapr.project.model.RoadNetwork.Node> roadNetworkNodeList, Document doc) {

        NodeList sections = doc.getElementsByTagName("road_section");

        for (int i = 0; i < sections.getLength(); i++) {

            Node node = sections.item(i);

            if (node instanceof Element) {

                Element element = (Element) node;

//                lapr.project.model.RoadNetwork.Node beginningNode = null;
//                lapr.project.model.RoadNetwork.Node endingNode = null;
//                Road road = null;
//
//                for (lapr.project.model.RoadNetwork.Node roadNetworkNode : roadNetworkNodeList) {
//
//                    String nodeId = roadNetworkNode.toString();
//
//                    if (element.getAttribute("begin").equals(nodeId)) {
//                        beginningNode = new lapr.project.model.RoadNetwork.Node(nodeId);
//                    }
//
//                    if (element.getAttribute("end").equals(nodeId)) {
//                        endingNode = new lapr.project.model.RoadNetwork.Node(nodeId);
//                    }
//
//                }
//
//                // if the nodes in the file don't exist, we don't add the section
//                if (beginningNode == null || endingNode == null) {
//                    continue;
//                }
//
//                for (Road road : roadList) {
//
//                    road.getID()
//
//                }

            }

        }

    }


//    /**
//     * Adds sections from file in the RoadNetwork graph
//     *
//     * @param roadNetwork
//     * @param doc
//     * @throws Exception
//     */
//    private void addSections(RoadNetwork roadNetwork, Document doc) {
//
//        NodeList sections = doc.getElementsByTagName("road_section");
//
//        for (int i = 0; i < sections.getLength(); i++) {
//
//            org.w3c.dom.Node node = sections.item(i);
//
//            if (node instanceof Element) {
//
//                Element element = (Element) node;
//
//            }
//
////            if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
//
////                Element element = (Element) node;
////                String str = node.getTextContent();
////                Class cls = Class.forName(str);
////                Section section = (Section) cls.newInstance();
////                //roadNetwork.addSection(section);
//
////            }
//        }
//    }

}
