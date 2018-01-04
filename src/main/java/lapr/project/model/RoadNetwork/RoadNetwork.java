package lapr.project.model.RoadNetwork;

import lapr.project.utils.Graph.Graph;

import java.util.Objects;

/**
 * Graph view of a network of roads
 */
public class RoadNetwork extends Graph<Node, Section> {

    private String id;

    private String description;

    /**
     * Full constructor for the class RoadNetwork
     *
     * @param id the id of the road network
     * @param description the description of the road network
     */
    public RoadNetwork(String id, String description) {
        super(false);
        this.id = id;
        this.description = description;
    }

    /**
     * Constructor
     *
     * @param directed if the graph is directed or not
     */
    public RoadNetwork(boolean directed) {
        super(directed);
    }

    /**
     * Empty constructor
     */
    public RoadNetwork() {
        super(false);
    }

    /**
     * Defines identifier of roadNetwork, obtained during the importation of
     * data from file
     *
     * @param id identifier of roadNetwork
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the id
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Inserts a Node into the graph
     *
     * @param node the node to be added
     * @return true if the operation succeeds
     */
    public boolean addNode(Node node) {
        return insertVertex(node);
    }

    /**
     * Inserts a Section into the graph
     *
     * @param n1 the first node of the section
     * @param n2 the second node of the section
     * @param section the section to be added
     * @return true if the operation succeeds
     */
    public boolean addSection(Node n1, Node n2, Section section) {
        boolean flag = false;
        if (insertEdge(n1, n2, section, section.getWeight())) {
            n1.addAdjVert(n2.getElement(), section);
            n2.addAdjVert(n1.getElement(), section);
            flag = true;
        }
        return flag;
    }

    /**
     * Equals for objects of the class RoadNetwork
     * @param o other object
     * @return true if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoadNetwork that = (RoadNetwork) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description);
    }

    /**
     * Hash code for objects of the class RoadNetwork
     * @return the hash code
     */
    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, description);
    }
}
