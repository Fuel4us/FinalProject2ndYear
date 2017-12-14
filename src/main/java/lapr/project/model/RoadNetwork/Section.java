package lapr.project.model.RoadNetwork;

import javax.xml.bind.annotation.*;
import java.util.Collection;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Section {

    @XmlElement(name = "begin")
    private Node beginningNode;
    @XmlElement(name = "end")
    private Node endingNode;
    @XmlElement
    private Direction direction;
    @XmlElementWrapper(name = "segment_list")
    @XmlElement(name = "segment")
    private Collection<Segment> segments;

    public Section(Node beginningNode, Node endingNode, Direction direction, Collection<Segment> segments) {
        this.beginningNode = beginningNode;
        this.endingNode = endingNode;
        this.direction = direction;
        this.segments = segments;
    }
}