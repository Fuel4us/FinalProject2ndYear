package lapr.project.utils;

import lapr.project.model.Gears;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gonçalo Fonseca <goncalo7bfc@gmail.com>
 */
public class EnergyExpenditureAccelResultsTest {

    Measurable energyExpenditure = new Measurable(1.0, Unit.KILOJOULE);
    Measurable finalVelocity = new Measurable(1.0, Unit.METERS_PER_SECOND);
    Measurable timeSpent = new Measurable(0.0, Unit.HOUR);
    Gears[] gearForEachSegment = new Gears[0];
    Measurable tollClass = new Measurable(2.2, Unit.KILOGRAM);
    EnergyExpenditureAccelResults instance = new EnergyExpenditureAccelResults(energyExpenditure, finalVelocity,
            timeSpent, gearForEachSegment, tollClass);

    public EnergyExpenditureAccelResultsTest() {
        EnergyExpenditureAccelResults emptyTest = new EnergyExpenditureAccelResults();
        Measurable expResult = null;
        Measurable result = emptyTest.getEnergyExpenditure();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnergyExpenditure method, of class
     * EnergyExpenditureAccelResults.
     */
    @Test
    public void testGetEnergyExpenditure() {
        System.out.println("getEnergyExpenditure");
        Measurable expResult = energyExpenditure;
        Measurable result = instance.getEnergyExpenditure();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFinalVelocity method, of class EnergyExpenditureAccelResults.
     */
    @Test
    public void testGetFinalVelocity() {
        System.out.println("getFinalVelocity");
        Measurable expResult = finalVelocity;
        Measurable result = instance.getFinalVelocity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimeSpent method, of class EnergyExpenditureAccelResults.
     */
    @Test
    public void testGetTimeSpent() {
        System.out.println("getTimeSpent");
        Measurable expResult = timeSpent;
        Measurable result = instance.getTimeSpent();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGearForEachSegment method, of class
     * EnergyExpenditureAccelResults.
     */
    @Test
    public void testGetGearForEachSegment() {
        System.out.println("getGearForEachSegment");
        Gears[] expResult = gearForEachSegment;
        Gears[] result = instance.getGearForEachSegment();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getTollCosts method, of class EnergyExpenditureAccelResults.
     */
    @Test
    public void testGetTollCosts() {
        System.out.println("getTollCosts");
        Measurable expResult = tollClass;
        Measurable result = instance.getTollCosts();
        assertEquals(expResult, result);
    }

}
