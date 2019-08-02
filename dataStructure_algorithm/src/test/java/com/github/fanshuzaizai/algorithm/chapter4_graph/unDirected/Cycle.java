package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected;

/**
 * 有无环形（不考虑自环）
 * 深度优先方式
 *
 * @author Jzy.
 * @date 2019/7/26 10:22
 */
public class Cycle {

    private final Graph graph;

    private boolean hasCycle;

    /**
     * 是否已经经过顶点
     */
    private boolean[] marked;

    public Cycle(Graph graph) {
        this.graph = graph;
        int vertexSize = graph.vertexSize();
        marked = new boolean[vertexSize];

        for (int i = 0; i < vertexSize; i++) {
            if (!marked[i]) {
                dfs(graph, i, i);
            }
        }
    }

    /**
     * 标记联通
     *
     * @param graph
     * @param v
     * @param previous 上一个节点
     */
    private void dfs(Graph graph, int v, int previous) {
        marked[v] = true;
        Iterable<Integer> connectedVertexes = graph.connectedVertexes(v);
        //递归访问其他与之联通的顶点
        for (Integer i : connectedVertexes) {
            if (!marked[i]) {
                dfs(graph, i, v);
            } else if (i != previous) {//如果已经被访问过了，且和上一个节点不同，则是一个环形                                                                                                                                                                                                  一样,说明又绕回来了
                hasCycle = true;
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        Cycle cycle = new Cycle(graph);
        System.out.println(cycle.hasCycle);
    }

}
