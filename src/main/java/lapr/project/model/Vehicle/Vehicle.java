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

    /**
     * Constructor
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
    public Vehicle(String name, String description, VehicleType type, TollClass vehicleClass, Motorization motorization, Fuel fuel, Measurable mass, Measurable load, double dragCoefficient, double frontalRear, double rollingReleaseCoefficient, double wheelSize, List<VelocityLimitList> velocityLimitList, Energy energy) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.vehicleClass = vehicleClass;
        this.motorization = motorization;
        this.fuel = fuel;
        this.mass = mass;
        this.load = load;
        this.dragCoefficient = dragCoefficient;
        this.frontalRear = frontalRear;
        this.rollingReleaseCoefficient = rollingReleaseCoefficient;
        this.wheelSize = wheelSize;
        this.velocityLimitList = velocityLimitList;
        this.energy = energy;
    }
}