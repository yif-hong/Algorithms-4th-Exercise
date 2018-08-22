package exercise.chapter4_2;

public class Topological {
    private Iterable<Integer> order;        //顶点的拓扑排序,为所有顶点的逆后序排列。

    public Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public static void main(String[] args) {
        String fileName = args[0];
        String separator = args[1];
        SymbolDigraph sg = new SymbolDigraph(fileName, separator);

        Topological top = new Topological(sg.G());
        for (int v : top.order())
            System.out.println(sg.name(v));
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}
