package com.eugeneborshch.algorithm.graph.model;

import java.util.*;

/**
 * Non directed graph implementation.
 * <p/>
 * User: Eugene Borshch
 */
public class NonDirectedGraph<T> implements Graph<T> {


    protected LinkedHashMap<Vertex<T>, Vertex<T>> vertexes = new LinkedHashMap<Vertex<T>, Vertex<T>>();

    /**
     * key - edge between two vertices
     * value - number of edges between same vertices
     */
    protected LinkedHashMap<Edge<T>, Integer> edges = new LinkedHashMap<Edge<T>, Integer>();


    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!vertexes.containsKey(vertex)) {
            vertexes.put(vertex, vertex);
        }
    }

    @Override
    public Vertex<T> getVertex(Vertex<T> tVertex) {
        return vertexes.get(tVertex);
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

        if (!isEdge(source, destination)) {
            Edge<T> edge = new Edge<T>(source, destination);
            edges.put(edge, 1);
            vertexes.get(source).getEdges().add(edge);
            vertexes.get(destination).getEdges().add(edge);
        } else {
            //As we have non directed graph V1->V2 is the same as V2->V1
            Edge<T> edge = new Edge<T>(source, destination);
            if (edges.get(edge) != null) {
                edges.put(edge, edges.get(edge) + 1);
                vertexes.get(source).getEdges().add(edge);
                vertexes.get(destination).getEdges().add(edge);
            }

            edge = new Edge<T>(destination, source);
            if (edges.get(edge) != null) {
                edges.put(edge, edges.get(edge) + 1);
                vertexes.get(source).getEdges().add(edge);
                vertexes.get(destination).getEdges().add(edge);
            }
        }
    }

    @Override
    public boolean isEdge(Vertex<T> source, Vertex<T> destination) {
        Edge<T> edge = new Edge<T>(source, destination);
        Edge<T> opposite = new Edge<T>(destination, source);
        //As we have non directed graph V1->V2 is the same as V2->V1
        return edges.containsKey(edge) || edges.containsKey(opposite);
    }

    @Override
    public void removeEdge(Vertex<T> source, Vertex<T> destination) {
        if (!vertexes.containsKey(source)) {
            throw new RuntimeException("There is no vertex in graph " + source);
        }

        if (!vertexes.containsKey(destination)) {
            throw new RuntimeException("There is no vertex in graph " + destination);
        }

        Edge<T> edge = new Edge<T>(source, destination);
        Edge<T> opposite = new Edge<T>(destination, source);

        if (!isEdge(source, destination)) {
            throw new RuntimeException("There is no edge to delete " + edge);
        }


        //As we have non directed graph V1->V2 is the same as V2->V1
        if (edges.get(edge) != null) {
            Integer occurences = edges.get(edge);
            if (occurences == 1) {
                edges.remove(edge);
            } else {
                edges.put(edge, occurences - 1);
            }
            vertexes.get(source).getEdges().remove(edge);
            vertexes.get(destination).getEdges().remove(edge);
            vertexes.get(source).getEdges().remove(opposite);
            vertexes.get(destination).getEdges().remove(opposite);
        }


        if (edges.get(opposite) != null) {
            Integer occurences = edges.get(opposite);
            if (occurences == 1) {
                edges.remove(opposite);
            } else {
                edges.put(opposite, occurences - 1);
            }
            vertexes.get(source).getEdges().remove(opposite);
            vertexes.get(destination).getEdges().remove(opposite);
            vertexes.get(source).getEdges().remove(edge);
            vertexes.get(destination).getEdges().remove(edge);
        }
    }

    @Override
    public Set<Vertex<T>> getVertices() {
        return vertexes.keySet();
    }

    @Override
    public List<Edge<T>> getEdges() {
        List<Edge<T>> result = new ArrayList<Edge<T>>();
        for (Map.Entry<Edge<T>, Integer> edgeEntry : edges.entrySet()) {
            for (int j = 0; j < edgeEntry.getValue(); j++) {
                result.add(edgeEntry.getKey());
            }
        }

        return result;
    }

    @Override
    public int numVertices() {
        return vertexes.size();
    }


    public void print() {

        String format = "";
        for (Vertex<T> vertex : vertexes.keySet()) {

            format = format + "\n" + String.format("%10s", vertex.getValue());
            for (Edge<T> edge : vertex.getEdges()) {
                if (edge.getVertex1().equals(vertex)) {
                    format = format + String.format("%10s", edge.getVertex2().getValue());
                }
            }
        }
        System.out.println(format);

    }


}
