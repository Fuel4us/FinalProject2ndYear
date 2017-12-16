package lapr.project.model.Vehicle;

import java.util.List;
import java.util.Optional;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import lapr.project.utils.Measurable;

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
    private Measurable mass;
    
    @XmlElement
    private Measurable load;

    @XmlElement(name = "drag")
    private double dragCoefficient;
    
    @XmlElement(name = "frontal_area")
    private double frontalRear;
    
    @XmlElement(name = "rrc")
    private double rollingReleaseCoefficient;

    @XmlElement(name = "wheel_size")
    private double wheelSize;
    
    @XmlElementWrapper(name = "velocity_limit_list")
    private List<VelocityLimitList> velocityLimitList;
    
    @XmlElement(name = "energy")
    private Energy energy;
    
    
}