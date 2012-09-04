package com.eugeneborshch.algorithm.graph;

import com.eugeneborshch.algorithm.graph.model.NonDirectedGraph;
import com.eugeneborshch.algorithm.graph.model.Vertex;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Non directed graph implementation test
 * User: EBorshch
 */
public class NonDirectedGraphTest {

    @Test
    public void testAddRemoveVertices() {
        NonDirectedGraph<Integer> graph = new NonDirectedGraph<Integer>();

        graph.addVertex(new Vertex<Integer>(1));
        graph.addVertex(new Vertex<Integer>(1));
        graph.addVertex(new Vertex<Integer>(2));

        assertEquals("Wrong num vertices ", 2, graph.numVertices());

        graph.removeVertex(new Vertex<Integer>(1));
        assertEquals("Wrong num vertices after remove ", 1, graph.numVertices());

        graph.removeVertex(new Vertex<Integer>(2));
        assertEquals("Wrong num vertices after remove ", 0, graph.numVertices());

        graph.removeVertex(new Vertex<Integer>(2));
        assertEquals("Wrong num vertices after remove ", 0, graph.numVertices());

    }

    @Test
    public void testAddRemoveEdges() {
        NonDirectedGraph<Integer> graph = new NonDirectedGraph<Integer>();

        Vertex<Integer> vertex1 = new Vertex<Integer>(1);
        Vertex<Integer> vertex2 = new Vertex<Integer>(2);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);

        graph.addEdge(vertex1, vertex2);

        assertEquals("Edge is missing ", true, graph.isEdge(vertex1, vertex2));
        assertEquals("Edge is missing ", true, graph.isEdge(vertex2, vertex1));


        graph.removeEdge(vertex2, vertex1);
        assertEquals("Edge is missing ", false, graph.isEdge(vertex1, vertex2));
        assertEquals("Edge is missing ", false, graph.isEdge(vertex2, vertex1));

    }

}
