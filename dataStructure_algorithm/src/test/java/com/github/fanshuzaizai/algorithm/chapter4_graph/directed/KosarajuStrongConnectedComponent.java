package com.github.fanshuzaizai.algorithm.chapter4_graph.directed;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 连通分量
 * 使用深度优先法
 *
 * @author Jzy.
 * @date 2019/7/25 17:36
 */
public class KosarajuStrongConnectedComponent {

    private final DirectedGraph graph;

    private boolean[] marked;

    private int[] ids;

    private int count;

    public KosarajuStrongConnectedComponent(DirectedGraph graph) {
        this.graph = graph;
        int vertexSize = graph.vertexSize();
        marked = new boolean[vertexSize];
        ids = new int[vertexSize];

        DepthFirstOrder dfo = new DepthFirstOrder(graph.reverse());
        Iterable<Integer> integers = dfo.reversePost();

        /*
            G 中已知s -> v，假设 s <=> v

            1.访问s的时候一定会访问到v
                反证：如果之前访问到 v ，因为是强连通的（可以互相访问）,那么必定会递归访问到 s，所以也就不可能再次访问 s

            2.G 中一定存在 v -> s ，那么G® 中一定存在 s -> v
                结论：如果 G® 中 s->v ，那么这两个顶点是强连通的

            3.在 G® 的后逆顺序（reversePost）迭代中，一定先访问 s，再访问 v，那么存在2种可能
                1：v 是在访问 s 的递归之前被访问过的。根据 1. 说明这条不成立，说明当前 s !-> v， 则 s !<=> v
                2. v 是在访问 s 的递归中被访问的，说明 s -> v，则 s <=> v

         */
        for (Integer i : integers) {
            if (!marked[i]) {
                count++;
                dfs(graph, i);
            }
        }
    }

    private void dfs(DirectedGraph graph, int v) {

        marked[v] = true;
        //当前cc所处的下标
        ids[v] = count - 1;
        Iterable<Integer> connectedVertexes = graph.connectedVertexes(v);
        //递归访问其他与之联通的顶点
        for (Integer i : connectedVertexes) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    /**
     * 是否连通
     *
     * @param x
     * @param y
     * @return
     */
    public boolean strongConnected(int x, int y) {
        return ids[x] == ids[y];
    }

    /**
     * 连通分量的数量
     *
     * @return
     */
    public int countCC() {
        return count;
    }

    /**
     * 所处的连通分量 [0,(countCC-1)]
     *
     * @param v
     * @return
     */
    public int indexCC(int v) {
        return ids[v];
    }

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\tinyDG.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);

        Integer v = Integer.valueOf(lines.get(0));
        DirectedGraph graph = new DirectedGraph(v);
        lines.stream().skip(2).forEach(e -> {
            if (StringUtils.isNotBlank(e)) {
                String[] split = e.trim().split("\\s+");
                graph.addEdge(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
            }
        });

        KosarajuStrongConnectedComponent kscc = new KosarajuStrongConnectedComponent(graph);
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < kscc.ids.length; i++) {
            int t = i;
            map.compute(kscc.ids[i], (key, val) -> {
                if (val == null) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(t);
                    return list;
                } else {
                    val.add(t);
                    return val;
                }
            });
        }

        System.out.println(map);

        System.out.println(kscc.count);
    }

}
