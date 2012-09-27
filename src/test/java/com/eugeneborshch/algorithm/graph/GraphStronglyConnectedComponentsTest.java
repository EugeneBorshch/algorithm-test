package com.eugeneborshch.algorithm.graph;

import com.eugeneborshch.algorithm.graph.model.DirectedGraph;
import com.eugeneborshch.algorithm.graph.model.Vertex;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private DirectedGraph<Integer> reversedGraph;


    public void init() throws IOException {

        graph = new DirectedGraph<Integer>();
        reversedGraph = new DirectedGraph<Integer>();
        ZipFile zipFile = new ZipFile(getClass().getResource("/SCC.zip").getPath());
        Scanner scanner = new Scanner(zipFile.getInputStream(zipFile.getEntry("SCC.txt")));
        int i = 0;
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
            source = new Vertex<Integer>(Integer.valueOf(split[0]));
            dest = new Vertex<Integer>(Integer.valueOf(split[1]));

            reversedGraph.addVertex(source);
            reversedGraph.addVertex(dest);

            if (!reversedGraph.isEdge(dest, source)) {
                reversedGraph.addEdge(dest, source);
            }

            if (i % 100000 == 0) {
                System.out.println("Lines loaded " + i);
            }
            i++;

        }

        System.out.println("Graph loaded " + graph.getVertices().size() +" vertices");
        System.out.println("Graph loaded " + i +" edges");
        scanner.close();

    }

    @Test
    public void testStronglyConnectedComponents() throws IOException {
        init();

        List<Integer> scc = new StronglyConnectedComponents<Integer>().getSCC(graph, reversedGraph);
        Collections.sort(scc);
        System.out.println(scc.toString());
    }




    @Test
    public void testStronglyConnectedComponentsMin() {

        DirectedGraph<String>  graphM = new DirectedGraph<String>();
        DirectedGraph<String>  reversedGraphM = new DirectedGraph<String>();


        List<String> input = new ArrayList<String>();

        input.add("A B");
        input.add("B D");
        input.add("D A");
        input.add("A F");
        input.add("F E");
        input.add("E F");
        input.add("E C");
        input.add("C G");
        input.add("D G");
        input.add("G H");
        input.add("H C");
        for(String line : input) {
            String[] split = line.split(" ");

            Vertex<String> source = new Vertex<String>(split[0]);
            Vertex<String> dest = new Vertex<String>(split[1]);

            graphM.addVertex(source);
            graphM.addVertex(dest);

            if (!graphM.isEdge(source, dest)) {
                graphM.addEdge(source, dest);
            }
            source = new Vertex<String>(split[0]);
            dest = new Vertex<String>(split[1]);

            reversedGraphM.addVertex(source);
            reversedGraphM.addVertex(dest);

            if (!reversedGraphM.isEdge(dest, source)) {
                reversedGraphM.addEdge(dest, source);
            }


        }




        List<Integer> scc = new StronglyConnectedComponents<String>().getSCC(graphM, reversedGraphM);
        Collections.sort(scc);
        System.out.println(scc.toString());
    }
}
