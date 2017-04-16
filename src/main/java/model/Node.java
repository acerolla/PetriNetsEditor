package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Acerolla on 27.03.2017.
 */
public abstract class Node {

    private List<Arc> arcs;

    private int id;
    private String label;

    private Point startPoint;

    Node(int id) {
        this.id = id;
        arcs = new ArrayList<Arc>();
    }

    public void setStartPoint(Point point) {
        this.startPoint = point;
    }

    public Point getStartPoint() {
        return startPoint;
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

    public String toString() {
        String str = "";
        if (getClass() == Place.class) {
            str += "P";
        } else {
            str += "T";
        }
        str += Integer.toString(getId());

        return str;
    }
}
