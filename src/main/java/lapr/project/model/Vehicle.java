package lapr.project.model;

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
    public Vehicle(String name, String description, VehicleType type, int vehicleClass, MotorType motorType, Fuel fuel, Measurable mass, Measurable maxLoad,
            float dragCoefficient, Measurable frontalArea, float rollingResistanceCoefficient, Measurable wheelSize, List<VelocityLimit> velocityLimitList, Energy energy) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.vehicleClass = vehicleClass;
        this.motorType = motorType;
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
     * Package-private constructor for test purposes
     */
    Vehicle() {}

    /**
     * @return vehicle class
     */
    int getVehicleClass() {
        return vehicleClass;
    }

    /**
     * Retrieves the max velocity of the vehicle according to the road's
     * typology given as a parameter
     *
     * @param roadTypology the road's typology
     * @return the max velocity of the vehicle
     */
    Measurable retrieveMaxVelocity(String roadTypology) {

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
     * @param segment the segment
     * @param load the vehicle's load
     * @param length the length to be used
     * @param velocity the velocity to be used
     * @param energySaving true if the vehicle has the energy saving mode turned on
     * @param polynomialInterpolation true if the torque is to be calculated using the polynomial interpolation
     * @return the energy expenditure in KJ taking into account the fuel of the
     * vehicle, the gear position used in the segment, the velocity the vehicle
     * used and the energy expenditure using the formula "Power * timeSpent"
     */
    Measurable[] determineEnergyExpenditure(Segment segment, Measurable load, double length, Measurable velocity, boolean energySaving,
                                            boolean polynomialInterpolation) {

        Measurable velocityToBeUsed = new Measurable(velocity.getQuantity(), Unit.KILOMETERS_PER_HOUR);
        int gearPosition = energy.getGears().size() - 1;
        int throttlePosition = 0;

        Measurable[] data = calculateEngineSpeedTorqueSFCVelocity(segment, gearPosition, throttlePosition, velocityToBeUsed, load, energySaving, polynomialInterpolation);

        Measurable power = calculatePowerGenerated(data[0], data[1]);

        double SFC = data[2].getQuantity();
        double timeSpent = length / data[3].getQuantity();

        double fuelQuantity = power.getQuantity() * Physics.KILOMETERS_METERS_CONVERSION_RATIO * SFC * timeSpent;

        return new Measurable[]{new Measurable(fuelQuantity * fuel.getSpecificEnergy().getQuantity(), Unit.KILOJOULE), data[4], data[3],
            new Measurable(power.getQuantity() * timeSpent, Unit.KILOJOULE)};
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
     * Calculates the engine speed, torque, SFC and velocity for this vehicle in
     * the segment and maximum linear velocity given by parameter
     *
     * @param segment the segment
     * @param gearPosition the gear position
     * @param throttlePosition the throttle position
     * @param velocity the maximum linear velocity
     * @param load the vehicle's load
     * @param energySaving true if the vehicle has the energy saving mode on
     * @param polynomialInterpolation true if the torque is to be calculated using the polynomial interpolation
     * @return an array with the engine speed in the first position, the torque
     * in the second position, the SFC in the third position, the velocity in
     * the forth position and the gear position in the fifth position
     */
    private Measurable[] calculateEngineSpeedTorqueSFCVelocity(Segment segment, int gearPosition, int throttlePosition,
                                                               Measurable velocity, Measurable load, boolean energySaving, boolean polynomialInterpolation) {

        double engineSpeed
                = Math.round((velocity.getQuantity() * 60 * energy.getFinalDriveRatio() * energy.getGears().get(gearPosition).getRatio())
                / (2 * Math.PI * (wheelSize.getQuantity() / 2) * Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO));

        double torque = 0;
        double SFC = 0;

        for (Regime regime : energy.getThrottles().get(throttlePosition).getRegimes()) {
            if (regime.getRpmHigh() >= engineSpeed && regime.getRpmLow() <= engineSpeed) {
                if (!polynomialInterpolation) {
                    torque = Physics.calculateLinearInterpolation(regime.getRpmLow(), regime.getRpmHigh(), regime.getTorqueLow(), regime.getTorqueHigh(), engineSpeed);
                } else {
                    torque = Physics.calculatePolynomialInterpolation(regime.getRpmLow(), regime.getRpmHigh(), regime.getTorqueLow(), regime.getTorqueHigh(), engineSpeed);
                }
                SFC = regime.getSFC();
                break;
            } else if (regime.getRpmLow() > engineSpeed) {
                return calculateEngineSpeedTorqueSFCVelocity(segment, --gearPosition, throttlePosition, velocity, load, energySaving, polynomialInterpolation);
            }
        }

        // if engine speed isn't inside the limits of this velocity, we reduce the velocity
        if (Double.compare(torque, 0) == 0) {
            velocity.setQuantity(velocity.getQuantity() - velocity.getQuantity() * 0.02d);
            gearPosition = energy.getGears().size() - 1;
            throttlePosition = 0;
            return calculateEngineSpeedTorqueSFCVelocity(segment, gearPosition, throttlePosition, velocity, load, energySaving, polynomialInterpolation);
        }

        double motorForce = (torque * energy.getFinalDriveRatio() * energy.getGears().get(gearPosition).getRatio())
                / (wheelSize.getQuantity() / 2);

        Measurable segmentAngle = segment.calculateAngle();

        double rollingResistance = rollingResistanceCoefficient * (mass.getQuantity() + load.getQuantity())
                * Physics.GRAVITY_ACCELERATION.getQuantity() * Math.cos(segmentAngle.getQuantity());

        Measurable maxAirRelatedVelocity = segment.calculateAirRelatedVelocity(velocity);
        double airDrag = 0.5 * dragCoefficient * frontalArea.getQuantity() * Physics.AIR_DENSITY.getQuantity()
                * Math.pow(maxAirRelatedVelocity.getQuantity(), 2);

        double gravitationalForce = (mass.getQuantity() + load.getQuantity()) * Physics.GRAVITY_ACCELERATION.getQuantity()
                * Math.sin(segmentAngle.getQuantity());

        // if the sum of the motor force and the acceleration force is lesser than the sum of the rolling resistance, air drag and gravitational force
        if (motorForce < rollingResistance + airDrag + gravitationalForce) {

            if (energySaving) {
                if (gearPosition == 0) {
                    throw new IllegalArgumentException();
                }
                return calculateEngineSpeedTorqueSFCVelocity(segment, --gearPosition, throttlePosition, velocity, load, true, polynomialInterpolation);
            }

            // if the throttle position is not 100%, we increase the throttle position
            if (throttlePosition < 2) {
                return calculateEngineSpeedTorqueSFCVelocity(segment, gearPosition, ++throttlePosition, velocity, load, true, polynomialInterpolation);
            }

            // if the throttle position is in 100%, we decrease the gear position and start the throttle as 25%
            throttlePosition = 0;
            return calculateEngineSpeedTorqueSFCVelocity(segment, --gearPosition, throttlePosition, velocity, load, true, polynomialInterpolation);

        }

        return new Measurable[]{new Measurable(engineSpeed, Unit.ROTATIONS_PER_MINUTE), new Measurable(torque, Unit.NEWTON_METER),
            new Measurable(SFC, Unit.GRAM_PER_KILOWATT_HOUR), velocity, new Measurable(gearPosition, Unit.POSITION)};
    }

    /**
     * Equals for objects of the class Vehicle
     *
     * @param obj the other object
     * @return true if the objects are equal
     */
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
        return Objects.equals(this.name, other.name);
    }

    /**
     * Hash code for instances of the class Vehicle
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * @return the string format of instances of the class Vehicle
     */
    @Override
    public String toString() {
        return String.format("%s - %s.", name, description);
    }

    /**
     * @return the vehicle's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return motor type
     */
    public MotorType getMotorType() {
        return motorType;
    }

    /**
     * Checks that this vehicle is able to support a given load
     *
     * @param load an instance of {@link Measurable}
     * @return true if the vehicle is able to support the given load
     */
    public boolean hasValidLoad(Measurable load) {
        return load.getQuantity() <= maxLoad.getQuantity();
    }

    public void storeVehicleInformation(CallableStatement storeVehicleInfoProcedure) throws SQLException {
        storeVehicleInfoProcedure.setString("name", name);
        storeVehicleInfoProcedure.setString("description", description);
        storeVehicleInfoProcedure.setString("vehicleType", type.name());
        storeVehicleInfoProcedure.setInt("vehicleTollClass", vehicleClass);
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
     * Calculates the acceleration force acting in the vehicle depending on the
     * acceleration and the load the car takes
     *
     * @param load the load the car takes
     * @param acceleration the acceleration used
     * @return the acceleration force in N
     */
    public Measurable calculateAccelerationForce(Measurable load, Measurable acceleration) {
        return new Measurable((mass.getQuantity() + load.getQuantity()) * acceleration.getQuantity(), Unit.NEWTON);
    }

    /**
     * Determines initial velocity of the vehicle, starting in the first gear
     * and lowest throttle
     *
     * @return the initial velocity in km/h
     */
    public Measurable determineInitialVelocity() {
        double velocity = (2 * Math.PI * (wheelSize.getQuantity() / 2d) * energy.getMinRpm()
                / (60 * energy.getFinalDriveRatio() * energy.getGears().get(0).getRatio()))
                * Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO;
        return new Measurable(Double.compare(velocity, 0) == 0 ? 1 : velocity, Unit.KILOMETERS_PER_HOUR);
    }

    /**
     * Indicates motor type.
     * May assist in the instantiation of the correct motorization
     */
    public enum MotorType {

        COMBUSTION, NONCOMBUSTION;

    }

}
