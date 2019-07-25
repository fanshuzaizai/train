package com.github.fanshuzaizai.algorithm.chapter1_base;

import com.github.fanshuzaizai.dataStructure_algorithm.StopWatch;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * quick方法
 *
 * @author Jzy.
 * @date 2019/7/5 14:30
 */
public class UnionFind_v1 {

    //分量id，以触点为id
    private int[] id;

    //寻找的次数
    private int _count;

    public UnionFind_v1(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        //初始化设置为 每个顶点的 连通分量都是他自己
        this.id = IntStream.range(0, size).toArray();
    }

    public int length() {
        return id.length;
    }

    public int size() {
        return (int) Arrays.stream(id).distinct().count();
    }

    public int find(int p) {
        _count++;
        return id[p];
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        if (isConnected(p, q)) {
            return;
        }

        //遍历所有的顶点，将p的 分量id  以及所有 相同的 改成 p
        int old = id[p];
        _count++;
        for (int i = 0; i < id.length; i++) {
            _count++;
            if (id[i] == old) {
                id[i] = id[q];
            }
        }
    }

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\mediumUF.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);

        UnionFind_v1 unionFind = new UnionFind_v1(Integer.parseInt(lines.get(0)));
        System.out.println(unionFind.size());

        StopWatch stopWatch = new StopWatch();
        lines.stream().skip(1).forEach(e -> {
            String[] s = e.split(" ");
            unionFind.union(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        });

        stopWatch.over();
        System.out.println(unionFind._count); //medium 389372
        System.out.println(unionFind.size());
    }
}
