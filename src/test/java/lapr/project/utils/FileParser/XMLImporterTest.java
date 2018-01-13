package lapr.project.utils.FileParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Project;
import lapr.project.model.RoadNetwork;
import lapr.project.model.Vehicle;
import org.junit.Test;

/**
 *
 * @author Gonçalo Fonseca, Pedro Fonseca
 */
public class XMLImporterTest {

    List<Vehicle> instance = new ArrayList<>();
    RoadNetwork instanceRoad = new RoadNetwork();
    File filename1 = new File("src/test/resources/TestSet02_Vehicles_v2.xml");
    File filename2 = new File ("src/test/resources/TestSet02_Network_v2.xml")   ;
    XMLImporter obj = new XMLImporter(filename2, filename1);
    Project object = new Project("test","test", "description", new RoadNetwork(), new ArrayList<>());

    /**
     * Test of importNetwork method, of class XMLImporter.
     * @throws java.lang.Exception
     */
    @Test
    public void testImportNetwork() throws Exception {
        
    }
    /**
     * Test of importVehicles method, of class XMLImporter.
     * @throws java.lang.Exception
     */
    @Test
    public void testImportVehicles() throws Exception {
        
    }

}
