package com.eugeneborshch.algorithm.graph.model;

/**
 * Weighted non directed graph impl.
 * User: EBorshch
 */
public class WeightedNonDirectedGraphImpl<T> extends NonDirectedGraph<T> {


    public void addEdge(Vertex<T> source, Vertex<T> destination, Integer weight) {


        if (!vertexes.containsKey(source)) {
            throw new RuntimeException("There is no vertex in graph " + source);
        }

        if (!vertexes.containsKey(destination)) {
            throw new RuntimeException("There is no vertex in graph " + destination);
        }

        if (!isEdge(source, destination)) {
            Edge<T> edge = new Edge<T>(source, destination, weight);
            edges.put(edge, 1);
            vertexes.get(source).getEdges().add(edge);
            vertexes.get(destination).getEdges().add(edge);
        } else {
            //As we have non directed graph V1->V2 is the same as V2->V1
            Edge<T> edge = new Edge<T>(source, destination, weight);
            if (edges.get(edge) != null) {
                edges.put(edge, edges.get(edge) + 1);
                vertexes.get(source).getEdges().add(edge);
                vertexes.get(destination).getEdges().add(edge);
            }

            edge = new Edge<T>(destination, source, weight);
            if (edges.get(edge) != null) {
                edges.put(edge, edges.get(edge) + 1);
                vertexes.get(source).getEdges().add(edge);
                vertexes.get(destination).getEdges().add(edge);
            }
        }
    }
}
