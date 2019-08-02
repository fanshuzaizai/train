package com.github.fanshuzaizai.algorithm.chapter4_graph.directed;

import com.github.fanshuzaizai.algorithm.chapter1_base.Queue;

/**
 * @author Jzy.
 * @date 2019/7/30 15:18
 */
public class DirectedCycle {

    private final DirectedGraph graph;

    private Integer[] edgeTo;

    /**
     * 是否已经经过顶点
     */
    private boolean[] marked;

    /**
     * 是否在栈中
     */
    private boolean[] onStack;

    private Queue<Integer> cycle;

    public DirectedCycle(DirectedGraph graph) {
        this.graph = graph;
        int vertexSize = graph.vertexSize();
        marked = new boolean[vertexSize];
        onStack = new boolean[vertexSize];
        edgeTo = new Integer[vertexSize];

        for (int i = 0; i < vertexSize; i++) {
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
    private void dfs(DirectedGraph graph, int v) {
        marked[v] = true;
        //临时放入stack
        onStack[v] = true;
        Iterable<Integer> connectedVertexes = graph.connectedVertexes(v);
        //递归访问其他与之联通的顶点
        for (Integer i : connectedVertexes) {
            if (hasCycle()) {
                return;
            } else if (!marked[i]) {
                edgeTo[i] = v;
                dfs(graph, i);
            } else if (onStack[i]) {//如果已经被标记，且在当前stack中，说明当前是一个环
                cycle = new Queue<>();
                for (int x = v; x != i; x = edgeTo[x]) {
                    cycle.enqueue(x);
                }
                cycle.enqueue(i);
                cycle.enqueue(v);
            }
        }
        //移出stack
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public static void main(String[] args) {

        DirectedGraph graph = new DirectedGraph(5);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
//        graph.addEdge(3, 1);

        DirectedCycle directedCycle = new DirectedCycle(graph);
        System.out.println(directedCycle.hasCycle());
//        directedCycle.cycle.forEach(System.out::println);

    }

}
