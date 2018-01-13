package lapr.project.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gonçalo Fonseca <goncalo7bfc@gmail.com>
 */
public class MeasurableTest {
    
    Measurable instance = new Measurable(8.2, Unit.DEGREE);
    
    public MeasurableTest() {
        Measurable emptyTest = new Measurable();
        double expResult = 0.0;
        double result = emptyTest.getQuantity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getQuantity method, of class Measurable.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        double expResult = 8.2;
        double result = instance.getQuantity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setQuantity method, of class Measurable.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        double quantity = 2.2;
        instance.setQuantity(quantity);
        double result = instance.getQuantity();
        assertEquals(quantity, result, 0.0);
    }

    /**
     * Test of getUnit method, of class Measurable.
     */
    @Test
    public void testGetUnit() {
        System.out.println("getUnit");
        Unit expResult = Unit.DEGREE;
        Unit result = instance.getUnit();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Measurable.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Measurable instance = new Measurable(50, Unit.KILOJOULE);
        int expResult = 684309643;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Measurable.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Measurable();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result); 
    }

    /**
     * Test of toString method, of class Measurable.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "8.2 º";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
