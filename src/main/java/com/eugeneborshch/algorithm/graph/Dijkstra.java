package com.eugeneborshch.algorithm.graph;

import com.eugeneborshch.algorithm.graph.model.Edge;
import com.eugeneborshch.algorithm.graph.model.Vertex;
import com.eugeneborshch.algorithm.graph.model.WeightedNonDirectedGraphImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of Dijkstra graph search
 * User: EBorshch
 */
public class Dijkstra {

    private Set<Vertex<Integer>> processedVertices;
    private Set<Vertex<Integer>> unsettledVertices;
    private Map<Vertex, Integer> distances;

    public Map<Vertex, Integer> calculate(WeightedNonDirectedGraphImpl<Integer> graph, Vertex<Integer> start) {
        processedVertices = new HashSet<Vertex<Integer>>();
        unsettledVertices = new HashSet<Vertex<Integer>>();
        distances = new HashMap<Vertex, Integer>(graph.numVertices());


        for (Vertex vertex : graph.getVertices()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }

        distances.put(start, 0);

        unsettledVertices.add(start);

        while (!unsettledVertices.isEmpty()) {
            Vertex<Integer> next = getClosest(unsettledVertices);
            unsettledVertices.remove(next);
            Integer distance = distances.get(next);
            if (distance == Integer.MAX_VALUE) {
                System.out.println("Execution stopped vertices are non connected");
                break;
            }


            for (Edge<Integer> neighbor : next.getEdges()) {

                Vertex neighborVertex;
                if (neighbor.getVertex1().equals(next)) {
                    neighborVertex = neighbor.getVertex2();
                } else {
                    neighborVertex = neighbor.getVertex1();
                }
                if (processedVertices.contains(neighborVertex)) {
                    continue;
                }

                distance +
            }

        }


        return distances;
    }

    private Vertex<Integer> getClosest(Set<Vertex<Integer>> unsettledVertices) {

        int minDistance = Integer.MAX_VALUE;
        Vertex<Integer> vertex = null;
        for (Vertex<Integer> unsettled : unsettledVertices) {
            Integer unsetteledDist = distances.get(unsettled);
            if (unsetteledDist < minDistance) {
                minDistance = unsetteledDist;
                vertex = unsettled;
            }

        }
        return vertex;
    }
}
