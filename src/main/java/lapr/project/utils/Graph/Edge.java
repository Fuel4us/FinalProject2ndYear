package lapr.project.utils.Graph;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
    private final double test = 0.00000000000000000000001;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?, ?> edge = (Edge<?, ?>) o;
        return Double.compare(edge.weight, weight) == 0 &&
                Double.compare(edge.test, test) == 0 &&
                Objects.equals(element, edge.element) &&
                Objects.equals(vOrig, edge.vOrig) &&
                Objects.equals(vDest, edge.vDest);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public int compareTo(Edge<V, E> edge) {

        if (this.weight < edge.weight) return -1;
        if (Math.abs(this.weight - edge.weight) < test) {  // Bug was here because this.weight == edge.weight
            return 0;
        }
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

        if (BigDecimal.valueOf(weight).compareTo(BigDecimal.valueOf(0.0))!=0) // had bug here because was [weight != 0]
            st += weight + " - " + vDest.getElement() + "\n";
        else
            st += vDest.getElement() + "\n";

        return st;
    }

}
