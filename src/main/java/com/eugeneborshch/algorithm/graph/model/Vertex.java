package com.eugeneborshch.algorithm.graph.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Vertex in graph.
 * <p/>
 * User: Eugene Borshch
 */
public class Vertex<T> {

    private T value;

    protected Collection<Edge<T>> edges = new LinkedList<Edge<T>>();

    public Vertex(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Collection<Edge<T>> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (!value.equals(vertex.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "V{" + value + "}";
    }
}
