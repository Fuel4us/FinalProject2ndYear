package lapr.project.model.Vehicle;

import java.util.Optional;

/**
 * <p>
 * Defines abstract properties common
 * to various vehicle implementations
 * </p>
 */
public abstract class AbstractVehicle {

    private String name;

    private VehicleType type;

    private TollClass vehicleClass;

    private double mass;

    /**
     * Not all vehicles transport load.
     * {@link Optional} is used to force unwrap,
     * reminding the programmer that load may be null
     */
    private Optional<Load> load;

    private double dragCoefficient;

    private double rollingReleaseCoefficient;

    private double wheelSize;

    /**
     * Determines the energy expenditure of a vehicle
     */
    public abstract double determineEnergyExpenditure();

}