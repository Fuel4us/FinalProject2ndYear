package lapr.project.model.RoadNetwork;

import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.EnergyExpenditureAccelResults;
import lapr.project.utils.Graph.Edge;
import lapr.project.utils.Measurable;
import lapr.project.utils.Physics;
import lapr.project.utils.Unit;
import org.antlr.stringtemplate.StringTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class Segment {

    private int index;

    private double initialHeight;

    private double finalHeight;

    private double length;

    private double windAngle;

    private double windSpeed;

    private double maxVelocity;

    private double minVelocity;

    /**
     * Forbid default no-arg instantiation
     */
    private Segment() {
    }

    public Segment(int index, double initialHeight, double finalHeight, double length, double windAngle, double windSpeed, double maxVelocity, double minVelocity) {
        this.index = index;
        this.initialHeight = initialHeight;
        this.finalHeight = finalHeight;
        this.length = length;
        this.windAngle = windAngle;
        this.windSpeed = windSpeed;
        this.maxVelocity = maxVelocity;
        this.minVelocity = minVelocity;
    }

    /**
     * @return length of the segment
     */
    public double getLength() {
        return length;
    }

    /**
     * @return minimum velocity of the segment
     */
    public double getMinVelocity() {
        return minVelocity;
    }

    /**
     * Fills and prints data of segment in a file
     *
     * @param segmentTemplate instance of {@link StringTemplate}
     */
    public void printDataFromSegment(StringTemplate segmentTemplate, FileWriter file) throws IOException {
        String segmentId = String.valueOf(index);
        String segmentIniHeight = String.valueOf(initialHeight).replace(".", ",");
        String segmentFinHeight = String.valueOf(finalHeight).replace(".", ",");
        String segmentLength = String.valueOf(length).replace(".", ",");
        String segmentWindAngle = String.valueOf(windAngle).replace(".", ",");
        String segmentWindSpeed = String.valueOf(windSpeed).replace(".", ",");
        String segmentMaxVelocity = String.valueOf(maxVelocity).replace(".", ",");
        String segmentMinVelocity = String.valueOf(minVelocity).replace(".", ",");

        segmentTemplate.setAttribute("sampleId", segmentId);
        segmentTemplate.setAttribute("sampleIniHeight", segmentIniHeight);
        segmentTemplate.setAttribute("sampleFimHeight", segmentFinHeight);
        segmentTemplate.setAttribute("sampleLength", segmentLength);
        segmentTemplate.setAttribute("sampleWindAngle", segmentWindAngle);
        segmentTemplate.setAttribute("sampleWindSpeed", segmentWindSpeed);
        segmentTemplate.setAttribute("sampleMaxVel", segmentMaxVelocity);
        segmentTemplate.setAttribute("sampleMinVel", segmentMinVelocity);

        file.write(segmentTemplate.toString());

    }

    /**
     * Calculates the minimum time interval spent for the segment,
     * taking into account the velocity limit, its length and
     * the velocity limit of the vehicle for the typology
     *
     * @param roadNetwork the road network of the current project
     * @param vehicle     the vehicle
     * @param length      the length used
     * @return the minimum time interval
     */
    public double calculateMinimumTimeInterval(RoadNetwork roadNetwork, Vehicle vehicle, double length) {

        Iterable<Edge<Node, Section>> edges = roadNetwork.edges();

        Section section = null;

        //check which section has this segment
        for (Edge<Node, Section> edge : edges) {

            section = edge.getElement();

            if (section.containsSegment(this)) break;

        }

        if (section == null) return 0;

        String roadTypology = section.retrieveRoadTypology();

        Measurable vehicleMaxVelocity = vehicle.retrieveMaxVelocity(roadTypology);

        if (vehicleMaxVelocity == null) {

            return length / maxVelocity;

        } else {

            return length / Math.min(maxVelocity, vehicleMaxVelocity.getQuantity());

        }

    }

    /**
     * Calculates the maximum possible velocity for the segment, knowing its length and
     * time spent
     *
     * @param roadNetwork the road network
     * @param vehicle     the vehicle
     * @return the minimum velocity
     */
    public Measurable calculateMaximumVelocityInterval(RoadNetwork roadNetwork, Vehicle vehicle, double length) {
        return new Measurable(length / calculateMinimumTimeInterval(roadNetwork, vehicle, length),
                Unit.KILOMETERS_PER_HOUR);
    }

    /**
     * Calculates the angle of the segment according to its initial and final
     * heights and length
     *
     * @return the angle
     */
    public Measurable calculateAngle() {

        //finalHeight and initialHeight m -> km
        return new Measurable(Math.asin(
                (finalHeight * Physics.KILOMETERS_METERS_CONVERSION_RATIO - initialHeight * Physics.KILOMETERS_METERS_CONVERSION_RATIO)
                        / length), Unit.DEGREE);
    }

    /**
     * Calculates the velocity relative to the air (considering wind velocity)
     *
     * @param maxLinearVelocity the maximum velocity possible
     * @return the velocity relative to the air
     */
    public Measurable calculateAirRelatedVelocity(Measurable maxLinearVelocity) {

        Measurable convertedVelocity = new Measurable(maxLinearVelocity.getQuantity() / Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO, Unit.METERS_PER_SECOND);


        //conversion km/h -> m/s
        double airRelatedVelocity = Math.sqrt(Math.pow(windSpeed * Math.cos(Math.toRadians(windAngle)) - convertedVelocity.getQuantity(), 2)
                + Math.pow(windSpeed * Math.sin(Math.toRadians(windAngle)), 2));

        return new Measurable(airRelatedVelocity, Unit.METERS_PER_SECOND);
    }

    /**
     * Calculates the energy expenditure, the time spent and the final velocity this segment
     * given the vehicle, the initial velocity, the load, the max acceleration and braking and the information
     * about this being the last segment of the path or not
     *
     * @param roadNetwork     the road network
     * @param initialVelocity the initial velocity of the vehicle
     * @param vehicle         the vehicle
     * @param load            the load the vehicle takes
     * @param maxAcceleration the max acceleration
     * @param maxBraking      the max braking
     * @param finalVelocity   the final velocity to be reached
     * @param lastSegment     true if this is the last segment of the path
     * @param energySaving    true if the vehicle has the energy saving mode on
     * @return an instance of the type EnergyExpenditureAccelResults with the information about this algorithm
     * (energy expenditure, time spent, final velocity and gear position)
     */
    public EnergyExpenditureAccelResults calculateEnergyExpenditureAccel(RoadNetwork roadNetwork, Measurable initialVelocity, Vehicle vehicle,
                                                                         Measurable load, Measurable maxAcceleration, Measurable maxBraking, Measurable finalVelocity,
                                                                         boolean lastSegment, boolean energySaving) {

        Measurable energyExpenditure = new Measurable(0, Unit.KILOJOULE);
        Measurable timeSpent = new Measurable(0, Unit.HOUR);

        Measurable usedAcceleration;
        if (initialVelocity.getQuantity() < finalVelocity.getQuantity()) {
            usedAcceleration = maxAcceleration;
        } else {
            usedAcceleration = maxBraking;
        }

        // check if the theoretical final velocity to reach is possible for the car, if not
        Measurable possibleVelocityToReach = vehicle.determineEnergyExpenditure(this, load, length,
                usedAcceleration.getQuantity() > 0 ? finalVelocity : initialVelocity, usedAcceleration, energySaving)[2];

        if (Double.compare(possibleVelocityToReach.getQuantity(), finalVelocity.getQuantity()) != 0) {
            if (possibleVelocityToReach.getQuantity() >= this.minVelocity) {
                finalVelocity = possibleVelocityToReach;
            } else {
                throw new IllegalArgumentException();
            }
            if (initialVelocity.getQuantity() < finalVelocity.getQuantity()) {
                usedAcceleration = maxAcceleration;
            } else {
                usedAcceleration = maxBraking;
            }
        }

        // if this is the last segment of the path, the car must stop in the ending node of the section
        if (lastSegment) {

            Measurable finishingPathVelocity = vehicle.determineInitialVelocity();

            Measurable[] distanceAndTimeFinishingPath = calculateTravelledDistanceAndTimeSpent(finishingPathVelocity, initialVelocity, maxBraking);

            if (distanceAndTimeFinishingPath[0].getQuantity() > length) {

                // moment of braking until the end
                Measurable brakingFinalVelocity = calculateFinalVelocity(initialVelocity, maxBraking, length);
                energyExpenditure.setQuantity(calculateEnergyExpenditureWithAcceleration(initialVelocity, brakingFinalVelocity, maxBraking, vehicle, load, energySaving).getQuantity());

                return new EnergyExpenditureAccelResults(energyExpenditure, brakingFinalVelocity, distanceAndTimeFinishingPath[1]);

            }

            distanceAndTimeFinishingPath = calculateTravelledDistanceAndTimeSpent(finishingPathVelocity, finalVelocity, maxBraking);

            Measurable[] distanceAndTimeEnteringSegment = calculateTravelledDistanceAndTimeSpent(finalVelocity, initialVelocity, usedAcceleration);

            double lengthForInitialAcceleration = distanceAndTimeEnteringSegment[0].getQuantity();

            if (distanceAndTimeEnteringSegment[0].getQuantity() + distanceAndTimeFinishingPath[0].getQuantity() > length) {

                lengthForInitialAcceleration = length - distanceAndTimeFinishingPath[0].getQuantity();
                finalVelocity = calculateFinalVelocity(initialVelocity, maxAcceleration, lengthForInitialAcceleration);

            }

            // moment of acceleration in the beginning
            energyExpenditure.setQuantity(calculateEnergyExpenditureWithAcceleration(initialVelocity, finalVelocity, usedAcceleration, vehicle, load, energySaving).getQuantity());
            timeSpent.setQuantity(calculateTravelledDistanceAndTimeSpent(finalVelocity, initialVelocity, usedAcceleration)[1].getQuantity());

            // moment of braking until the end
            double lengthForFinalBraking = calculateTravelledDistanceAndTimeSpent(finishingPathVelocity, finalVelocity, maxBraking)[0].getQuantity();
            energyExpenditure.setQuantity(energyExpenditure.getQuantity() + calculateEnergyExpenditureWithAcceleration(finalVelocity,
                    finishingPathVelocity, maxBraking, vehicle, load, energySaving).getQuantity());
            timeSpent.setQuantity(timeSpent.getQuantity() + calculateTravelledDistanceAndTimeSpent(finishingPathVelocity, finalVelocity, maxBraking)[1].getQuantity());

            // moment of uniform movement in the remaining length
            double lengthForUniformMovement = length - lengthForInitialAcceleration - lengthForFinalBraking;
            energyExpenditure.setQuantity(energyExpenditure.getQuantity() + determineEnergyExpenditureUniformMovement(new Measurable(0, Unit.METERS_PER_SECOND_SQUARED),
                    vehicle, load, lengthForUniformMovement, finalVelocity, energySaving));
            timeSpent.setQuantity(timeSpent.getQuantity() + (lengthForUniformMovement / finalVelocity.getQuantity()));

            return new EnergyExpenditureAccelResults(energyExpenditure, finishingPathVelocity, timeSpent);

        }

        Measurable[] distanceAndTimeEnteringSegment = calculateTravelledDistanceAndTimeSpent(finalVelocity, initialVelocity, usedAcceleration);

        if (distanceAndTimeEnteringSegment[0].getQuantity() < length) {

            energyExpenditure.setQuantity(calculateEnergyExpenditureWithAcceleration(initialVelocity, finalVelocity, usedAcceleration, vehicle, load, energySaving).getQuantity());
            timeSpent.setQuantity(distanceAndTimeEnteringSegment[1].getQuantity());

            double lengthForUniformMovement = length - distanceAndTimeEnteringSegment[0].getQuantity();
            energyExpenditure.setQuantity(energyExpenditure.getQuantity() + determineEnergyExpenditureUniformMovement(
                    new Measurable(0, Unit.METERS_PER_SECOND_SQUARED), vehicle, load, lengthForUniformMovement, finalVelocity, energySaving));
            timeSpent.setQuantity(timeSpent.getQuantity() + (lengthForUniformMovement / finalVelocity.getQuantity()));

            return new EnergyExpenditureAccelResults(energyExpenditure, finalVelocity, timeSpent);

        } else {

            finalVelocity = calculateFinalVelocity(initialVelocity, usedAcceleration, length);
            distanceAndTimeEnteringSegment = calculateTravelledDistanceAndTimeSpent(finalVelocity, initialVelocity, usedAcceleration);
            energyExpenditure.setQuantity(calculateEnergyExpenditureWithAcceleration(initialVelocity, finalVelocity, usedAcceleration, vehicle, load, energySaving).getQuantity());
            timeSpent.setQuantity(distanceAndTimeEnteringSegment[1].getQuantity());

            return new EnergyExpenditureAccelResults(energyExpenditure, finalVelocity, timeSpent);

        }

    }

    /**
     * Calculates the energy expenditure when accelerating or braking
     *
     * @param initialVelocity the initial velocity (the beginning of the acceleration/braking)
     * @param finalVelocity   the final velocity to reach in the end of the acceleration/braking
     * @param acceleration    the acceleration (positive if accelerating, negative if braking)
     * @param vehicle         the vehicle that will travel in these conditions
     * @param load            the load the vehicle takes
     * @param energySaving    true if the vehicle has the energy saving mode on
     * @return the energy expenditure in KJ
     */
    private Measurable calculateEnergyExpenditureWithAcceleration(Measurable initialVelocity, Measurable finalVelocity, Measurable acceleration, Vehicle vehicle,
                                                                  Measurable load, boolean energySaving) {

        Measurable initialVelocityToBeUsed = new Measurable(initialVelocity.getQuantity(), Unit.KILOMETERS_PER_HOUR);
        Measurable energyExpenditure = new Measurable(0, Unit.KILOJOULE);
        double timeInterval = 1d / Math.abs(acceleration.getQuantity());

        boolean velocityExceeded = false;
        while (true) {

            if (acceleration.getQuantity() > 0 && initialVelocityToBeUsed.getQuantity() >= finalVelocity.getQuantity() - 1
                    || acceleration.getQuantity() < 0 && initialVelocityToBeUsed.getQuantity() <= finalVelocity.getQuantity() + 1) {
                initialVelocityToBeUsed.setQuantity(finalVelocity.getQuantity());
                velocityExceeded = true;
            }

            double intervalLength = (initialVelocityToBeUsed.getQuantity() / Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO) * timeInterval
                    + 0.5 * acceleration.getQuantity() * Math.pow(timeInterval, 2);

            double intervalEnergyExpenditure = determineEnergyExpenditureUniformMovement(acceleration, vehicle, load,
                    intervalLength * Physics.KILOMETERS_METERS_CONVERSION_RATIO, initialVelocityToBeUsed, energySaving);

            energyExpenditure.setQuantity(energyExpenditure.getQuantity() + intervalEnergyExpenditure);

            if (velocityExceeded) {
                break;
            }

            initialVelocityToBeUsed.setQuantity(((initialVelocityToBeUsed.getQuantity() / Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO) + acceleration.getQuantity() * timeInterval)
                    * Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO);

        }

        return energyExpenditure;
    }

    /**
     * Determines the energy expenditure for the uniform movement taking into account the acceleration, the vehicle,
     * the load the vehicle has, the distance traveled, the velocity in that uniform movement and if the vehicle has
     * the energy saving mode on
     * @param acceleration the acceleration
     * @param vehicle the vehicle
     * @param load the load the vehicle has
     * @param length the length
     * @param velocity the velocity in that uniform movement
     * @param energySaving true if the vehicle has the energy saving mode on
     * @return the energy expenditure in KJ
     */
    public double determineEnergyExpenditureUniformMovement(Measurable acceleration, Vehicle vehicle, Measurable load, double length, Measurable velocity, boolean energySaving) {
        if (acceleration.getQuantity() < 0 && vehicle.getMotorType().equals(Vehicle.MotorType.NONCOMBUSTION)) {
            return -vehicle.determineEnergyExpenditure(this, load, length, velocity, acceleration, energySaving)[3].getQuantity()
                    * vehicle.getEnergy().getEnergyRegenerationRatio();
        } else if (acceleration.getQuantity() >= 0 && vehicle.getMotorType().equals(Vehicle.MotorType.NONCOMBUSTION)) {
            return vehicle.determineEnergyExpenditure(this, load, length, velocity, acceleration, energySaving)[3].getQuantity();
        } else {
            return vehicle.determineEnergyExpenditure(this, load, length, velocity, acceleration, energySaving)[0].getQuantity();
        }
    }

    /**
     * Calculates the velocity reached starting with the velocity given, the acceleration and the length traveled
     *
     * @param initialVelocity the initial velocity when starting to accelerate / brake (km/h)
     * @param acceleration    the maximum acceleration / braking for the vehicle (m/s^2)
     * @param length          the length travelled while accelerating / braking (km)
     * @return the velocity reached in km/h
     */
    private Measurable calculateFinalVelocity(Measurable initialVelocity, Measurable acceleration, double length) {
        return new Measurable((Math.sqrt(Math.pow(initialVelocity.getQuantity() / Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO, 2)
                + 2 * Math.abs(acceleration.getQuantity()) * (length / Physics.KILOMETERS_METERS_CONVERSION_RATIO)) * Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO),
                Unit.KILOMETERS_PER_HOUR);
    }

    /**
     * Calculates the travelled distance accelerating or breaking and the time spent
     *
     * @param finalVelocity   the final velocity the vehicle has to reach
     * @param initialVelocity the velocity of the vehicle when it enters the segment
     * @param acceleration    the value of the acceleration (positive for acceleration, negative for braking)
     * @return the travelled distance in km and the time spent in hours
     */
    private Measurable[] calculateTravelledDistanceAndTimeSpent(Measurable finalVelocity, Measurable initialVelocity, Measurable acceleration) {

        double time = (finalVelocity.getQuantity() / Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO -
                initialVelocity.getQuantity() / Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO) / acceleration.getQuantity();

        return new Measurable[]{new Measurable((initialVelocity.getQuantity() / Physics.KILOMETERS_PER_HOUR_METERS_PER_SECOND_CONVERSION_RATIO
                * time + 0.5 * acceleration.getQuantity() * Math.pow(time, 2)) * Physics.KILOMETERS_METERS_CONVERSION_RATIO, Unit.KILOMETER),
                new Measurable(time / 3600, Unit.HOUR)};

    }

    /**
     * Defines parameters to be used by callable statement
     *
     * @param storeSegmentProcedure callable statement
     * @throws SQLException
     */
    public void storeSegmentInformation(CallableStatement storeSegmentProcedure) throws SQLException {
        storeSegmentProcedure.setInt("id", index);
        storeSegmentProcedure.setDouble("initialHeight", initialHeight);
        storeSegmentProcedure.setDouble("finalHeight", finalHeight);
        storeSegmentProcedure.setDouble("length", length);
        storeSegmentProcedure.setDouble("windAngle", windAngle);
        storeSegmentProcedure.setDouble("windSpeed", windSpeed);
        storeSegmentProcedure.setDouble("maxVelocity", maxVelocity);
        storeSegmentProcedure.setDouble("minVelocity", minVelocity);
    }
}