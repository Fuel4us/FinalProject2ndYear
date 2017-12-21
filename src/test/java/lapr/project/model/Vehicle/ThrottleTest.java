/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author goncalo
 */
public class ThrottleTest {
    
    List<Regime> regime = new ArrayList<>();
    Throttle instance = new Throttle(20, regime);
    
    public ThrottleTest() {
    }

    /**
     * Test of getRegimes method, of class Throttle.
     */
    @Test
    public void testGetRegimes() {
        System.out.println("getRegimes");
        List<Regime> expResult = regime;
        List<Regime> result = instance.getRegimes();
        assertEquals(expResult, result);
    }
    
}
