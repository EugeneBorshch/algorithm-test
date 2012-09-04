package com.eugeneborshch.algorithm.graph.model;

/**
 * Graph representation.
 * <p/>
 * User: Eugene Borshch
 */
public interface Graph<T> {

    public void addVertex(Vertex<T> vertex);

    public void removeVertex(Vertex<T> vertex);

    public void addEdge(Vertex<T> source, Vertex<T> destination);

    public boolean isEdge(Vertex<T> source, Vertex<T> destination);

    public Edge<T> getEdge(Vertex<T> source, Vertex<T> destination);

    public void removeEdge(Vertex<T> source, Vertex<T> destination);

    public int numVertices();


}
