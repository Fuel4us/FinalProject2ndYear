package lapr.project.model.Vehicle;

/**
 * Defines properties and behaviour for hybrid vehicles
 */
public abstract class HybridVehicle extends AbstractNonCombustionVehicle {

    /**
     * Calculates the energy regenerated when braking
     */
    @Override
    public double determineBrakingEnergyRegeneration() {
        //ToDo Implement method body
        throw new UnsupportedOperationException();
    }

}