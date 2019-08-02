package com.github.fanshuzaizai.algorithm.chapter4_graph.directed;

import com.github.fanshuzaizai.algorithm.chapter1_base.Queue;
import com.github.fanshuzaizai.algorithm.chapter1_base.Stack;
import com.github.fanshuzaizai.algorithm.chapter4_graph.directed.weighted.DirectedWeightedEdge;
import com.github.fanshuzaizai.algorithm.chapter4_graph.directed.weighted.DirectedWeightedEdgeGraph;

/**
 * 深度优先排序
 * <p>
 * 必须是有序无环图
 *
 * @author Jzy.
 * @date 2019/7/30 16:16
 */
public class DepthFirstOrder {

    /**
     * 是否已经经过顶点
     */
    private boolean[] marked;

    //dfs调用顺序
    private Queue<Integer> pre;

    //遍历完成后的顺序
    private Queue<Integer> post;

    private Stack<Integer> reversePost;

    public DepthFirstOrder(DirectedGraph graph) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        int vertexSize = graph.vertexSize();
        marked = new boolean[vertexSize];
        for (int i = 0; i < vertexSize; i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    /**
     * 加权有向图
     * @param graph
     */
    public DepthFirstOrder(DirectedWeightedEdgeGraph graph) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        int vertexSize = graph.vertexSize();
        marked = new boolean[vertexSize];
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
    private void dfs(DirectedGraph graph, int v) {
        pre.enqueue(v);

        marked[v] = true;
        Iterable<Integer> connectedVertexes = graph.connectedVertexes(v);
        //递归访问其他与之联通的顶点
        for (Integer i : connectedVertexes) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }

        post.enqueue(v);

        //拓扑排序用的这一种，本条链的最后一个元素先放入stack中，取出来的时候就是最后一个
        reversePost.push(v);
    }

    //加权有向图
    private void dfs(DirectedWeightedEdgeGraph graph, int v) {
        pre.enqueue(v);

        marked[v] = true;
        Iterable<DirectedWeightedEdge> connectedVertexes = graph.connectedVertexes(v);
        //递归访问其他与之联通的顶点
        for (DirectedWeightedEdge edge : connectedVertexes) {
            int to = edge.to();
            if (!marked[to]) {
                dfs(graph, to);
            }
        }

        post.enqueue(v);

        //拓扑排序用的这一种，本条链的最后一个元素先放入stack中，取出来的时候就是最后一个
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

}
