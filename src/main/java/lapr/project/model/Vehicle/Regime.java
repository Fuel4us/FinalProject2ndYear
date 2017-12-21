package lapr.project.model.Vehicle;

public class Regime {
    
    private int torqueLow;
    private int torqueHigh;
    private int rpmLow;
    private int rpmHigh;
    private double SFC;
    
    public Regime() {
    }

    public Regime(int torqueLow,int torqueHigh, int rpmLow, int rpmHigh, double SFC) {
        this.torqueLow = torqueLow;
        this.torqueHigh = torqueHigh;
        this.rpmLow = rpmLow;
        this.rpmHigh = rpmHigh;
        this.SFC = SFC;
    }

    /**
     * Getter method for torqueLow
     * 
     * @return torqueLow
     */
    public int getTorqueLow() {
        return torqueLow;
    }

    /**
     * Getter method for torqueLow
     * 
     * @return torqueLow
     */
    public int getTorqueHigh() {
        return torqueHigh;
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
    public double getSFC() {
        return SFC;
    }
}
