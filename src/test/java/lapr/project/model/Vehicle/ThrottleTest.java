/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author goncalo
 */
public class ThrottleTest {

    List<Regime> regime = new ArrayList<>();
    Throttle instance = new Throttle(20, regime);

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

    /**
     * Test of toString method, of class Throttle.
     */
    @Test
    public void testToString() {
        
        assertEquals(instance.toString(), "Throttle "+instance.getId());
    }

    /**
     * Test of getId method, of class Throttle.
     */
    @Test
    public void testGetId() {
        assertEquals(instance.getId(), 20);
    }

}
