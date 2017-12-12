package lapr.project.model.Vehicle;

/**
 * Defines properties and behaviour for electric vehicles
 */
public abstract class ElectricVehicle extends AbstractNonCombustionVehicle {

    /**
     * Calculates the energy regenerated when braking
     */
    @Override
    public double determineBrakingEnergyRegeneration() {
        //ToDo Implement method body
        throw new UnsupportedOperationException();
    }

}