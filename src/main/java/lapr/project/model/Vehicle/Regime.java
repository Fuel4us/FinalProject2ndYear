package lapr.project.model.Vehicle;

public class Regime {
    
    private final int torqueLow;
    private final int torqueHigh;
    private final int rpmLow;
    private final int rpmHigh;
    private final double SFC;
    
    public Regime() {
        this.torqueLow = 0;
        this.torqueHigh = 0;
        this.rpmLow = 0;
        this.rpmHigh = 0;
        this.SFC = 0;
    }

    public Regime(int torqueLow,int torqueHigh, int rpmLow, int rpmHigh, double SFC) {
        this.torqueLow = torqueLow;
        this.torqueHigh = torqueHigh;
        this.rpmLow = rpmLow;
        this.rpmHigh = rpmHigh;
        this.SFC = SFC;
    }

    public Regime(int newTorqueLow, int newTorqueHigh, int newRpmLow, int newRpmHigh) {
        torqueLow = newTorqueLow;
        torqueHigh = newTorqueHigh;
        rpmLow = newRpmLow;
        rpmHigh = newRpmHigh;
        SFC = 0;
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
