package com.eugeneborshch.algorithm.graph;

import com.eugeneborshch.algorithm.graph.model.DirectedGraph;
import com.eugeneborshch.algorithm.graph.model.Vertex;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipFile;

/**
 * Find strongly connected components test.
 * Week 4 of coursera.org Algorithms: Design and Analysis, Part 1
 * <p/>
 * User: EBorshch
 */
public class GraphStronglyConnectedComponentsTest {

    private DirectedGraph<Integer> graph;

    @Before
    public void init() throws IOException {

        graph = new DirectedGraph<Integer>();
        ZipFile zipFile = new ZipFile(getClass().getResource("/SCC.zip").getPath());
        Scanner scanner = new Scanner(zipFile.getInputStream(zipFile.getEntry("SCC.txt")));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(" ");

            Vertex<Integer> source = new Vertex<Integer>(Integer.valueOf(split[0]));
            Vertex<Integer> dest = new Vertex<Integer>(Integer.valueOf(split[1]));

            graph.addVertex(source);
            graph.addVertex(dest);

            if (!graph.isEdge(source, dest)) {
                graph.addEdge(source, dest);
            }

        }
        scanner.close();

    }

    @Test
    public void testStronglyConnectedComponents() {

    }
}
