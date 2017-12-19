package lapr.project.utils.Graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @param <V>
 * @param <E>
 * @author DEI-ESINF
 */

public class Edge<V, E> implements Comparable<Edge<V, E>> {

    private E element;           // Edge information
    private double weight;       // Edge weight
    private Vertex<V, E> vOrig;  // vertex origin
    private Vertex<V, E> vDest;  // vertex destination
    private final double teste = 0.000000001;

    public Edge() {
        element = null;
        weight = 0.0;
        vOrig = null;
        vDest = null;
    }

    public Edge(E eInf, double ew, Vertex<V, E> vo, Vertex<V, E> vd) {
        element = eInf;
        weight = ew;
        vOrig = vo;
        vDest = vd;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E eInf) {
        element = eInf;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double ew) {
        weight = ew;
    }

    /**
     * @return the element of the origin vertex
     */
    public V getVOrig() {
        if (this.vOrig != null)
            return vOrig.getElement();
        return null;
    }

    /**
     * @return the origin vertex
     */
    public Vertex<V, E> getOriginVertex() {
        return vOrig;
    }

    /**
     * @return the destiny vertex
     */
    public Vertex<V, E> getDestinyVertex() {
        return vDest;
    }

    public void setVOrig(Vertex<V, E> vo) {
        vOrig = vo;
    }

    /**
     * @return the element of the destiny vertex
     */
    public V getVDest() {
        if (this.vDest != null)
            return vDest.getElement();
        return null;
    }

    public void setVDest(Vertex<V, E> vd) {
        vDest = vd;
    }

    public List<V> getEndpoints() {

        V oElem = null, dElem = null;

        if (this.vOrig != null)
            oElem = vOrig.getElement();

        if (this.vDest != null)
            dElem = vDest.getElement();

        if (oElem == null && dElem == null)
            return null;

        List<V> endverts = new LinkedList<>();

        endverts.add(0, oElem);
        endverts.add(1, dElem);

        return endverts;
    }

    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Edge<V, E> otherEdge = (Edge<V, E>) otherObj;

        // if endpoints vertices are not equal
        if ((this.vOrig == null && otherEdge.vOrig != null) ||
                (this.vOrig != null && otherEdge.vOrig == null))
            return false;

        if ((this.vDest == null && otherEdge.vDest != null) ||
                (this.vDest != null && otherEdge.vDest == null))
            return false;

        if (this.vOrig != null && otherEdge.vOrig != null &&
                !this.vOrig.equals(otherEdge.vOrig))
            return false;

        if (this.vDest != null && otherEdge.vDest != null &&
                !this.vDest.equals(otherEdge.vDest))
            return false;
        
        if (Math.abs(this.weight - otherEdge.weight) > teste)
            return false;

        if (this.element != null && otherEdge.element != null)
            return this.element.equals(otherEdge.element);

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = element != null ? element.hashCode() : 0;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (vOrig != null ? vOrig.hashCode() : 0);
        result = 31 * result + (vDest != null ? vDest.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Edge<V, E> edge) {

        if (this.weight < edge.weight) return -1;
        if (this.weight == edge.weight) return 0;
        return 1;
    }

    @Override
    public Edge<V, E> clone() {

        Edge<V, E> newEdge = new Edge<>();

        newEdge.element = element;
        newEdge.weight = weight;
        newEdge.vOrig = vOrig;
        newEdge.vDest = vDest;

        return newEdge;
    }

    @Override
    public String toString() {
        String st = "";
        if (element != null)
            st = "      (" + element + ") - ";
        else
            st = "\t ";

        if (weight != 0)
            st += weight + " - " + vDest.getElement() + "\n";
        else
            st += vDest.getElement() + "\n";

        return st;
    }

}
