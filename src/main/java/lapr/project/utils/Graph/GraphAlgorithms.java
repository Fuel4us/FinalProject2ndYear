package lapr.project.utils.Graph;

import lapr.project.utils.GeneralOperator;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class GraphAlgorithms {

    /**
     * Performs breadth-first search of a Graph starting in a Vertex
     * @param g Graph instance
     * @param vert information of the Vertex that will be the source of the
     * search
     * @return qbfs a queue with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {

        if (!g.validVertex(vert)) {
            return null;
        }

        LinkedList<V> qbfs = new LinkedList<>();
        LinkedList<V> qaux = new LinkedList<>();
        boolean[] visited = new boolean[g.numVertices()];  //default initializ.: false

        qbfs.add(vert);
        qaux.add(vert);
        int vKey = g.getKey(vert);
        visited[vKey] = true;

        while (!qaux.isEmpty()) {
            vert = qaux.remove();
            for (Edge<V, E> edge : g.outgoingEdges(vert)) {
                V vAdj = g.opposite(vert, edge);
                vKey = g.getKey(vAdj);
                if (!visited[vKey]) {
                    qbfs.add(vAdj);
                    qaux.add(vAdj);
                    visited[vKey] = true;
                }
            }
        }
        return qbfs;
    }

    /**
     * Performs depth-first search starting in a Vertex
     * @param g Graph instance
     * @param vOrig Vertex of graph g that will be the source of the search
     * @param visited set of discovered vertices
     * @param qdfs queue with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {

        visited[g.getKey(vOrig)] = true;

        for (Edge<V, E> ed : g.outgoingEdges(vOrig)) {
            if (!visited[g.getKey(ed.getVDest())]) {
                qdfs.add(ed.getVDest());
                DepthFirstSearch(g, ed.getVDest(), visited, qdfs);
            }

        }
    }

    /**
     * @param g Graph instance
     * @param vert information of the Vertex that will be the source of the
     * search
     * @return qdfs a queue with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {

        if (g.validVertex(vert)) {
            boolean[] visited = new boolean[g.numVertices()];
            LinkedList<V> qdfs = new LinkedList<>();
            qdfs.add(vert);
            DepthFirstSearch(g, vert, visited, qdfs);
            return qdfs;
        }
        return null;
    }


    /**
     * <p>
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non negative edge weights
     * This implementation uses Dijkstra's algorithm
     * This method allows a flexible definition of the weight of an edge
     * via the use of a functional interface {@link GeneralOperator}
     * </p>
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param visited set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist minimum distances
     * @param edgeOperator An operation applied on each edge
     * taken into account as the processing condition.
     * This could be the weight of an edge in a graph,
     * but allows for various different definitions of weight.
     * If this value is null, the definition of weight in the edge class is used instead.
     */
    private static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, List<V> vertices,
                                                  boolean[] visited, int[] pathKeys, double[] dist, GeneralOperator<Edge<V, E>> edgeOperator) {
        int vkey = g.getKey(vOrig);
        dist[vkey] = 0;

        while (vkey != -1) {
            vOrig = vertices.get(vkey);
            visited[vkey] = true;

            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {

                V vAdj = g.opposite(vOrig, edge);
                int vkeyAdj = g.getKey(vAdj);

                double definedWeight = edgeOperator != null ? edgeOperator.apply(edge) : edge.getWeight();

                if (!visited[vkeyAdj] && dist[vkeyAdj] > dist[vkey] + definedWeight) {
                    dist[vkeyAdj] = dist[vkey] + definedWeight;
                    pathKeys[vkeyAdj] = vkey;
                }

            }
            double minDist = Double.MAX_VALUE;
            vkey = -1;

            for (int i = 0; i < g.numVertices(); i++) {

                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    vkey = i;
                }

            }
        }
    }

    /**
     * <h1>Dynamic Weight Shortest Path Finder Algorithm</h1>
     * <p>
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a <em>dynamic weight graph</em> with non negative edge weights.
     * A <em>dynamic weight graph</em> is a graph where there are updates to the values of the weight according to previously visited edges.
     * This implementation is based off of Dijkstra's algorithm but applies cumulative dependency between outputs of previous iterations.
     * Three functional interfaces may be implemented to customize behaviour, allowing the use of this method for several different cases,
     * as long as the contracts for these interfaces are satisfied.
     * <br>
     * <br>
     * <em>This means that this algorithm can be used to compute the shortest path, whenever the weight of an edge depends upon the weight of the previous visited edge.</em>
     * <br>
     * <br>
     * It abstracts from implementation details since it is coupled to functional interfaces via the use of functional programming oriented techniques.
     * <br>
     * <br>
     * The algorithm invokes a given function of type {@link BiFunction} named {@code cumulativeApplier}
     * on an {@link Edge} instance or subclass instance, applying it and regarding a generic argument.
     * The contract for the call of this method is such that the implementation of {@code cumulativeApplier} calls a method that returns some type T through which
     * both weigth and the argument for the next functional call can be inferred, either through identity or some other functional mechanism.
     * <br>
     * <br>
     * Subsequent calls to the {@code cumulativeApplier} are made on each visited edge, the definition of weight is used to determine the shortest path
     * and the <em>derived output</em> (the next argument, extracted from the output of the invoked function via the use of a {@code cumulativeAttributeExtractor})
     * of the previous call is passed on as an argument to the {@code cumulativeApplier} so that a transformation can take place iteratively,
     * which is to mean, simply, <em>that the weight of an edge is dependent upon the weight of the previous edge</em>, and a specific type,
     * which can be resolved into an argument, can be passed on to be used to the next iteration, in the calculation of the weight of the next edge being visited.
     * The function that resolves the output of the method called by the implementation of {@code cumulativeApplier} into the definition of weigth to be taken into account
     * is the functional interface instance ({@link Function}) {@code weightExtractor}
     * <p>
     * The starting value for the iterative application performed by {@code cumulativeApplier} is given by a {@code seed},
     * which must be of the same type as the one returned by the implementation of {@code cumulativeAttributeExtractor}, since this is the one that will be used for every edge after the first.
     * </p>
     * <br>
     * <p>
     * The cost of the path can be summarized as the sum of the composition of these functions
     * <p>
     * <h1>Cost function</h1>
     * (Syntax based off of lambda calculus / Haskell)
     * <br>
     * lambda x lambda g lambda f -> sum f(g(x)) for x in numNodes - 1
     * <br>
     * \xgf -> sum f(g(x)) for x in numPathEdges where numPathEdges = numNodes - 1
     * <p>
     * </p>
     * @param graph Graph instance
     * @param originVertex Vertex that will be the source of the path
     * @param visited set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist minimum distances
     * @param cumulativeApplier Defines a function wherein input can be the result of a previous call to this function, given a seed
     * @param seed The starting value for the transformation/cumulative function
     * @param cumulativeAttributeExtractor Extracts the information of the input to pass on to the next call by invoking a method on the return type of the function
     * This can be the identity function (x -> x) in case return type is the same as the transformed input.
     * @param weightExtractor Extracts the definition of weigth of an edge to be used from the output of the method called by the cumulativeApplier implementation (lambda/anonymous class/method reference)
     * @author Pedro Lopes
     */
    private static <V, E, R, A> void dynamicWeightSPFinder(Graph<V, E> graph, V originVertex, List<V> vertices,
                                                           boolean[] visited, int[] pathKeys, double[] dist,
                                                           BiFunction<Edge<V, E>, A, R> cumulativeApplier,
                                                           ToDoubleFunction<R> weightExtractor, A seed,
                                                           Function<R, A> cumulativeAttributeExtractor) {

        int vkey = graph.getKey(originVertex);
        dist[vkey] = 0;

        HashMap<Edge<V, E>, A> attributesMap = new HashMap<>();

        //this is only true for the outgoing edges of the first origin node
        A attribute = seed;

        while (vkey != -1) {
            //next vertex to visit
            originVertex = vertices.get(vkey);
            //mark vertex as visited and explore its outgoing edges
            visited[vkey] = true;

            //Initialize cumulative applier args and output
            R product;
            for (Edge<V, E> edge : graph.outgoingEdges(originVertex)) {

                V vAdj = graph.opposite(originVertex, edge);
                int vkeyAdj = graph.getKey(vAdj);


                //-----BEGIN find argument

                //<editor-fold desc="findArgument">
                //If this origin vertex is not the first origin vertex
                if (vkey > 0) {
                    findNextTransformedArgument(graph, vertices, vkey, edge, attributesMap, attribute);
                }
                //</editor-fold>

                //-----END find argument

                //product is the result of application of f(x) where initial x is the seed
                product = cumulativeApplier.apply(edge, attribute);

                //the definition of weight is extractable from the product
                double definedWeight = weightExtractor.applyAsDouble(product);

                //the cumulative attribute is extractable from the product
                //map this attribute to the current edge, so that it can be used to compute the weight of edges that had this edge as its predecessor
                attributesMap.put(edge, cumulativeAttributeExtractor.apply(product));

                if (!visited[vkeyAdj] && dist[vkeyAdj] > dist[vkey] + definedWeight) {
                    dist[vkeyAdj] = dist[vkey] + definedWeight;
                    pathKeys[vkeyAdj] = vkey;
                }

            }

            //BEGIN Rank paths
            double minDist = Double.MAX_VALUE;
            vkey = -1;

            for (int i = 0; i < graph.numVertices(); i++) {

                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    vkey = i;
                }

            }
            //END Rank Paths
        }
    }


    private static <V, E, A> void findNextTransformedArgument(Graph<V, E> graph, List<V> vertices, int vkey, Edge<V, E> currentEdge, HashMap<Edge<V, E>, A> attributesMap, A attribute) {
        V previousVertex = vertices.get(vkey - 1);
        Iterable<Edge<V, E>> previousNodeEdges = graph.outgoingEdges(previousVertex);
        List<Edge<V, E>> previousNodesEdgeList = new LinkedList<>((Collection<? extends Edge<V, E>>) previousNodeEdges);

        //Search in the list of outgoing edges of the previous visited node for the edge that was used to reach this edge
        //This information is required so that the weight of the current edge can be calculated
        Optional<Edge<V, E>> predecessor = previousNodesEdgeList.stream().filter(previousEdge -> graph.opposite(previousVertex, previousEdge).equals(currentEdge)).findFirst();

        if (predecessor.isPresent()) attribute = attributesMap.get(predecessor.get());
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, List<V> verts, int[] pathKeys, LinkedList<V> path) {
        if (vOrig != vDest) {
            path.push(vDest);
            int vKey = g.getKey(vDest);
            int prevVKey = pathKeys[vKey];
            vDest = verts.get(prevVKey);
            getPath(g, vOrig, vDest, verts, pathKeys, path);
        } else {
            path.push(vOrig);
        }
    }

    /**
     * <p>
     * Uses Dijkstra's algorithm to compute the shortest path.
     * </p>
     * @param g Graph
     * @param vOrig Origin vertex
     * @param vDest Destiny vertex
     * @param shortPath The list to be filled with the path
     * This condition only applies if weighted is set to false
     * @return the weight of the shortestPath if weighted is set to true, 1 otherwise
     */
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {
        return shortestPath(g, vOrig, vDest, shortPath, null, false, null, null, null, null);
    }

    /**
     * <p>
     * Uses Dijkstra's algorithm to compute the shortest path,
     * allowing for a definition of edge weight
     * via the implementation of a {@link FunctionalInterface} : {@link GeneralOperator} edgeOperator.
     * If edgeOperator is null, the weight of the edge is assumed to be that of the attribute
     * defined in the class attribute scope.
     * </p>
     * @param g Graph
     * @param vOrig Origin vertex
     * @param vDest Destiny vertex
     * @param shortPath The list to be filled with the path
     * This condition only applies if weighted is set to false
     * @param edgeOperator The instance defining the {@code weight} of the edge, if null, the class attribute definition is used instead
     * @return the weight of the shortestPath if weighted is set to true, 1 otherwise
     */
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath, GeneralOperator<Edge<V, E>> edgeOperator) {
        return shortestPath(g, vOrig, vDest, shortPath, edgeOperator, false, null, null, null, null);
    }

    /**
     * <p>
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non negative edge weights
     * This implementation uses Dijkstra's algorithm, and applies cumulative dependency between outputs of previous iterations.
     * <br>
     * <br>
     * <em>This means that this algorithm can be used to compute the shortest path, whenever the weight of an edge depends upon the weight of the previous visited edge.</em>
     * <br>
     * <br>
     * It abstracts from implementation details since it is coupled to functional interfaces via the use of functional programming oriented techniques.
     * <br>
     * <br>
     * However, effective use of this method requires awareness of the envisioned use for these functions.
     * <br>
     * <br>
     * The algorithm invokes a given function of type {@link BiFunction} named {@code cumulativeApplier}
     * on an {@link Edge} instance or subclass instance, applying it and regarding a generic argument.
     * The contract for the call of this method is such that the implementation of {@code cumulativeApplier} calls a method that returns some type T through which
     * both weigth and the argument for the next functional call can be inferred, either through identity or some other functional mechanism.
     * <br>
     * <br>
     * Subsequent calls to the {@code cumulativeApplier} are made on each visited edge, the definition of weight is used to determine the shortest path
     * and the <em>derived output</em> (the next argument, extracted from the output of the invoked function via the use of a {@code cumulativeAttributeExtractor})
     * of the previous call is passed on as an argument to the {@code cumulativeApplier} so that a transformation can take place iteratively,
     * which is to mean, simply, <em>that the weight of an edge is dependent upon the weight of the previous edge</em>, and a specific type,
     * which can be resolved into an argument, can be passed on to be used to the next iteration, in the calculation of the weight of the next edge being visited.
     * The function that transforms the output of the method called by the implementation of {@code cumulativeApplier} is the functional interface instance ({@link Function}) {@code weightExtractor}
     * <p>
     * The starting value for the iterative application performed by {@code cumulativeApplier} is given by a {@code seed},
     * which must be of the same type as the one returned by the implementation of {@code cumulativeAttributeExtractor}, since this is the one that will be used for every edge after the first.
     * </p>
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param cumulativeApplier Defines a function wherein input can be the result of a previous call to this function, given a seed
     * @param seed The starting value for the transformation/cumulative function
     * @param cumulativeAttributeExtractor Extracts the information of the input to pass on to the next call by invoking a method on the return type of the function
     * This can be the identity function (x -> x) in case return type is the same as the transformed input.
     * @param weightExtractor Extracts the definition of weigth of an edge to be used from the output of the method called by the cumulativeApplier implementation (lambda/anonymous class/method reference)
     */
    public static <I, S, E, P> double shortestPath(Graph<I, S> g, I vOrig, I vDest, LinkedList<I> shortPath,
                                                   BiFunction<Edge<I, S>, P, E> cumulativeApplier,
                                                   ToDoubleFunction<E> weightExtractor, P seed,
                                                   Function<E, P> cumulativeAttributeExtractor) {

        return shortestPath(g, vOrig, vDest, shortPath, null, true, cumulativeApplier, weightExtractor, seed, cumulativeAttributeExtractor);
    }

    /**
     * <p>
     * Uses Dijkstra's algorithm to compute the shortest path.
     * This is the method where implementation is focused, but the overloads provided should be used instead.
     * </p>
     * @param g Graph
     * @param vOrig Origin vertex
     * @param vDest Destiny vertex
     * @param shortPath The list to be filled with the path
     * @return the {@code weight} (total cost) of the shortest path
     */
    private static <V, E, R, A> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath,
                                                    GeneralOperator<Edge<V, E>> edgeOperator,
                                                    boolean operateCumulatively,
                                                    BiFunction<Edge<V, E>, A, R> cumulativeApplier,
                                                    ToDoubleFunction<R> weightExtractor, A seed,
                                                    Function<R, A> cumulativeAttributeExtractor) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }

        int numVertices = g.numVertices();
        boolean[] visited = new boolean[numVertices]; //default value: false
        int[] pathKeys = new int[numVertices];
        double[] dist = new double[numVertices];
        List<V> vertices = g.allkeyVerts();

        for (int i = 0; i < numVertices; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        if (operateCumulatively) {
            dynamicWeightSPFinder(g, vOrig, vertices, visited, pathKeys, dist, cumulativeApplier, weightExtractor, seed, cumulativeAttributeExtractor);
        } else {
            shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist, edgeOperator);
        }

        double lengthPath = dist[g.getKey(vDest)];

        if (Math.abs(lengthPath - Double.MAX_VALUE) > 0) {
            getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);
            return lengthPath;
        }

        return 0;
    }

}
