package com.github.fanshuzaizai.nacos.market;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jiangzy
 * @date 2019/12/2
 */
public class AAA {

    public static void main(String[] args) throws UnknownHostException {

        List<String> integers = Arrays.asList("c", "d");

        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("a", "b"));

        System.out.println(arrayList.removeAll(integers));


        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());
        System.out.println(localHost.getHostName());
        System.out.println(localHost.getCanonicalHostName());

    }

}
