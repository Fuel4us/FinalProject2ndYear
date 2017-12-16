/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

/**
 *
 * @author goncalo
 */
public class Measurable {
    
    private double quantity;
    private Unit unit;

    public Measurable(double quantity, Unit unit) {
        this.quantity = quantity;
        this.unit = unit;
    }    
    
}
