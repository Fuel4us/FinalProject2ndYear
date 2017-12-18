package lapr.project.model.Vehicle;

import lapr.project.utils.Measurable;

import java.util.List;

/**
 * Defines properties and behaviour of vehicles not using combustion
 */
public abstract class AbstractNonCombustionVehicle extends Vehicle {

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
     * @param frontalRear
     * @param rollingReleaseCoefficient
     * @param wheelSize
     * @param velocityLimitList
     * @param energy
     */
    public AbstractNonCombustionVehicle(String name, String description, String type, int vehicleClass, String motorization, String fuel, int mass, int load, double dragCoefficient, double frontalArea, double rollingReleaseCoefficient, double wheelSize, List<VelocityLimit> velocityLimitList, Energy energy) {
        super(name, description, type, vehicleClass, motorization, fuel, mass, load, dragCoefficient, frontalArea, rollingReleaseCoefficient, wheelSize, velocityLimitList, energy);
    }

    /**
     *  Defines abstract behaviour for vehicle
     *  energy regeneration when braking
     */
    public abstract double determineBrakingEnergyRegeneration();

}