package lapr.project.model.Vehicle;

import java.util.List;

public class Energy {

    private int minRpm;
    private int maxRpm;
    private float finalDriveRatio;
    private double energyRegenerationRatio;
    private List<Gears> gears;
    private List<Throttle> throttles;

    /**
     * Constructor
     * @param minRpm the minimum value for the rpm
     * @param maxRpm the maximum value for the rpm
     * @param finalDriveRatio the final drive ration
     * @param energyRegenerationRatio the energy regeneration ratio (for electric vehicles)
     * @param gears the gears
     * @param throttles the throttles
     */
    public Energy(int minRpm, int maxRpm, float finalDriveRatio, double energyRegenerationRatio, List<Gears> gears, List<Throttle> throttles) {
        this.minRpm = minRpm;
        this.maxRpm = maxRpm;
        this.finalDriveRatio = finalDriveRatio;
        this.energyRegenerationRatio = energyRegenerationRatio;
        this.gears = gears;
        this.throttles = throttles;
    }

    /**
     * Constructor
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

    /**
     * Copies an instance of {@link Energy}.
     * Can be used to enforce composition over aggregation
     * @param energy The {@link Energy} to be copied
     */
    public Energy(Energy energy) {
        this(energy.minRpm, energy.maxRpm, energy.finalDriveRatio, energy.energyRegenerationRatio, energy.gears, energy.throttles);
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
     * @return minRPM
     */
    public int getMinRpm() {
        return minRpm;
    }

    /**
     * @return maxRMP
     */
    public int getMaxRpm() {
        return maxRpm;
    }

    /**
     *
     * @return energyRegenerationRatio
     */
    public double getEnergyRegenerationRatio() {
        return energyRegenerationRatio;
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
