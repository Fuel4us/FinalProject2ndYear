package lapr.project.model.Vehicle;

import java.util.Optional;

/**
 * <p>
 * Defines abstract properties common
 * to various vehicle implementations
 * </p>
 */
public abstract class Vehicle {

    private String name;
    
    private String description;
    
    private Motorization motorization;
    
    private Fuel fuel;

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

}