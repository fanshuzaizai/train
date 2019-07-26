package com.github.fanshuzaizai.algorithm.chapter3_search;

/**
 * @author Jzy.
 * @date 2019/7/18 11:30
 */
public class BinarySearchTreeSt<K extends Comparable<K>, V> {

    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        return n == null ? 0 : n.size;
    }

    private K min() {
        return root == null ? null : min(root).k;
    }

    private Node min(Node n) {
        return n.left == null ? n : min(n.left);
    }

    private K max() {
        return root == null ? null : max(root).k;
    }

    private Node max(Node n) {
        return n.right == null ? n : max(n.right);
    }

    public boolean contains(K k) {
        return get(k) != null;
    }


    public V get(K k) {
        return get(root, k);
    }

    public void delMin() {
        if (root != null) {
            root = delMin(root);
        }
    }

    /**
     * 删除当前节点下最小的元素
     *
     * @param n
     */
    private Node delMin(Node n) {
        if (n.left == null) {
            return null;
        } else {
            n.left = delMin(n.left);
        }
        n.size--;
        return n;
    }


    public void delMax() {
        if (root != null) {
            root = delMax(root);
        }
    }

    private Node delMax(Node n) {
        if (n.right == null) {
            return null;
        } else {
            n.right = delMax(n.right);
        }
        n.size--;
        return n;
    }

    public void delete(K k) {
        delete(root, k);
    }

    public Node delete(Node n, K k) {
        if (n == null) {
            return null;
        }

        int i = k.compareTo(n.k);
        if (i > 0) {
            n.right = delete(n.right, k);
        } else if (i < 0) {
            n.left = delete(n.left, k);
        } else {
            if (n.left == null) {//左子节点为空，直接替换为右子节点
                return n.right;
            } else if (n.right == null) {//右子节点为空，直接替换为左子节点
                return n.left;
            } else {//同时存在2个子节点的情况
                //保存当前节点
                Node t = n;
                n = min(n.right);
                //将当前节点置为右子节下最小的元素并删除
                n.right = delMin(t.right);
                n.left = t.left;
            }
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    /**
     * 返回对应排名的k
     *
     * @param k
     * @return
     */
    public K select(int k) {
        return select(root, k);
    }

    private K select(Node n, int k) {
        //超过当前节点所持有的数量
        if (k < 0 || n == null || k > n.size - 1) {
            return null;
        }

        //当前节点作为root时的位置的索引（从0开始）
        int currentNodeIndex = size(n.left);

        if (k < currentNodeIndex) {//在左边节点中寻找
            return select(n.left, k);
        } else if (k > currentNodeIndex) {//在右边节点寻找
            //右边的节点数量-左边和当前根
            return select(n.right, k - currentNodeIndex - 1);
        } else {//就是当前节点
            return n.k;
        }
    }

    /**
     * k应该在的位置
     *
     * @param k
     * @return
     */
    public int rank(K k) {
        return rank(k, root, 0);
    }

    private int rank(K k, Node n, int val) {
        if (n == null) {
            return val;
        }

        //中间节点的位置
        int currentNodeIndex = size(n.left);

        int i = k.compareTo(n.k);
        if (i < 0) {
            return rank(k, n.left, val);
        } else if (i > 0) {
            return rank(k, n.right, val + currentNodeIndex + 1);
        } else {
            return val + currentNodeIndex;
        }
    }


    /**
     * 自某个节点开始寻找 k
     *
     * @param n
     * @param k
     * @return
     */
    private V get(Node n, K k) {
        if (n == null) {
            return null;
        }
        int i = k.compareTo(n.k);
        if (i > 0) {
            return get(n.right, k);
        } else if (i < 0) {
            return get(n.left, k);
        } else {
            return n.v;
        }
    }

    public void put(K k, V v) {
        root = put(k, v, root);
    }

    private Node put(K k, V v, Node n) {

        if (n == null) {
            return new Node(k, v, 1, null, null);
        }
        int i = k.compareTo(n.k);
        if (i > 0) {
            n.right = put(k, v, n.right);
        } else if (i < 0) {
            n.left = put(k, v, n.left);
        } else {
            n.v = v;
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    private class Node {
        K k;
        V v;
        int size;
        Node left, right;

        public Node(K k, V v, int size, Node left, Node right) {
            this.k = k;
            this.v = v;
            this.size = size;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "k=" + k +
                    ", v=" + v +
                    ", size=" + size +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BinarySearchTreeSt{" +
                "root=" + root +
                '}';
    }

    public static void main(String[] args) {
        BinarySearchTreeSt<Integer, String> st = new BinarySearchTreeSt<>();

        st.put(12, "12");
        st.put(13, "13");
        st.put(5, "5");
        st.put(15, "15");
        st.put(17, "17");
        System.out.println(st);

        System.out.println(st.select(2));
        System.out.println(st.select(4));
        System.out.println(st.select(-1));
        System.out.println(st.select(100));

        System.out.println(st.rank(100));
        System.out.println(st.rank(13));
        System.out.println(st.rank(14));
        System.out.println(st.rank(1));


        st.delete(13);
        st.delete(15);

        System.out.println(st.select(2));
        System.out.println(st.select(3));

        st.delete(15);
        System.out.println(st.select(3));

        st.delMin();
        System.out.println(st.select(0));
        System.out.println("====");
        System.out.println(st.size());
        System.out.println(st);
        st.delMax();
        System.out.println(st.size());
        System.out.println(st.select(0));
        System.out.println(st);
    }
}
