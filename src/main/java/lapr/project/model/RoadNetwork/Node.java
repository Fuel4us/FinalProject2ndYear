package lapr.project.model.RoadNetwork;

import lapr.project.utils.Graph.Vertex;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ToDo add JavaDoc
 */
@XmlRootElement
public class Node extends Vertex<Node,Direction> {

    @XmlElement
    private String id;
}