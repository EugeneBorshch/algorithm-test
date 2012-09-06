package com.eugeneborshch.algorithm.graph;

import com.eugeneborshch.algorithm.graph.model.NonDirectedGraph;
import com.eugeneborshch.algorithm.graph.model.Vertex;
import org.junit.Test;

import java.util.Scanner;

import static junit.framework.Assert.assertEquals;

/**
 * Graph minimal cut test.
 * Homework for the course Algorithms: Design and Analysis (https://class.coursera.org)
 * <p/>
 * User: Eugene Borshch
 */
public class MinimalCutTest {

    public static final int TEST_SIZE = 10;
    private NonDirectedGraph<Integer>[] graph = new NonDirectedGraph[TEST_SIZE];


    public void init() {

        for (int i = 0; i < TEST_SIZE; i++) {
            graph[i] = new NonDirectedGraph<Integer>();
        }

        Scanner scanner = new Scanner(getClass().getResourceAsStream("/kargerMinCut.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split("\t");

            for (int i = 1; i < split.length; i++) {
                Vertex<Integer> source = new Vertex<Integer>(Integer.valueOf(split[0]));
                Vertex<Integer> dest = new Vertex<Integer>(Integer.valueOf(split[i]));
                for (int j = 0; j < TEST_SIZE; j++) {
                    graph[j].addVertex(source);
                    graph[j].addVertex(dest);

                    if (!graph[j].isEdge(source, dest)) {
                        if (i % 2 == 0) {
                            graph[j].addEdge(source, dest);
                        } else {
                            graph[j].addEdge(dest, source);
                        }

                    }
                }
            }
        }
        scanner.close();
    }

    @Test
    public void testMinimalCuts() {
        init();
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < TEST_SIZE; i++) {
            //
            MinimalCuts minimalCuts = new MinimalCuts();
            int minCut = minimalCuts.getMinimalCut(graph[i]);
            if (minCut < result) {
                result = minCut;
            }
        }
        // int courseraCorrectAnswer = 17;
        // assertEquals(courseraCorrectAnswer, result);
        System.out.printf("Graph minimal cut %s\n", result);
    }


    @Test
    public void testSmallGraphMinimalCuts() {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {

            NonDirectedGraph<Integer> smallGraph = new NonDirectedGraph<Integer>();
            smallGraph.addVertex(new Vertex<Integer>(1));
            smallGraph.addVertex(new Vertex<Integer>(2));
            smallGraph.addVertex(new Vertex<Integer>(3));
            smallGraph.addVertex(new Vertex<Integer>(4));
            smallGraph.addEdge(new Vertex<Integer>(1), new Vertex<Integer>(2));
            smallGraph.addEdge(new Vertex<Integer>(3), new Vertex<Integer>(1));
            smallGraph.addEdge(new Vertex<Integer>(1), new Vertex<Integer>(4));
            smallGraph.addEdge(new Vertex<Integer>(2), new Vertex<Integer>(3));
            smallGraph.addEdge(new Vertex<Integer>(4), new Vertex<Integer>(3));


            smallGraph.addVertex(new Vertex<Integer>(11));
            smallGraph.addVertex(new Vertex<Integer>(12));
            smallGraph.addVertex(new Vertex<Integer>(13));
            smallGraph.addVertex(new Vertex<Integer>(14));
            smallGraph.addEdge(new Vertex<Integer>(11), new Vertex<Integer>(12));
            smallGraph.addEdge(new Vertex<Integer>(11), new Vertex<Integer>(13));
            smallGraph.addEdge(new Vertex<Integer>(11), new Vertex<Integer>(14));
            smallGraph.addEdge(new Vertex<Integer>(12), new Vertex<Integer>(13));
            smallGraph.addEdge(new Vertex<Integer>(14), new Vertex<Integer>(13));


            smallGraph.addEdge(new Vertex<Integer>(3), new Vertex<Integer>(11));

            MinimalCuts minimalCuts = new MinimalCuts();
            int minCut = minimalCuts.getMinimalCut(smallGraph);
            if (minCut < result) {
                result = minCut;
            }

        }
        assertEquals(1, result);

    }
}
