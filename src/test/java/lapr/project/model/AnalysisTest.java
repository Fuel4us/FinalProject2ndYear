package lapr.project.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gonçalo Fonseca <goncalo7bfc@gmail.com>
 */
public class AnalysisTest {

    Project project = new Project("Alpha", "", new RoadNetwork(), new ArrayList<>());
    String name = "Beta";
    Collection<Section> path = new ArrayList<>();
    Measurable energyExp = new Measurable(2500, Unit.KILOJOULE);
    Measurable travelTime = new Measurable(2, Unit.HOUR);
    Measurable travelCost = new Measurable(999, Unit.EUROS);

    Analysis instance = new Analysis(project, name, path, energyExp, travelTime, travelCost);

    public AnalysisTest() {
        Analysis testEmpty = new Analysis();
        String expResult = null;
        String result = testEmpty.getAlgorithmName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRequestingInstance method, of class Analysis.
     */
    @Test
    public void testGetRequestingInstance() {
        System.out.println("getRequestingInstance");
        Project expResult = project;
        Project result = instance.getRequestingInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAlgorithmName method, of class Analysis.
     */
    @Test
    public void testGetAlgorithmName() {
        System.out.println("getAlgorithmName");
        String expResult = "Beta";
        String result = instance.getAlgorithmName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBestPath method, of class Analysis.
     */
    @Test
    public void testGetBestPath() {
        System.out.println("getBestPath");
        Collection<Section> expResult = path;
        Collection<Section> result = instance.getBestPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExpendedEnergy method, of class Analysis.
     */
    @Test
    public void testGetExpendedEnergy() {
        System.out.println("getExpendedEnergy");
        Measurable expResult = energyExp;
        Measurable result = instance.getExpendedEnergy();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTravelTime method, of class Analysis.
     */
    @Test
    public void testGetTravelTime() {
        System.out.println("getTravelTime");
        Measurable expResult = travelTime;
        Measurable result = instance.getTravelTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTravelCost method, of class Analysis.
     */
    @Test
    public void testGetTravelCost() {
        System.out.println("getTravelCost");
        Measurable expResult = travelCost;
        Measurable result = instance.getTravelCost();
        assertEquals(expResult, result);
    }

    /**
     * Test of issueRequestingEntity method, of class Analysis.
     */
    @Test
    public void testIssueRequestingEntity() {
        System.out.println("issueRequestingEntity");
        Project expResult = project;
        Project result = instance.issueRequestingEntity();
        assertEquals(expResult, result);
    }

    /**
     * Test of generateReport method, of class Analysis.
     */
    @Test
    public void testGenerateReport() {
        System.out.println("generateReport");
        String expResult = String.format("%s:%s%n%n %s:%s%n%n %s:%s%n%n %s:%s%n%n",
                "Travelled sections", path.toString(),
                "Expended Energy during travel", energyExp.toString(),
                "Total Travel time", travelTime.toString(),
                "Total toll costs", travelCost.toString());;
        String result = instance.generateReport();
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of exportDataHTML method, of class Analysis.
//     */
//    @Test
//    public void testExportDataHTML() throws Exception {
//        System.out.println("exportDataHTML");
//        StringTemplate stringTemplate1 = null;
//        StringTemplate stringTemplate2 = null;
//        FileWriter file = null;
//        Analysis instance = null;
//        instance.exportDataHTML(stringTemplate1, stringTemplate2, file);
//         TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of exportDataCSV method, of class Analysis.
//     */
//    @Test
//    public void testExportDataCSV() throws Exception {
//        System.out.println("exportDataCSV");
//        StringTemplate stringTemplate1 = null;
//        StringTemplate stringTemplate2 = null;
//        FileWriter file = null;
//        Analysis instance = null;
//        instance.exportDataCSV(stringTemplate1, stringTemplate2, file);
//         TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
