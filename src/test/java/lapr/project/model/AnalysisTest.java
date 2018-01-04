//package lapr.project.model;
//
//import java.io.FileWriter;
//import java.util.Collection;
//import lapr.project.model.RoadNetwork.Section;
//import lapr.project.utils.Measurable;
//import org.antlr.stringtemplate.StringTemplate;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Gonçalo Fonseca <goncalo7bfc@gmail.com>
// */
//public class AnalysisTest {
//    
//    public AnalysisTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of issueRequestingEntity method, of class Analysis.
//     */
//    @Test
//    public void testIssueRequestingEntity() {
//        System.out.println("issueRequestingEntity");
//        Analysis instance = null;
//        Project expResult = null;
//        Project result = instance.issueRequestingEntity();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of identify method, of class Analysis.
//     */
//    @Test
//    public void testIdentify() {
//        System.out.println("identify");
//        Analysis instance = null;
//        int expResult = 0;
//        int result = instance.identify();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateReport method, of class Analysis.
//     */
//    @Test
//    public void testGenerateReport() {
//        System.out.println("generateReport");
//        Analysis instance = null;
//        String expResult = "";
//        String result = instance.generateReport();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
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
//        // TODO review the generated test code and remove the default call to fail.
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
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRequestingInstance method, of class Analysis.
//     */
//    @Test
//    public void testGetRequestingInstance() {
//        System.out.println("getRequestingInstance");
//        Analysis instance = null;
//        Project expResult = null;
//        Project result = instance.getRequestingInstance();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAlgorithmName method, of class Analysis.
//     */
//    @Test
//    public void testGetAlgorithmName() {
//        System.out.println("getAlgorithmName");
//        Analysis instance = null;
//        String expResult = "";
//        String result = instance.getAlgorithmName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getBestPath method, of class Analysis.
//     */
//    @Test
//    public void testGetBestPath() {
//        System.out.println("getBestPath");
//        Analysis instance = null;
//        Collection<Section> expResult = null;
//        Collection<Section> result = instance.getBestPath();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getExpendedEnergy method, of class Analysis.
//     */
//    @Test
//    public void testGetExpendedEnergy() {
//        System.out.println("getExpendedEnergy");
//        Analysis instance = null;
//        Measurable expResult = null;
//        Measurable result = instance.getExpendedEnergy();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTravelTime method, of class Analysis.
//     */
//    @Test
//    public void testGetTravelTime() {
//        System.out.println("getTravelTime");
//        Analysis instance = null;
//        Measurable expResult = null;
//        Measurable result = instance.getTravelTime();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTravelCost method, of class Analysis.
//     */
//    @Test
//    public void testGetTravelCost() {
//        System.out.println("getTravelCost");
//        Analysis instance = null;
//        Measurable expResult = null;
//        Measurable result = instance.getTravelCost();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
