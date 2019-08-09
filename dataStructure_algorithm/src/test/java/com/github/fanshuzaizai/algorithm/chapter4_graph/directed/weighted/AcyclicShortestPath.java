package com.github.fanshuzaizai.algorithm.chapter4_graph.directed.weighted;

import com.github.fanshuzaizai.algorithm.chapter1_base.Stack;
import com.github.fanshuzaizai.algorithm.chapter4_graph.directed.Topological;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 无环加权有向图 最短路径
 * 利用 拓扑排序
 *
 * @author Jzy.
 * @date 2019/8/2 17:15
 */
public class AcyclicShortestPath {

    private final DirectedWeightedEdgeGraph graph;

    private final int source;

    //到对应顶点的权重
    private double[] weightTo;

    //到对应顶点的前一个顶点
    private DirectedWeightedEdge[] edgeTo;

    private int _enqueue;
    private int _compare;

    public AcyclicShortestPath(DirectedWeightedEdgeGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        int vertexSize = graph.vertexSize();
        edgeTo = new DirectedWeightedEdge[vertexSize];
        weightTo = new double[vertexSize];

        //每个顶点到起点的距离 初始值无穷大
        for (int i = 0; i < vertexSize; i++) {
            weightTo[i] = Double.POSITIVE_INFINITY;
        }
        weightTo[source] = 0;

        //进行拓扑排序
        Topological topological = new Topological(graph);
        for (Integer integer : topological.order()) {
            System.out.print(integer + ",");
            //因为使用了拓扑排序，每条边只会放松一次
            relax(graph, integer);
        }
        System.out.println();
    }

    /**
     * 放松边
     *
     * @param graph
     * @param v
     */
    private void relax(DirectedWeightedEdgeGraph graph, int v) {
        Iterable<DirectedWeightedEdge> connectedVertexes = graph.connectedVertexes(v);

        for (DirectedWeightedEdge edge : connectedVertexes) {
            int to = edge.to();
            //通过当前边去目标顶点的权重
            double toWeightByCurrentEdge = weightTo[edge.from()] + edge.getWeight();
            //如果历史值大于当前值，则替换
            _compare++;
            if (weightTo[to] > toWeightByCurrentEdge) {
                weightTo[to] = toWeightByCurrentEdge;
                edgeTo[to] = edge;
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
        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\tinyEWDAG.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);

        DirectedWeightedEdgeGraph graph = new DirectedWeightedEdgeGraph(Integer.parseInt(lines.get(0)));

        lines.stream().skip(2).forEach(e -> {
            if (StringUtils.isNotBlank(e)) {
                String[] split = e.trim().split("\\s");
                graph.addEdge(new DirectedWeightedEdge(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Double.valueOf(split[2])));
            }
        });

        AcyclicShortestPath acyclicShortestPath = new AcyclicShortestPath(graph, 0);
        System.out.println(acyclicShortestPath._compare);//13
        System.out.println(acyclicShortestPath._enqueue);
        Iterable<DirectedWeightedEdge> iterable = acyclicShortestPath.pathTo(7);
        for (DirectedWeightedEdge edge : iterable) {
            System.out.println(edge);
        }
    }


}
