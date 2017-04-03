import GUI.PlaceGUI;
import GUI.PlaceWithGUI;
import GUI.TransitionGUI;
import GUI.TransitionWithGUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Net;
import model.Place;
import model.Point;
import model.Transition;

import java.net.URL;


/**
 * Created by Acerolla on 21.03.2017.
 */
public class JavaFXTest extends Application{

    private Pane root;
    private HBox hBox;
    private VBox vBox;
    private TabPane tabPane;
    private ScrollPane scrollPane;
    private AnchorPane pane;
    private Tab tab;
    private TreeView<String> treeView;
    private TreeItem<String> rootItem;

    private boolean placeFlag;
    private boolean transFlag;

    private Net outerNet;

    private int placeId,
            transitionId;

    private boolean isClicked;

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Petri Nets Editor");
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(450);
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



        final Button btn = new Button("Click me!");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                tab = new Tab("New Tab");
                tab.setContent(new ScrollPane());
                AnchorPane newPane = new AnchorPane();
                ((ScrollPane)tab.getContent()).setContent(newPane);
                newPane.setPrefSize(5000, 2000);
                newPane.getChildren().add(btn);
                tabPane.getTabs().add(tab);
            }
        });
        pane.getChildren().add(btn);


        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    if (placeFlag) {
                        placeId ++ ;
                        Place pl = new Place(placeId);
                        pl.setStartPoint(new Point(event.getX(), event.getY()));

                        //PlaceWithGUI place = new PlaceWithGUI(pane, pl);
                        //PlaceGUI place = new PlaceGUI(pane, pl);
                        //pane.getChildren().addAll(place.getRoot());



//                        outerNet.addNode(pl);
                        rootItem.getChildren().add(new TreeItem<String>("P" + placeId));

                        placeFlag = false;
                        int z = 0;
                    } else if (transFlag) {
                        transitionId ++ ;
                        Transition tr = new Transition(transitionId);
                        tr.setStartPoint(new Point(event.getX(), event.getY()));

                        //TransitionGUI transition = new TransitionGUI(pane, tr);
                        //pane.getChildren().addAll(transition.getRoot());


//                        outerNet.addNode(tr);

                        transFlag = false;
                        int z = 0;
                    }
                }
            }
        });




        final Button btn1 = (Button) ((HBox)((BorderPane)root.getChildren().get(0)).getChildren().get(1)).getChildren().get(0);
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                placeFlag = true;

            }
        });

        final Button btn2 = (Button) ((HBox)((BorderPane)root.getChildren().get(0)).getChildren().get(1)).getChildren().get(1);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                transFlag = true;

            }
        });


        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
