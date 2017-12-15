package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Defines values for
 * the toll class of a vehicle
 * </p>
 */
public class TollClass {

    @XmlElement
    private int id;

    @XmlElement(name = "class")
    private double tollValue = 0;
}
