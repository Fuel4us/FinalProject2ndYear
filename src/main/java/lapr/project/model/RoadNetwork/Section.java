package lapr.project.model.RoadNetwork;

import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.Graph.Edge;

import javax.xml.bind.annotation.*;
import java.util.Collection;


@XmlRootElement(name = "road_section")
@XmlAccessorType(XmlAccessType.FIELD)
public class Section extends Edge<String, Direction> {

    @XmlAttribute(name = "begin")
    private Node beginningNode;

    @XmlAttribute(name = "end")
    private Node endingNode;

    @XmlElement
    private Direction direction;

    @XmlElementWrapper(name = "segment_list")
    @XmlElement(name = "segment")
    private Collection<Segment> segments;

    private Road owningRoad;

    /**
     * Constructor
     * @param beginningNode
     * @param endingNode
     * @param direction
     * @param segments
     */
    public Section(lapr.project.model.RoadNetwork.Node beginningNode, lapr.project.model.RoadNetwork.Node endingNode, Direction direction, Collection<Segment> segments, Road road) {

        super(direction, calculateTotalLength(segments), beginningNode, endingNode);
        this.segments = segments;
        this.beginningNode = beginningNode;
        this.endingNode = endingNode;
        this.direction = direction;
        this.owningRoad = road;
    }

    /**
     * Retrieves the road's typology
     * @return the road's typology
     */
    public String retrieveRoadTypology() {
        return owningRoad.getTypology();
    }

    /**
     * Calculates the total minimum time interval spent for the whole section,
     * taking into account the velocity limit in each segment, its length and
     * the velocity limit of the vehicle in the typology of the segment
     * @param vehicle the vehicle
     * @param roadNetwork the road network of the current project
     * @return the total minimum time interval
     */
    public double calculateTotalMinimumTimeInterval(Vehicle vehicle, RoadNetwork roadNetwork) {

        double totalMinimumTimeInterval = 0;

        for (Segment segment : segments) {

            totalMinimumTimeInterval += segment.calculateMinimumTimeInterval(vehicle, roadNetwork);

        }

        return totalMinimumTimeInterval;

    }

    /**
     * Determines the weight of the edge, equating weight with the sum of the length of each segment
     * @param segments The instances of Segment that belong to this Section
     * @return the total weight of this Section
     */
    private static double calculateTotalLength(Collection<Segment> segments) {
        double weight = 0.0;
        for (Segment segment : segments) {
            weight += segment.getLength();
        }
        return weight;
    }

}