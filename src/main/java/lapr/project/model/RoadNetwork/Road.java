package lapr.project.model.RoadNetwork;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the roads
 */
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
     * Getter for the typology of the Road
     * @return the typology of the Road
     */
    public String getTypology() {
        return typology;
    }

    /**
     * Equals method for objects of the class Road
     * @param o other object
     * @return true if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Road road = (Road) o;

        if (id != null ? !id.equals(road.id) : road.id != null) return false;
        if (name != null ? !name.equals(road.name) : road.name != null) return false;
        if (typology != null ? !typology.equals(road.typology) : road.typology != null) return false;
        return tollFare != null ? tollFare.equals(road.tollFare) : road.tollFare == null;
    }

    /**
     * Hash code for the class Road
     * @return the value of the hash code
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (typology != null ? typology.hashCode() : 0);
        result = 31 * result + (tollFare != null ? tollFare.hashCode() : 0);
        return result;
    }
}