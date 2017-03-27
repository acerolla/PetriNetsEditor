package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 27.03.2017.
 */
public abstract class Node {

    private List<Arc> arcs;

    private int id;
    private String label;

    Node(int id) {
        this.id = id;
        arcs = new ArrayList<Arc>();
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    public void addArc(Arc arc) {
        arcs.add(arc);
    }

    public void removeArc(Arc arc) {
        arcs.remove(arc);
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
