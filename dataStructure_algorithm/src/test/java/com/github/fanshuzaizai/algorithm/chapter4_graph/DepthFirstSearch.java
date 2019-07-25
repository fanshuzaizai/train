package com.github.fanshuzaizai.algorithm.chapter4_graph;

import java.util.Stack;

/**
 * @author Jzy.
 * @date 2019/7/24 15:02
 */
public class DepthFirstSearch implements Search, Path {

    private final Graph graph;

    private final int source;

    /**
     * 和其他顶点是否联通
     */
    private boolean[] connectedArr;

    /**
     * 连接 下标顶点 时，最后一个连接的顶点
     */
    private int[] edgeTo;

    private int count;

    public DepthFirstSearch(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        int vertexSize = graph.vertexSize();
        connectedArr = new boolean[vertexSize];
        edgeTo = new int[vertexSize];
        //第一个顶点，自联通
        dfs(graph, source);
    }

    /**
     * 标记联通
     *
     * @param graph
     * @param v
     */
    private void dfs(Graph graph, int v) {
        connectedArr[v] = true;
        count++;
        Iterable<Integer> connectedVertexes = graph.connectedVertexes(v);
        //递归访问其他与之联通的顶点
        for (Integer i : connectedVertexes) {
            if (!connectedArr[i]) {
                //如果顶点i还没有被访问过，则连接i的顶点就是当前的v
                edgeTo[i] = v;
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

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!connected(v)) {
            return null;
        }

        Stack<Integer> stack = new Stack<>();

//        stack.push(v);
        do {
            v = edgeTo[v];
            stack.push(v);
        } while (v != source);
        return stack;
    }
}
