package lapr.project.model.RoadNetwork;

import lapr.project.model.Vehicle.Vehicle;
import lapr.project.utils.Graph.Edge;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

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
     * Checks if the list of segments contains the segment given as a parameter
     * @param segment the segment to be considered
     * @return true if the segment is in the list of segments
     */
    public boolean containsSegment(Segment segment) {
        return segments.contains(segment);
    }

    /**
     * Calculates the total minimum time interval spent for the whole section,
     * taking into account the velocity limit in each segment, its length and
     * the velocity limit of the vehicle in the typology of the segment
     * @param roadNetwork the road network of the current project
     * @param vehicle the vehicle
     * @return the total minimum time interval
     */
    public double calculateTotalMinimumTimeInterval(RoadNetwork roadNetwork, Vehicle vehicle) {

        double totalMinimumTimeInterval = 0;

        for (Segment segment : segments) {

            totalMinimumTimeInterval += segment.calculateMinimumTimeInterval(roadNetwork, vehicle);

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

    /**
     * Prints data of each segment included in the section, according to a certain file
     */
    public void printSegmentsFromSection() {
        for (Segment segment : segments) {
            printDataFromSegment(segment);
        }
    }

    /**
     * Prints data from a given segment filling the information missing in a given file template
     * @param segment
     */
    public void printDataFromSegment(Segment segment) {
        StringTemplateGroup groupSegment =  new StringTemplateGroup("myGroup", "C:\\Tutorials", DefaultTemplateLexer.class);
        StringTemplate segmentTemplate = groupSegment.getInstanceOf("html_structure_segment");
        segment.printDataFromSegment(segmentTemplate);
    }
}