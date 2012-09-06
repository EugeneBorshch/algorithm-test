package com.eugeneborshch.algorithm.graph.model;

import java.util.List;
import java.util.Set;

/**
 * Graph representation.
 * <p/>
 * User: Eugene Borshch
 */
public interface Graph<T> {

    public void addVertex(Vertex<T> vertex);

    public Vertex<T> getVertex(Vertex<T> vertex);

    public void removeVertex(Vertex<T> vertex);

    public void addEdge(Vertex<T> source, Vertex<T> destination);

    public boolean isEdge(Vertex<T> source, Vertex<T> destination);

    public void removeEdge(Vertex<T> source, Vertex<T> destination);

    public Set<Vertex<T>> getVertices();

    public List<Edge<T>> getEdges();

    public int numVertices();


}
