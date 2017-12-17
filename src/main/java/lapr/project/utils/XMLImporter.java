package lapr.project.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import lapr.project.model.Vehicle.*;

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
//       completeVehiclesDOMParsing(vehicle, file);

        return vehicle;

    }

//    private void completeVehiclesDOMParsing(Vehicle vehicle, File file) throws Exception {
//
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        dbf.setNamespaceAware(true);
//        DocumentBuilder db = dbf.newDocumentBuilder();
//        Document doc = db.parse(file);
//
//        // ToDo missing adds
//        addVehicles(vehicle, doc);
//    }
//    
//    private void addVehicles(Vehicle vehicle, Document doc) throws Exception {
//        
//        List<Vehicle> vehicles = new ArrayList<>();
//        
//        String name;
//        String description;
//        VehicleType type;
//    }
    
}
