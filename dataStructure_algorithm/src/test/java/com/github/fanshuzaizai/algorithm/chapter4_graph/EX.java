package com.github.fanshuzaizai.algorithm.chapter4_graph;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Jzy.
 * @date 2019/7/24 17:13
 */
@RunWith(JUnit4.class)
public class EX {

    @Test
    public void test1() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\tinyG.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);

        Integer v = Integer.valueOf(lines.get(0));
        Graph graph = new Graph(v);
        lines.stream().skip(2).forEach(e -> {
            String[] split = e.split("\\s+");
            graph.addEdge(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
        });

        System.out.println(graph);

        DepthFirstSearch dfs = new DepthFirstSearch(graph, 5);
        for (Integer i = 0; i < v; i++) {
            System.out.println(dfs.pathTo(i));
        }
        System.out.println("=============");
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 5);
        for (Integer i = 0; i < v; i++) {
            System.out.println(bfs.pathTo(i));
        }

        ConnectedComponent cc = new ConnectedComponent(graph);
        System.out.println(cc.countCC());
        System.out.println(cc.connected(0, 10));
        System.out.println(cc.connected(12, 8));
        System.out.println(cc.connected(9, 12));
        System.out.println(cc.connected(2, 6));
    }

}
