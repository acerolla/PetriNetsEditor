import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Label;
import guru.nidi.graphviz.model.Node;

import javax.swing.*;

import java.awt.*;
import java.io.File;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

/**
 * Created by Acerolla on 17.03.2017.
 */
public class JPanelTest extends JPanel {
    public JPanelTest() {
        setPreferredSize(new Dimension(420, 420));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(java.awt.Color.WHITE);

        Node
                init = node("init"),
                execute = node("execute"),
                compare = node("compare").with(Shape.RECTANGLE, Style.FILLED, Color.hsv(.7, .3, 1.0)),
                mkString = node("mkString").with(Label.of("make a\nstring")),
                printf = node("printf");

        Graph gr = graph("example2").directed().with(
                node("main").with(Shape.RECTANGLE).link(
                        to(node("parse").link(execute)).with("weight", 8),
                        to(init).with(Style.DOTTED),
                        node("cleanup"),
                        to(printf).with(Style.BOLD, Label.of("100 times"), Color.RED)),
                execute.link(
                        graph().with(mkString, printf),
                        to(compare).with(Color.RED)),
                init.link(mkString));

        Graphviz.fromGraph(gr).renderToGraphics((Graphics2D) g);

    }
}
