package com.github.fanshuzaizai.algorithm.chapter4_graph.directed.weighted;

import com.github.fanshuzaizai.algorithm.chapter1_base.Bag;
import com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected.weighted.Edge;

/**
 * 加权无向图
 *
 * @author Jzy.
 * @date 2019/8/1 10:37
 */
public class DirectedWeightedEdgeGraph {

    private final int v;

    private int e;

    private Bag<DirectedWeightedEdge>[] connectArr;

    private Bag<DirectedWeightedEdge> edges = new Bag<>();

    public DirectedWeightedEdgeGraph(int v) {
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
     * @param edge
     */
    public void addEdge(DirectedWeightedEdge edge) {
        connectArr[edge.from()].add(edge);
        edges.add(edge);
        e++;
    }

    /**
     * 返回所有连接顶点v的顶点
     *
     * @param v
     * @return
     */
    public Iterable<DirectedWeightedEdge> connectedVertexes(int v) {
        return connectArr[v];
    }

    public Iterable<DirectedWeightedEdge> edges() {
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(v).append(" vertexSize, ").append(e).append(" edgeSize\n");
        for (int i = 0; i < v; i++) {
            builder.append(i).append(": ");
            for (DirectedWeightedEdge connectVertex : connectedVertexes(i)) {
                builder.append(connectVertex).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }


}
