package lapr.project.model.RoadNetwork;

import lapr.project.utils.Graph.Vertex;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Road {

    @XmlAttribute
    private String id;

    @XmlElement(name = "road_name")
    private String name;

    @XmlElement
    private String typology;
    @XmlElementWrapper(name = "toll_fare")
    @XmlElement(name = "class")
    private List<Float> tollFare;

    /**
     * @return the ID of this road
     */
    public String getID() {
        return id;
    }

}