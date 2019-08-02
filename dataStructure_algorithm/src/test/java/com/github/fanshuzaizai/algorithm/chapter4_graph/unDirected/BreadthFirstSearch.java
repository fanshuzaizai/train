package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected;

import com.github.fanshuzaizai.algorithm.chapter1_base.Queue;

import java.util.Collections;
import java.util.Stack;

/**
 * 广度优先搜索
 * 求到个顶点的最短路径
 *
 * @author Jzy.
 * @date 2019/7/25 16:14
 */
public class BreadthFirstSearch implements Search, Path {

    private final Graph graph;

    private final int source;

    /**
     * 是否已找到连接对应下标的顶点的最短路径
     */
    private boolean[] shortestArr;

    /**
     * 连接 下标顶点 时，最后一个连接的顶点
     */
    private int[] edgeTo;

    private int count;

    public BreadthFirstSearch(Graph graph, int source) {
        this.graph = graph;
        this.source = source;

        int vertexSize = graph.vertexSize();
        shortestArr = new boolean[vertexSize];
        edgeTo = new int[vertexSize];
        bfs(graph, source);
    }

    private void bfs(Graph graph, int v) {
        shortestArr[v] = true;
        count++;
        //需要待检查的队列
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(v);

        while (!queue.isEmpty()) {
            //从队列中取出一个顶点
            Integer i = queue.dequeue();
            //找到所有连接的顶点
            Iterable<Integer> connectedVertexes = graph.connectedVertexes(i);
            connectedVertexes.forEach(e -> {
                //从未经过这个顶点
                if (!shortestArr[e]) {
                    //到达这个顶点的最短距离设为true
                    shortestArr[e] = true;
                    //到达这个顶点的最近顶点是i
                    edgeTo[e] = i;
                    //放入待检查的队列
                    //因为队列是先进先出，所以会等和i平级的顶点先遍历完，才会再进行下一级e的查找
                    queue.enqueue(e);
                }
            });
        }
    }


    @Override
    public boolean connected(int v) {
        return shortestArr[v];
    }

    @Override
    public int connectedCount() {
        return count;
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!connected(v)) {
            return Collections.emptyList();
        }

        Stack<Integer> stack = new Stack<>();

        do {
            v = edgeTo[v];
            stack.push(v);
        } while (v != source);
        return stack;
    }
}
