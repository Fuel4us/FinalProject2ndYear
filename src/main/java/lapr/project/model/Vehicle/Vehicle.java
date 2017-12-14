package lapr.project.model.Vehicle;

import java.util.Optional;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Defines abstract properties common
 * to various vehicle implementations
 * </p>
 */
public abstract class Vehicle {

    @XmlElement
    private String name;
    
    @XmlElement
    private String description;
    
    @XmlElement
    private VehicleType type;
    
    @XmlElement(name = "toll_class")
    private TollClass vehicleClass;
    
    @XmlElement
    private Motorization motorization;
   
    @XmlElement
    private Fuel fuel;
    
    @XmlElement
    private int mass;
    
    /**
     * Not all vehicles transport load.
     * {@link Optional} is used to force unwrap,
     * reminding the programmer that load may be null
     */
    @XmlElement
    private Optional<Load> load;

    @XmlElement(name = "drag")
    private double dragCoefficient;
    
    @XmlElement(name = "frontal_area")
    private double frontalRear;
    
    @XmlElement(name = "rrc")
    private double rollingReleaseCoefficient;

    @XmlElement(name = "wheel_size")
    private double wheelSize;
    
    
    
}