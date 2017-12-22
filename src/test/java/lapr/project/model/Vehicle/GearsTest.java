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
public class GearsTest {
    
    Gears instance = new Gears(10, 40);
    
    public GearsTest() {
    }

    /**
     * Test of getRatio method, of class Gears.
     */
    @Test
    public void testGetRatio() {
        System.out.println("getRatio");
        float expResult = 40F;
        float result = instance.getRatio();
        assertEquals(expResult, result, 0.0);
    }
    
}
