package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlElement;

public class Throttle {

    @XmlElement
    private int id;

    @XmlElement
    private int torque;

    @XmlElement
    private int rpm_low;

    @XmlElement
    private int rpm_high;

    @XmlElement
    private int SFC;

    public Throttle(int id, int torque, int rpm_low, int rpm_high, int SFC) {
        this.id = id;
        this.torque = torque;
        this.rpm_low = rpm_low;
        this.rpm_high = rpm_high;
        this.SFC = SFC;
    }

    public Throttle() {
    }

    public int getId() {
        return id;
    }

    public int getRpm_high() {
        return rpm_high;
    }

    public int getRpm_low() {
        return rpm_low;
    }

    public int getSFC() {
        return SFC;
    }

    public int getTorque() {
        return torque;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRpm_high(int rpm_high) {
        this.rpm_high = rpm_high;
    }

    public void setRpm_low(int rpm_low) {
        this.rpm_low = rpm_low;
    }

    public void setSFC(int SFC) {
        this.SFC = SFC;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

}
