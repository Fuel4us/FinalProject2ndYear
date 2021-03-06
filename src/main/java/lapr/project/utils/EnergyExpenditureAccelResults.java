package lapr.project.utils;

import lapr.project.model.Gears;

/**
 * Contains the results of the method calculateEnergyExpenditureAccel of a
 * Section
 */
public class EnergyExpenditureAccelResults {

    private Measurable energyExpenditure;
    private Measurable finalVelocity;
    private Measurable timeSpent;
    private Gears[] gearForEachSegment;
    private Measurable tollCosts;

    public EnergyExpenditureAccelResults() {
    }

    /**
     * Constructor
     *
     * @param energyExpenditure the energy expenditure in the section
     * @param finalVelocity the velocity that the vehicle reached at the end of
     * the section
     * @param timeSpent the time spent in the section
     * @param gearForEachSegment the gear used for each segment
     * @param tollCosts the toll costs for the section
     */
    public EnergyExpenditureAccelResults(Measurable energyExpenditure, Measurable finalVelocity, Measurable timeSpent,
            Gears[] gearForEachSegment, Measurable tollCosts) {
        this.energyExpenditure = energyExpenditure;

        this.finalVelocity = finalVelocity;
        this.timeSpent = timeSpent;
        this.gearForEachSegment = gearForEachSegment;
        this.tollCosts = tollCosts;
    }

    /**
     * Constructor with the parameters energyExpenditure, finalVelocity,
     * timeSpent and gearForEachSegment
     *
     * @param energyExpenditure the energy expenditure in the section
     * @param finalVelocity the velocity that the vehicle reached at the end of
     * the section
     * @param timeSpent the time spent in the section
     * @param gearForEachSegment the gear used for each segment
     */
    public EnergyExpenditureAccelResults(Measurable energyExpenditure, Measurable finalVelocity, Measurable timeSpent, Gears[] gearForEachSegment) {
        this.energyExpenditure = energyExpenditure;
        this.finalVelocity = finalVelocity;
        this.timeSpent = timeSpent;
        this.gearForEachSegment = gearForEachSegment;
        tollCosts = new Measurable(0, Unit.EUROS);
    }

    /**
     * Constructor with the parameters energyExpenditure, finalVelocity and
     * timeSpent
     *
     * @param energyExpenditure the energy expenditure
     * @param finalVelocity the final velocity of the vehicle
     * @param timeSpent the time spent
     */
    public EnergyExpenditureAccelResults(Measurable energyExpenditure, Measurable finalVelocity, Measurable timeSpent) {
        this.energyExpenditure = energyExpenditure;
        this.finalVelocity = finalVelocity;
        this.timeSpent = timeSpent;
        gearForEachSegment = new Gears[]{};
        tollCosts = new Measurable(0, Unit.EUROS);
    }

    /**
     * Creates an instance of {@link EnergyExpenditureAccelResults} containing
     * information about travelling time and toll costs.
     *
     * @param timeSpent the time spent in the section
     * @param tollCosts the toll costs for the section
     */
    public EnergyExpenditureAccelResults(Measurable timeSpent, Measurable tollCosts) {
        this.timeSpent = timeSpent;
        this.tollCosts = tollCosts;
    }

    /**
     * Constructor with the parameters energyExpenditure, finalVelocity,
     * timeSpent and tollCosts
     *
     * @param energyExpenditure the energy expenditure
     * @param finalVelocity the final velocity of the vehicle
     * @param timeSpent the time spent
     * @param tollCosts the toll costs
     */
    public EnergyExpenditureAccelResults(Measurable energyExpenditure, Measurable finalVelocity, Measurable timeSpent, Measurable tollCosts) {
        this.energyExpenditure = energyExpenditure;
        this.finalVelocity = finalVelocity;
        this.timeSpent = timeSpent;
        this.tollCosts = tollCosts;
    }

    /**
     * Copy constructor
     *
     * @param results other instance of the class EnergyExpenditureAccelResults
     */
    public EnergyExpenditureAccelResults(EnergyExpenditureAccelResults results) {
        this.energyExpenditure = results.energyExpenditure;
        this.finalVelocity = results.finalVelocity;
        this.timeSpent = results.timeSpent;
        this.tollCosts = results.tollCosts;
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

    /**
     * @return the toll costs
     */
    public Measurable getTollCosts() {
        return tollCosts;
    }
}
