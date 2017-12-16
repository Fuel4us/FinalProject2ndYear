package lapr.project.model.RoadNetwork;

import lapr.project.utils.Graph.Edge;

import javax.xml.bind.annotation.*;
import java.util.Collection;


@XmlRootElement(name = "road_section")
@XmlAccessorType(XmlAccessType.FIELD)
public class Section extends Edge<Node, Direction> {

    @XmlElement(name = "begin")
    private lapr.project.model.RoadNetwork.Node beginningNode;
    @XmlElement(name = "end")
    private lapr.project.model.RoadNetwork.Node endingNode;
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
     * Determines the weight of the edge, equating weight with the sum of the length of each segment
     * @param segments
     * @return
     */
    private static double calculateTotalLength(Collection<Segment> segments){
        double weight = 0.0;
        for (Segment segment: segments) {
            weight += segment.getLength();
        }
        return weight;
    }

}