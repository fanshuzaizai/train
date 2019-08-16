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
import java.util.Map;
import java.util.TreeMap;

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

    private final DirectedWeightedEdgeGraph graph;

    //到个顶点的权重
    private double[] weightTo;

    private DirectedWeightedEdge[] edgeTo;

    //是否在队列中
    private boolean[] onQueue;

    private final int source;

    private Queue<DirectedWeightedEdge> negativeCycle;


    // 轮数->[需要放松的顶点]
    private TreeMap<Integer, Queue<Integer>> roundMap = new TreeMap<>();

    public BellmanFordShortestPath(DirectedWeightedEdgeGraph graph, int source) {
        this.source = source;
        this.graph = graph;
        int vertexSize = graph.vertexSize();
        onQueue = new boolean[vertexSize];
        weightTo = new double[vertexSize];
        edgeTo = new DirectedWeightedEdge[vertexSize];

        for (int i = 0; i < vertexSize; i++) {
            weightTo[i] = Double.POSITIVE_INFINITY;
        }

        weightTo[source] = 0;
        onQueue[source] = true;

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(source);
        //第一轮
        roundMap.put(1, queue);

        while (!this.roundMap.isEmpty() && !hasNegativeCycle()) {
            Map.Entry<Integer, Queue<Integer>> entry = roundMap.firstEntry();
            Queue<Integer> value = entry.getValue();
            Integer dequeue = value.dequeue();
            //如果队列空了，则删除队列
            if (value.isEmpty()) {
                roundMap.pollFirstEntry();
            }
            onQueue[dequeue] = false;
            relax(graph, dequeue, entry.getKey());
        }
    }

    /**
     * 类似广度优先，进行松弛
     * 并检测负权重环
     *
     * @param graph
     * @param v
     * @param round 第几轮
     */
    private void relax(DirectedWeightedEdgeGraph graph, int v, int round) {

        /*
        检测负权重换
        正常情况下，V个顶点，任意顶点s -> t，最多经过 v个顶点，也就是松弛v轮，超过这个数，说明有负权重环
         */
//        System.out.println(round);
        if (round > graph.vertexSize()) {
            findNegativeCycle();
        }

        for (DirectedWeightedEdge edge : graph.connectedVertexes(v)) {
            int to = edge.to();
            double currentEdgeWeight = weightTo[v] + edge.getWeight();

            //如果之前去目标的权重大于走当前路径的权重
            if (weightTo[to] > currentEdgeWeight) {
                edgeTo[to] = edge;
                weightTo[to] = currentEdgeWeight;
                if (!onQueue[to]) {
                    //把需要放松的节点结果放入下一轮中
                    roundMap.compute(round + 1, (key, value) -> {
                        if (value != null) {
                            value.enqueue(to);
                            return value;
                        } else {
                            Queue<Integer> integers = new Queue<>();
                            integers.enqueue(to);
                            return integers;
                        }
                    });
                    onQueue[to] = true;
                }
            }

        }
    }

    public boolean hasNegativeCycle() {
        return negativeCycle != null;
    }

    public Queue<DirectedWeightedEdge> negativeCycle() {
        return negativeCycle;
    }

    private void findNegativeCycle() {
        NegativeDirectedCycle cycle = new NegativeDirectedCycle(this.graph);
        if (!cycle.hasCycle()) {
            throw new RuntimeException("错误");
        }
        this.negativeCycle = cycle.cycle();
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
        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\tinyEWDnc.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);

        DirectedWeightedEdgeGraph graph = new DirectedWeightedEdgeGraph(Integer.parseInt(lines.get(0)));

        lines.stream().skip(2).forEach(e -> {
            if (StringUtils.isNotBlank(e)) {
                String[] split = e.trim().split("\\s+");
                graph.addEdge(new DirectedWeightedEdge(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Double.valueOf(split[2])));
            }
        });

        BellmanFordShortestPath path = new BellmanFordShortestPath(graph, 0);
        boolean hasNegativeCycle = path.hasNegativeCycle();
        System.out.println(hasNegativeCycle);
        if (hasNegativeCycle) {
            System.out.println("负权重环：");
            path.negativeCycle().forEach(System.out::println);
        } else {
            System.out.println("到达顶点 1 的路径：");
            path.pathTo(1).forEach(System.out::println);
        }
    }
}
