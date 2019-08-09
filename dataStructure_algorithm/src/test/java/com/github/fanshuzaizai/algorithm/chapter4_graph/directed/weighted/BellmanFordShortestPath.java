package com.github.fanshuzaizai.algorithm.chapter4_graph.directed.weighted;

import com.github.fanshuzaizai.algorithm.chapter1_base.Queue;
import com.github.fanshuzaizai.algorithm.chapter1_base.Stack;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 允许环和负权重的存在，但不允许负权重环
 * <p>
 * 负权重环：
 * a->b     1
 * b->c     -0.5
 * c->a     -0.6
 * 每绕一圈，权重都会减小，这样会死循环下去
 *
 * @author Jzy.
 * @date 2019/8/5 17:29
 */
public class BellmanFordShortestPath {

    //到个顶点的权重
    private double[] weightTo;

    private DirectedWeightedEdge[] edgeTo;

    //先进先出队列
    private Queue<Integer> queue = new Queue<>();

    //是否在队列中
    private boolean[] onQueue;

    private int relaxTime;

    private boolean haveNegativeCycle;

    private final int source;

    public BellmanFordShortestPath(DirectedWeightedEdgeGraph graph, int source) {
        this.source = source;
        int vertexSize = graph.vertexSize();
        onQueue = new boolean[vertexSize];
        weightTo = new double[vertexSize];
        edgeTo = new DirectedWeightedEdge[vertexSize];

        for (int i = 0; i < vertexSize; i++) {
            weightTo[i] = Double.POSITIVE_INFINITY;
        }

        weightTo[source] = 0;
        queue.enqueue(source);
        onQueue[source] = true;

        while (!queue.isEmpty() && !haveNegativeCycle) {
            Integer dequeue = queue.dequeue();
            onQueue[dequeue] = false;
            relax(graph, dequeue);
        }
    }

    /**
     * 类似广度优先，进行松弛
     * 并检测负权重环
     *
     * @param graph
     * @param v
     */
    private void relax(DirectedWeightedEdgeGraph graph, int v) {
        Iterable<DirectedWeightedEdge> edges = graph.connectedVertexes(v);
        for (DirectedWeightedEdge edge : edges) {
            int to = edge.to();
            double weight = edge.getWeight();
            double currentEdgeWeight = weightTo[v] + weight;

            //如果之前去目标的权重大于走当前路径的权重
            if (weightTo[to] > currentEdgeWeight) {
                edgeTo[to] = edge;
                weightTo[to] = currentEdgeWeight;
                if (!onQueue[to]) {
                    queue.enqueue(to);
                    onQueue[to] = true;
                }
            }

        }
         /*
            检测负权重换
            正常情况下，V个顶点，s -> t，最多松弛 V-1 次，超过这个数，说明有负权重环
             */
        if (++relaxTime > graph.vertexSize() - 1) {
            //说明有负权重换
            haveNegativeCycle = true;
        }
    }

    public boolean hasPathTo(int v) {
        return weightTo[v] < Double.POSITIVE_INFINITY;
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

        BellmanFordShortestPath path = new BellmanFordShortestPath(graph, 0);
        System.out.println(path.haveNegativeCycle);
        Iterable<DirectedWeightedEdge> directedWeightedEdges = path.pathTo(1);
        directedWeightedEdges.forEach(System.out::println);
    }
}
