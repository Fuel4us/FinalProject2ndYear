package lapr.project.utils.Graph;

import lapr.project.utils.GeneralOperator;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class GraphAlgorithms {

    /**
     * Performs breadth-first search of a Graph starting in a Vertex
     * @param g Graph instance
     * @param vInf information of the Vertex that will be the source of the
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
     * @param vInf information of the Vertex that will be the source of the
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
     * <p>
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non negative edge weights
     * This implementation uses Dijkstra's algorithm, and applies cumulative dependency between outputs of previous iterations.
     *
     * </p>
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param visited set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist minimum distances
     * ToDo
     */
    private static <V, E, R, A> void cumulativeDijkstra(Graph<V, E> g, V vOrig, List<V> vertices,
                                                        boolean[] visited, int[] pathKeys, double[] dist,
                                                        //use edge and P to produce a E
                                                        BiFunction<Edge<V, E>, A, R> cumulativeApplier,
                                                        //defines weight in terms of E
                                                        ToDoubleFunction<R> weightExtractor, A seed,
                                                        //extract P from E
                                                        Function<R, A> cumulativeAttributeExtractor) {

        //uma função que define a forma como o double (weight) é extraído da function principal
        // O return da function principal será guardado
        // um contentor do tipo do return type de function será passado para inicializar

        //o return guardado será passado como parametro nas iterações seguintes

        int vkey = g.getKey(vOrig);
        dist[vkey] = 0;

        while (vkey != -1) {
            vOrig = vertices.get(vkey);
            visited[vkey] = true;

            //Initialize cumulative applier args
            R product;
            A attribute = seed;

            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {

                V vAdj = g.opposite(vOrig, edge);
                int vkeyAdj = g.getKey(vAdj);

                //product is the result of application of f(x) where initial x is the seed
                product = cumulativeApplier.apply(edge, attribute);

                //the definition of weight is extractable from the product
                double definedWeight = weightExtractor.applyAsDouble(product);

                //the cumulative attribute is extractable from the product
                attribute = cumulativeAttributeExtractor.apply(product);

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
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     * @param g Graph instance
     * @param voInf information of the Vertex origin
     * @param vdInf information of the Vertex destination
     * @param pathkeys minimum path vertices keys
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
        return shortestPath(g, vOrig, vDest, shortPath, null, false, null, null, null,null);
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
     * @param edgeOperator
     * @return the weight of the shortestPath if weighted is set to true, 1 otherwise
     */
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath, GeneralOperator<Edge<V, E>> edgeOperator) {
        return shortestPath(g, vOrig, vDest, shortPath, edgeOperator, false, null, null, null,null);
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
     * @param cumulativeApplier
     * @param weightExtractor
     * @param seed @return the weight of the shortestPath if weighted is set to true, 1 otherwise
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
     * This condition only applies if weighted is set to false
     * @return the weight of the shortestPath if weighted is set to true, 1 otherwise
     */
    private static <V, E, R, A> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath, GeneralOperator<Edge<V, E>> edgeOperator,
                                                    boolean operateCumulatively,
                                                    BiFunction<Edge<V, E>, A, R> cumulativeApplier,
                                                    //defines weight in terms of E
                                                    ToDoubleFunction<R> weightExtractor, A seed,
                                                    //extract P from E
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
            cumulativeDijkstra(g, vOrig, vertices, visited, pathKeys, dist, cumulativeApplier, weightExtractor, seed, cumulativeAttributeExtractor);
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

    /**
     * Reverses the path
     * @param path stack with path
     */
    private static <V, E> LinkedList<V> Path(LinkedList<V> path) {

        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty()) {
            pathrev.push(pathcopy.pop());
        }

        return pathrev;
    }

}
