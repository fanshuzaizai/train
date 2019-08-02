package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Jzy.
 * @date 2019/7/24 17:13
 */
@RunWith(JUnit4.class)
public class EX {

    @Test
    public void test1() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\tinyG.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);

        Integer v = Integer.valueOf(lines.get(0));
        Graph graph = new Graph(v);
        lines.stream().skip(2).forEach(e -> {
            String[] split = e.split("\\s+");
            graph.addEdge(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
        });

        System.out.println(graph);

        DepthFirstSearch dfs = new DepthFirstSearch(graph, 5);
        for (Integer i = 0; i < v; i++) {
            System.out.println(dfs.pathTo(i));
        }
        System.out.println("=============");
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 5);
        for (Integer i = 0; i < v; i++) {
            System.out.println(bfs.pathTo(i));
        }

        ConnectedComponent cc = new ConnectedComponent(graph);
        System.out.println(cc.countCC());
        System.out.println(cc.connected(0, 10));
        System.out.println(cc.connected(12, 8));
        System.out.println(cc.connected(9, 12));
        System.out.println(cc.connected(2, 6));
    }

    /**
     * 最短路径
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\movies.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);


        SymbolGraph symbolGraph = new SymbolGraph(lines, "/");

        int kevin_bacon = symbolGraph.index("Bacon, Kevin");
        Iterable<Integer> integers = symbolGraph.graph().connectedVertexes(kevin_bacon);
//        integers.forEach(e->{
//            String name = symbolGraph.name(e);
//            System.out.println(name);
//        });
//
//        if (true) {
//            return;
//        }

        BreadthFirstSearch bfs = new BreadthFirstSearch(symbolGraph.graph(), kevin_bacon);

        String target = "Hudson, Lord Tim";

        int targetIndex = symbolGraph.index(target);
        Iterable<Integer> path = bfs.pathTo(targetIndex);
        path.forEach(e -> System.out.println("\t" + symbolGraph.name(e)));

        /*
        Jungle Book, The (1967 I)/Hudson, Lord Tim/Reitherman, Bruce/Källerud, Arne/Cabot, Sebastian (I)/Stuart, Chad/Prüzelius, Gösta/DeLyon, Leo/Wolfe, Digby/O'Malley, J. Pat/Isedal, Tor/Howard, Clint/Wright, Ben (I)/Holloway, Sterling/Harris, Phil (I)/Wolgers, Beppe/Sanders, George (I)/Prima, Louis/Abbott, John (I)/Gustafsson, Pontus/Felton, Verna (I)/Carr, Darleen

        My Dog Skip (2000)/Coryell, Bradley/Wimberly, Wayne/Dog, Enzo the/Lee Jr., Nathaniel/Wilson, Luke (I)/Williams, Jordan (I)/Gordy, Graham/Butler, William (I)/Crombie, Peter (I)/Sullivan II, John M./Pickens, David/Honeycutt, Daylan/Jerald, Jerome/Moose (III)/Bacon, Kevin/Fraiser, Jim/Swaim, Gordon/Beech, Mark (I)/Howard, Clint/Boutwell, Owen/Stiritz, John/Hayes, Hunter/Yates, Josh (I)/Witt, Brian/Greenwell, Stuart/Berkshire, Michael/Thweat, James/Bynum, Nate/Connick Jr., Harry/Hood, Harry (II)/Muniz, Frankie/Linley, Cody/Smith, Cannon (II)/Blackenship, Joann/Davis, Susan Carol/Wachs, Caitlin/Ewing, Lucile Doan/Doublin, Stacie/Shoulders, Katherine/Rice, Elizabeth (I)/Cross, Chaon/Craig, Polly/Brown, Courtney (II)/Lane, Diane (I)

         */

    }

}
