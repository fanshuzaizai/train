package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected;

/**
 * 连通分量
 * 使用深度优先法
 *
 * @author Jzy.
 * @date 2019/7/25 17:36
 */
public class ConnectedComponent {

    private final Graph graph;

    private boolean[] marked;

    private int[] ids;

    private int count;

    public ConnectedComponent(Graph graph) {
        this.graph = graph;
        int vertexSize = graph.vertexSize();
        marked = new boolean[vertexSize];
        ids = new int[vertexSize];

        for (int i = 0; i < vertexSize; i++) {
            //每次都进入一个新的cc
            if (!marked[i]) {
                count++;
                dfs(graph, i);
            }
        }

    }

    private void dfs(Graph graph, int v) {

        marked[v] = true;
        //当前cc所处的下标
        ids[v] = count - 1;
        Iterable<Integer> connectedVertexes = graph.connectedVertexes(v);
        //递归访问其他与之联通的顶点
        for (Integer i : connectedVertexes) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    /**
     * 是否连通
     *
     * @param x
     * @param y
     * @return
     */
    public boolean connected(int x, int y) {
        return ids[x] == ids[y];
    }

    /**
     * 连通分量的数量
     *
     * @return
     */
    public int countCC() {
        return count;
    }

    /**
     * 所处的连通分量 [0,(countCC-1)]
     *
     * @param v
     * @return
     */
    public int indexCC(int v) {
        return ids[v];
    }

}
