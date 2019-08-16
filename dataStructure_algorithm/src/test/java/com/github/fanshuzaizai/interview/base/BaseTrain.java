package com.github.fanshuzaizai.interview.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.UUID;

/**
 * @author Jzy.
 * @date 2019/8/14 9:25
 */
@RunWith(JUnit4.class)
public class BaseTrain {

    @Test
    public void test0() {
        int i = 1;
        i = i++;//i=1
        int j = i++;//i=2,j=1
        int k = i + ++i * i++;//i=4,k=2+3*3

        System.out.println(i);
        System.out.println(j);
        System.out.println(k);

    }

}
