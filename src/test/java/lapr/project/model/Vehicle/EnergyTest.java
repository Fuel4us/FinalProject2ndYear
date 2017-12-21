/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.Vehicle;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author goncalo
 */
public class EnergyTest {
    
    public EnergyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFinalDriveRatio method, of class Energy.
     */
    @Test
    public void testGetFinalDriveRatio() {
        System.out.println("getFinalDriveRatio");
        Energy instance = null;
        float expResult = 0.0F;
        float result = instance.getFinalDriveRatio();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGears method, of class Energy.
     */
    @Test
    public void testGetGears() {
        System.out.println("getGears");
        Energy instance = null;
        List<Gears> expResult = null;
        List<Gears> result = instance.getGears();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThrottles method, of class Energy.
     */
    @Test
    public void testGetThrottles() {
        System.out.println("getThrottles");
        Energy instance = null;
        List<Throttle> expResult = null;
        List<Throttle> result = instance.getThrottles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Energy.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Energy instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
