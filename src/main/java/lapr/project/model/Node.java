package lapr.project.model;

import lapr.project.utils.Graph.Vertex;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(id, node.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

}