package com.github.fanshuzaizai.algorithm.chapter4_graph;

/**
 * @author Jzy.
 * @date 2019/7/26 11:29
 */
public class TwoColor {

    private final Graph graph;

    /**
     * 是否已经经过顶点
     */
    private boolean[] marked;

    private boolean[] color;

    private boolean twoColorable = true;

    public TwoColor(Graph graph) {
        this.graph = graph;
        int vertexSize = graph.vertexSize();
        marked = new boolean[vertexSize];
        color = new boolean[vertexSize];

        for (int i = 0; i < vertexSize; i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    /**
     * 标记联通
     *
     * @param graph
     * @param v
     */
    private void dfs(Graph graph, int v) {
        marked[v] = true;
        Iterable<Integer> connectedVertexes = graph.connectedVertexes(v);
        //递归访问其他与之联通的顶点
        for (Integer i : connectedVertexes) {
            if (!marked[i]) {
                //颜色和v相反
                color[i] = !color[v];
                dfs(graph, i);
            } else if (color[v] == color[i]) {
                twoColorable = false;
            }
        }
    }

    public boolean twoColorable() {
        return twoColorable;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(0, 3);

        TwoColor twoColor = new TwoColor(graph);
        System.out.println(twoColor.twoColorable());
    }

}
