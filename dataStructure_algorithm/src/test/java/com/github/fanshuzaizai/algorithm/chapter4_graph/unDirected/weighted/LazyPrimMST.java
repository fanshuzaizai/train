package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected.weighted;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 从一个顶点逐渐扩展
 * <p>
 * 延时实现
 *
 * @author Jzy.
 * @date 2019/8/1 11:31
 */
public class LazyPrimMST extends AbsMST {

    //优先队列
    private PriorityQueue<Edge> priorityQueue;

    //树中是否加入了 顶点
    private boolean[] marked;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        super(graph);

        marked = new boolean[graph.vertexSize()];
        priorityQueue = new PriorityQueue<>(graph.edgeSize());

        //从 顶点0 开始生成树
        visit(graph, 0);
        while (!priorityQueue.isEmpty()) {
            //取出一条最小的
            Edge edge = priorityQueue.poll();
            int v1 = edge.either();
            int v2 = edge.other(v1);
            //这条边失效了
            if (marked[v1] && marked[v2]) {
                continue;
            }
            //放入树中
            edges.enqueue(edge);
            visit(graph, marked[v1] ? v2 : v1);
        }

    }

    /**
     * 将最新加入树的顶点的边加入优先队列中
     *
     * @param graph
     * @param v
     */
    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        Iterable<Edge> edges = graph.connectedVertexes(v);
        //遍历所有的边
        for (Edge edge : edges) {
            int other = edge.other(v);
            if (!marked[other]) {
                //如果相连的顶点没有被加入树中，则将这条边加入优先队列中
                priorityQueue.add(edge);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\tinyEWG.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);

        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(Integer.parseInt(lines.get(0)));

        lines.stream().skip(2).forEach(e -> {
            if (StringUtils.isNotBlank(e)) {
                String[] split = e.trim().split("\\s");
                edgeWeightedGraph.addEdge(new Edge(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Double.valueOf(split[2])));
            }
        });

        LazyPrimMST lazyPrimMST = new LazyPrimMST(edgeWeightedGraph);
        for (Edge edge : lazyPrimMST.edges()) {
            System.out.println(edge);
        }
        System.out.println( lazyPrimMST.weight());
    }
}
