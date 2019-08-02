package com.github.fanshuzaizai.algorithm.chapter4_graph.unDirected;

import com.github.fanshuzaizai.algorithm.chapter3_search.BinarySearchTreeSt;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Jzy.
 * @date 2019/7/26 15:49
 */
public class SymbolGraph {

    private final Graph graph;

    /**
     * 顶点->序号
     */
    private BinarySearchTreeSt<String, Integer> st;

    /**
     * 下标->顶点
     */
    private String[] keys;

    public SymbolGraph(List<String> lines, String separator) {

        st = new BinarySearchTreeSt<>();
        lines.forEach(e -> {
            for (String s : e.split(separator)) {
                if (!st.contains(s)) {
                    //将每个key放入st中
                    st.put(s, st.size());
                }
            }
        });

        keys = new String[st.size()];
        //将st中的k对应的下标放入数组中
        st.keys().forEach(e -> this.keys[st.get(e)] = e);

        this.graph = new Graph(st.size());
        lines.forEach(e -> {
            String[] split = e.split(separator);
            int movie = st.get(split[0]);
            for (int i = 1; i < split.length; i++) {
                graph.addEdge(movie, st.get(split[i]));
            }

        });
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        Integer integer = st.get(s);
        return integer != null ? integer : -1;
    }

    public String name(int i) {
        return keys[i];
    }

    public Graph graph() {
        return this.graph;
    }


    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\movies.txt"));
        List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);


        SymbolGraph symbolGraph = new SymbolGraph(lines, "/");

        /*
        Tin Men (1987)/
        DeBoy, David/Blumenfeld, Alan/
        MacPherson, Walt/
        Steele, David (I)/
        Moser, Jeffrey/DeVito, Danny/Willis, Michael (I)/Walsh, J.T./Billings, Joshua/Cassel, Seymour/Citronbaum, Myron/Craven, Matt (I)/Brock, Stanley/Goldman, Theodore/Costantini, Brian/Tabakin, Ralph/Jackson, Todd (I)/Gift, Roland/Kirby, Bruno/Godsey, William C./Mahoney, John (I)/Danoff, Bill/Dreyfuss, Richard/Gayle, Jackie/Sullivan, Brad/Levinson, Herb/Portnow, Richard/Cox, Andy (I)/Tucker, Michael (I)/Stevens, Freddie (I)/McCauley, Sheila/Ford, Lisa/Posner, Norma/Berg, Eva Jean/Ziman, Sharon/Morgan, Mary (II)/Goldpaugh, Kathleen/Ellis, Katherine (II)/Pohlman, Patricia/Sills, Ellen/Crofoot, Sharon/Wilson, Shirley Ann/Nichols, Penny (I)/Rappaport, Barbara/Barth, Karen/Moody, Florence/Duvall, Susan/Kelbaugh, Geri Lynn/Weidner, Becky/O'Connell, Deirdre/Munchel, Lois Raymond/Herr, Marcia/Jones, Kathy (II)/Geppi, Cindy/Hershey, Barbara
         */
        String name = "Tin Men (1987)";
        int index = symbolGraph.index(name);
        Integer integer1 = symbolGraph.st.get(name);
        System.out.println(integer1);
        System.out.println(symbolGraph.keys[integer1]);
        Iterable<Integer> integers = symbolGraph.graph().connectedVertexes(index);
        for (Integer integer : integers) {
            System.out.println("\t" + symbolGraph.name(integer));
        }
    }
}
