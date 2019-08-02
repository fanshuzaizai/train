package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected.weighted;

import com.github.fanshuzaizai.algorithm.chapter1_base.Bag;

/**
 * 加权无向图
 *
 * @author Jzy.
 * @date 2019/8/1 10:37
 */
public class EdgeWeightedGraph {

    private final int v;

    private int e;

    private Bag<Edge>[] connectArr;

    private Bag<Edge> edges = new Bag<>();

    public EdgeWeightedGraph(int v) {
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
    public void addEdge(Edge edge) {
        int x = edge.either();
        connectArr[x].add(edge);
        connectArr[edge.other(x)].add(edge);
        edges.add(edge);
        e++;
    }

    /**
     * 返回所有连接顶点v的顶点
     *
     * @param v
     * @return
     */
    public Iterable<Edge> connectedVertexes(int v) {
        return connectArr[v];
    }

    public Iterable<Edge> edges(){
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(v).append(" vertexSize, ").append(e).append(" edgeSize\n");
        for (int i = 0; i < v; i++) {
            builder.append(i).append(": ");
            for (Edge connectVertex : connectedVertexes(i)) {
                builder.append(connectVertex).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }


}
