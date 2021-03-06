package model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class Net {

    private String Id;
    private String label;

    private List<Node> nodes;

    public Net() {
        nodes = new ArrayList<Node>();
        Id = "";
        label = "";
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void setRelation(Node source, Node target) throws Exception {
        source.addArc(new Arc(source, target));
    }

    public void removeRelation(Arc arc) {
        for (Node node : nodes) {
            node.removeArc(arc);
        }
    }

    public void removeNode(Node node) {
        for (Node tempNode : nodes) {
            Arc tempArc = null;
            for (Arc arc : tempNode.getArcs()) {
                if (arc.getTarget() == node) {
                    //tempNode.removeArc(arc);
                    tempArc = arc;
                }
            }
            tempNode.removeArc(tempArc);
        }
        nodes.remove(node);
    }



    public List<Node> getNodes() {
        return nodes;
    }

    public int getLastPlace(int id) {
        //int id = 0;
        for (Node node : getNodes()) {
            if (node.getClass() == Place.class) {
                if (node.getId() >= id) {
                    id = node.getId();
                }
                for (Token token : ((Place)node).getTokens()) {
                    if (token.getClass() == NetToken.class) {
                        id = ((NetToken)token).getInnerNet().getLastPlace(id);
                    }
                }
            }
        }

        return  id;
    }

    public int getLastTransition(int id) {
        //int id = 0;
        for (Node node : getNodes()) {
            if (node.getClass() == Transition.class) {
                if (node.getId() >= id) {
                    id = node.getId();
                }
            } else {
                for (Token token : ((Place)node).getTokens()) {
                    if (token.getClass() == NetToken.class) {
                        id = ((NetToken)token).getInnerNet().getLastTransition(id);
                    }
                }
            }
        }

        return  id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
