package lapr.project.model.RoadNetwork;

import lapr.project.utils.Graph.Vertex;

import javax.xml.bind.annotation.XmlElement;

/**
 * ToDo add JavaDoc
 */
public class Node extends Vertex<Node,Direction> {

    @XmlElement
    private String id;
}