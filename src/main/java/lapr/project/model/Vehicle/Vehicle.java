package lapr.project.model.Vehicle;

import java.util.List;
import java.util.Optional;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

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
    
    @XmlElementWrapper(name = "velocity_limit_list")
    private List<VelocityLimitList> velocityLimitsList;
    
    @XmlElement(name = "min_rpm")
    private int minRPM;
    
    @XmlElement(name = "max_rpm")
    private int maxRPM;
    
    @XmlElement(name = "final_drive_ratio")
    private int drive_ratio;
    
    @XmlElementWrapper(name = "gear_list")
    private List<Gear> gearsList;
    
    @XmlElementWrapper(name = "throttle_list")
    private List<Throttle> throttleList;
}