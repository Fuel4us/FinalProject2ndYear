package lapr.project.utils.FileParser;

import java.util.ArrayList;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.utils.FileParser.XMLImporterVehicles;
import static org.junit.Assert.assertEquals;

/**
 * XML Test
 * @author goncalo
 */
public class XMLImporterVehiclesTest {

    private final String filename;

    public XMLImporterVehiclesTest() {
        this.filename="src/test/resources/TestSet02_Vehicles.xml";
    }


    public void testImportVehicles() {
        
        System.out.println("importVehicles");
        Project object = new Project("test","description",new RoadNetwork(),new ArrayList<>());
        XMLImporterVehicles instance = new XMLImporterVehicles();
        boolean expResult = true;
        boolean result = instance.importVehicles(object, filename);
        assertEquals(expResult, result);
    }

}