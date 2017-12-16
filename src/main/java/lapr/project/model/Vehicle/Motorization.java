package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Designation of motor types
 */
@XmlRootElement
public enum Motorization {
    Combustion, Electric, Hybrid
}