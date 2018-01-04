package lapr.project.model.Vehicle;

import java.math.BigDecimal;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.RoadNetwork.Segment;
import lapr.project.utils.Measurable;
import lapr.project.utils.Physics;
import lapr.project.utils.Unit;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Defines abstract properties common to various vehicle implementations
 * </p>
 */
public class Vehicle {

    private String name;
    private String description;

    private VehicleType type;

    private int vehicleClass;

    private Motorization motorization;

    private MotorType motorType;

    private Fuel fuel;

    private Measurable mass;
    private Measurable maxLoad;

    private double dragCoefficient;
    private Measurable frontalArea;
    private double rollingResistanceCoefficient;
    private Measurable wheelSize;

    private List<VelocityLimit> velocityLimitList;
    private Energy energy;

    /**
     * Creates a new vehicle
     *
     * @param name This vehicle's name
     * @param description This vehicle's description
     * @param type This vehicle's type
     * @param vehicleClass This vehicle's class
     * @param motorType This vehicle's motor type
     * @param fuel This vehicle's fuel
     * @param mass This vehicle's mass
     * @param maxLoad This vehicle's max load
     * @param dragCoefficient This vehicle's drag coefficient
     * @param frontalArea This vehicle's frontal area
     * @param rollingResistanceCoefficient This vehicle's rolling release
     * coefficient
     * @param wheelSize This vehicle's wheel size
     * @param velocityLimitList This vehicle's velocity limit list
     * @param energy This vehicle's energy
     */
    public Vehicle(String name, String description, VehicleType type, int vehicleClass, MotorType motorType, Fuel fuel, Measurable mass, Measurable maxLoad, float dragCoefficient, Measurable frontalArea, float rollingResistanceCoefficient, Measurable wheelSize, List<VelocityLimit> velocityLimitList, Energy energy) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.vehicleClass = vehicleClass;
        this.motorType = motorType;

        if (motorType == MotorType.COMBUSTION) {
            motorization = new CombustionMotor();
        } else if (motorType == MotorType.NONCOMBUSTION) {
            motorization = new NonCombustionMotor();
        }

