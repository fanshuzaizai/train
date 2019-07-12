package com.github.fanshuzaizai.algorithm.chapter1;

import com.github.fanshuzaizai.dataStructure_algorithm.StopWatch;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Jzy.
 * @date 2019/7/5 14:30
 */
public class UnionFind_v2 {

    //分量id，以触点为id
    private int[] id;
    private int[] sz;

    private int _count;

    public UnionFind_v2(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        this.id = IntStream.range(0, size).toArray();

        sz = new int[size];
        for (int i = 0; i < sz.length; i++) {
            sz[i] = 1;
        }
    }

    public int length() {
        return id.length;
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == i) {
                size++;
            }
        }
        return size;
    }

    public int find(int p) {
        int t;
        _count++;
        while (p != (t = id[p])) {
            p = t;
            _count++;
        }
        return p;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (p == q) {
            return;
        }

        //如果p节点比q大,将q挂到p下面
        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
    }

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\mediumUF.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);

        UnionFind_v2 unionFind = new UnionFind_v2(Integer.parseInt(lines.get(0)));
        System.out.println(unionFind.size());

        StopWatch stopWatch = new StopWatch();
        lines.stream().skip(1).forEach(e -> {
            String[] s = e.split(" ");
            unionFind.union(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        });
        System.out.println(unionFind._count); //medium 11224
        stopWatch.over();

        System.out.println(unionFind.size());
    }
}
