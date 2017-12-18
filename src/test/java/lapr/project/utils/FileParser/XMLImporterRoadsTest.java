package lapr.project.utils.FileParser;

import lapr.project.model.RoadNetwork.Node;
import lapr.project.model.RoadNetwork.RoadNetwork;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class XMLImporterRoadsTest {

    File file;
    XMLImporterRoads fileParser;
    RoadNetwork roadNetworkResult;
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;

    public XMLImporterRoadsTest() throws JAXBException, IOException, SAXException, ParserConfigurationException {
        file = new File("src/test/resources/TestSet02_Network.xml");
        fileParser = new XMLImporterRoads(file, true);

        roadNetworkResult = fileParser.importNetwork();

        dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        db = dbf.newDocumentBuilder();
        doc = db.parse(file);
    }

    /**
     * Ensures the method importNetwork() created the correct road network
     * @throws Exception
     */
    @Test
    public void ensureImportNetworkCreatesCorrectRoadNetwork() throws Exception {

        RoadNetwork roadNetworkExpected = new RoadNetwork(true);

        Node nodeExpected1 = new Node("n0");
        Node nodeExpected2 = new Node("n1");
        Node nodeExpected3 = new Node("n2");
        Node nodeExpected4 = new Node("n3");
        Node nodeExpected5 = new Node("n4");
        Node nodeExpected6 = new Node("n5");

        roadNetworkExpected.addNode(nodeExpected1);
        roadNetworkExpected.addNode(nodeExpected2);
        roadNetworkExpected.addNode(nodeExpected3);
        roadNetworkExpected.addNode(nodeExpected4);
        roadNetworkExpected.addNode(nodeExpected5);
        roadNetworkExpected.addNode(nodeExpected6);

        assertEquals(roadNetworkExpected, roadNetworkResult);

    }

//    @Test
//    public void addSectionsTest() throws Exception {
//
//        fileParser.addSections(roadNetworkResult, doc);
//
//        List<Edge<Node, Section>> result = roadNetworkResult.getEdges();
//        List<Edge<Node, Section>> expected = new LinkedList<>();
//
//    }

//    @Test
//    public void addNodesTest() throws Exception {
//
//        fileParser.addNodes(roadNetworkResult, doc);
//
//    }
}