package com.eugeneborshch.algorithm.graph;

import com.eugeneborshch.algorithm.graph.model.NonDirectedGraph;
import com.eugeneborshch.algorithm.graph.model.Vertex;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Graph minimal cut test.
 * Homework for the course Algorithms: Design and Analysis (https://class.coursera.org)
 * <p/>
 * User: Eugene Borshch
 */
public class MinimalCutTest {

    private NonDirectedGraph<Integer> graph;

    @Before
    public void init() {
        graph = new NonDirectedGraph<Integer>();
        InputStream inputStream =
                getClass().getResourceAsStream("/kargerMinCut.txt");


        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] split = line.split("\t");

            for (int i = 1; i < split.length; i++) {
                Vertex<Integer> source = new Vertex<Integer>(Integer.valueOf(split[0]));
                Vertex<Integer> dest = new Vertex<Integer>(Integer.valueOf(split[i]));
                graph.addVertex(source);
                graph.addVertex(dest);
                if (!graph.isEdge(source, dest)) {
                    graph.addEdge(source, dest);
                }
            }
        }
        graph.print();

    }

    @Test
    public void testMinimalCuts() {

    }
}
