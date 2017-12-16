package lapr.project.utils.FileParser;

import lapr.project.model.RoadNetwork.Node;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.utils.ClassCast;
import lapr.project.utils.Graph.Edge;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class XMLImporterRoadsTest {

    File file;
    XMLImporterRoads fileParser;
    JAXBContext context;
    Unmarshaller unmarshaller;
    RoadNetwork<lapr.project.model.RoadNetwork.Node, Section> roadNetwork;
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;

    public XMLImporterRoadsTest() throws JAXBException, IOException, SAXException, ParserConfigurationException {
        File file = new File("src\\main\\resources\\TestSet02\\TestSet02_Network.xml");
        fileParser = new XMLImporterRoads(file);

        context = JAXBContext.newInstance(RoadNetwork.class);
        unmarshaller = context.createUnmarshaller();
        roadNetwork = new RoadNetwork<>(true);
        roadNetwork = ClassCast.uncheckedCast(unmarshaller.unmarshal(file));
        fileParser.completeNetworkInformationDOMParsing(roadNetwork, file);

        dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        db = dbf.newDocumentBuilder();
        doc = db.parse(file);
    }

//    @Test
//    public void addSectionsTest() throws Exception {
//
//        fileParser.addSections(roadNetwork, doc);
//
//        List<Edge<Node, Section>> result = roadNetwork.getEdges();
//        List<Edge<Node, Section>> expected = new LinkedList<>();
//
//    }

//    @Test
//    public void addNodesTest() throws Exception {
//
//        fileParser.addNodes(roadNetwork, doc);
//
//    }
}