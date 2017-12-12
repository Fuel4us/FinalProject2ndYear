package lapr.project.model.Vehicle;

/**
 * Defines properties and behaviour of vehicles not using combustion
 */
public abstract class AbstractNonCombustionVehicle extends AbstractVehicle {

    /**
     *  Defines abstract behaviour for vehicle
     *  energy regeneration when braking
     */
    public abstract double determineBrakingEnergyRegeneration();

}