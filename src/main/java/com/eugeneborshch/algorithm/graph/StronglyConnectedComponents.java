package com.eugeneborshch.algorithm.graph;

import com.eugeneborshch.algorithm.graph.model.Edge;
import com.eugeneborshch.algorithm.graph.model.Graph;
import com.eugeneborshch.algorithm.graph.model.Vertex;

import java.util.*;

/**
 * Computing Strongly Connected Components with Kosaraju’s algorithm
 *
 * http://scienceblogs.com/goodmath/2007/10/30/computing-strongly-connected-c/
 *
 * User: Eugene Borshch
 */
public class StronglyConnectedComponents {

    public void getSCC(Graph graph, Graph reversedGraph) {
        Iterator<Vertex<Integer>> iterator = graph.getVertices().iterator();
        Stack<Vertex<Integer>> dfsSorting = getDfsSorting(graph, iterator.next());


    }


    private  Stack<Vertex<Integer>>  getDfsSorting(Graph graph, Vertex<Integer> root) {

        Set<Vertex<Integer>> nonVisited = new HashSet<Vertex<Integer>>();
        nonVisited.addAll(graph.getVertices());

        Stack<Vertex<Integer>> stack = new Stack<Vertex<Integer>>();
        Stack<Vertex<Integer>> sorted = new Stack<Vertex<Integer>>();
        stack.push(root);

        while (!stack.isEmpty()) {

            Vertex<Integer> vertex = stack.pop();
            nonVisited.remove(vertex);

            for(Edge<Integer> edge :vertex.getEdges()){
                if(nonVisited.contains(edge.getVertex2()))
                {
                    stack.push(edge.getVertex2());

                    sorted.push(edge.getVertex2());
                }

            }
        }

        if(stack.size()!=graph.getVertices().size())
        {
            throw new RuntimeException("Wrong DFS sorting");
        }
        return stack;
    }


}
