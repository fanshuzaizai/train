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

        return balance(n);
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

    private boolean isBlack(Node n) {
        return n != null && n.color == BLACK;
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
     * 旋转当前节点和子节点的颜色
     *
     * @param n
     */
    public void flipColor(Node n) {
        n.color = !n.color;
        n.left.color = !n.left.color;
        n.right.color = !n.right.color;
    }

    /**
     * 删除最小值
     */
    public void delMin() {
        if (root == null) {
            return;
        }
        //将root节点转成一个普通节点的规则
        if (isRed(root.left) && isRed(root.right)) {
            root.color = BLACK;
        }
        root = delMin(root);

        if (root != null) {
            root.color = BLACK;
        }
    }

    private Node delMin(Node n) {
        //如果左子节点为空，右子节点一定也为空，可以直接删除
        if (n.left == null) {
            return null;
        }
        //如果当前节点和左子节点都不是3-节点
        if (isBlack(n.left) && !isRed(n.left.left)) {
            n = moveRedLeft(n);
        }
        n.left = delMin(n.left);

        return balance(n);
    }

    public void delMax() {
        if (root == null) {
            return;
        }
        //将root节点转成一个普通节点的规则
        if (isRed(root.left) && isRed(root.right)) {
            root.color = BLACK;
        }
        root = delMax(root);

        if (root != null) {
            root.color = BLACK;
        }
    }

    private Node delMax(Node n) {
        //如果左子节点是红色，则右旋
        if (isRed(n.left)) {
            n = rotateRight(n);
        }
        //如果右子节点为空，左子节点一定也为空，可以直接删除当前节点
        if (n.right == null) {
            return null;
        }
        //如果当前节点和右子节点都不是3-节点
        if (!isRed(n.right) && !isRed(n.right.left)) {
            n = moveRedRight(n);
        }
        n.right = delMax(n.right);

        return balance(n);
    }

    public void delete(K k) {
        if (root == null) {
            return;
        }
        //将root节点转成一个普通节点的规则
        if (isRed(root.left) && isRed(root.right)) {
            root.color = BLACK;
        }
        root = delete(k, root);

        if (root != null) {
            root.color = BLACK;
        }
    }

    private Node delete(K k, Node n) {
        /*
        保证左节点或右节点
         */
        if (k.compareTo(n.k) < 0) {
            //左边已经没有了，说明不存在
            if (n.left == null) {
                return n;
            } else {
                if (isBlack(n.left) && !isRed(n.left.left))
                    n = moveRedLeft(n);
                n.left = delete(k, n.left);
            }
        } else {
            //类似删除最大节点
            if (isRed(n.left)) {
                n = rotateRight(n);
            }
            //如果右节点不存在，左节点一定也没有，所以是一个孤立的节点
            if (n.right == null) {
                //找到节点，删除
                if (k.compareTo(n.k) == 0) {
                    return null;
                } else {//没找到，原路返回
                    return n;
                }
            } else {
                //构建一个右3-节点
                if (isBlack(n.right) && !isRed(n.right.left)) {
                    n = moveRedRight(n);
                }
                //找到了
                if (k.compareTo(n.k) == 0) {
                    //右子节点中最小的节点(和当前节点最接近的节点)
                    Node closest = min(n.right);
                    //替换给当前节点
                    n.k = closest.k;
                    n.v = closest.v;
                    //再把这个节点(closest)删除
                    n.right = delMin(n.right);
                } else {
                    n.right = delete(k, n.right);
                }
            }
        }

        return balance(n);
    }

    private Node min(Node n) {
        if (n.left == null) {
            return n;
        } else {
            return min(n.left);
        }
    }

    /**
     * @param n
     * @return
     */
    private Node moveRedLeft(Node n) {
        //将当前节点转成一个4-节点（2个子节点都是红色）
        flipColor(n);

        //如果右子节点的左子节点是红色
        if (n.right != null && isRed(n.right.left)) {
            //右子节点右旋，和当前节点组成一个5-节点（一共4个平级节点）
            n.right = rotateRight(n.right);
            //将当前节点由5-节点中的第2个左旋成第3个
            n = rotateLeft(n);
            //将当前元素变成上级，最终转换成，左子节点2个（3-节点），右子节点1个
            flipColor(n);
        }
        return n;
    }

    private Node moveRedRight(Node n) {
        //将当前节点转成一个4-节点（2个子节点都是红色）
        flipColor(n);

        //如果左子节点的左子节点是红色，说明当前节点是一个5-节点(一个4个平级节点),当前节点处于第3个
        if (n.left != null && isRed(n.left.left)) {
            //将当前节点由5-节点中的第3个右旋成第2个
            n = rotateRight(n);
            //将当前元素变成上级，最终转换成，左子节点1个，右子节点2个（3-节点）
            flipColor(n);
        }
        return n;
    }

    private Node balance(Node n) {
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


        RedBlackTree<String, String> tree = new RedBlackTree<>();

        tree.put("S", "S");
        tree.put("E", "E");
        tree.put("A", "A");
        tree.put("R", "R");
        tree.put("C", "C");
        tree.put("H", "H");
        tree.put("X", "X");
        tree.put("M", "M");
        tree.put("P", "P");
        tree.put("L", "L");
        System.out.println(tree);

        tree.delete("H");
        System.out.println(tree);

        tree.delete("M");
        System.out.println(tree);

        tree.delMax();
        System.out.println(tree);
        System.out.println("===");

        tree.delete("E");
        System.out.println(tree);
        tree.delete("E");
        System.out.println(tree);
//        tree.delMin();
//        System.out.println(tree);




    }
}
