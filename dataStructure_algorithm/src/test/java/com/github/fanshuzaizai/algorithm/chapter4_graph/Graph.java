package com.github.fanshuzaizai.algorithm.chapter4_graph;

import com.github.fanshuzaizai.algorithm.chapter1_base.Bag;

/**
 * @author Jzy.
 * @date 2019/7/24 11:16
 */
public class Graph {

    private final int v;

    private int e;

    private Bag<Integer>[] connectArr;

    public Graph(int v) {
        this.v = v;
        connectArr = new Bag[v];
        for (int i = 0; i < v; i++) {
            connectArr[i] = new Bag<>();
        }
    }

    /**
     * 顶点数量
     *
     * @return
     */
    public int vertexSize() {
        return v;
    }

    /**
     * 边数量
     *
     * @return
     */
    public int edgeSize() {
        return e;
    }

    /**
     * 添加一个 v1 连接 v2 的边
     *
     * @param v1
     * @param v2
     */
    public void addEdge(int v1, int v2) {
        connectArr[v1].add(v2);
        connectArr[v2].add(v1);
        e++;
    }

    /**
     * 返回所有连接顶点v的顶点
     *
     * @param v
     * @return
     */
    public Iterable<Integer> connectedVertexes(int v) {
        return connectArr[v];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(v).append(" vertexSize, ").append(e).append(" edgeSize\n");
        for (int i = 0; i < v; i++) {
            builder.append(i).append(": ");
            for (Integer connectVertex : connectedVertexes(i)) {
                builder.append(connectVertex).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
