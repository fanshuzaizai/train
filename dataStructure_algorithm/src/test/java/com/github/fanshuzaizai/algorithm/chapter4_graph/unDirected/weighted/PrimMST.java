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
 * 即时实现
 *
 * @author Jzy.
 * @date 2019/8/1 11:31
 */
public class PrimMST extends AbsMST {

    //优先队列
    private PriorityQueue<Edge> priorityQueue;

    //每个顶点距离树最近的边
    private Edge[] edgeTo;

    //树中是否加入了 顶点
    private boolean[] marked;

    public PrimMST(EdgeWeightedGraph graph) {
        super(graph);
        int vertexSize = graph.vertexSize();
        marked = new boolean[vertexSize];
        edgeTo = new Edge[vertexSize];
        priorityQueue = new PriorityQueue<>(graph.edgeSize());

        //从 顶点0 开始生成树
        visit(graph, 0);
        while (!priorityQueue.isEmpty()) {
            //取出一条最小的边，直接放入树中
            Edge edge = priorityQueue.poll();
            edges.enqueue(edge);

            int v1 = edge.either();
            int v2 = edge.other(v1);
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
            //如果顶点没有加入到树中
            if (!marked[other]) {
                //即时计算
                // 和已保存的到树最小的边进行比较
                Edge oldEdge = edgeTo[other];
                //不存在
                if (oldEdge == null) {
                    edgeTo[other] = edge;
                    priorityQueue.add(edge);
                } else if (edge.compareTo(oldEdge) < 0) {//替换边
                    edgeTo[other] = edge;
                    priorityQueue.remove(oldEdge);
                    priorityQueue.add(edge);
                }
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

        PrimMST primMST = new PrimMST(edgeWeightedGraph);
        for (Edge edge : primMST.edges()) {
            System.out.println(edge);
        }
        System.out.println(primMST.weight());
    }
}
