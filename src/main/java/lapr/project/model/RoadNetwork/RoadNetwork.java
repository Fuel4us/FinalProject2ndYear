package lapr.project.model.RoadNetwork;

import lapr.project.utils.Graph.Graph;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Graph view of a network of roads
 */
@XmlRootElement(name = "Network")
public class RoadNetwork extends Graph<Node,Section> {

    /**
     * Constructor
     * @param directed
     */
    public RoadNetwork(boolean directed) {
        super(directed);
    }

    /**
     * Empty constructor
     */
    public RoadNetwork() { super(false);}

    /**
     * Inserts a Node into the graph
     * @param node
     * @return
     */
    public boolean addNode(Node node) {
        return insertVertex(node);
    }

    /**
     * Inserts a Section into the graph
     *
     * @param n1
     * @param n2
     * @param section
     * @return
     */
    public boolean addSection(Node n1, Node n2, Section section) {
        boolean flag = false;
        if (insertEdge(n1, n2, section, section.getWeight())) {
            n1.addAdjVert(n2, section);
            n2.addAdjVert(n1, section);
            flag = true;
        }
        return flag;
    }

}
