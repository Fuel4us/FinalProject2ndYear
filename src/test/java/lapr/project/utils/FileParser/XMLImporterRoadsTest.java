package lapr.project.utils.FileParser;

import lapr.project.model.RoadNetwork.*;
import lapr.project.utils.Graph.Edge;
import lapr.project.utils.Graph.Vertex;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class XMLImporterRoadsTest {

    File file;
    XMLImporterRoads fileParser;
    RoadNetwork roadNetworkResult;
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;

    public XMLImporterRoadsTest() throws JAXBException, IOException, SAXException, ParserConfigurationException {
        file = new File("src/test/resources/TestSet02_Network_v2.xml");
        fileParser = new XMLImporterRoads(file);

        roadNetworkResult = fileParser.importNetwork(true);

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

        RoadNetwork roadNetworkExpected = new RoadNetwork("TestSet01", "5 node test set");

        Node nodeExpected1 = new Node("n0");
        Node nodeExpected2 = new Node("n1");
        Node nodeExpected3 = new Node("n2");
        Node nodeExpected4 = new Node("n3");
        Node nodeExpected5 = new Node("n4");
        Node nodeExpected6 = new Node("n5");
        Node nodeExpected7 = new Node("n7");

        roadNetworkExpected.addNode(nodeExpected1);
        roadNetworkExpected.addNode(nodeExpected2);
        roadNetworkExpected.addNode(nodeExpected3);
        roadNetworkExpected.addNode(nodeExpected4);
        roadNetworkExpected.addNode(nodeExpected5);
        roadNetworkExpected.addNode(nodeExpected6);
        roadNetworkExpected.addNode(nodeExpected7);

        List<Double> tollFareExpected = new ArrayList<>();
        tollFareExpected.add(0.15);
        tollFareExpected.add(0.25);
        tollFareExpected.add(0.35);

        Road roadExpected1 = new Road("E01", "E01", "regular road", new ArrayList<>());
        Road roadExpected2 = new Road("A01", "A1", "toll highway", tollFareExpected);
        Road roadExpected3 = new Road("A02", "A2", "gantry toll highway", new ArrayList<>());
        Road roadExpected4 = new Road("E06", "E06", "regular road", new ArrayList<>());
        Road roadExpected5 = new Road("N232", "N232", "regular road", new ArrayList<>());

        List<Segment> segmentListExpected1 = new ArrayList<>();
        segmentListExpected1.add(new Segment(1, 100, 200, 1.2, 20, 5, 90, 0));
        segmentListExpected1.add(new Segment(2, 200, 150, 6.5, -10, 2, 90, 0));
        segmentListExpected1.add(new Segment(3, 150, 350, 4, -10, 2.5, 90, 0));
        segmentListExpected1.add(new Segment(4, 350, 150, 10, -60, 2.7, 90, 0));

        Section sectionExpected1 = new Section(nodeExpected1, nodeExpected3, Direction.BIDIRECTIONAL, segmentListExpected1, roadExpected1, new ArrayList<>());

        List<Segment> segmentListExpected2 = new ArrayList<>();
        segmentListExpected2.add(new Segment(1, 150, 250, 1.5, 120, 1.5, 90, 0));
        segmentListExpected2.add(new Segment(2, 250, 450, 6.5, -170, 2, 90, 0));
        segmentListExpected2.add(new Segment(3, 450, 250, 4, -80, 2.5, 90, 0));

        Section sectionExpected2 = new Section(nodeExpected3, nodeExpected4, Direction.BIDIRECTIONAL, segmentListExpected2, roadExpected1, new ArrayList<>());

        List<Segment> segmentListExpected3 = new ArrayList<>();
        segmentListExpected3.add(new Segment(1, 250, 350, 10.5, 40, 2.5, 90, 0));
        segmentListExpected3.add(new Segment(2, 350, 550, 4, -120, 2, 90, 0));
        segmentListExpected3.add(new Segment(3, 550, 350, 6, -10, 2.5, 90, 0));

        Section sectionExpected3 = new Section(nodeExpected4, nodeExpected5, Direction.BIDIRECTIONAL, segmentListExpected3, roadExpected1, new ArrayList<>());

        List<Segment> segmentListExpected4 = new ArrayList<>();
        segmentListExpected4.add(new Segment(1, 100, 250, 10.2, 30, 2, 120, 50));
        segmentListExpected4.add(new Segment(2, 250, 350, 15, -10, 2, 120, 50));
        segmentListExpected4.add(new Segment(3, 350, 300, 4, 10, 2.5, 120, 50));

        Section sectionExpected4 = new Section(nodeExpected1, nodeExpected2, Direction.BIDIRECTIONAL, segmentListExpected4, roadExpected2, new ArrayList<>());

        List<Segment> segmentListExpected5 = new ArrayList<>();
        segmentListExpected5.add(new Segment(1, 300, 250, 10.2, 30, 2, 120, 50));
        segmentListExpected5.add(new Segment(2, 250, 350, 5, -10, 2, 120, 50));
        segmentListExpected5.add(new Segment(3, 350, 250, 6, 10, 2.5, 120, 50));

        Section sectionExpected5 = new Section(nodeExpected2, nodeExpected4, Direction.BIDIRECTIONAL, segmentListExpected5, roadExpected2, new ArrayList<>());

        List<Segment> segmentListExpected6 = new ArrayList<>();
        segmentListExpected6.add(new Segment(1, 250, 350, 6.5, 15, 0.5, 120, 50));
        segmentListExpected6.add(new Segment(2, 350, 395, 5.8, -10, 0.2, 120, 50));
        segmentListExpected6.add(new Segment(3, 395, 480, 7.5, 30, 0.5, 120, 50));
        segmentListExpected6.add(new Segment(4, 480, 420, 2.5, -10, 1.5, 120, 50));

        Section sectionExpected6 = new Section(nodeExpected4, nodeExpected7, Direction.BIDIRECTIONAL, segmentListExpected6, roadExpected2, new ArrayList<>());

        List<Segment> segmentListExpected7 = new ArrayList<>();
        segmentListExpected7.add(new Segment(1, 150, 250, 15.7, 70, 1.75, 90, 0));
        segmentListExpected7.add(new Segment(2, 250, 350, 6.5, 150, 2, 90, 0));
        segmentListExpected7.add(new Segment(3, 350, 350, 4, -170, 2.5, 70, 0));
        segmentListExpected7.add(new Segment(4, 350, 250, 10, -60, 2.7, 90, 0));

        Section sectionExpected7 = new Section(nodeExpected3, nodeExpected5, Direction.BIDIRECTIONAL, segmentListExpected7, roadExpected4, new ArrayList<>());

        List<Segment> segmentListExpected8 = new ArrayList<>();
        segmentListExpected8.add(new Segment(1, 150, 250, 8, 40, 1.15, 90, 0));
        segmentListExpected8.add(new Segment(2, 250, 350, 6.5, 15, 1.5, 90, 0));
        segmentListExpected8.add(new Segment(3, 350, 450, 4, -20, 1.1, 90, 0));
        segmentListExpected8.add(new Segment(4, 450, 350, 10, 5, 1.7, 90, 0));

        Section sectionExpected8 = new Section(nodeExpected3, nodeExpected6, Direction.BIDIRECTIONAL, segmentListExpected8, roadExpected4, new ArrayList<>());

        List<Segment> segmentListExpected9 = new ArrayList<>();
        segmentListExpected9.add(new Segment(1, 100, 250, 10.2, 135, 1.5, 120, 50));
        segmentListExpected9.add(new Segment(2, 250, 370, 8, 170, 1.2, 120, 50));
        segmentListExpected9.add(new Segment(3, 370, 350, 4, -170, 0.5, 120, 50));

        List<Double> tollFareSection = new ArrayList<>();
        tollFareSection.add(2.15);
        tollFareSection.add(3.25);
        tollFareSection.add(4.35);

        Section sectionExpected9 = new Section(nodeExpected1, nodeExpected6, Direction.BIDIRECTIONAL, segmentListExpected9, roadExpected3, tollFareSection);

        List<Segment> segmentListExpected10 = new ArrayList<>();
        segmentListExpected10.add(new Segment(1, 250, 280, 1.7, 20, 0.75, 50, 0));
        segmentListExpected10.add(new Segment(2, 280, 350, 3.5, 50, 1.2, 70, 0));
        segmentListExpected10.add(new Segment(3, 350, 270, 4, 20, 0.5, 70, 0));
        segmentListExpected10.add(new Segment(4, 270, 150, 70, -10, 1.7, 90, 0));

        Section sectionExpected10 = new Section(nodeExpected2, nodeExpected3, Direction.BIDIRECTIONAL, segmentListExpected10, roadExpected5, new ArrayList<>());

        roadNetworkExpected.addSection(nodeExpected1, nodeExpected3, sectionExpected1);
        roadNetworkExpected.addSection(nodeExpected3, nodeExpected4, sectionExpected2);
        roadNetworkExpected.addSection(nodeExpected4, nodeExpected5, sectionExpected3);
        roadNetworkExpected.addSection(nodeExpected1, nodeExpected2, sectionExpected4);
        roadNetworkExpected.addSection(nodeExpected2, nodeExpected4, sectionExpected5);
        roadNetworkExpected.addSection(nodeExpected4, nodeExpected7, sectionExpected6);
        roadNetworkExpected.addSection(nodeExpected3, nodeExpected5, sectionExpected7);
        roadNetworkExpected.addSection(nodeExpected3, nodeExpected6, sectionExpected8);
        roadNetworkExpected.addSection(nodeExpected1, nodeExpected6, sectionExpected9);
        roadNetworkExpected.addSection(nodeExpected2, nodeExpected3, sectionExpected10);

        assertEquals(roadNetworkExpected, roadNetworkResult);

    }

}