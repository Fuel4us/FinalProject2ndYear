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

    private double dragCoefficient;
    private float frontalArea;
    private double rollingReleaseCoefficient;
    private double wheelSize;
    private List<VelocityLimit> velocityLimitList;
    private Energy energy;

    /**
     * Constructor
     *
     * @param name
     * @param description
     * @param type
     * @param vehicleClass
     * @param motorization
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
    public Vehicle(String name, String description, VehicleType type, int vehicleClass, Motorization motorization, Fuel fuel, Measurable mass, Measurable load, double dragCoefficient, Float frontalArea, double rollingReleaseCoefficient, double wheelSize, List<VelocityLimit> velocityLimitList, Energy energy) {
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
        this.energy = energy;
    }
}
