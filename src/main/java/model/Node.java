package model;

import java.util.List;

/**
 * Created by Acerolla on 27.03.2017.
 */
public abstract class Node {

    private List<Arc> arcs;


    public List<Arc> getArcs() {
        return arcs;
    }

    public void addArc(Arc arc) {
        arcs.add(arc);
    }

    public void setArcs(List<Arc> arcs) {
        this.arcs = arcs;
    }
}
