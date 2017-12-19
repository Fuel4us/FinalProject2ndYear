package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlElement;

public class Regime {
    
    @XmlElement
    private int torque;
    
    @XmlElement
    private int rpmLow;

    @XmlElement
    private int rpmHigh;
    
    @XmlElement
    private int SFC;

    public Regime(int torque, int rpmLow, int rpmHigh, int SFC) {
        this.torque = torque;
        this.rpmLow = rpmLow;
        this.rpmHigh = rpmHigh;
        this.SFC = SFC;
    }

    /**
     * @return torque
     */
    public int getTorque() {
        return torque;
    }

    /**
     * @return rpm low
     */
    public int getRpmLow() {
        return rpmLow;
    }

    /**
     * @return rpm high
     */
    public int getRpmHigh() {
        return rpmHigh;
    }

    /**
     * @return SFC
     */
    public int getSFC() {
        return SFC;
    }
}
