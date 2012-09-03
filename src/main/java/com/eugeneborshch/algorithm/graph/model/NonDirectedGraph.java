package main.java.com.eugeneborshch.algorithm.graph.model;

import java.util.LinkedHashMap;

/**
 * Non directed graph implementation.
 * <p/>
 * User: Eugene Borshch
 */
public class NonDirectedGraph<T> implements Graph<T> {


    private LinkedHashMap<Vertex<T>, Vertex<T>> vertexes = new LinkedHashMap<Vertex<T>, Vertex<T>>();

    /**
     * key - edge between two vertices
     * value - number of edges between same vertices
     */
    private LinkedHashMap<Edge<T>, Integer> edges = new LinkedHashMap<Edge<T>, Integer>();


    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!vertexes.containsKey(vertex)) {
            vertexes.put(vertex, vertex);
        }
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        vertexes.remove(vertex);
    }

    @Override
    public void addEdge(Vertex<T> source, Vertex<T> destination) {

        if (!vertexes.containsKey(source)) {
            throw new RuntimeException("There is no vertex in graph " + source);
        }

        if (!vertexes.containsKey(destination)) {
            throw new RuntimeException("There is no vertex in graph " + destination);
        }

        Edge<T> edge = new Edge<T>(source, destination);
        if (!edges.containsKey(edge)) {
            edges.put(edge, 1);
        } else {
            edges.put(edge, edges.get(edge) + 1);
        }

        vertexes.get(source).getEdges().add(edge);
        vertexes.get(destination).getEdges().add(edge);
    }

    @Override
    public Edge<T> getEdge(Vertex<T> source, Vertex<T> destination) {
        Edge<T> edge = new Edge<T>(source, destination);
        return edges.containsValue(edge) ? edge : null;
    }

    @Override
    public void removeEdge(Vertex<T> source, Vertex<T> destination) {
        Edge<T> edge = new Edge<T>(source, destination);

        if (!edges.containsKey(edge)) {
            throw new RuntimeException("There is no edge to delete " + edge);
        }

        Integer occurences = edges.get(edge);
        if (occurences == 1) {
            edges.remove(edge);
        } else {
            edges.put(edge, occurences - 1);
        }

        if (!vertexes.containsKey(source)) {
            throw new RuntimeException("There is no vertex in graph " + source);
        }

        if (!vertexes.containsKey(destination)) {
            throw new RuntimeException("There is no vertex in graph " + destination);
        }


        vertexes.get(source).getEdges().remove(edge);
        vertexes.get(destination).getEdges().remove(edge);
    }

    @Override
    public int numVertices() {
        return vertexes.size();
    }


    public void print() {

        String format = "";
        for (Vertex<T> vertex : vertexes.keySet()) {

            format = String.format("%10s", vertex.getValue());
            for (Edge<T> edge : vertex.getEdges()) {
                if (!edge.getVertex1().equals(vertex)) {
                    format = format + String.format("%10s", edge.getVertex1().getValue());
                } else if (!edge.getVertex2().equals(vertex)) {
                    format = format + String.format("%10s", edge.getVertex2().getValue());
                }
            }
            format = format +"\n";
        }
        System.out.println(format);

    }


}
