package com.github.fanshuzaizai.algorithm.chapter4_graph.directed;

import com.github.fanshuzaizai.algorithm.chapter1_base.Bag;

/**
 * 有向图
 *
 * @author Jzy.
 * @date 2019/7/30 14:19
 */
public class DirectedGraph {
    private final int v;

    private int e;

    private Bag<Integer>[] connectArr;

    public DirectedGraph(int v) {
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
     * 添加一个 v1 -> v2 的边
     *
     * @param v1
     * @param v2
     */
    public void addEdge(int v1, int v2) {
        connectArr[v1].add(v2);
//        connectArr[v2].add(v1);
        e++;
    }

    /**
     * 返回所有 v 指向(->)的顶点
     *
     * @param v
     * @return
     */
    public Iterable<Integer> connectedVertexes(int v) {
        return connectArr[v];
    }

    public DirectedGraph reverse() {
        DirectedGraph directedGraph = new DirectedGraph(v);
        for (int i = 0; i < connectArr.length; i++) {
            Bag<Integer> bag = connectArr[i];
            for (Integer e : bag) {
                directedGraph.addEdge(e, i);
            }
        }

        return directedGraph;
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
