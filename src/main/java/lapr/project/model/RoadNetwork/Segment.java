package lapr.project.model.RoadNetwork;

import lapr.project.model.Vehicle.Gears;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.FileParser.ExportableHTML;
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
    
    private final double zero = 0.00000000000001;


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

    public double getLength() {
        return length;
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
     * Calculates the energy expenditure, the time spent, the final velocity and the gear position this segment
     * given the vehicle, the initial velocity, the load, the max acceleration abd braking and the information
     * about this being the last segment of the path or not
     * @param roadNetwork the road network
     * @param initialVelocity the initial velocity of the vehicle
     * @param vehicle the vehicle
     * @param load the load the vehicle takes
     * @param maxAcceleration the max acceleration
     * @param maxBraking the max braking
     * @param lastSegment true if this is the last segment of the path
     * @return an instance of the type EnergyExpenditureAccelResults with the information about this algorithm
     * (energy expenditure, time spent, final velocity and gear position)
     */
    public EnergyExpenditureAccelResults calculateEnergyExpenditureAccel(RoadNetwork roadNetwork, Measurable initialVelocity, Vehicle vehicle,
                                                                         Measurable load, Measurable maxAcceleration, Measurable maxBraking, boolean lastSegment) {

        Measurable energyExpenditure = new Measurable(0, Unit.KILOJOULE);
        int gearPosition = -1;
        Measurable timeSpent = new Measurable(0, Unit.HOUR);

        Measurable finalVelocity = calculateMaximumVelocityInterval(roadNetwork, vehicle, length);

        Measurable usedAcceleration = new Measurable(0, Unit.METERS_PER_SECOND_SQUARED);

        Measurable remainingLength = new Measurable(length, Unit.KILOMETER);

        boolean usedInitialVelocity = false;

        // if this is the last segment of the path, the car must stop in the ending node of the section
        if (lastSegment) {

            Measurable[] distanceAndTimeFinishingPath = calculateTravelledDistanceAndTimeSpent(new Measurable(0, Unit.KILOMETERS_PER_HOUR),
                    finalVelocity, maxBraking);

            Measurable travelledDistanceFinishingPath = distanceAndTimeFinishingPath[0];

            if (travelledDistanceFinishingPath.getQuantity() > length) {

                distanceAndTimeFinishingPath = calculateTravelledDistanceAndTimeSpent(new Measurable(0, Unit.KILOMETERS_PER_HOUR),
                        initialVelocity, maxBraking);

                usedInitialVelocity = true;

                travelledDistanceFinishingPath = distanceAndTimeFinishingPath[0];

                // the case where the vehicle has to start braking right when it enters the section
                if (travelledDistanceFinishingPath.getQuantity() > length) {

                    energyExpenditure.setQuantity(vehicle.calculateAccelerationForce(load, maxBraking).getQuantity() * length);

                    timeSpent.setQuantity(distanceAndTimeFinishingPath[1].getQuantity());

                    return new EnergyExpenditureAccelResults(energyExpenditure, new Measurable(0, Unit.KILOMETERS_PER_HOUR),
                            timeSpent, new Gears[]{new Gears(gearPosition, 0f)});
                }

            }

            // simplified the case where the vehicle does not need to start braking right when it enters the section

            // it is assumed that the vehicle reaches the maxVelocity and then we check if we have to start braking
            // until the vehicle stops or keep in a uniform movement and afterwards stop

            double stoppingLength = distanceAndTimeFinishingPath[0].getQuantity();

            if (vehicle.getMotorType() != Vehicle.MotorType.COMBUSTION) {
                energyExpenditure.setQuantity(vehicle.calculateAccelerationForce(load, maxBraking).getQuantity() * stoppingLength);
            } else {
                energyExpenditure.setQuantity(-vehicle.calculateAccelerationForce(load, maxBraking).getQuantity() * stoppingLength);
            }

            timeSpent.setQuantity(distanceAndTimeFinishingPath[1].getQuantity());
            remainingLength.setQuantity(remainingLength.getQuantity() - stoppingLength);

        }

        // if the vehicle enters the segment with the same speed as the speed allowed
        if (initialVelocity.getQuantity() == finalVelocity.getQuantity()) { // Initial.getQuantity == Final.getQuantity

            Measurable[] data = vehicle.determineEnergyExpenditure(this, load, remainingLength.getQuantity(), initialVelocity);
            energyExpenditure.setQuantity(energyExpenditure.getQuantity() + data[3].getQuantity());
            gearPosition = (int) data[1].getQuantity();

            timeSpent.setQuantity(timeSpent.getQuantity() + remainingLength.getQuantity() / finalVelocity.getQuantity());

            return new EnergyExpenditureAccelResults(energyExpenditure, finalVelocity, timeSpent,
                    new Gears[]{new Gears(gearPosition, 0f)});

            // if the vehicle enters the segment with bigger speed than the speed allowed
        } else if (initialVelocity.getQuantity() < finalVelocity.getQuantity()) {

            usedAcceleration = maxAcceleration;

            // if the vehicle enters the segment with lesser speed than the speed allowed
        } else if (initialVelocity.getQuantity() > finalVelocity.getQuantity()) {

            usedAcceleration = maxBraking;

        }

        Measurable[] distanceAndTimeInitialPath = calculateTravelledDistanceAndTimeSpent(finalVelocity, initialVelocity, usedAcceleration);

        Measurable travelledDistance = distanceAndTimeInitialPath[0];

        /*
         if the travelled distance is bigger than the remaining length, the velocity that the vehicle will have
         when finishing the first acceleration/braking will be different than the one that it should reach, and
         the travelled distance will be smaller

         it is assumed that the vehicle remains with the same speed when it enters the segment and keeps in a uniform
         movement for the remaining length
         */
        if (travelledDistance.getQuantity() > remainingLength.getQuantity()) {

            if (!usedInitialVelocity) {

                if (distanceAndTimeInitialPath[0].getQuantity() <= remainingLength.getQuantity()) {

                    if (vehicle.getMotorType() != Vehicle.MotorType.COMBUSTION && usedAcceleration.getQuantity() < 0) {
                        energyExpenditure.setQuantity(energyExpenditure.getQuantity() +
                                vehicle.calculateAccelerationForce(load, usedAcceleration).getQuantity() * distanceAndTimeInitialPath[0].getQuantity());
                    } else {
                        if (usedAcceleration.getQuantity() < 0) {
                            energyExpenditure.setQuantity(energyExpenditure.getQuantity() -
                                    vehicle.calculateAccelerationForce(load, usedAcceleration).getQuantity() * distanceAndTimeInitialPath[0].getQuantity());
                        } else {
                            energyExpenditure.setQuantity(energyExpenditure.getQuantity() +
                                    vehicle.calculateAccelerationForce(load, usedAcceleration).getQuantity() * distanceAndTimeInitialPath[0].getQuantity());
                        }
                    }

                    remainingLength.setQuantity(remainingLength.getQuantity() - distanceAndTimeInitialPath[0].getQuantity());
                    timeSpent.setQuantity(timeSpent.getQuantity() + distanceAndTimeInitialPath[1].getQuantity());

                }

                if (remainingLength.getQuantity() > 0) {
                    Measurable[] data = vehicle.determineEnergyExpenditure(this, load, remainingLength.getQuantity(), finalVelocity);
                    energyExpenditure.setQuantity(energyExpenditure.getQuantity() + data[3].getQuantity());
                    timeSpent.setQuantity(timeSpent.getQuantity() + remainingLength.getQuantity() / finalVelocity.getQuantity());
                    gearPosition = (int) data[1].getQuantity();
                }

                return new EnergyExpenditureAccelResults(energyExpenditure, finalVelocity, timeSpent,
                        new Gears[]{new Gears(gearPosition, 0f)});

            }

            Measurable[] data = vehicle.determineEnergyExpenditure(this, load, remainingLength.getQuantity(), initialVelocity);
            energyExpenditure.setQuantity(energyExpenditure.getQuantity() + data[3].getQuantity());
            timeSpent.setQuantity(timeSpent.getQuantity() + remainingLength.getQuantity() / initialVelocity.getQuantity());
            gearPosition = (int) data[1].getQuantity();

            return new EnergyExpenditureAccelResults(energyExpenditure, initialVelocity, timeSpent,
                    new Gears[]{new Gears(gearPosition, 0f)});

        }

        if (!usedInitialVelocity) {

            Measurable accelerationForce = vehicle.calculateAccelerationForce(load, usedAcceleration);

            double accelerationWork = accelerationForce.getQuantity() * travelledDistance.getQuantity();

            if (vehicle.getMotorType() != Vehicle.MotorType.COMBUSTION && usedAcceleration.getQuantity() < 0) {
                energyExpenditure.setQuantity(energyExpenditure.getQuantity() + accelerationWork);
            } else {
                if (usedAcceleration.getQuantity() < 0) {
                    energyExpenditure.setQuantity(energyExpenditure.getQuantity() - accelerationWork);
                } else {
                    energyExpenditure.setQuantity(energyExpenditure.getQuantity() + accelerationWork);
                }
            }

            remainingLength.setQuantity(remainingLength.getQuantity() - travelledDistance.getQuantity());
            timeSpent.setQuantity(timeSpent.getQuantity() + distanceAndTimeInitialPath[1].getQuantity());

        } else {

            finalVelocity.setQuantity(initialVelocity.getQuantity());

        }

        if (remainingLength.getQuantity() > 0) {
            Measurable[] data = vehicle.determineEnergyExpenditure(this, load, remainingLength.getQuantity(), finalVelocity);
            timeSpent.setQuantity(timeSpent.getQuantity() + remainingLength.getQuantity() / finalVelocity.getQuantity());
            energyExpenditure.setQuantity(energyExpenditure.getQuantity() + data[3].getQuantity());
            gearPosition = (int) data[1].getQuantity();
        }

        return new EnergyExpenditureAccelResults(energyExpenditure, finalVelocity, timeSpent,
                new Gears[]{new Gears(gearPosition, 0f)});

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