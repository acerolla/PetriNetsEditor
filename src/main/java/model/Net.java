package model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class Net {
    private List<Node> nodes;

    public Net() {
        nodes = new ArrayList<Node>();
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

    public int getLastPlace() {
        int id = 0;
        for (Node node : getNodes()) {
            if (node.getClass() == Place.class) {
                if (node.getId() >= id) {
                    id = node.getId();
                }
                for (Token token : ((Place)node).getTokens()) {
                    if (token.getClass() == NetToken.class) {
                        id = ((NetToken)token).getInnerNet().getLastPlace();
                    }
                }
            }
        }

        return  id;
    }

    public int getLastTransition() {
        int id = 0;
        for (Node node : getNodes()) {
            if (node.getClass() == Transition.class) {
                if (node.getId() >= id) {
                    id = node.getId();
                }
            } else {
                for (Token token : ((Place)node).getTokens()) {
                    if (token.getClass() == NetToken.class) {
                        id = ((NetToken)token).getInnerNet().getLastTransition();
                    }
                }
            }
        }

        return  id;
    }
}
