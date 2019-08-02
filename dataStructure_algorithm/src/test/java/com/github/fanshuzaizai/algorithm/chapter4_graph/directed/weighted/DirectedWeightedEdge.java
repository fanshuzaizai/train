package com.github.fanshuzaizai.algorithm.chapter4_graph.directed.weighted;

/**
 * @author Jzy.
 * @date 2019/8/2 9:53
 */
public class DirectedWeightedEdge implements Comparable<DirectedWeightedEdge> {

    private final int from;

    private final int to;

    private final double weight;

    public DirectedWeightedEdge(int from, int to, double weightht) {
        this.from = from;
        this.to = to;
        this.weight = weightht;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public double getWeight(){
        return weight;
    }

    @Override
    public int compareTo(DirectedWeightedEdge o) {
        return Double.compare(weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", from, to, weight);
    }

}
