



import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Label;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.Node;
import guru.nidi.graphviz.parse.Parser;


import java.io.File;

import static guru.nidi.graphviz.attribute.Records.rec;
import static guru.nidi.graphviz.attribute.Records.turn;
import static guru.nidi.graphviz.model.Compass.*;
import static guru.nidi.graphviz.model.Factory.*;

/**
 * Created by Acerolla on 17.03.2017.
 */
public class GraphvizJavaTest {


    public static void main(String[] args) {
        try {


            //MutableGraph g = Parser.read(getClass().getResourceAsStream("D:/color.dot"));

            MutableGraph g = Parser.read(new File("D:/color.dot"));
            Graphviz.fromGraph(g).renderToFile(new File("D:/ex4-1.png"));


            //Graphviz.fromGraph(g).renderToFile(new File("example/ex4-2.png"));


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
