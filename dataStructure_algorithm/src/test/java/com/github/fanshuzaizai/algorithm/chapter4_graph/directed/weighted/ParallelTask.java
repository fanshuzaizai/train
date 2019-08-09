package com.github.fanshuzaizai.algorithm.chapter4_graph.directed.weighted;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 加权有向图，并行执行任务
 *
 *
 * @author Jzy.
 * @date 2019/8/5 15:13
 */
public class ParallelTask {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\jobsPC.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);

        //任务数
        int N = Integer.parseInt(lines.get(0));
        DirectedWeightedEdgeGraph graph = new DirectedWeightedEdgeGraph(2 * N + 2);//最大的是 end= 2*n+1，此处传的是数量

        /*
        定义一个起点和终点
        在循环内，虚拟顶点最大为 N+(N-1)
         */
        int start = 2 * N;
        int end = 2 * N + 1;

        for (int i = 0; i < N; i++) {
            String[] split = lines.stream().skip(1).collect(Collectors.toList()).get(i).trim().split("\\s+");
            // i 即是当前顶点
            //创建一个虚拟顶点
            int virtualV = N + i;
            double duration = Double.valueOf(split[0]);
            /*
                   0   duration         0
            start ---> i ---> virtualV --->end
             */
            //创建一条当前顶点指向虚拟顶点的边，权重即是时间
            graph.addEdge(new DirectedWeightedEdge(i, virtualV, duration));
            //创建一条从起点指向当前顶点的边，权重为0
            graph.addEdge(new DirectedWeightedEdge(start, i, 0));
            //创建一条从虚拟顶点指向终点的边，权重为0
            graph.addEdge(new DirectedWeightedEdge(virtualV, end, 0));

            for (int j = 1; j < split.length; j++) {
                //创建一条从虚拟顶点指向下一个顶点（任务）的边，权重是0
                graph.addEdge(new DirectedWeightedEdge(virtualV, Integer.valueOf(split[j]), 0));
            }
        }

        //最长路径
        AcyclicLongestPath path = new AcyclicLongestPath(graph, start);

        System.out.println("start time:");

        //到各个顶点的最长长度即是任务花费最小时间
        for (int i = 0; i < N; i++) {
            System.out.println("执行 " + i + " 花费的时间是：" + path.weightTo(i));
        }

        System.out.println("end time：" + path.weightTo(end));

    }

}
