package com.github.fanshuzaizai.algorithm.chapter4_graph.directed.weighted;

import com.github.fanshuzaizai.algorithm.chapter1_base.Queue;

/**
 * 寻找负权重环
 *
 * @author Jzy.
 * @date 2019/7/30 15:18
 */
public class NegativeDirectedCycle {

    private final DirectedWeightedEdgeGraph graph;

    private DirectedWeightedEdge[] edgeTo;

    /**
     * 是否已经经过顶点
     */
    private boolean[] marked;

    /**
     * 是否在栈中
     */
    private boolean[] onStack;

    private Queue<DirectedWeightedEdge> cycle;

    public NegativeDirectedCycle(DirectedWeightedEdgeGraph graph) {
        this.graph = graph;
        int vertexSize = graph.vertexSize();
        marked = new boolean[vertexSize];
        onStack = new boolean[vertexSize];
        edgeTo = new DirectedWeightedEdge[vertexSize];

        for (int i = 0; i < vertexSize && !hasCycle(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    /**
     * 标记联通
     *
     * @param graph
     * @param v
     */
    private void dfs(DirectedWeightedEdgeGraph graph, int v) {
        marked[v] = true;
        //临时放入stack
        onStack[v] = true;
        Iterable<DirectedWeightedEdge> connectedVertexes = graph.connectedVertexes(v);
        //递归访问其他与之联通的顶点
        for (DirectedWeightedEdge edge : connectedVertexes) {
            int to = edge.to();
            if (hasCycle()) {
                return;
            } else if (!marked[to]) {
                edgeTo[to] = edge;
                //递归访问
                dfs(graph, to);
            } else if (onStack[to]) {//如果已经被标记，且在当前stack中，说明当前是一个环
                Queue<DirectedWeightedEdge> cycle = new Queue<>();

                int x;
                DirectedWeightedEdge e = edge;
                while (e.from() != to) {
                    cycle.enqueue(e);
                    x = e.from();
                    e = edgeTo[x];
                }
                cycle.enqueue(e);

                double weight = calculateCycleWeight(cycle);
                if (weight < 0) {
                    this.cycle = cycle;
                }
            }
        }
        //移出stack
        onStack[v] = false;
    }

    private double calculateCycleWeight(Queue<DirectedWeightedEdge> cycle) {
        double w = 0;
        for (DirectedWeightedEdge edge : cycle) {
            double weight = edge.getWeight();
            w += weight;
        }
        return w;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Queue<DirectedWeightedEdge> cycle() {
        return cycle;
    }

    public static void main(String[] args) {

        DirectedWeightedEdgeGraph graph = new DirectedWeightedEdgeGraph(5);


        graph.addEdge(new DirectedWeightedEdge(2, 3, 0.5));
        graph.addEdge(new DirectedWeightedEdge(2, 4, 0.5));
        graph.addEdge(new DirectedWeightedEdge(0, 1, 0.5));
        graph.addEdge(new DirectedWeightedEdge(1, 2, -1.1));
        graph.addEdge(new DirectedWeightedEdge(2, 0, 0.5));

        NegativeDirectedCycle directedCycle = new NegativeDirectedCycle(graph);
        System.out.println(directedCycle.hasCycle());
        if (directedCycle.hasCycle()) {
            directedCycle.cycle.forEach(System.out::println);
        }

    }

}
