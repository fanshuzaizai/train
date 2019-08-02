package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected;

/**
 * @author Jzy.
 * @date 2019/7/24 16:19
 */
public interface Path {


    /**
     * 到顶点 v 的路径
     *
     * @param v
     * @return
     */
    Iterable<Integer> pathTo(int v);
}
