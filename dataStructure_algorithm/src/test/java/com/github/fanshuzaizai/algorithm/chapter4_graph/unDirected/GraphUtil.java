package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected;

/**
 * @author Jzy.
 * @date 2019/7/24 11:27
 */
public class GraphUtil {

    /**
     * 计算v的度数
     *
     * @param g
     * @param v
     * @return
     */
    public static int degree(Graph g, int v) {
        return (int) g.connectedVertexes(v).spliterator().estimateSize();
    }

    /**
     * 返回一个图中最大度数的顶点
     *
     * @param g
     * @return
     */
    public static int maxDegree(Graph g) {
        int maxDegree = 0;
        for (int i = 0; i < g.vertexSize(); i++) {
            maxDegree = Math.max(degree(g, i), maxDegree);
        }
        return maxDegree;
    }

    /**
     * 平均度数
     *
     * @param g
     * @return
     */
    public static int avgDegree(Graph g) {
        return g.edgeSize() * 2 / g.vertexSize();
    }

    /**
     * 自环个数
     *
     * @param g
     * @return
     */
    public static int selfLoop(Graph g) {
        int count = 0;
        for (int i = 0; i < g.vertexSize(); i++) {
            for (Integer integer : g.connectedVertexes(i)) {
                if (integer == i) {
                    count++;
                }
            }
        }
        return count;
    }

}
