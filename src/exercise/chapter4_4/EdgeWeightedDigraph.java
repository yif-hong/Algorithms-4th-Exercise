package exercise.chapter4_4;

import edu.princeton.cs.algs4.In;
import exercise.chapter1_3.Bag;

public class EdgeWeightedDigraph {
    private final int V;                //顶点
    private int E;                      //边
    private Bag<DirectedEdge>[] adj;    //邻接表

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++)
            addEdge(new DirectedEdge(in.readInt(), in.readInt(), in.readDouble()));
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(13);
        graph.addEdge(new DirectedEdge(9, 10, 0.1));
        graph.addEdge(new DirectedEdge(9, 11, 0.3));
        graph.addEdge(new DirectedEdge(9, 12, 0.4));
        graph.addEdge(new DirectedEdge(11, 10, 0.02));
        System.out.println(graph);
        System.out.println("-----------------");
        System.out.println("has 10->11 0.02:" + graph.hasEdge(new DirectedEdge(10, 11, 0.02)));
        System.out.println("has 11->10 0.02:" + graph.hasEdge(new DirectedEdge(11, 10, 0.02)));
        System.out.println("has 10->9 0.1:" + graph.hasEdge(new DirectedEdge(10, 9, 0.1)));
        System.out.println("has 9->10 0.1:" + graph.hasEdge(new DirectedEdge(9, 10, 0.1)));
        System.out.println("has 12->10 0.4:" + graph.hasEdge(new DirectedEdge(12, 10, 0.4)));
        System.out.println("-----------------");
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge edge) {
        adj[edge.from()].add(edge);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge edge : adj[v])
                bag.add(edge);
        return bag;
    }

    public boolean hasEdge(DirectedEdge edge) {
        if (V() < 2) return false;
        if (edge.from() < 0 || edge.to() > V()) return false;
        for (DirectedEdge e : edges())
            if (e.equals(edge)) return true;
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" Vertex ;").append(E).append(" Edge");
        for (int v = 0; v < V; v++) {
            s.append("\n").append(v).append(":");
            for (DirectedEdge edge : adj[v])
                s.append("[").append(edge.from()).append("->")
                        .append(edge.to()).append(": ")
                        .append(edge.weight()).append("] ");
        }
        return s.toString();
    }
}
