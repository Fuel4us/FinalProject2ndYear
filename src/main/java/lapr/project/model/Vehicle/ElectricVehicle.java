package lapr.project.model.Vehicle;

import lapr.project.utils.Measurable;

import java.util.List;

/**
 * Defines properties and behaviour for electric vehicles
 */
public abstract class ElectricVehicle extends AbstractNonCombustionVehicle {

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
    public ElectricVehicle(String name, String description, VehicleType type, TollClass vehicleClass, Motorization motorization, Fuel fuel, Measurable mass, Measurable load, double dragCoefficient, double frontalRear, double rollingReleaseCoefficient, double wheelSize, List<VelocityLimit> velocityLimitList, Energy energy) {
        super(name, description, type, vehicleClass, motorization, fuel, mass, load, dragCoefficient, frontalRear, rollingReleaseCoefficient, wheelSize, velocityLimitList, energy);
    }

    /**
     * Calculates the energy regenerated when braking
     */
    @Override
    public double determineBrakingEnergyRegeneration() {
        //ToDo Implement method body
        throw new UnsupportedOperationException();
    }

}