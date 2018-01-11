package lapr.project.model.Vehicle;

import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author goncalo
 */
public class VelocityLimitTest {
    
    private final String test = "Test";
    Measurable boas = new Measurable(0, Unit.WATT);
    VelocityLimit instance = new VelocityLimit(test, boas);
    
    public VelocityLimitTest() {
        VelocityLimit instanceEmpty = new VelocityLimit();
        String expResult = instanceEmpty.getSegmentType();
        String result = null;
        assertEquals(expResult, result);
    }

    /**
     * Test of getSegmentType method, of class VelocityLimit.
     */
    @Test
    public void testGetSegmentType() {
        System.out.println("getSegmentType");
        String expResult = "Test";
        String result = instance.getSegmentType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLimit method, of class VelocityLimit.
     */
    @Test
    public void testGetLimit() {
        System.out.println("getLimit");
        Measurable expResult = boas;
        Measurable result = instance.getLimit();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLimit method, of class VelocityLimit.
     */
    @Test
    public void testSetLimit() {
        System.out.println("setLimit");
        Measurable measurable = new Measurable(20, Unit.EUROS);
        instance.setLimit(measurable);
        assertEquals(instance.getLimit(), measurable);
    }

    /**
     * Test of setSegmentType method, of class VelocityLimit.
     */
    @Test
    public void testSetSegmentType() {
        System.out.println("setSegmentType");
        String segmentType = "Teste2345";
        instance.setSegmentType(segmentType);
        assertEquals(instance.getSegmentType(), segmentType);
    }
    
}
