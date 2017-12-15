package lapr.project.model.RoadNetwork;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Road {

    @XmlElement(name = "id")
    private String id;

    @XmlElement(name = "road_name")
    private String name;

    @XmlElement
    private Typology typology;
    @XmlElementWrapper(name = "toll_fare")
    private List<Float> tollFare;



    /**
     * @return the ID of this road
     */
    public String getID() {
        return id;
    }

}