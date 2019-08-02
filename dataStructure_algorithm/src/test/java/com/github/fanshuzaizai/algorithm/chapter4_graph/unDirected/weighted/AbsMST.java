package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected.weighted;

import com.github.fanshuzaizai.algorithm.chapter1_base.Queue;

import java.math.BigDecimal;

/**
 * 无向图最小生成树
 *
 * @author Jzy.
 * @date 2019/8/1 10:41
 */
public abstract class AbsMST {

    private final EdgeWeightedGraph graph;

    protected final Queue<Edge> edges = new Queue<>();

    protected AbsMST(EdgeWeightedGraph graph) {
        this.graph = graph;
    }

    /**
     * 所含的边
     *
     * @return
     */
    public Iterable<Edge> edges() {
        return edges;
    }

    /**
     * 所含边的权重
     *
     * @return
     */
    public String weight() {//练习4.3.31
        BigDecimal weight = BigDecimal.ZERO;
        for (Edge edge : edges) {
            weight = weight.add(new BigDecimal(edge.getWeight() + ""));
        }
        return weight.toPlainString();
    }

}
