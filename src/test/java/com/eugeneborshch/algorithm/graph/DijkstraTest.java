package com.eugeneborshch.algorithm.graph;


import com.eugeneborshch.algorithm.graph.model.Vertex;
import com.eugeneborshch.algorithm.graph.model.WeightedNonDirectedGraphImpl;
import org.junit.Test;

import java.util.Map;
import java.util.Scanner;

import static junit.framework.Assert.assertEquals;

/**
 * Dijkstra test.
 * Coursera 5 week
 * User: Eugene Borshch
 */
public class DijkstraTest {

    WeightedNonDirectedGraphImpl<Integer> graph;

    public void init() {

        graph = new WeightedNonDirectedGraphImpl<Integer>();
        Scanner scanner = new Scanner(getClass().getResourceAsStream("/dijkstraData.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split("\t");

            Vertex<Integer> source = new Vertex<Integer>(Integer.valueOf(split[0]));
            graph.addVertex(source);
            for (int i = 1; i < split.length; i++) {
                String[] vertexWeight = split[i].split(",");

                Vertex<Integer> dest = new Vertex<Integer>(Integer.valueOf(vertexWeight[0]));

                graph.addVertex(dest);

                if (!graph.isEdge(source, dest)) {
                    graph.addEdge(source, dest, Integer.valueOf(vertexWeight[1]));
                }
            }

        }
    }

    @Test
    public void testCoursera()
    {
        init();

        Vertex<Integer> start = graph.getVertex(new Vertex<Integer>(1));
        Map<Vertex, Integer> distances = new Dijkstra().calculate(graph, start);

        System.out.printf("%d,%d,%d,%d,%d,%d,%d,%d,%d,%d" , distances.get(new Vertex<Integer>(7)),
                distances.get(new Vertex<Integer>(37)),
                distances.get(new Vertex<Integer>(59)),
                distances.get(new Vertex<Integer>(82)),
                distances.get(new Vertex<Integer>(99)),
                distances.get(new Vertex<Integer>(115)),
                distances.get(new Vertex<Integer>(133)),
                distances.get(new Vertex<Integer>(165)),
                distances.get(new Vertex<Integer>(188)),
                distances.get(new Vertex<Integer>(197)));

    }

    @Test
    public void simpleTest() {

        WeightedNonDirectedGraphImpl<Integer> graph = new WeightedNonDirectedGraphImpl<Integer>();
        graph.addVertex(new Vertex<Integer>(1));
        graph.addVertex(new Vertex<Integer>(2));
        graph.addVertex(new Vertex<Integer>(3));
        graph.addVertex(new Vertex<Integer>(4));
        graph.addVertex(new Vertex<Integer>(5));
        graph.addVertex(new Vertex<Integer>(6));

        graph.addEdge(new Vertex<Integer>(1), new Vertex<Integer>(2), 7);
        graph.addEdge(new Vertex<Integer>(1), new Vertex<Integer>(3), 9);
        graph.addEdge(new Vertex<Integer>(1), new Vertex<Integer>(6), 14);
        graph.addEdge(new Vertex<Integer>(6), new Vertex<Integer>(5), 9);
        graph.addEdge(new Vertex<Integer>(6), new Vertex<Integer>(3), 2);
        graph.addEdge(new Vertex<Integer>(2), new Vertex<Integer>(3), 10);
        graph.addEdge(new Vertex<Integer>(2), new Vertex<Integer>(4), 15);
        graph.addEdge(new Vertex<Integer>(3), new Vertex<Integer>(4), 11);
        graph.addEdge(new Vertex<Integer>(4), new Vertex<Integer>(5), 6);


        Vertex<Integer> start = graph.getVertex(new Vertex<Integer>(1));
        Map<Vertex, Integer> distances = new Dijkstra().calculate(graph, start);
        assertEquals(0, distances.get(new Vertex<Integer>(1)).intValue());
        assertEquals(7, distances.get(new Vertex<Integer>(2)).intValue());
        assertEquals(9, distances.get(new Vertex<Integer>(3)).intValue());
        assertEquals(20, distances.get(new Vertex<Integer>(4)).intValue());
        assertEquals(20, distances.get(new Vertex<Integer>(5)).intValue());
        assertEquals(11, distances.get(new Vertex<Integer>(6)).intValue());


    }
}
