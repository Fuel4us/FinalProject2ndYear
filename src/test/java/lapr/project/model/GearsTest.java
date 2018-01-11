/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import com.google.common.base.Objects;
import lapr.project.model.Gears;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author goncalo
 */
public class GearsTest {

    Gears instance = new Gears(10, 40);

    public GearsTest() {
        Gears gearsTest = new Gears();
        int result = gearsTest.getId();
        assertEquals(0, result);
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

    /**
     * Test of getId method, of class Gears.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 10;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Gears.
     */
    @Test
    public void testEquals() {

        Gears g1 = new Gears(0, 0);
        Gears g2 = new Gears(0, 0);

        //same objects
        boolean result = java.util.Objects.equals(g1, g1);
        assertEquals(true, result);

        //different objects
        result = Objects.equal(g2, g1);
        assertEquals(true, result);
    }

    /**
     * Test of hashCode method, of class Gears.
     */
    @Test
    public void testHashCode() {
        
        Gears test = new Gears(10,40);
        int expResult = test.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

}
