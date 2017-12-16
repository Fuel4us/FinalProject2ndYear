package lapr.project.utils;

import lapr.project.model.RoadNetwork.Road;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.model.RoadNetwork.Segment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by anily on 13/12/2017.
 */
public class FileParser {

    private final File file;
    private RoadNetwork<lapr.project.model.RoadNetwork.Node, Section> roadNetwork;
    private Road road;
    private Section section;
    private Segment segment;
    private lapr.project.model.RoadNetwork.Node node;


    //ToDo

    /**
     * Constructor of class
     *
     * @param file xmlFile
     * @throws Exception
     */
    public FileParser(File file) throws Exception {
        this.file = file;
    }

    /**
     * Reads RoadNetwork from file
     *
     * @return
     * @throws Exception
     */
    public RoadNetwork<lapr.project.model.RoadNetwork.Node, Section> importNetwork() throws Exception {

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
    public void completeNetworkInformationDOMParsing(RoadNetwork<lapr.project.model.RoadNetwork.Node, Section> roadNetwork, File file) throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

//        addNodes(roadNetwork, doc);
        addSections(roadNetwork, doc);
    }


    /**
     * Adds sections from file in the RoadNetwork graph
     *
     * @param roadNetwork
     * @param doc
     * @throws Exception
     */
    public void addSections(RoadNetwork<lapr.project.model.RoadNetwork.Node, Section> roadNetwork, Document doc) throws Exception {

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
    public void addNodes(RoadNetwork<lapr.project.model.RoadNetwork.Node, Section> roadNetwork, Document doc) throws Exception {

        NodeList nodes = doc.getElementsByTagName("node");
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
//                String str = node.getTextContent();
//                Class cls = Class.forName(str);
//                lapr.project.model.RoadNetwork.Node junction = (lapr.project.model.RoadNetwork.Node) cls.newInstance();
//                roadNetwork.addNode(junction);
            }
        }
    }

}
