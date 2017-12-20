package lapr.project.model.Vehicle;

import lapr.project.utils.Measurable;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Defines abstract properties common to various vehicle implementations
 * </p>
 */
public class Vehicle {

    private String name;
    private String description;

    private VehicleType type;

    private int vehicleClass;

    private Motorization motorization;

    private MotorType motorType;

    private Fuel fuel;

    private Measurable mass;
    private Measurable load;

    private double dragCoefficient;
    private Measurable frontalArea;
    private double rollingResistanceCoefficient;
    private Measurable wheelSize;

    private List<VelocityLimit> velocityLimitList;
    private Energy energy;

    /**
     * Creates a new vehicle
     * @param name This vehicle's name
     * @param description This vehicle's description
     * @param type This vehicle's type
     * @param vehicleClass This vehicle's class
     * @param motorType This vehicle's motor type
     * @param fuel This vehicle's fuel
     * @param mass This vehicle's mass
     * @param load This vehicle's load
     * @param dragCoefficient This vehicle's drag coefficient
     * @param frontalArea This vehicle's frontal area
     * @param rollingResistanceCoefficient This vehicle's rolling release coefficient
     * @param wheelSize This vehicle's wheel size
     * @param velocityLimitList This vehicle's velocity limit list
     * @param energy This vehicle's energy
     */
    public Vehicle(String name, String description, VehicleType type, int vehicleClass, MotorType motorType, Fuel fuel, Measurable mass, Measurable load, float dragCoefficient, Measurable frontalArea, float rollingResistanceCoefficient, Measurable wheelSize, List<VelocityLimit> velocityLimitList, Energy energy) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.vehicleClass = vehicleClass;
        this.motorType = motorType;

        if (motorType == MotorType.COMBUSTION) {
            motorization = new CombustionMotor();
        } else if (motorType == MotorType.NONCOMBUSTION) {
            motorization = new NonCombustionMotor();
        }

        this.fuel = fuel;
        this.mass = mass;
        this.load = load;
        this.dragCoefficient = dragCoefficient;
        this.frontalArea = frontalArea;
        this.rollingResistanceCoefficient = rollingResistanceCoefficient;
        this.wheelSize = wheelSize;
        this.velocityLimitList = velocityLimitList;
        this.energy = new Energy(energy);
    }

    /**
     * Retrieves the max velocity of the vehicle according to the road's typology given as a parameter
     * @param roadTypology the road's typology
     * @return the max velocity of the vehicle
     */
    public Measurable retrieveMaxVelocity(String roadTypology) {

        if (velocityLimitList.isEmpty()) {

            //the max velocity will be the max velocity of the road
            return null;

        } else {

            if (roadTypology.toLowerCase().contains("highway")) {

                for (VelocityLimit velocityLimit : velocityLimitList) {

                    if (velocityLimit.getSegmentType().equalsIgnoreCase("highway")) {

                        return velocityLimit.getLimit();

                    }

                }

            }

            if (roadTypology.toLowerCase().contains("road")) {

                for (VelocityLimit velocityLimit : velocityLimitList) {

                    if (velocityLimit.getSegmentType().equalsIgnoreCase("road")) {

                        return velocityLimit.getLimit();

                    }

                }

            }

        }

        return null;
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
        final Vehicle other = (Vehicle) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("%s - %s.", name, description);
    }

    /**
     * Indicates motor type
     * Assists in the instantiation of the correct motorization
     */
    public enum MotorType {

        COMBUSTION, NONCOMBUSTION;

    }

}
