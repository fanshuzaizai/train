package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected.weighted;

import com.github.fanshuzaizai.algorithm.chapter1_base.UnionFind_v2;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 按照边来处理
 * <p>
 * 最后由数个森林连接成一个树
 *
 * @author Jzy.
 * @date 2019/8/1 16:37
 */
public class KruskalMST extends AbsMST {

    //优先队列
    private PriorityQueue<Edge> priorityQueue;

    public KruskalMST(EdgeWeightedGraph graph) {
        super(graph);
        int vertexSize = graph.vertexSize();
        priorityQueue = new PriorityQueue<>(graph.edgeSize());
        //将所有的边放入优先队列中
        for (Edge edge : graph.edges()) {
            priorityQueue.add(edge);
        }

        UnionFind_v2 uf = new UnionFind_v2(vertexSize);

        while (!priorityQueue.isEmpty() && edges.size() < vertexSize - 1) {
            Edge edge = priorityQueue.poll();
            int v1 = edge.either();
            int v2 = edge.other(v1);

            if (uf.union(v1, v2)) {
                edges.enqueue(edge);
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

        KruskalMST lazyPrimMST = new KruskalMST(edgeWeightedGraph);
        for (Edge edge : lazyPrimMST.edges()) {
            System.out.println(edge);
        }
        System.out.println(lazyPrimMST.weight());
    }

}
