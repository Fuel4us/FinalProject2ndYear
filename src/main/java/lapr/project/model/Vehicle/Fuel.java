package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Type of fuel that the car uses
 */
@XmlRootElement(name = "fuel")
public enum Fuel {

   Diesel, Gasoline, Electric, 

}
