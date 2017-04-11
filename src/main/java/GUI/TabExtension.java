package GUI;

import javafx.event.EventHandler;
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

    private static int placeId, transitionId;

    List<NodeGUI> nodesGUI;
    List<ArcGUI> arcsGUI;

    private boolean isClicked;
    private NodeGUI firstNode;

    private boolean
        placeFlag,
        transitionFlag;

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


    public static void setPlaceId(int placeId) {
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
    }

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


    public void addPlaceClicked() {
        placeFlag       = true;
        transitionFlag  = false;
    }

    public void addTransitionClicked() {
        transitionFlag  = true;
        placeFlag       = false;
    }

    public void tryToLink(NodeGUI node) {
        if (!isClicked) {
            System.out.println("1st time");
            firstNode = node;
            isClicked = Boolean.TRUE;
        } else {
            System.out.println("2nd time");
            try {
                Arc arc = new Arc(firstNode.getNode(), node.getNode());
                firstNode.getNode().addArc(arc);
                ArcGUI arcGUI = new ArcGUI(anchorPane, arc);
                arcsGUI.add(arcGUI);
                anchorPane.getChildren().add(arcGUI.getGroup());
            } catch (Exception e) {
                e.printStackTrace();
            }
            firstNode = null;
            isClicked = Boolean.FALSE;
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
                            placeId ++ ;
                            Place pl = new Place(placeId);
                            pl.setStartPoint(new Point(event.getX(), event.getY()));

                            PlaceGUI place = new PlaceGUI(TabExtension.this, pl);
                            anchorPane.getChildren().addAll(place.getRoot());

                            nodesGUI.add(place);

                            net.addNode(pl);
                            //rootItem.getChildren().add(new TreeItem<String>("P" + placeId));

                            placeFlag = false;
                        } else if (transitionFlag) {
                            transitionId ++ ;
                            Transition tr = new Transition(transitionId);
                            tr.setStartPoint(new Point(event.getX(), event.getY()));

                            TransitionGUI transition = new TransitionGUI(TabExtension.this, tr);
                            anchorPane.getChildren().addAll(transition.getRoot());

                            nodesGUI.add(transition);

                            net.addNode(tr);

                            transitionFlag = false;
                        }
                    }
                }
            }
        });
    }


}
