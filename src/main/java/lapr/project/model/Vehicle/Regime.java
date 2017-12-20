package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlElement;

public class Regime {
    
    
    private int torque_low;
    
    
    private int torque_high;
        
    
    private int rpmLow;

    
    private int rpmHigh;
    
    
    private int SFC;
    
    

    public Regime() {
    }

    public Regime(int torqueLow,int torqueHigh, int rpmLow, int rpmHigh, int SFC) {
        this.torque_low = torqueLow;
        this.torque_high = torqueHigh;
        this.rpmLow = rpmLow;
        this.rpmHigh = rpmHigh;
        this.SFC = SFC;
    }

    /**
     * Getter method for torque_low
     * 
     * @return torque_low 
     */
    public int getTorque_low() {
        return torque_low;
    }

    /**
     * Getter method for torque_low
     * 
     * @return torque_low 
     */
    public int getTorque_high() {
        return torque_high;
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
