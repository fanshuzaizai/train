package com.github.fanshuzaizai.algorithm.chapter4_graph.directed.weighted;

import com.github.fanshuzaizai.algorithm.chapter1_base.Stack;
import com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected.weighted.PrimMST;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 无环加权有向图 最短路径
 * <p>
 * 类似{@link PrimMST}，每次加入一个连接树且权重最小的边
 *
 * @author Jzy.
 * @date 2019/8/2 14:42
 */
public class DijkstraShortestPath {

    private final DirectedWeightedEdgeGraph graph;

    private final int source;

    private PriorityQueue<DirectedWeightedEdge> pq = new PriorityQueue<>();

    //到对应顶点的权重
    private double[] weightTo;

    //到对应顶点的前一个顶点
    private DirectedWeightedEdge[] edgeTo;

    private int _enqueue;

    private int _compare;

    public DijkstraShortestPath(DirectedWeightedEdgeGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        int vertexSize = graph.vertexSize();
        edgeTo = new DirectedWeightedEdge[vertexSize];
        weightTo = new double[vertexSize];

        //初始值无穷大
        for (int i = 0; i < vertexSize; i++) {
            weightTo[i] = Double.POSITIVE_INFINITY;
        }
        weightTo[source] = 0;

        relax(graph, source);

        while (!pq.isEmpty()) {
            DirectedWeightedEdge minimum = pq.poll();
            relax(graph, minimum.to());
        }
    }

    /**
     * 松弛边
     *
     * @param graph
     * @param v
     */
    private void relax(DirectedWeightedEdgeGraph graph, int v) {
        Iterable<DirectedWeightedEdge> connectedVertexes = graph.connectedVertexes(v);

        for (DirectedWeightedEdge edge : connectedVertexes) {
            //不能判断 mark[v]，因为之前最短路径可能会被新路径推翻
            int to = edge.to();
            //通过当前边去目标顶点的权重
            double toWeightByCurrentEdge = weightTo[edge.from()] + edge.getWeight();
            //如果历史值大于当前值，则替换
            _compare++;
            if (weightTo[to] > toWeightByCurrentEdge) {
                weightTo[to] = toWeightByCurrentEdge;
                edgeTo[to] = edge;
                //删除已存在连接 to 的边
                pq.stream().filter(e -> e.to() == to).findAny().ifPresent(pq::remove);
                pq.add(edge);
                _enqueue++;
            }
        }
    }

    public double weightTo(int v) {
        return weightTo[v];
    }

    public Iterable<DirectedWeightedEdge> pathTo(int v) {
        Stack<DirectedWeightedEdge> stack = new Stack<>();
        if (!hasPathTo(v)) {
            return stack;
        }
        DirectedWeightedEdge edge = edgeTo[v];

        while (edge.from() != source) {
            stack.push(edge);
            edge = edgeTo[edge.from()];
        }
        stack.push(edge);
        return stack;
    }

    public boolean hasPathTo(int v) {
        return weightTo[v] < Double.POSITIVE_INFINITY;
    }

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\tinyEWDn.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);

        DirectedWeightedEdgeGraph graph = new DirectedWeightedEdgeGraph(Integer.parseInt(lines.get(0)));

        lines.stream().skip(2).forEach(e -> {
            if (StringUtils.isNotBlank(e)) {
                String[] split = e.trim().split("\\s+");
                graph.addEdge(new DirectedWeightedEdge(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Double.valueOf(split[2])));
            }
        });
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph, 0);
        System.out.println(dijkstraShortestPath._enqueue);
        System.out.println(dijkstraShortestPath._compare);
        Iterable<DirectedWeightedEdge> iterable = dijkstraShortestPath.pathTo(7);
        for (DirectedWeightedEdge edge : iterable) {
            System.out.println(edge);
        }

    }


}
