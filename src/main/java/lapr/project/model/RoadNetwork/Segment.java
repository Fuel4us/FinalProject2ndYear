package lapr.project.model.RoadNetwork;

import lapr.project.model.Vehicle.Gears;
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
     * @return the minimum time interval
     */
    public double calculateMinimumTimeInterval(RoadNetwork roadNetwork, Vehicle vehicle) {

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
    public Measurable calculateMaximumVelocityInterval(RoadNetwork roadNetwork, Vehicle vehicle) {
        return new Measurable(length / calculateMinimumTimeInterval(roadNetwork, vehicle),
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

//    public EnergyExpenditureAccelResults calculateEnergyExpenditureAccel(RoadNetwork roadNetwork, Measurable initialVelocity, Vehicle vehicle, Measurable load, Measurable maxAcceleration, Measurable maxBraking, double maxVelocity) {
//
//        Measurable finalVelocity = new Measurable(0, Unit.KILOMETERS_PER_HOUR);
//        Measurable energyExpenditure = new Measurable(0, Unit.KILOJOULE);
//        int gearPosition = -1;
//        Measurable timeSpent = new Measurable(0, Unit.HOUR);
//
//        // if the vehicle enters the segment with the same speed as the speed allowed
//        if (initialVelocity.getQuantity() == maxVelocity) {
//
//            Measurable[] data = vehicle.determineEnergyExpenditure(roadNetwork, this, load);
//            energyExpenditure = data[0];
//            gearPosition = (int) data[1].getQuantity();
//
//            finalVelocity = calculateMaximumVelocityInterval(roadNetwork, vehicle);
//            timeSpent.setQuantity(length / finalVelocity.getQuantity());
//
//            // if the vehicle enters the segment with bigger speed than the speed allowed
//        } else if (initialVelocity.getQuantity() > maxVelocity) {
//
//            Measurable travelledDistance = calculateTravelledDistance(finalVelocity, initialVelocity, maxAcceleration);
//
//            // if the vehicle enters the segment with lesser speed than the speed allowed
//        } else if (initialVelocity.getQuantity() < maxVelocity) {
//
//            Measurable travelledDistance = calculateTravelledDistance(finalVelocity, initialVelocity, maxBraking);
//
//        }
//
//        return new EnergyExpenditureAccelResults(energyExpenditure, finalVelocity, timeSpent,
//                new Gears[]{new Gears(gearPosition, 0f)});
//    }

    /**
     * Calculates the travelled distance accelerating or breaking
     * @param finalVelocity the final velocity the vehicle has to reach
     * @param initialVelocity the velocity of the vehicle when it enters the segment
     * @param acceleration the value of the acceleration (positive for acceleration, negative for braking)
     * @return the travelled distance in km
     */
    private Measurable calculateTravelledDistance(Measurable finalVelocity, Measurable initialVelocity, Measurable acceleration) {

        double time = (finalVelocity.getQuantity() - initialVelocity.getQuantity()) / acceleration.getQuantity();

        return new Measurable(initialVelocity.getQuantity() * time + 0.5 * acceleration.getQuantity() * Math.pow(time, 2), Unit.KILOMETER);

    }


    /**
     * Defines parameters to be used by callable statement
     * @param storeSegmentProcedure callable statement
     * @throws SQLException
     */
    public void storeSegmentInformation(CallableStatement storeSegmentProcedure) throws SQLException {
        storeSegmentProcedure.setInt("ID", index);
        storeSegmentProcedure.setDouble("initialHeight", initialHeight);
        storeSegmentProcedure.setDouble("finalHeight", finalHeight);
        storeSegmentProcedure.setDouble("length", length);
        storeSegmentProcedure.setDouble("windAngle", windAngle);
        storeSegmentProcedure.setDouble("windSpeed", windSpeed);
        storeSegmentProcedure.setDouble("maxVelocity", maxVelocity);
        storeSegmentProcedure.setDouble("minVelocity", minVelocity);
    }
}