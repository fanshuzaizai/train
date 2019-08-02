package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected.weighted;

/**
 * @author Jzy.
 * @date 2019/8/1 10:28
 */
public class Edge implements Comparable<Edge> {

    private final int x;

    private final int y;

    private final double weight;

    public Edge(int x, int y, double weightht) {
        this.x = x;
        this.y = y;
        this.weight = weightht;
    }

    public int either() {
        return x;
    }

    public int other(int v) {
        return v == x ? y : x;
    }

    public double getWeight(){
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", x, y, weight);
    }
}
