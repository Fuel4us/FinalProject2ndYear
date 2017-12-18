package lapr.project.model.RoadNetwork;

import lapr.project.utils.Graph.Vertex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ToDo add JavaDoc
 */
@XmlRootElement
public class Node extends Vertex<String,Direction> {

    @XmlAttribute
    private String id;

    private static int key = 0;

    /**
     * Constructor.
     * @param id
     */
    public Node(String id){
        super((++key), id);
        this.id=id;
    }
}