package com.github.fanshuzaizai.algorithm.chapter4_graph.directed;

import com.github.fanshuzaizai.algorithm.chapter4_graph.directed.weighted.DirectedWeightedEdgeGraph;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 拓扑排序
 *
 * @author Jzy.
 * @date 2019/7/30 16:31
 */
public class Topological {

    private Iterable<Integer> order;

    private boolean hasCycle;

    public Topological(DirectedGraph graph) {
        DirectedCycle cycle = new DirectedCycle(graph);
        hasCycle = cycle.hasCycle();
        if (!hasCycle) {
            DepthFirstOrder dfo = new DepthFirstOrder(graph);
            order = dfo.reversePost();
        }
    }

    /**
     * 加权有向图
     *
     * @param graph
     */
    public Topological(DirectedWeightedEdgeGraph graph) {
        //todo 检测是否是无环图
        DepthFirstOrder dfo = new DepthFirstOrder(graph);
        order = dfo.reversePost();
    }

    public boolean isDAG() {
        return !hasCycle;
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\jobs.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);


        DirectedSymbolGraph symbolGraph = new DirectedSymbolGraph(lines, "/");
        Topological topological = new Topological(symbolGraph.graph());
        for (Integer integer : topological.order) {
            System.out.println(symbolGraph.name(integer));
        }

    }

}
