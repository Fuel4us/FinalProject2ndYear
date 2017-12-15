package lapr.project.utils;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.model.Vehicle.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML Importer of vehicles
 */
public class XMLImporter {

    private final File file;
    private Vehicle vehicle;

    public XMLImporter(File file) throws Exception {
        this.file = file;
    }

    public Vehicle importVehicle() throws Exception {

        JAXBContext context = JAXBContext.newInstance(Vehicle.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        vehicle = (Vehicle) unmarshaller.unmarshal(file);
        completeVehiclesDOMParsing(vehicle, file);

        return vehicle;

    }

    private void completeVehiclesDOMParsing(Vehicle vehicle, File file) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

        // missing adds
    }

    private void add() {

    }

    /**
     * Counts the number of children nodes (nested nodes of an Element) Based on
     * : See
     * <a href="https://stackoverflow.com/questions/24759710/xml-child-node-count-java">stackoverflow.com</a>
     *
     * @param element An Element corresponding to the parent Node
     * @return Number of children nodes
     */
    private int countNodeChildren(Element element) {

        int nodeNum = 0;
        NodeList parent = element.getChildNodes();
        for (int j = 0; j < parent.getLength(); j++) {
            if (parent.item(j).getNodeType() == Node.ELEMENT_NODE) {
                nodeNum++;
            }
        }

        return nodeNum;
    }

}
