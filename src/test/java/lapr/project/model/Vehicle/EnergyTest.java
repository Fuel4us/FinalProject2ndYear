/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author goncalo
 */
public class EnergyTest {

    List<Gears> gears = new ArrayList<>();
    List<Throttle> throttle = new ArrayList<>();
    List<Regime> regime = new ArrayList<>();
    private final Energy instance = new Energy(20, 20, 4, gears, throttle);

    public EnergyTest() {
    }

    /**
     * Test of getFinalDriveRatio method, of class Energy.
     */
    @Test
    public void testGetFinalDriveRatio() {
        System.out.println("getFinalDriveRatio");
        float expResult = 4;
        float result = instance.getFinalDriveRatio();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getGears method, of class Energy.
     */
    @Test
    public void testGetGears() {
        System.out.println("getGears");
        List<Gears> expResult = gears;
        List<Gears> result = instance.getGears();
        assertEquals(expResult, result);
    }

    /**
     * Test of getThrottles method, of class Energy.
     */
    @Test
    public void testGetThrottles() {
        System.out.println("getThrottles");
        List<Throttle> expResult = throttle;
        List<Throttle> result = instance.getThrottles();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Energy.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Energy{"
                + "minRpm=" + 20
                + ", maxRpm=" + 20
                + ", finalDriveRatio=" + 4f
                + ", gears=" + gears
                + ", throttles=" + throttle
                + '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
