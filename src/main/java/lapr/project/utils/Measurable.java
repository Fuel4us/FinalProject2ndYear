/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Objects;

/**
 * <p>
 * A measurable is a data structure containing
 * information about quantity and a corresponding unit
 * </p>
 */
public class Measurable {

    private double quantity;
    private Unit unit;
    
    public Measurable(){
    }

    public Measurable(double quantity, Unit unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * Getter for the attribute quantity
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Setter for the attribute quantity
     * @param quantity the quantity
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the unit of this measurable
     */
    public Unit getUnit() {
        return unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurable that = (Measurable) o;
        return Double.compare(that.quantity, quantity) == 0 &&
                unit == that.unit;
    }

    @Override
    public String toString() {
        return String.format("%s %s", quantity, unit.toString());
    }

}
