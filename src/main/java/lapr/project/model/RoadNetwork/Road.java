package lapr.project.model.RoadNetwork;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
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
     * Full constructor for the class Road
     * @param id the road's id
     * @param name the road's name
     * @param typology the road's typology
     * @param tollFare the road's toll fare
     */
    public Road(String id, String name, String typology, List<Float> tollFare) {
        this.id = id;
        this.name = name;
        this.typology = typology;
        this.tollFare = tollFare;
    }

    /**
     * Constructor with id, name and typology for the class Road
     * @param id the road's id
     * @param name the road's name
     * @param typology the road's typology
     */
    public Road(String id, String name, String typology) {
        this.id = id;
        this.name = name;
        this.typology = typology;
        tollFare = new ArrayList<>();
    }

    /**
     * @return the ID of this road
     */
    public String getID() {
        return id;
    }

}