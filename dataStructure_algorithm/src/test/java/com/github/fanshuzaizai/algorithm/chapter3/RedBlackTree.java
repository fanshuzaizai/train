package com.github.fanshuzaizai.algorithm.chapter3;

import java.util.HashMap;

/**
 * 类似 平衡查找树
 * 结构是向上增长的
 * 根节点是黑色的
 * 红色节点和父节点表示平级
 * 红色节点只能出现在左侧 && 两个连续的节点不能都为红色
 *
 * @author Jzy.
 * @date 2019/7/19 11:17
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public void put(K k, V v) {
        root = put(k, v, root);
        root.color = BLACK;
    }

    /**
     * 在当前节点下插入一个节点
     *
     * @param k
     * @param v
     * @param n
     */
    private Node put(K k, V v, Node n) {
        if (n == null) {
            return new Node(k, v, 1, RED, null, null);
        }

        int i = k.compareTo(n.k);
        if (i > 0) {//大于，插入左边
            n.right = put(k, v, n.right);
        } else if (i < 0) {//小于，插入右边
            n.left = put(k, v, n.left);
        } else {//相等，直接替换
            n.v = v;
            return n;
        }

        //右节点是红色
        if (!isRed(n.left) && isRed(n.right)) {
            n = rotateLeft(n);
        }
        //连续两个左节点是红色
        if (isRed(n.left) && isRed(n.left.left)) {
            n = rotateRight(n);
        }
        //两个子节点都是红色
        if (isRed(n.left) && isRed(n.right)) {
            flipColor(n);
        }
        calculateSize(n);
        return n;
    }

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        return n == null ? 0 : n.size;
    }

    private boolean isRed(Node n) {
        return n != null && n.color == RED;
    }

    /**
     * 将红色的右子节点旋转成左边
     *
     * @param current
     */
    private Node rotateLeft(Node current) {
        Node originRight;
        if (current == null || !isRed(originRight = current.right)) {
            throw new IllegalArgumentException();
        }

        //备份当前节点
        Node original = current;
        //将右子节点置为当前节点
        current = originRight;
        //将原右子节点的左子节点置为当前左子节点的右子节点
        original.right = current.left;
        //将原当前节点置为左子节点
        current.left = original;

        current.color = original.color;

        //左子节点置为红色
        original.color = RED;

        //size不变，因为节点数量没有发生变化
        current.size = original.size;
        calculateSize(original);
        return current;
    }

    /**
     * 将红色的左子节点旋转成右边
     *
     * @param current
     */
    private Node rotateRight(Node current) {
        Node originLeft;
        if (current == null || !isRed(originLeft = current.left)) {
            throw new IllegalArgumentException();
        }

        //备份当前节点
        Node original = current;

        current = originLeft;

        original.left = current.right;

        current.right = original;

        current.color = original.color;

        original.color = RED;
        //size不变，因为节点数量没有发生变化
        current.size = original.size;
        calculateSize(original);
        return current;
    }

    /**
     * 将两个子节点都是红色的节点转换成：自己是红色，两个子节点是黑色
     *
     * @param n
     */
    public void flipColor(Node n) {
        if (n == null || !isRed(n.left) || !isRed(n.right)) {
            throw new IllegalArgumentException();
        }
        n.color = RED;
        n.left.color = BLACK;
        n.right.color = BLACK;
    }

    public void delMin(K k) {
        if (root == null) {
            return;
        }
        //将root节点转成一个普通节点的规则
        if (isRed(root.left) && isRed(root.right)) {
            root.color = BLACK;
        }
        root = delMin(k, root);

        if (root != null) {
            root.color = BLACK;
        }
    }

    public Node delMin(K k, Node n) {
        if (n.left == null) {
            return null;
        }
        if (!isRed(n.left) && !isRed(n.left.left)) {

        }

        return n;
    }

    private


    private void calculateSize(Node n) {
        n.size = size(n.left) + size(n.right) + 1;
    }


    private class Node {
        K k;
        V v;
        int size;
        Node left, right;
        //当前节点的颜色
        boolean color;

        private Node(K k, V v, int size, boolean color, Node left, Node right) {
            this.k = k;
            this.v = v;
            this.size = size;
            this.color = color;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "\n\tNode{" +
                    "k=" + k +
                    ", v=" + v +
                    ", size=" + size +
                    ", color=" + color +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RedBlackTree{" +
                "root=" + root +
                '}';
    }

    public static void main(String[] args) {


        RedBlackTree<String, String> tree1 = new RedBlackTree<>();
        tree1.put("A", "A");
        tree1.put("C", "C");
        tree1.put("E", "E");
        tree1.put("H", "H");
        tree1.put("L", "L");
        tree1.put("M", "M");
        tree1.put("P", "P");
        tree1.put("R", "R");
        tree1.put("S", "S");
        tree1.put("X", "X");
        tree1.put("X", "XX");

        System.out.println(tree1);


        if (true) {
            return;
        }

        RedBlackTree<String, String> tree = new RedBlackTree<>();
        RedBlackTree.Node a = tree.new Node("A", "A", 1, BLACK, null, null);
        RedBlackTree.Node b = tree.new Node("B", "B", 5, BLACK, null, null);
        RedBlackTree.Node c = tree.new Node("C", "C", 1, BLACK, null, null);
        RedBlackTree.Node d = tree.new Node("D", "D", 3, RED, null, null);
        RedBlackTree.Node e = tree.new Node("E", "E", 1, BLACK, null, null);

        d.left = c;
        d.right = e;
        b.left = a;
        b.right = d;

        System.out.println(b);

        RedBlackTree.Node node = tree.rotateLeft(b);

        System.out.println(node);

        RedBlackTree.Node node2 = tree.rotateRight(node);

        System.out.println(node2);


    }
}
