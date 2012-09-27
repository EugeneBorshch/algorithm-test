package com.eugeneborshch.algorithm.graph;

import com.eugeneborshch.algorithm.graph.model.Edge;
import com.eugeneborshch.algorithm.graph.model.Graph;
import com.eugeneborshch.algorithm.graph.model.Vertex;

import java.util.*;

/**
 * Computing Strongly Connected Components with Kosaraju’s algorithm
 * <p/>
 * http://scienceblogs.com/goodmath/2007/10/30/computing-strongly-connected-c/
 * <p/>
 * User: Eugene Borshch
 */
public class StronglyConnectedComponents<T> {

   // Stack<Vertex<T>> sorted = new Stack<Vertex<T>>();
   List<Vertex<T>> sorted = new ArrayList<Vertex<T>>();

    public List<Integer> getSCC(Graph graph, Graph reversedGraph) {
        List<Integer> result = new ArrayList<Integer>();


        System.out.println("Start sorting");
        Stack<Vertex<T>> dfsSorting = getDfsSorting(graph);
        System.out.println("End sorting");


        Stack<Vertex<T>> stack = new Stack<Vertex<T>>();

        while (!dfsSorting.isEmpty()) {
            stack.push(reversedGraph.getVertex(dfsSorting.pop()));
            Set<Vertex<T>> scc = new HashSet<Vertex<T>>();

            while (!stack.isEmpty()) {
                Vertex<T> vertex = stack.pop();
                scc.add(vertex);

                for (Edge<T> edge : vertex.getEdges()) {
                    if (!scc.contains(edge.getVertex2()) && dfsSorting.contains(edge.getVertex2())) {
                        stack.push(edge.getVertex2());
                    }

                }
            }

            //remove all visited nodes
            dfsSorting.removeAll(scc);
            result.add(scc.size());
        }
        return result;
    }


    private Stack<Vertex<T>> getDfsSorting(Graph graph) {

        Set<Vertex<T>> nonVisited = new HashSet<Vertex<T>>();
        nonVisited.addAll(graph.getVertices());

        Stack<Vertex<T>> stack = new Stack<Vertex<T>>();
        //stack with vertices in order that corresponds to DFS run

        while (nonVisited.size() > 0) {

            Vertex<T> next = nonVisited.contains(new Vertex("A")) ? graph.getVertex(new Vertex("A")): nonVisited.iterator().next();
            stack.push(next);

            while (!stack.isEmpty()) {

                Vertex<T> vertex = stack.pop();
                nonVisited.remove(vertex);
                sorted.add(vertex);
                for (Edge<T> edge : vertex.getEdges()) {
                    if (nonVisited.contains(edge.getVertex2())) {
                        stack.push(edge.getVertex2());


                    }

                }
            }
            sorted.add(next);
        }

        if (sorted.size() != graph.getVertices().size()) {
            throw new RuntimeException("Wrong DFS sorting");
        }
        return new Stack<Vertex<T>>();
    }


}
