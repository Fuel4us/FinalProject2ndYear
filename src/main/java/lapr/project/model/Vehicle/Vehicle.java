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
     * Creates a new vehicle
     * @param name This vehicle's name
     * @param description This vehicle's description
     * @param type This vehicle's type
     * @param vehicleClass This vehicle's class
     * @param motorization This vehicle's motorization
     * @param fuel This vehicle's fuel
     * @param mass This vehicle's mass
     * @param load This vehicle's load
     * @param dragCoefficient This vehicle's drag coefficient
     * @param frontalArea This vehicle's frontal area
     * @param rollingReleaseCoefficient This vehicle's rolling release coefficient
     * @param wheelSize This vehicle's wheel size
     * @param velocityLimitList This vehicle's velocity limit list
     * @param energy This vehicle's energy
     */
    public Vehicle(String name, String description, VehicleType type, int vehicleClass, Motorization motorization, Fuel fuel, Measurable mass, Measurable load, float dragCoefficient, Float frontalArea, float rollingReleaseCoefficient, Float wheelSize, List<VelocityLimit> velocityLimitList, Energy energy) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.vehicleClass = vehicleClass;
        this.motorization = motorization;
        this.fuel = fuel;
        this.mass = mass;
        this.load = load;
        this.dragCoefficient = dragCoefficient;
        this.frontalArea = frontalArea;
        this.rollingReleaseCoefficient = rollingReleaseCoefficient;
        this.wheelSize = wheelSize;
        this.velocityLimitList = velocityLimitList;
        this.energy = new Energy(energy);
    }
    
    @Override
    public String toString(){
        return String.format("%s - %s.", name,description);
    }

}
