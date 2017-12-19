package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlElement;

public class Regime {
    
    @XmlElement
    private int torque;
    
    @XmlElement
    private int rpm_low;

    @XmlElement
    private int rpm_high;
    
    @XmlElement
    private int SFC;

    public Regime() {
    }

    public Regime(int torque, int rpm_low, int rpm_high, int SFC) {
        this.torque = torque;
        this.rpm_low = rpm_low;
        this.rpm_high = rpm_high;
        this.SFC = SFC;
    }
    
    
}
