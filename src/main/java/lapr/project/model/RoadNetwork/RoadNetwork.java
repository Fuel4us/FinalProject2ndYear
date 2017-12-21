package lapr.project.model.RoadNetwork;

import lapr.project.utils.Graph.Graph;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Graph view of a network of roads
 */
@XmlRootElement(name = "Network")
public class RoadNetwork extends Graph<Node, Section> {

    @XmlElement(name = "id")
    private String id;

    private String description;

    /**
     * Full constructor for the class RoadNetwork
     *
     * @param directed if the graph is directed or not
     * @param id the id of the road network
     * @param description the description of the road network
     */
    public RoadNetwork(boolean directed, String id, String description) {
        super(directed);
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
     *Returns the id
     * 
     * @return id
     */
    public String getId() {
        return id;
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
//            n1.addAdjVert(n2.getElement(), section);
//            n2.addAdjVert(n1.getElement(), section);
            flag = true;
        }
        return flag;
    }

}
