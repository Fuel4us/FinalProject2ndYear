package lapr.project.utils;

import lapr.project.model.RoadNetwork.Road;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.model.RoadNetwork.Segment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by anily on 13/12/2017.
 */
public class FileParser {

    private final File file;
    private Road road;
    private Section section;
    private Segment segment;
    private lapr.project.model.RoadNetwork.Node node;


    public FileParser(File file) throws Exception{
        this.file = file;
    }

    /**
     * Counts number of children nodes
     * Based on <a href="https://stackoverflow.com/questions/24759710/xml-child-node-count-java">stackoverflow.com</a>
     * @param element An Element corresponding to the parent Node
     * @return Number of children nodes
     */
    private int countNodesChildren(Element element) {

        int nodeNumber = 0;
        NodeList parentNode = element.getChildNodes();

        for (int j = 0; j < parentNode.getLength(); j++) {
            if (parentNode.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                nodeNumber++;
            }
        }

        return nodeNumber;
    }

    public lapr.project.model.RoadNetwork.Node importNode() throws Exception {

        JAXBContext context = JAXBContext.newInstance(lapr.project.model.RoadNetwork.Node.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        node = (lapr.project.model.RoadNetwork.Node) unmarshaller.unmarshal(file);

        return node;
    }

    public Road importRoad() throws Exception {

        JAXBContext context = JAXBContext.newInstance(Road.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        road = (Road) unmarshaller.unmarshal(file);

        return road;
    }

    public Section importSection() throws Exception {

        JAXBContext context = JAXBContext.newInstance(Section.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        section = (Section) unmarshaller.unmarshal(file);


        return section;
    }

    public Segment importSegment() throws Exception {

        JAXBContext context = JAXBContext.newInstance(Segment.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        segment = (Segment) unmarshaller.unmarshal(file);

        return segment;
    }

    public void completeRoadInformationDOMParsing(Road road, File file) throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

        addSections(road, file);

    }

    public void addSections(Road road, File file) throws Exception{

        //something
    }

}
