package lapr.project.model.RoadNetwork;

import lapr.project.utils.Graph.Vertex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ToDo add JavaDoc
 */
public class Node extends Vertex<String,Direction> {

    private String id;

    private static int key = 0;

    /**
     * Constructor.
     * @param id the node's id
     */
    public Node(String id){
        super((++key), id);
        this.id=id;
    }

    /**
     * String format of the class Node
     * @return a String that represents the Node
     */
    @Override
    public String toString(){
        return String.format("%s",id);
    }
}