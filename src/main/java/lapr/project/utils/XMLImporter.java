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
    
    public Vehicle importVehicle() throws Exception{
        
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

}
