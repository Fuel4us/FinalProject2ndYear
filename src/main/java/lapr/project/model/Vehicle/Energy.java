package lapr.project.model.Vehicle;

import java.util.List;

public class Energy {

    private int minRpm;
    private int maxRpm;
    private float finalDriveRatio;
    private List<Gears> gears;
    private List<Throttle> throttles;

    /**
     * Constructor
     *
     * @param minRpm minRpm
     * @param maxRpm maxRpm
     * @param finalDriveRatio finalDriveRatio
     * @param gears gears
     * @param throttles throttles
     */
    public Energy(int minRpm, int maxRpm, float finalDriveRatio, List<Gears> gears, List<Throttle> throttles) {
        this.minRpm = minRpm;
        this.maxRpm = maxRpm;
        this.finalDriveRatio = finalDriveRatio;
        this.gears = gears;
        this.throttles = throttles;
    }

    public Energy(Energy energy) {
        this(energy.minRpm, energy.maxRpm, energy.finalDriveRatio, energy.gears, energy.throttles);
    }

    /**
     * @return final drive ration
     */
    public float getFinalDriveRatio() {
        return finalDriveRatio;
    }

    /**
     * @return gears
     */
    public List<Gears> getGears() {
        return gears;
    }

    /**
     * @return throttles
     */
    public List<Throttle> getThrottles() {
        return throttles;
    }

    /**
     *
     * @return minRPM
     */
    public int getMinRpm() {
        return minRpm;
    }

    /**
     *
     * @return maxRMP
     */
    public int getMaxRpm() {
        return maxRpm;
    }

    @Override
    public String toString() {
        return "Energy{"
                + "minRpm=" + minRpm
                + ", maxRpm=" + maxRpm
                + ", finalDriveRatio=" + finalDriveRatio
                + ", gears=" + gears
                + ", throttles=" + throttles
                + '}';
    }
}
