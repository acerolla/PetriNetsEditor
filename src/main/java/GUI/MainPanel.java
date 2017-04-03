package GUI;

import GUI.PlaceWithGUI;
import GUI.TransitionWithGUI;
import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import model.*;
import model.Arc;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 21.03.2017.
 */
public class MainPanel extends Application {

    private Pane root;
    private HBox hBox;
    private TabPane tabPane;
    private ScrollPane scrollPane;
    private AnchorPane pane;
    private Tab tab;

    private TreeView<String> treeView;
    private TreeItem<String> rootItem;

    List<NodeGUI> listNodes;
    List<ArcWithGUI> listArcs;

    private boolean placeFlag;
    private boolean transFlag;

    private Net outerNet;

    private int placeId,
                transitionId;

    private boolean isClicked;
    private NodeGUI firstNode;

    private MainPanel self;

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Petri Nets Editor");
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(450);

        self = this;
        listNodes = new ArrayList<NodeGUI>();
        listArcs = new ArrayList<ArcWithGUI>();

        outerNet = new Net();
        placeId = 0;
        transitionId = 0;


        root = FXMLLoader.load(new URL(
                "file",
                "",
                -1,
                "src\\main\\resources\\GUI\\panel.fxml"));


        tabPane = (TabPane) ((BorderPane) root.getChildren().get(0)).getChildren().get(0);
        tab = tabPane.getTabs().get(0);
        scrollPane = (ScrollPane) tab.getContent();
        pane = (AnchorPane) scrollPane.getContent();

        pane.setPrefHeight(2000);
        pane.setPrefWidth(5000);

        treeView = (TreeView<String>) root.getChildren().get(2);
        rootItem = new TreeItem<String>("Outer Net");
        rootItem.setExpanded(true);
        treeView.setRoot(rootItem);







        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    if (placeFlag) {
                        placeId ++ ;
                        Place pl = new Place(placeId);
                        pl.setStartPoint(new Point(event.getX(), event.getY()));

                        PlaceGUI place = new PlaceGUI(self, pl);
                        pane.getChildren().addAll(place.getRoot());

                        listNodes.add(place);

                        outerNet.addNode(pl);
                        rootItem.getChildren().add(new TreeItem<String>("P" + placeId));

                        placeFlag = false;
                    } else if (transFlag) {
                        transitionId ++ ;
                        Transition tr = new Transition(transitionId);
                        tr.setStartPoint(new Point(event.getX(), event.getY()));

                        TransitionGUI transition = new TransitionGUI(self, tr);
                        pane.getChildren().addAll(transition.getRoot());

                        listNodes.add(transition);

                        outerNet.addNode(tr);

                        transFlag = false;
                    }
                }
            }
        });




        final Button btn = (Button) ((HBox)((BorderPane)root.getChildren().get(0)).getChildren().get(1)).getChildren().get(0);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                placeFlag = true;

            }
        });

        final Button btn1 = (Button) ((HBox)((BorderPane)root.getChildren().get(0)).getChildren().get(1)).getChildren().get(1);
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                transFlag = true;

            }
        });



        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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
                ArcWithGUI arcWithGUI = new ArcWithGUI(pane, arc);
                listArcs.add(arcWithGUI);
                pane.getChildren().add(arcWithGUI.getLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            isClicked = Boolean.FALSE;
        }
    }
/*
    public void redrawArc(NodeGUI node) {
        for (NodeGUI element : listNodes) {
            for (Arc arc : element.getNode().getArcs()) {
                if (arc.getTarget() == node.getNode()) {
                    for (ArcWithGUI arcWithGUI : listArcs) {
                        if (arcWithGUI.getArc() == arc) {
                            arcWithGUI.getLine().setEndX(node.getRoot().getLayoutX());
                            arcWithGUI.getLine().setEndY(node.getRoot().getLayoutY());
                        }
                    }
                } else if (arc.getSource() == node.getNode()) {
                    for (ArcWithGUI arcWithGUI : listArcs) {
                        if (arcWithGUI.getArc() == arc) {
                            arcWithGUI.getLine().setStartX(node.getRoot().getLayoutX());
                            arcWithGUI.getLine().setStartY(node.getRoot().getLayoutY());
                        }
                    }
                }
            }
        }
    }*/

    public void redrawArc(NodeGUI node) {
        for (NodeGUI element : listNodes) {
            for (Arc arc : element.getNode().getArcs()) {
                if (arc.getTarget() == node.getNode() || arc.getSource() == node.getNode()) {
                    for (ArcWithGUI arcWithGUI : listArcs) {
                        if (arcWithGUI.getArc() == arc) {
                            arcWithGUI.drawArrow(arc.getSource().getStartPoint(), arc.getTarget().getStartPoint());
                        }
                    }
                }
            }
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
