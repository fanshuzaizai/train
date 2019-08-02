package com.github.fanshuzaizai.algorithm.chapter4_graph.directed;

import com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected.Search;

/**
 * 有向图
 * 深度优先
 *
 * @author Jzy.
 * @date 2019/7/24 15:02
 */
public class DirectedDFS implements Search {

    private DirectedGraph graph;

    /**
     * 和其他顶点是否联通
     */
    private boolean[] connectedArr;

    private int count;

    public DirectedDFS(DirectedGraph graph, int source) {
        this.graph = graph;
        int vertexSize = graph.vertexSize();
        connectedArr = new boolean[vertexSize];
        //第一个顶点，自联通
        dfs(graph, source);
    }

    public DirectedDFS(DirectedGraph graph, int[] sources) {
        this.graph = graph;
        int vertexSize = graph.vertexSize();
        connectedArr = new boolean[vertexSize];
        for (int i : sources) {
            if (!connectedArr[i]) {
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
        connectedArr[v] = true;
        count++;
        Iterable<Integer> connectedVertexes = graph.connectedVertexes(v);
        //递归访问其他与之联通的顶点
        for (Integer i : connectedVertexes) {
            if (!connectedArr[i]) {
                dfs(graph, i);
            }
        }
    }

    @Override
    public boolean connected(int v) {
        return connectedArr[v];
    }

    @Override
    public int connectedCount() {
        return count;
    }

}
