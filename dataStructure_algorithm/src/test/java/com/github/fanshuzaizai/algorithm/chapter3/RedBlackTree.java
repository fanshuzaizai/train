package com.github.fanshuzaizai.algorithm.chapter3;

/**
 * 类似 平衡查找树
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

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        return n == null ? 0 : n.size;
    }

    private boolean isRed(Node n) {
        return (n != null) && (n.color = RED);
    }

    /**
     * 将红色的右子节点旋转成左边
     *
     * @param current
     */
    private Node rotateLeft(Node current) {
        Node originRight;
        if (current == null || (originRight = current.right) == null || originRight.color == BLACK) {
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
        original.size = size(original.left) + size(original.right) + 1;
        return current;
    }

    /**
     * 将红色的左子节点旋转成右边
     *
     * @param current
     */
    private Node rotateRight(Node current) {
        Node originLeft;
        if (current == null || (originLeft = current.left) == null || originLeft.color == BLACK) {
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
        original.size = size(original.left) + size(original.right) + 1;
        return current;
    }

    public void flipColor(Node n) {
        if (n == null || n.left == null || n.left.color == BLACK || n.right == null || n.right.color == BLACK) {
            throw new IllegalArgumentException();
        }
        n.color = RED;
        n.left.color = BLACK;
        n.right.color = BLACK;
    }


    private class Node {
        K k;
        V v;
        int size;
        Node left, right;
        //当前节点的颜色
        boolean color;

        public Node(K k, V v, int size, Node left, Node right, boolean color) {
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
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        RedBlackTree.Node a = tree.new Node("A", "A", 1, null, null, BLACK);
        RedBlackTree.Node b = tree.new Node("B", "B", 5, null, null, BLACK);
        RedBlackTree.Node c = tree.new Node("C", "C", 1, null, null, BLACK);
        RedBlackTree.Node d = tree.new Node("D", "D", 3, null, null, RED);
        RedBlackTree.Node e = tree.new Node("E", "E", 1, null, null, BLACK);

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
