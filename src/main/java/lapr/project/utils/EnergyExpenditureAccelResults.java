package lapr.project.utils;

import lapr.project.model.Vehicle.Gears;

/**
 * Contains the results of the method calculateEnergyExpenditureAccel of a Section
 */
public class EnergyExpenditureAccelResults {

    private final Measurable energyExpenditure;
    private final Measurable finalVelocity;
    private final Measurable timeSpent;
    private final Gears[] gearForEachSegment;

    /**
     * Constructor
     * @param energyExpenditure the energy expenditure
     * @param finalVelocity the velocity that the vehicle reached at the end of the section
     * @param timeSpent the time spent in the section
     * @param gearForEachSegment the gear used for each segment
     */
    public EnergyExpenditureAccelResults(Measurable energyExpenditure, Measurable finalVelocity, Measurable timeSpent,
                                         Gears[] gearForEachSegment) {
        this.energyExpenditure = energyExpenditure;
        this.finalVelocity = finalVelocity;
        this.timeSpent = timeSpent;
        this.gearForEachSegment = gearForEachSegment;
    }

    /**
     * @return the energy expenditure
     */
    public Measurable getEnergyExpenditure() {
        return energyExpenditure;
    }

    /**
     * @return the final velocity
     */
    public Measurable getFinalVelocity() {
        return finalVelocity;
    }

    /**
     * @return the time spent
     */
    public Measurable getTimeSpent() {
        return timeSpent;
    }

    /**
     * @return the gear used in each segment
     */
    public Gears[] getGearForEachSegment() {
        return gearForEachSegment;
    }
}
