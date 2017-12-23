package lapr.project.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gonçalo Fonseca <goncalo7bfc@gmail.com>
 */
public class UnitTest {
    
    Unit[] expResult = {Unit.KILOGRAM, Unit.GRAM, Unit.KILOMETERS_PER_HOUR, Unit.KILOMETER, Unit.METER, Unit.MILES_PER_HOUR, Unit.METERS_PER_SECOND,
    Unit.ROTATIONS_PER_MINUTE, Unit.DEGREE, Unit.METERS_PER_SECOND_SQUARED, Unit.METER_SQUARED,
    Unit.KILOGRAMS_PER_CUBIC_METER, Unit.NEWTON_METER, Unit.KILOJOULE_PER_GRAM, Unit.WATT, Unit.GRAM_PER_KILOWATT_HOUR,
    Unit.KILOJOULE, Unit.EUROS, Unit.HOUR};
    String test = "Boas";
    
    public UnitTest() {
    }

    /**
     * Test of values method, of class Unit.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Unit[] result = Unit.values();
        assertArrayEquals(expResult, result);
    }

//    /**
//     * Test of valueOf method, of class Unit.
//     */
//    @Test
//    public void testValueOf() {
//        System.out.println("valueOf");
//        String name = ;
//        Unit result = Unit.valueOf(name);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of toString method, of class Unit.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Unit instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
