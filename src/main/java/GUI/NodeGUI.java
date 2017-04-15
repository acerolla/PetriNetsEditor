package GUI;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Node;
import model.Place;
import model.Point;
import model.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 03.04.2017.
 */
public abstract class NodeGUI {

    Group root;
    TabExtension tab;
    Node node;

    //private NodeGUI self;

    private boolean isDragDetected;
    private double
            startX,
            startY;

    NodeGUI(TabExtension tabExtension, Node node) {
        this.tab = tabExtension;
        //self = this;
        root = new Group();
        this.node = node;
    }

    void initialize() {

        root.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                isDragDetected = true;


                root.setLayoutX(event.getSceneX() - startX);
                root.setLayoutY(event.getSceneY() - startY);

                node.setStartPoint(new Point(root.getLayoutX(), root.getLayoutY()));

                tab.redrawArc(NodeGUI.this);
                MainPanel.lastAction(NodeGUI.this.toString() + " dragged.");

                /*
                if (pane.getPrefWidth() - 200 < event.getSceneX()) {
                    pane.setPrefWidth(event.getSceneX() + 200);
                }
                if (pane.getPrefHeight() - 200 < event.getSceneY()) {
                    pane.setPrefHeight(event.getSceneY() + 200);
                }*/
            }
        });

        root.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (isDragDetected) {
                    //root.setLayoutX(event.getSceneX() - startX);
                    //root.setLayoutY(event.getSceneY() - startY);

                    MainPanel.lastAction(NodeGUI.this.toString() + " dropped.");
                    isDragDetected = Boolean.FALSE;
                } else if (event.getButton() == MouseButton.PRIMARY) {

                    tab.tryToLink(NodeGUI.this);
                }
                //System.out.println("RELEASED");
            }
        });

        root.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                startX = event.getSceneX() - root.getLayoutX();
                startY = event.getSceneY() - root.getLayoutY();
                //System.out.println("PRESSED");
            }
        });
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Group getRoot() {
        return root;
    }

    public TabExtension getTab() {
        return tab;
    }

    public void removeNode() {

        List<ArcGUI> forRemove = new ArrayList<ArcGUI>();
        for (ArcGUI arcGUI : tab.getArcsGUI()) {
            if (
                    arcGUI.getArc().getTarget() == this.getNode() ||
                    arcGUI.getArc().getSource() == this.getNode()) {
                forRemove.add(arcGUI);
            }
        }
        for (ArcGUI arcGUI : forRemove) {
            tab.getArcsGUI().remove(arcGUI);
            tab.getAnchorPane().getChildren().remove(arcGUI.getGroup());
        }
        tab.getAnchorPane().getChildren().remove(this.getRoot());
        tab.getNodesGUI().remove(this);
        tab.getNet().removeNode(node);
        TabExtension outer = (TabExtension) tab.getTabPane().getTabs().get(0);
        Place.setPlaceId(outer.getNet().getLastPlace(0));
        Transition.setTransitionId(outer.getNet().getLastTransition(0));
    }

    public String toString() {
        String ans = "";
        if (getNode().getClass() == Place.class) {
            ans += "P";
        } else {
            ans += "T";
        }

        ans += getNode().getId();

        return ans;
    }


}
