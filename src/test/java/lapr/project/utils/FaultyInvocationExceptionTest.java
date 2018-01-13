package lapr.project.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gonçalo Fonseca <goncalo7bfc@gmail.com>
 */
public class FaultyInvocationExceptionTest {
    
    Object faulty;
    FaultyInvocationException instance = new FaultyInvocationException(faulty);
    
    public FaultyInvocationExceptionTest() {
    }

    /**
     * Test of getFaultyObject method, of class FaultyInvocationException.
     */
    @Test
    public void testGetFaultyObject() {
        System.out.println("getFaultyObject");
        Object expResult = faulty;
        Object result = instance.getFaultyObject();
        assertEquals(expResult, result);
    }
    
}
