package GUI;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 10.04.2017.
 */
public class TabExtension extends Tab {

    private Net net;

    List<NodeGUI> nodesGUI;
    List<ArcGUI> arcsGUI;

    private boolean isClicked;
    private NodeGUI firstNode;

    private boolean
        placeFlag,
        transitionFlag,
        connector;

    private AnchorPane anchorPane;

    public TabExtension() {
        ScrollPane scrollPane = new ScrollPane();;

        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(5000, 2000);

        nodesGUI = new ArrayList<NodeGUI>();
        arcsGUI  = new ArrayList<ArcGUI>();
        net      = new Net();

        scrollPane.setContent(anchorPane);
        setContent(scrollPane);

        initialize();
    }


/*    public static void setPlaceId(int placeId) {
        TabExtension.placeId = placeId;
    }

    public static void setTransitionId(int transitionId) {
        TabExtension.transitionId = transitionId;
    }

    public static int getPlaceId() {
        return placeId;
    }

    public static int getTransitionId() {
        return transitionId;
    }*/

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public List<ArcGUI> getArcsGUI() {
        return arcsGUI;
    }

    public List<NodeGUI> getNodesGUI() {
        return nodesGUI;
    }

    public Net getNet() {
        return net;
    }

    public void setNet(Net net) {
        this.net = net;
    }


    public void addPlaceClicked() {
        placeFlag       = true;
        transitionFlag  = false;
    }

    public void addTransitionClicked() {
        transitionFlag  = true;
        placeFlag       = false;
    }

    public void connectorClicked() {
        connector = true;
    }

    public void tryToLink(NodeGUI node) {
        if (!connector) {
            return;
        }
        if (!isClicked) {
            MainPanel.lastAction(node.toString() + " clicked as 1st node.");
            firstNode = node;
            isClicked = Boolean.TRUE;
        } else {
            MainPanel.lastAction(node.toString() + " clicked as 2nd node.");
            try {
                if (firstNode == node) {
                    connector = false;
                    throw new Exception("Sorry ); \r\nIt's impossible to connect "
                            + node.toString() + " and " + node.toString());
                }
                for (Arc arc : firstNode.getNode().getArcs()) {
                    if (arc.getTarget() == node.getNode()) {
                        connector = false;
                        throw new Exception(firstNode.toString() + " and " + node.toString() + " already connected.");
                    }
                }
                Arc arc = new Arc(firstNode.getNode(), node.getNode());
                firstNode.getNode().addArc(arc);
                ArcGUI arcGUI = new ArcGUI(anchorPane, arc);
                arcsGUI.add(arcGUI);
                anchorPane.getChildren().add(arcGUI.getGroup());
            } catch (Exception e) {
                connector = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setHeaderText("Alarm!!! Connect exception!!!");
                alert.show();
            }
            firstNode = null;
            isClicked = Boolean.FALSE;
            connector = false;
        }
    }

    public void redrawArc(NodeGUI node) {
        for (NodeGUI element : nodesGUI) {
            for (Arc arc : element.getNode().getArcs()) {
                if (arc.getTarget() == node.getNode() || arc.getSource() == node.getNode()) {
                    for (ArcGUI arcGUI : arcsGUI) {
                        if (arcGUI.getArc() == arc) {
                            arcGUI.drawArrow(arc.getSource().getStartPoint(), arc.getTarget().getStartPoint());
                        }
                    }
                }
            }
        }
    }

    private void initialize() {

        anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (event.getClickCount() == 1) {
                        if (placeFlag) {
                            //placeId ++ ;
                            Place pl = new Place();
                            pl.setStartPoint(new Point(event.getX(), event.getY()));

                            PlaceGUI place = new PlaceGUI(TabExtension.this, pl);
                            anchorPane.getChildren().addAll(place.getRoot());

                            nodesGUI.add(place);

                            net.addNode(pl);

                            MainPanel.lastAction(place.toString() + " added.");

                            placeFlag = false;
                        } else if (transitionFlag) {
                            //transitionId ++ ;
                            Transition tr = new Transition();
                            tr.setStartPoint(new Point(event.getX(), event.getY()));

                            TransitionGUI transition = new TransitionGUI(TabExtension.this, tr);
                            anchorPane.getChildren().addAll(transition.getRoot());

                            nodesGUI.add(transition);

                            net.addNode(tr);

                            MainPanel.lastAction(transition.toString() + " added.");

                            transitionFlag = false;
                        }
                    } else if (event.getClickCount() == 2) {
                        boolean isNode = false;
                        for (NodeGUI nodeGUI : nodesGUI) {
                            if (nodeGUI.getRoot().contains(new Point2D(
                                    event.getX() - nodeGUI.getRoot().getLayoutX(),
                                    event.getY() - nodeGUI.getRoot().getLayoutY()))) {
                                isNode = true;
                                MainPanel.lastAction(nodeGUI.toString() + " touched.");
                                if (nodeGUI.getNode().getClass() == Place.class) {
                                    MainPanel.setContentVBox(new VBoxPlace((Place)nodeGUI.getNode()));
                                } else {
                                    MainPanel.setContentVBox(new VBoxTransition((Transition)nodeGUI.getNode()));
                                }
                            }
                        }
                        if (!isNode) {
                            MainPanel.lastAction(net.getId() + " touched.");
                            MainPanel.setContentVBox(new VBoxNet(getNet()));
                        }
                    }
                }
            }
        });
    }

    public void drawNet() {
        for (Node node : net.getNodes()) {
            if (node.getClass() == Place.class) {
                PlaceGUI placeGUI = new PlaceGUI(this, node);
                anchorPane.getChildren().add(placeGUI.getRoot());
                nodesGUI.add(placeGUI);
            } else {
                TransitionGUI transitionGUI = new TransitionGUI(this, node);
                anchorPane.getChildren().add(transitionGUI.getRoot());
                nodesGUI.add(transitionGUI);
            }

        }

        for (NodeGUI node : nodesGUI) {
            for (Arc arc : node.getNode().getArcs()) {
                for (NodeGUI anotherNode : nodesGUI) {
                    if (arc.getSource() == node.getNode() && arc.getTarget() == anotherNode.getNode()) {
                        firstNode = node;
                        isClicked = true;
                        try {
                            ArcGUI arcGUI = new ArcGUI(anchorPane, arc);
                            arcsGUI.add(arcGUI);
                            anchorPane.getChildren().add(arcGUI.getGroup());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}
