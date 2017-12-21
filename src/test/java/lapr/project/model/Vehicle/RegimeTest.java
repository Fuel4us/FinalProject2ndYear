/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.Vehicle;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author goncalo
 */
public class RegimeTest {
    
    Regime instance = new Regime(10,20,30,40,350);
    
    public RegimeTest() {
    }

    /**
     * Test of getTorqueLow method, of class Regime.
     */
    @Test
    public void testGetTorqueLow() {
        System.out.println("getTorqueLow");
        int expResult = 10;
        int result = instance.getTorqueLow();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTorqueHigh method, of class Regime.
     */
    @Test
    public void testGetTorqueHigh() {
        System.out.println("getTorqueHigh");
        int expResult = 20;
        int result = instance.getTorqueHigh();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRpmLow method, of class Regime.
     */
    @Test
    public void testGetRpmLow() {
        System.out.println("getRpmLow");
        int expResult = 30;
        int result = instance.getRpmLow();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRpmHigh method, of class Regime.
     */
    @Test
    public void testGetRpmHigh() {
        System.out.println("getRpmHigh");
        int expResult = 40;
        int result = instance.getRpmHigh();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSFC method, of class Regime.
     */
    @Test
    public void testGetSFC() {
        System.out.println("getSFC");
        double expResult = 350f;
        double result = instance.getSFC();
        assertEquals(expResult, result, 0.0);
    }
    
}