        this.fuel = fuel;
        this.mass = mass;
        this.maxLoad = maxLoad;
        this.dragCoefficient = dragCoefficient;
        this.frontalArea = frontalArea;
        this.rollingResistanceCoefficient = rollingResistanceCoefficient;
        this.wheelSize = wheelSize;
        this.velocityLimitList = velocityLimitList;
        this.energy = new Energy(energy);
    }

    /**
     * @return vehicle class
     */
    public int getVehicleClass() {
        return vehicleClass;
    }

    /**
     * Retrieves the max velocity of the vehicle according to the road's
     * typology given as a parameter
     *
     * @param roadTypology the road's typology
     * @return the max velocity of the vehicle
     */
    public Measurable retrieveMaxVelocity(String roadTypology) {

        if (velocityLimitList.isEmpty()) {

            //the max velocity will be the max velocity of the road
            return null;

        } else {

            if (roadTypology.toLowerCase().contains("highway")) {

                for (VelocityLimit velocityLimit : velocityLimitList) {

                    if (velocityLimit.getSegmentType().equalsIgnoreCase("highway")) {

                        return velocityLimit.getLimit();

                    }

                }

            }

            if (roadTypology.toLowerCase().contains("road")) {

                for (VelocityLimit velocityLimit : velocityLimitList) {

                    if (velocityLimit.getSegmentType().equalsIgnoreCase("road")) {

                        return velocityLimit.getLimit();

                    }

                }

            }

        }

        return null;
    }

    /**
     * Calculates the energy expenditure for this vehicle in this segment
     * without taking into account the acceleration and breaking
     *
     * @param roadNetwork the road network
     * @param segment the segment
     * @param load the vehicle's load
     * @return the energy expenditure in KJ
     */
    public Measurable determineEnergyExpenditure(RoadNetwork roadNetwork, Segment segment, Measurable load) {

        int gearPosition = energy.getGears().size() - 1;
        int throttlePosition = 0;
        Measurable maxLinearVelocity = segment.calculateMaximumVelocityInterval(roadNetwork, this);

        Measurable[] data = calculateEngineSpeedTorqueSFCVelocity(segment, gearPosition, throttlePosition, maxLinearVelocity, load);

        Measurable power = calculatePowerGenerated(data[0], data[1]);

        double SFC = data[2].getQuantity();
        double timeSpent = segment.getLength() / data[3].getQuantity();

        double fuelQuantity = power.getQuantity() * Physics.KILOMETERS_METERS_CONVERSION_RATIO * SFC * timeSpent;

        return new Measurable(fuelQuantity * fuel.getSpecificEnergy().getQuantity(), Unit.KILOJOULE);
    }

    /**
     * Calculates the power generated by the engine
     *
     * @param engineSpeed the engine speed
     * @param torque the torque
     * @return the power
     */
    private Measurable calculatePowerGenerated(Measurable engineSpeed, Measurable torque) {

        double power = 2 * Math.PI * torque.getQuantity() * engineSpeed.getQuantity() / 60d;

        return new Measurable(power, Unit.WATT);

    }

    /**
     * Calculates the engine speed, torque, SFC and velocity for this vehicle in the
     * segment and maximum linear velocity given by parameter
     *
     * @param segment the segment
     * @param gearPosition the gear position
     * @param throttlePosition the throttle position
     * @param maxLinearVelocity the maximum linear velocity
     * @param load the vehicle's load
     * @return an array with the engine speed in the first position, the torque
     * in the second position, the SFC in the third position and the velocity
     * in the forth position
     */
    private Measurable[] calculateEngineSpeedTorqueSFCVelocity(Segment segment,
                                                               int gearPosition, int throttlePosition, Measurable maxLinearVelocity, Measurable load) {

        double engineSpeed
                = (maxLinearVelocity.getQuantity() * 60 * energy.getFinalDriveRatio() * energy.getGears().get(gearPosition).getRatio())
                / (2 * Math.PI * (wheelSize.getQuantity() / 2) * Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO);

        double torque = 0;
        double SFC = 0;

        for (Regime regime : energy.getThrottles().get(throttlePosition).getRegimes()) {

            if (regime.getRpmHigh() >= engineSpeed && regime.getRpmLow() <= engineSpeed) {
                torque = regime.getTorqueLow();
                SFC = regime.getSFC();
                break;
            }
        }

        // if engine speed isn't inside the limits of this velocity, we reduce the velocity
        if (BigDecimal.valueOf(torque).compareTo(BigDecimal.ZERO) == 0) {
            maxLinearVelocity.setQuantity(maxLinearVelocity.getQuantity() - maxLinearVelocity.getQuantity() * 0.02d);
            gearPosition = energy.getGears().size() - 1;
            throttlePosition = 0;
            return calculateEngineSpeedTorqueSFCVelocity(segment, gearPosition, throttlePosition, maxLinearVelocity, load);
        }

        double motorForce = (torque * energy.getFinalDriveRatio() * energy.getGears().get(gearPosition).getRatio())
                / (wheelSize.getQuantity() / 2);

        Measurable segmentAngle = segment.calculateAngle();

        double rollingResistance = rollingResistanceCoefficient * (mass.getQuantity() + load.getQuantity())
                * Physics.GRAVITY_ACCELERATION.getQuantity() * Math.cos(segmentAngle.getQuantity());

        Measurable maxAirRelatedVelocity = segment.calculateAirRelatedVelocity(maxLinearVelocity);
        double airDrag = 0.5 * dragCoefficient * frontalArea.getQuantity() * Physics.AIR_DENSITY.getQuantity()
                * Math.pow(maxAirRelatedVelocity.getQuantity(), 2);

        double gravitationalForce = (mass.getQuantity() + load.getQuantity()) * Physics.GRAVITY_ACCELERATION.getQuantity()
                * Math.sin(segmentAngle.getQuantity());

        // if motor force is lesser than the sum of the rolling resistance, air drag and gravitational force
        if (motorForce < rollingResistance + airDrag + gravitationalForce) {

            // if the throttle position is not 100%, we increase the throttle position
            if (throttlePosition < 2) {
                return calculateEngineSpeedTorqueSFCVelocity(segment, gearPosition, ++throttlePosition, maxLinearVelocity, load);
            }

            // if the throttle position is in 100%, we decrease the gear position and start the throttle as 25%
            throttlePosition = 0;
            return calculateEngineSpeedTorqueSFCVelocity(segment, --gearPosition, throttlePosition, maxLinearVelocity, load);

        }

        return new Measurable[]{new Measurable(engineSpeed, Unit.ROTATIONS_PER_MINUTE), new Measurable(torque, Unit.NEWTON_METER),
            new Measurable(SFC, Unit.GRAM_PER_KILOWATT_HOUR), maxLinearVelocity};
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehicle other = (Vehicle) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("%s - %s.", name, description);
    }

    public String getName() {
        return name;
    }

    public Vehicle() {
    }

    /**
     * @return motor type
     */
    public MotorType getMotorType() {
        return motorType;
    }

    /**
     * Checks that this vehicle is able to support a given load
     * @param load an instance of {@link Measurable}
     * @return true if the vehicle is able to support the given load
     */
    public boolean hasValidLoad(Measurable load) {
        return load.getQuantity() <= maxLoad.getQuantity();
    }

    public void storeVehicleInformation(CallableStatement storeVehicleInfoProcedure) throws SQLException {
        storeVehicleInfoProcedure.setString("vehicleType", type.name());
        storeVehicleInfoProcedure.setString("motorType", motorType.name());
        storeVehicleInfoProcedure.setString("fuelType", fuel.name());
        storeVehicleInfoProcedure.setDouble("dragCoefficient", dragCoefficient);
        storeVehicleInfoProcedure.setDouble("rollingResistanceCoefficient", rollingResistanceCoefficient);
    }

    /**
     * @return {@link Measurable} mass
     */
    public Measurable getMass() {
        return mass;
    }

    /**
     * @return {@link Measurable} maxLoad
     */
    public Measurable getMaxLoad() {
        return maxLoad;
    }

    /**
     * @return {@link Measurable} frontalArea
     */
    public Measurable getFrontalArea() {
        return frontalArea;
    }

    /**
     * @return {@link Measurable} wheelSize
     */
    public Measurable getWheelSize() {
        return wheelSize;
    }

    /**
     * @return {@link List} of instances {@link VelocityLimit}
     */
    public List<VelocityLimit> getVelocityLimitList() {
        return velocityLimitList;
    }

    /**
     * @return {@link Energy} energy
     */
    public Energy getEnergy() {
        return energy;
    }

    /**
     * Indicates motor type Assists in the instantiation of the correct
     * motorization
     */
    public enum MotorType {

        COMBUSTION, NONCOMBUSTION;

    }

}
