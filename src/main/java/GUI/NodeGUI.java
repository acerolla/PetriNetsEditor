package GUI;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Node;
import model.Point;

/**
 * Created by Acerolla on 03.04.2017.
 */
public abstract class NodeGUI {

    Group root;
    MainPanel mainPanel;
    Node node;

    private NodeGUI self;

    private boolean isDragDetected;
    private double
            startX,
            startY;

    NodeGUI(MainPanel mainPanel, Node node) {
        this.mainPanel = mainPanel;
        self = this;
        root = new Group();
        this.node = node;
    }

    void initialize() {

        root.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                isDragDetected = true;
                //System.out.println("DRAGGED");

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
                    root.setLayoutX(event.getSceneX() - startX);
                    root.setLayoutY(event.getSceneY() - startY);
                    node.setStartPoint(new Point(root.getLayoutX(), root.getLayoutY()));
                    mainPanel.redrawArc(self);
                    isDragDetected = Boolean.FALSE;
                } else {
                    mainPanel.tryToLink(self);
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


}
