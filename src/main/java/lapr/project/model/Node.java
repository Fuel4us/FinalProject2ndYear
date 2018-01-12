package lapr.project.model;

import lapr.project.utils.Graph.Vertex;

/**
 * Represents the nodes that connect sections
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

    /**
     * Returns id
     * @return id identifier
     */
    public String getId() {
        return id;
    }
}