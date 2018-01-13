package lapr.project.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gonçalo Fonseca <goncalo7bfc@gmail.com>
 */
public class PhysicsTest {
    
    public PhysicsTest() {
    }

    /**
     * Test of calculateLinearInterpolation method, of class Physics.
     */
    @Test
    public void testCalculateLinearInterpolation() {
        System.out.println("calculateLinearInterpolation");
        double initialX = 8.0;
        double finalX = 1.2;
        double initialY = 7.2;
        double finalY = 0.9;
        double valueX = 0.4;
        double expResult = 0.15882352941176503;
        double result = Physics.calculateLinearInterpolation(initialX, finalX, initialY, finalY, valueX);
        assertEquals(expResult, result, 0.0);

    }
    
}
