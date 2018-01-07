package lapr.project.utils.Graph;


import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;

import static org.junit.Assert.assertTrue;

public class GraphAlgorithmsTest {

    private Graph<String, String> completeMap = new Graph<>(false);

    public GraphAlgorithmsTest() {
    }

    @Before
    public void setUp() throws Exception {

        completeMap.insertVertex("Porto");
        completeMap.insertVertex("Braga");
        completeMap.insertVertex("Vila Real");
        completeMap.insertVertex("Aveiro");
        completeMap.insertVertex("Coimbra");
        completeMap.insertVertex("Leiria");

        completeMap.insertVertex("Viseu");
        completeMap.insertVertex("Guarda");
        completeMap.insertVertex("Castelo Branco");
        completeMap.insertVertex("Lisboa");
        completeMap.insertVertex("Faro");

        completeMap.insertEdge("Porto", "Aveiro", "A1", 75);
        completeMap.insertEdge("Porto", "Braga", "A3", 60);
        completeMap.insertEdge("Porto", "Vila Real", "A4", 100);
        completeMap.insertEdge("Viseu", "Guarda", "A25", 75);
        completeMap.insertEdge("Guarda", "Castelo Branco", "A23", 100);
        completeMap.insertEdge("Aveiro", "Coimbra", "A1", 60);
        completeMap.insertEdge("Coimbra", "Lisboa", "A1", 200);
        completeMap.insertEdge("Coimbra", "Leiria", "A34", 80);
        completeMap.insertEdge("Aveiro", "Leiria", "A17", 120);
        completeMap.insertEdge("Leiria", "Lisboa", "A8", 150);

    }

    /**
     * Test of BreadthFirstSearch method, of class GraphAlgorithms.
     */
    @Test
    public void testBreadthFirstSearch() {

        assertTrue("Should be null if vertex does not exist", GraphAlgorithms.BreadthFirstSearch(completeMap, "LX") == null);

        LinkedList<String> path = GraphAlgorithms.BreadthFirstSearch(completeMap, "Faro");
        assertTrue("Should be just one", path.size() == 1);

        Iterator<String> it = path.iterator();
        assertTrue("it should be Faro", it.next().compareTo("Faro") == 0);

        path = GraphAlgorithms.BreadthFirstSearch(completeMap, "Porto");
        assertTrue("Should give seven vertices ", path.size() == 7);

        path = GraphAlgorithms.BreadthFirstSearch(completeMap, "Viseu");
        assertTrue("Should give 3 vertices", path.size() == 3);
    }

