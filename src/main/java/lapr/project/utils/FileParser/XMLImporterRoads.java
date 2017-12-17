package lapr.project.utils.FileParser;

import lapr.project.model.RoadNetwork.Road;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.model.RoadNetwork.Segment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.Element;
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
    private Road road;
    private Section section;
    private Segment segment;
    private lapr.project.model.RoadNetwork.Node node;

    /**
     * Constructor of class
     *
     * @param file xmlFile
     */
    public XMLImporterRoads(File file) {
        this.file = file;
    }

    /**
     * Reads RoadNetwork from file
     *
     * @return
     * @throws Exception
     */
    public RoadNetwork importNetwork() throws JAXBException, IOException, SAXException, ParserConfigurationException {

        JAXBContext context = JAXBContext.newInstance(RoadNetwork.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        roadNetwork = ClassCast.uncheckedCast(unmarshaller.unmarshal(file));
        completeNetworkInformationDOMParsing(roadNetwork, file);

        return roadNetwork;
    }

    /**
     * Creates document in order to complete information in the RoadNetwork
     *
     * @param roadNetwork
     * @param file
     * @throws Exception
     */
    public void completeNetworkInformationDOMParsing(RoadNetwork roadNetwork, File file) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

        addNodes(roadNetwork, doc);
//        addSections(roadNetwork, doc);
    }


    /**
     * Adds sections from file in the RoadNetwork graph
     *
     * @param roadNetwork
     * @param doc
     * @throws Exception
     */
    public void addSections(RoadNetwork roadNetwork, Document doc) {

        NodeList sections = doc.getElementsByTagName("road_section");
        for (int i = 0; i < sections.getLength(); i++) {
            org.w3c.dom.Node node = sections.item(i);
            if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
//                Element element = (Element) node;
//                String str = node.getTextContent();
//                Class cls = Class.forName(str);
//                Section section = (Section) cls.newInstance();
//                //roadNetwork.addSection(section);
            }
        }
    }

    /**
     * Adds roads from file in the RoadNetwork graph
     *
     * @param roadNetwork
     * @param doc
     * @throws Exception
     */
    public void addNodes(RoadNetwork roadNetwork, Document doc) {

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
    }

}
