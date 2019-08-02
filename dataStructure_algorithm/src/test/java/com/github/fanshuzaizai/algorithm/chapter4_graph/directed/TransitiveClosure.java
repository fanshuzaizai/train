package com.github.fanshuzaizai.algorithm.chapter4_graph.directed;

/**
 * 传递闭包
 *
 * @author Jzy.
 * @date 2019/7/31 16:17
 */
public class TransitiveClosure {

    private DirectedDFS[] arr;

    public TransitiveClosure(DirectedGraph graph) {
        int size = graph.vertexSize();
        arr = new DirectedDFS[size];

        for (int i = 0; i < size; i++) {
            arr[i] = new DirectedDFS(graph, i);
        }

    }


    public boolean reachable(int x, int y) {
        return arr[x].connected(y);
    }

}
