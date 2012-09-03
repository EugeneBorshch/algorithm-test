package main.java.com.eugeneborshch.algorithm.graph.model;

/**
 * Edge in Graph
 * User: Eugene Borshch
 */
public class Edge<T> {

    private Vertex<T> vertex1;
    private Vertex<T> vertex2;

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Vertex<T> getVertex1() {
        return vertex1;
    }

    public Vertex<T> getVertex2() {
        return vertex2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (vertex1 != null ? !vertex1.equals(edge.vertex1) : edge.vertex1 != null) return false;
        if (vertex2 != null ? !vertex2.equals(edge.vertex2) : edge.vertex2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vertex1 != null ? vertex1.hashCode() : 0;
        result = 31 * result + (vertex2 != null ? vertex2.hashCode() : 0);
        return result;
    }
}
