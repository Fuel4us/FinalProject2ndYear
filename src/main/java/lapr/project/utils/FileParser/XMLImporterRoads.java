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
     * @return
     * @throws Exception
     */
    public RoadNetwork importNetwork() throws JAXBException, IOException, SAXException, ParserConfigurationException {

//        JAXBContext context = JAXBContext.newInstance(RoadNetwork.class);
//        Unmarshaller unmarshaller = context.createUnmarshaller();
//        roadNetwork = ClassCast.uncheckedCast(unmarshaller.unmarshal(file));

//        roadNetwork.addNode()

        completeNetworkInformationDOMParsing();

        return roadNetwork;
    }

    /**
     * Creates document in order to complete information in the RoadNetwork
     * @throws Exception
     */
    public void completeNetworkInformationDOMParsing() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

        addNodes(doc);
//        addSections(roadNetwork, doc);
    }

    /**
     * Adds nodes from the file in the RoadNetwork graph
     * @param doc
     */
    private void addNodes(Document doc) {

        NodeList nodes = doc.getElementsByTagName("node_list");

        for (int i = 0; i < nodes.getLength(); i++) {

            org.w3c.dom.Node node = nodes.item(i);

            if (node instanceof Element) {

                Element element = (Element) node;

                roadNetwork.addNode(new lapr.project.model.RoadNetwork.Node(
                        element.getElementsByTagName("node").item(0).getTextContent()));

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

//    /**
//     * Adds roads from file in the RoadNetwork graph
//     *
//     * @param roadNetwork
//     * @param doc
//     * @throws Exception
//     */
//    private void addNodes(RoadNetwork roadNetwork, Document doc) {

//        NodeList nodes = doc.getElementsByTagName("node_list");
//
//        for (int i = 0; i < nodes.getLength(); i++) {
//            org.w3c.dom.Node node = nodes.item(i);
//                if (node.hasChildNodes()
//                        && node instanceof Element) {
//                    Node child = node.getFirstChild();
//                    lapr.project.model.RoadNetwork.Node junction = new lapr.project.model.RoadNetwork.Node();
//                    Element element = (Element) node;
//
//
//
////                String str = node.getTextContent();
////                Class cls = Class.forName(str);
////                lapr.project.model.RoadNetwork.Node junction = (lapr.project.model.RoadNetwork.Node) cls.newInstance();
////                roadNetwork.addNode(junction);
//                }
//        }
//    }

}
