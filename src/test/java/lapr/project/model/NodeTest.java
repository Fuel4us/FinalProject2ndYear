/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pedro
 */
public class NodeTest {
    
    public NodeTest() {
    }
    
    /**
     * Test of toString method, of class Node.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Node instance = new Node("id");
        String expResult = String.format("%s","id");
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Node.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Node instance = new Node("id");
        String expResult = "id";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Node.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = new Node("Beta");
        Node instance = new Node("Alpha");
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Node.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Node instance = new Node("Alpha");
        int expResult = 2027433794;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
}