    /**
     * Test of DepthFirstSearch method, of class GraphAlgorithms.
     */
    @Test
    public void testDepthFirstSearch() {

        LinkedList<String> path;

        assertTrue("Should be null if vertex does not exist", GraphAlgorithms.DepthFirstSearch(completeMap, "LX") == null);

        path = GraphAlgorithms.DepthFirstSearch(completeMap, "Faro");
        assertTrue("Should be just one", path.size() == 1);

        Iterator<String> it = path.iterator();
        assertTrue("it should be Faro", it.next().compareTo("Faro") == 0);

        path = GraphAlgorithms.DepthFirstSearch(completeMap, "Porto");
        assertTrue("Should give seven vertices ", path.size() == 7);

        path = GraphAlgorithms.DepthFirstSearch(completeMap, "Viseu");
        assertTrue("Should give 3 vertices", path.size() == 3);

        it = path.iterator();
        assertTrue("First in visit should be Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
    }


    /**
     * Test of shortestPath method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPath() {

        LinkedList<String> shortPath = new LinkedList<>();
        double lenpath = 0;
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "LX", shortPath);
        assertTrue("Length path should be 0 if vertex does not exist", shortPath.size() == 0);

        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Faro", shortPath);
        assertTrue("Length path should be 0 if there is no path", shortPath.size() == 0);

        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Porto", shortPath);
        assertTrue("Length path should be 1 if source and vertex are the same", shortPath.size() == 1);

        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Lisboa", shortPath);
        assertTrue("Path between Porto and Lisboa should be 335 Km", lenpath == 335);

        Iterator<String> it = shortPath.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);

        lenpath = GraphAlgorithms.shortestPath(completeMap, "Braga", "Leiria", shortPath);
        assertTrue("Path between Braga and Leiria should be 255 Km", lenpath == 255);

        it = shortPath.iterator();

        assertTrue("First in path should be Braga", it.next().compareTo("Braga") == 0);
        assertTrue("then Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);

        completeMap.insertEdge("Aveiro", "Viseu", "A25", 85);

        shortPath.clear();
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        assertTrue("Path between Porto and Castelo Branco should be 335 Km", lenpath == 335);
        assertTrue("N. cities between Porto and Castelo Branco should be 5 ", shortPath.size() == 5);

        it = shortPath.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco
        //should change shortest path between Porto and Castelo Branco
        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        shortPath.clear();
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        assertTrue("Path between Porto and Castelo Branco should now be 365 Km", lenpath == 365);
        assertTrue("Path between Porto and Castelo Branco should be 4 cities", shortPath.size() == 4);

        it = shortPath.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);

    }

    /**
     * Method : cumulative dijkstra (invoked through non private shortestPath overload)
     */
    @Test
    public void ensureCumulativeDijkstraOutputsCorrectPathAndPathCost() throws Exception {

        //<editor-fold desc="Initialization">
        double seed = 2d;
        Function<Double, Double> cumulativeAttributeExtractor = x -> x * 3;

        Graph<String, TestEdge<String,String>> graph = new Graph<>(true);
        String a = "A";
        String b = "B";
        String c = "C";
        String d = "D";

        graph.insertVertex(a);
        graph.insertVertex(b);
        graph.insertVertex(c);
        graph.insertVertex(d);

        TestEdge<String,String> edge0 = new TestEdge<>();
        TestEdge<String,String> edge1 = new TestEdge<>();
        TestEdge<String,String> edge2 = new TestEdge<>();
        TestEdge<String,String> edge3 = new TestEdge<>();
        TestEdge<String,String> edge4 = new TestEdge<>();
        TestEdge<String,String> edge5 = new TestEdge<>();
        //</editor-fold>

        //the weight is defined via the weight extractor, so the value of weight passed in insertEdge is irrelevant

        //path one : a-b-c-d, edges : 0-1-2
        graph.insertEdge(a, b, edge0, 1);
        graph.insertEdge(c, d, edge1, 1);
        graph.insertEdge(b, c, edge2, 1);

        //cumulative transformation (lambda x, f(x)) -> f(f(x))), costs of new edges depend on previous edge
        double edge0Cost = edge0.computeUniqueValueSeedEdgeProperties(seed);
        double edge2Cost = edge2.computeUniqueValueSeedEdgeProperties(cumulativeAttributeExtractor.apply(edge0Cost));
        double edge1Cost = edge1.computeUniqueValueSeedEdgeProperties(cumulativeAttributeExtractor.apply(edge2Cost));

        double pathOneCost = edge0Cost + edge1Cost + edge2Cost;
        List<String> pathOne = Arrays.asList(a, b, c, d);

        //path two: a-d, edges : 3
        graph.insertEdge(a, d, edge3, 1);

        double pathTwoCost = edge3.computeUniqueValueSeedEdgeProperties(seed);
        List<String> pathTwo = Arrays.asList(a, d);

        //path 3 : a-c-d, edges : 4-1 (the graph is directed)
        graph.insertEdge(a, c, edge4, 1);
        graph.insertEdge(d, c, edge5, 1);

        //cumulative transformation (lambda x, f(x)) -> f(f(x))), costs of new edges depend on previous edge
        double edge4Cost = edge4.computeUniqueValueSeedEdgeProperties(seed);
        edge1Cost = edge1.computeUniqueValueSeedEdgeProperties(cumulativeAttributeExtractor.apply(edge4Cost));

        double pathThreeCost = edge4Cost + edge1Cost;
        List<String> pathThree = Arrays.asList(a, c, d);

        //Cost function
        //lambda x lambda g lambda f -> sum f(g(x)) for x in numNodes - 1
        // \xgf -> sum f(g(x)) for x in numPathEdges where numPathEdges = numNodes - 1
        LinkedList<String> shortestPath = new LinkedList<>();
        double totalCost = GraphAlgorithms.shortestPath(graph, a, d, shortestPath,
                //cumulative applier (transformation function)
                (edge, argument) -> edge.getElement().computeUniqueValueSeedEdgeProperties(argument),
                //weigth extractor - defines how weight is interpreted from the output of the transformation function
                value -> value,
                //initial value for the first edge, upon which the weigth of all other edges depends
                seed,
                //defines how the argument for the next call is interpreted/obtained/extracted from the output of the transformation function
                cumulativeAttributeExtractor);

        double min1 = Math.min(pathOneCost, pathTwoCost);
        double min2 = Math.min(pathTwoCost, pathThreeCost);
        double leastCost = Math.min(min1, min2);

        assert totalCost == leastCost;

        if (leastCost == pathOneCost) {
            assert shortestPath.equals(pathOne);
        } else if (leastCost == pathTwoCost) {
            assert shortestPath.equals(pathTwo);
        } else if (leastCost == pathThreeCost) {
            assert shortestPath.equals(pathThree);
        } else throw new AssertionError("Least cost path not found!");

    }

    /**
     * Edge subclass for test purposes only
     */
    private static class TestEdge<V,E> extends Edge<V,E> {

        private int id;

        private static int numInstances = 0;

        TestEdge() {
            this.id = numInstances;
            numInstances++;
        }

        /**
         * Simulate different outputs for different instances, even if the same arguments are provided
         * @param seed a seed value
         * @return the generated value
         */
        double computeUniqueValueSeedEdgeProperties(Double seed) {
            return seed * (id + 1);
        }

        @Override
        public String toString() {

            return "edge" + id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            TestEdge<?, ?> testEdge = (TestEdge<?, ?>) o;
            return id == testEdge.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), id);
        }
    }

}
