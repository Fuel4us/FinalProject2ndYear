package lapr.project.model.RoadNetwork;

import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.Graph.Edge;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Segment {

    @XmlElement(name = "id")
    private int index;
    @XmlElement(name = "init_height")
    private double initialHeight;
    @XmlElement(name = "final_height")
    private double finalHeight;

    //private double slope;
    @XmlElement
    private double length;
    @XmlElement(name = "wind_direction")
    private double windAngle;
    @XmlElement(name = "wind_speed")
    private double windSpeed;
    @XmlElement(name = "max_velocity")
    private double maxVelocity;
    @XmlElement(name = "min_velocity")
    private double minVelocity;


    /**
     * Forbid default no-arg instantiation
     */
    private Segment() {}

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
     * Calculates the minimum time interval spent for the segment,
     * taking into account the velocity limit, its length and
     * the velocity limit of the vehicle for the typology
     * @param roadNetwork the road network of the current project
     * @param vehicle the vehicle
     * @return the minimum time interval
     */
    public double calculateMinimumTimeInterval(RoadNetwork roadNetwork, Vehicle vehicle) {

        Iterable<Edge<Node, Section>> edges = roadNetwork.edges();

        Section section = null;

        //check which section has this segment
        for (Edge<Node,Section> edge : edges) {

            section = edge.getElement();

            if (section.containsSegment(this)) break;

        }

        if (section == null) return 0;

        String roadTypology = section.retrieveRoadTypology();

        double vehicleMaxVelocity = vehicle.retrieveMaxVelocity(roadTypology);

        if (vehicleMaxVelocity == 0) {

            return length / maxVelocity;

        } else {

            return length / Math.min(maxVelocity, vehicleMaxVelocity);

        }

    }
}