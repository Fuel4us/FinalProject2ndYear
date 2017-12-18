package lapr.project.model.Vehicle;

import java.util.List;
import java.util.Optional;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import lapr.project.utils.Measurable;

/**
 * <p>
 * Defines abstract properties common to various vehicle implementations
 * </p>
 */
public class Vehicle {

    @XmlElement
    private String name;

    @XmlElement
    private String description;

//    @XmlElement
//    private VehicleType type;
    
//    @XmlElement(name = "toll_class")
//    private TollClass vehicleClass;
//
//    @XmlElement
//    private Motorization motorization;
//
//    @XmlElement(name = "fuel")
//    private Fuel fuel;
//
//    @XmlElement
//    private Measurable mass;
//
//    @XmlElement
//    private Measurable load;
    
    private String type;
    private int vehicleClass;
    private String motorization;
    private String fuel;
    private int mass;
    private int load;

    @XmlElement(name = "drag")
    private double dragCoefficient;

    @XmlElement(name = "frontal_area")
    private double frontalArea;

    @XmlElement(name = "rrc")
    private double rollingReleaseCoefficient;

    @XmlElement(name = "wheel_size")
    private double wheelSize;

    @XmlElementWrapper(name = "velocity_limit_list")
    private List<VelocityLimit> velocityLimitList;

    @XmlElement(name = "energy")
    private Energy energy;

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
     * @param frontalArea
     * @param rollingReleaseCoefficient
     * @param wheelSize
     * @param velocityLimitList
     * @param energy
     */
    public Vehicle(String name, String description, String type, int vehicleClass, String motorization, String fuel, int mass, int load, double dragCoefficient, double frontalArea, double rollingReleaseCoefficient, double wheelSize, List<VelocityLimit> velocityLimitList, Energy energy) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.vehicleClass = vehicleClass;
        this.motorization = motorization;
        this.fuel = fuel;
        this.mass = mass;
        this.load = load;
        this.dragCoefficient = dragCoefficient;
        this.frontalArea = frontalArea;
        this.rollingReleaseCoefficient = rollingReleaseCoefficient;
        this.wheelSize = wheelSize;
        this.velocityLimitList = velocityLimitList;
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public VehicleType getType() {
//        return type;
//    }
//
//    public void setType(VehicleType type) {
//        this.type = type;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public TollClass getVehicleClass() {
//        return vehicleClass;
//    }
//
//    public void setVehicleClass(TollClass vehicleClass) {
//        this.vehicleClass = vehicleClass;
//    }
//
//    public Motorization getMotorization() {
//        return motorization;
//    }
//
//    public void setMotorization(Motorization motorization) {
//        this.motorization = motorization;
//    }
//
//    public Fuel getFuel() {
//        return fuel;
//    }
//
//    public void setFuel(Fuel fuel) {
//        this.fuel = fuel;
//    }
//
//    public Measurable getMass() {
//        return mass;
//    }
//
//    public void setMass(Measurable mass) {
//        this.mass = mass;
//    }
//
//    public Measurable getLoad() {
//        return load;
//    }
//
//    public void setLoad(Measurable load) {
//        this.load = load;
//    }

    public int getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(int vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getMotorization() {
        return motorization;
    }

    public void setMotorization(String motorization) {
        this.motorization = motorization;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }
    
    

    public double getDragCoefficient() {
        return dragCoefficient;
    }

    public void setDragCoefficient(double dragCoefficient) {
        this.dragCoefficient = dragCoefficient;
    }

    public double getFrontalArea() {
        return frontalArea;
    }

    public void setFrontalArea(double frontalArea) {
        this.frontalArea = frontalArea;
    }

    public double getRollingReleaseCoefficient() {
        return rollingReleaseCoefficient;
    }

    public void setRollingReleaseCoefficient(double rollingReleaseCoefficient) {
        this.rollingReleaseCoefficient = rollingReleaseCoefficient;
    }

    public double getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(double wheelSize) {
        this.wheelSize = wheelSize;
    }

    public List<VelocityLimit> getVelocityLimitList() {
        return velocityLimitList;
    }

    public void setVelocityLimitList(List<VelocityLimit> velocityLimitList) {
        this.velocityLimitList = velocityLimitList;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

}
