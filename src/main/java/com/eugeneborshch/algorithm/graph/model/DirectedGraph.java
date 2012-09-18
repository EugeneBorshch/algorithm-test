package com.eugeneborshch.algorithm.graph.model;

import java.util.*;

/**
 * Directed graph implementation
 * User: EBorshch
 */
public class DirectedGraph<T> implements Graph<T> {


    private HashMap<Vertex<T>, Vertex<T>> vertexes = new HashMap<Vertex<T>, Vertex<T>>();

    /**
     * key - edge between two vertices
     * value - number of edges between same vertices
     */
    private HashMap<Edge<T>, Integer> edges = new HashMap<Edge<T>, Integer>();

    @Override
    public void addVertex(Vertex<T> tVertex) {
        if (!vertexes.containsKey(tVertex)) {
            vertexes.put(tVertex, tVertex);
        }
    }

    @Override
    public Vertex<T> getVertex(Vertex<T> tVertex) {
        return vertexes.get(tVertex);
    }

    @Override
    public void removeVertex(Vertex<T> tVertex) {
        vertexes.remove(tVertex);
    }

    @Override
    public void addEdge(Vertex<T> source, Vertex<T> destination) {

        Edge<T> edge = new Edge<T>(vertexes.get(source), vertexes.get(destination));
        if (edges.containsKey(edge)) {
            return;
        }

        edges.put(edge, 1);

        vertexes.get(source).getEdges().add(edge);
    }

    @Override
    public boolean isEdge(Vertex<T> source, Vertex<T> destination) {

        return edges.containsKey(new Edge<T>(source, destination));
    }

    @Override
    public void removeEdge(Vertex<T> source, Vertex<T> destination) {
        Edge<T> edge = new Edge<T>(source, destination);
        edges.remove(edge);

        vertexes.get(source).getEdges().remove(edge);
    }

    @Override
    public Set<Vertex<T>> getVertices() {
        return vertexes.keySet();
    }

    @Override
    public List<Edge<T>> getEdges() {
        return new ArrayList<Edge<T>>(edges.keySet());
    }

    @Override
    public int numVertices() {
        return vertexes.size();
    }
}
