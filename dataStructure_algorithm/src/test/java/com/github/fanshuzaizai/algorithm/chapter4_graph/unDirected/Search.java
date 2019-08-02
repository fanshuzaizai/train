package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected;

/**
 * @author Jzy.
 * @date 2019/7/24 14:51
 */
public interface Search {

    /**
     * source 和 v 是否是联通的
     *
     * @param v
     * @return
     */
    boolean connected(int v);


    /**
     * 和 source 联通的 所有顶点的数量
     *
     * @return
     */
    int connectedCount();

}
