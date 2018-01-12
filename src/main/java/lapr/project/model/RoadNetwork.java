package lapr.project.model;

import lapr.project.utils.Graph.Edge;
import lapr.project.utils.Graph.Graph;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Graph view of a network of roads
 */
public class RoadNetwork extends Graph<Node, Section> {

    private String id;

    private String description;

    /**
     * Full constructor for the class RoadNetwork
     * @param id the id of the road network
     * @param description the description of the road network
     */
    public RoadNetwork(String id, String description) {
        super(true);
        this.id = id;
        this.description = description;
    }

    /**
     * Constructor
     * @param directed if the graph is directed or not
     */
    public RoadNetwork(boolean directed) {
        super(directed);
    }

    /**
     * Empty constructor
     */
    public RoadNetwork() {
        super(true);
    }

    /**
     * Defines identifier of roadNetwork, obtained during the importation of
     * data from file
     * @param id identifier of roadNetwork
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the id
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
     * @param node the node to be added
     * @return true if the operation succeeds
     */
    public boolean addNode(Node node) {
        return insertVertex(node);
    }

    /**
     * Inserts a Section into the graph, taking into account its {@link Direction}
     * @param n1 the first node of the section
     * @param n2 the second node of the section
     * @param section the section to be added
     * @return true if the operation succeeds
     */
    public boolean addSection(Node n1, Node n2, Section section) {
        if (section.getDirection() == Direction.BIDIRECTIONAL) {
            boolean firstAdded = addDirectedSection(n1, n2, section);
            return addDirectedSection(n2, n1, section)
                    && firstAdded;
        } else if (section.getDirection() == Direction.DIRECT) {
            return addDirectedSection(n1, n2, section);
        } else if (section.getDirection() == Direction.REVERSE) {
            return addDirectedSection(n2, n1, section);
        }
        return false;
    }

    private boolean addDirectedSection(Node n1, Node n2, Section section) {

        if (insertEdge(n1, n2, section, section.getWeight())) {
            if (!n1.getSetAllAdjVerts().contains(n2.getElement())) {
                n1.addAdjVert(n2.getElement(), section);
            }
            if (!n2.getSetAllAdjVerts().contains(n1.getElement())) {
                n2.addAdjVert(n1.getElement(), section);
            }
            return true;
        }
        return false;
    }

    /**
     * Retrieves the list of all roads if the road network
     * @return a list of instances of the class Road
     */
    public List<Road> retrieveAllRoads() {
        return getEdges().stream()
                .map(Edge::getElement)
                .map(Section::getOwningRoad)
                .distinct()
                .collect(Collectors.toList());
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
