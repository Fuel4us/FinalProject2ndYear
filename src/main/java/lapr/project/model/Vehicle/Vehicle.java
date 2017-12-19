package lapr.project.model.Vehicle;

import java.util.List;
import lapr.project.utils.*;

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
    private Fuel fuel;
    private Measurable mass;
    private Measurable load;
    
    private float dragCoefficient;
    private float frontalArea;
    private float rollingReleaseCoefficient;
    private float wheelSize;
    
    private List<VelocityLimit> velocityLimitList;
    private Energy energy;

    /**
     * Constructor
     *
     * @param name
     * @param description
     * @param type
     * @param vehicleClass
     * @param motorType
     * @param fuel
     * @param mass
     * @param load
     * @param dragCoefficient
     * @param frontalArea
     * @param rollingReleaseCoefficient
     * @param wheelSize
     * @param velocityLimitList
     * @param energy
     */
    public Vehicle(String name, String description, VehicleType type, int vehicleClass, MotorType motorType, Fuel fuel, Measurable mass, Measurable load, float dragCoefficient, Float frontalArea, float rollingReleaseCoefficient, Float wheelSize, List<VelocityLimit> velocityLimitList, Energy energy) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.vehicleClass = vehicleClass;
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
        this.rollingReleaseCoefficient = rollingReleaseCoefficient;
        this.wheelSize = wheelSize;
        this.velocityLimitList = velocityLimitList;
        this.energy = energy;
    }
    
    @Override
    public String toString(){
        return String.format("%s - %s.", name,description);
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

    /**
     * Indicates motor type
     * Assists in the instantiation of the correct motorization
     */
    public enum MotorType {

        COMBUSTION, NONCOMBUSTION

    }
}
