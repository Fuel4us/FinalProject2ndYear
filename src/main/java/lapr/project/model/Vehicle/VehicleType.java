package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Defines discrete values for vehicle designations
 */
@XmlRootElement
public enum VehicleType {
    Truck,
    Tractor,
    Car,
    Motorcycle
}