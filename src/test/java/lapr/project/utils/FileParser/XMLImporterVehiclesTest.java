//package lapr.project.utils.FileParser;
//
//import lapr.project.model.Project;
//import lapr.project.model.RoadNetwork.RoadNetwork;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class XMLImporterVehiclesTest {
//
//    @Test
//    public void testImportVehicles() {
//
//        System.out.println("importVehicles");
//        Project object = new Project("test", "description", new RoadNetwork(), new ArrayList<>());
//        XMLImporterVehicles instance = new XMLImporterVehicles();
//        boolean expResult = true;
//        boolean result = instance.importVehicles(object, "src/test/resources/TestSet02_Vehicles.xml");
//        assertEquals(expResult, result);
//    }
//
//}