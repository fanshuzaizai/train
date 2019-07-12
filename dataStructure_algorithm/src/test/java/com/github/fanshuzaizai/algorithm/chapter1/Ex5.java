package com.github.fanshuzaizai.algorithm.chapter1;

import com.github.fanshuzaizai.dataStructure_algorithm.StopWatch;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Jzy.
 * @date 2019/7/4 11:52
 */
@RunWith(JUnit4.class)
public class Ex5 {

    @Before
    public void start() throws IOException {
    }

    @After
    public void end() {
    }

    public void test1(){
        UnionFind_v1 unionFind_v1 = new UnionFind_v1(10);
        unionFind_v1.union(9, 0);
        unionFind_v1.union(3,4 );
        unionFind_v1.union(5,8 );
        unionFind_v1.union(7, 2);
        unionFind_v1.union(2,1 );
        unionFind_v1.union(5,7 );
        unionFind_v1.union(0,3 );
        unionFind_v1.union(4,2 );
    }



}
