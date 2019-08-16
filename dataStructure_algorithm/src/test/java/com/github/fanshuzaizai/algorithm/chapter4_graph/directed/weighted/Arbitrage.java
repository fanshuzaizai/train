package com.github.fanshuzaizai.algorithm.chapter4_graph.directed.weighted;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

/**
 * 套汇
 *
 * @author Jzy.
 * @date 2019/8/13 14:07
 */
public class Arbitrage {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\rates.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);

        DirectedWeightedEdgeGraph graph = new DirectedWeightedEdgeGraph(Integer.parseInt(lines.get(0)));
        HashMap<Integer, String> names = new HashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] split = lines.get(i).trim().split("\\s+");
            names.put(i - 1, split[0]);
            for (int j = 1; j < split.length; j++) {
                if (i != j) {
                    graph.addEdge(new DirectedWeightedEdge(i - 1, j - 1, -Math.log(Double.valueOf(split[j]))));
                }
            }
        }
        System.out.println(graph);
        System.out.println(names);
        BellmanFordShortestPath path = new BellmanFordShortestPath(graph, 0);
        if (path.hasNegativeCycle()) {
            for (DirectedWeightedEdge edge : path.negativeCycle()) {
                System.out.println(names.get(edge.from()) + "->" + names.get(edge.to()) + "=" + edge.getWeight());
            }
        }

    }

}
