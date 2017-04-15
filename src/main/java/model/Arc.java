package model;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class Arc {

    private Node source;
    private Node target;

    public Arc(Node source, Node target) throws Exception {
        setRelation(source, target);
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }

    public void setRelation(Node source, Node target) throws Exception {
        if (source.getClass() == target.getClass()) {
            throw new Exception("Cannot relate the same nodes.");
        }

        this.source = source;
        this.target = target;
    }
}
