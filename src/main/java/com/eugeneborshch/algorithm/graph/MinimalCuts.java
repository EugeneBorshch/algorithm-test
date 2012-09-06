package com.eugeneborshch.algorithm.graph;


import com.eugeneborshch.algorithm.graph.model.Edge;
import com.eugeneborshch.algorithm.graph.model.Graph;
import com.eugeneborshch.algorithm.graph.model.NonDirectedGraph;
import com.eugeneborshch.algorithm.graph.model.Vertex;

import java.util.*;

/**
 * Karger's algorithm to compute the minimum cut of a connected graph.
 * <p/>
 * User: EBorshch
 */
public class MinimalCuts {
    private Random generator = new Random();
    private LinkedHashMap<Vertex<Integer>, List<Vertex>> history;


    public int getMinimalCut(NonDirectedGraph<Integer> graph) {
        history = new LinkedHashMap<Vertex<Integer>, List<Vertex>>();
        while (graph.numVertices() > 2) {
            Edge<Integer> edge = getRandomEdge(graph);


            Vertex<Integer> remainingVertex = graph.getVertex(graph.numVertices() % 2 == 0 ? edge.getVertex2() : edge.getVertex1());
            //vertex to be removed
            Vertex<Integer> mergedVertex = graph.getVertex(graph.numVertices() % 2 == 0 ? edge.getVertex1() : edge.getVertex2());

            putToHistory(remainingVertex, mergedVertex);

            ArrayList<Edge<Integer>> mergedEdges = new ArrayList<Edge<Integer>>(mergedVertex.getEdges());
            for (int i = 0; i < mergedEdges.size(); i++) {
                Edge<Integer> mergedEdge = mergedEdges.get(i);
                while (graph.isEdge(mergedEdge.getVertex1(), mergedEdge.getVertex2())) {
                    //remove old edge
                    graph.removeEdge(mergedEdge.getVertex1(), mergedEdge.getVertex2());

                    Vertex<Integer> newVertex = mergedVertex.equals(mergedEdge.getVertex1()) ?
                            mergedEdge.getVertex2() : mergedEdge.getVertex1();

                    //don't allow self loops
                    if (!remainingVertex.equals(newVertex)) {
                        if (i % 2 != 0) {
                            graph.addEdge(remainingVertex, newVertex);
                        } else {
                            graph.addEdge(newVertex, remainingVertex);
                        }
                    }
                }
            }
            graph.removeVertex(mergedVertex);
        }
        return graph.getVertices().iterator().next().getEdges().size();
    }

    private Edge<Integer> getRandomEdge(Graph<Integer> graph) {
        List<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();
        List<Edge<Integer>> graphEdges = graph.getEdges();
        for (int i = 0; i < graphEdges.size(); i++) {
            if (i % 2 == 0) {
                edges.add(graphEdges.get(i));
            } else {
                edges.add(new Edge<Integer>(graphEdges.get(i).getVertex2(), graphEdges.get(i).getVertex1()));
            }

        }
        int edgeIndex = generator.nextInt(edges.size());
        return edges.get(edgeIndex);
    }

    public LinkedHashMap<Vertex<Integer>, List<Vertex>> getHistory() {
        return history;
    }

    private void putToHistory(Vertex<Integer> survived, Vertex<Integer> merged) {
        List<Vertex> vertexes = history.get(survived);
        if (vertexes == null) {
            vertexes = new ArrayList<Vertex>();
            history.put(survived, vertexes);
        }
        vertexes.add(merged);
        if (history.containsKey(merged)) {
            vertexes.addAll(history.get(merged));
        }
    }
}
