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
        int hash = 3;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.quantity) ^ (Double.doubleToLongBits(this.quantity) >>> 32));
        hash = 19 * hash + Objects.hashCode(this.unit);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Measurable other = (Measurable) obj;
        if (Double.doubleToLongBits(this.quantity) != Double.doubleToLongBits(other.quantity)) {
            return false;
        }
        if (this.unit != other.unit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s %s", quantity, unit.toString());
    }

}
