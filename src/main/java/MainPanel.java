import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by Acerolla on 21.03.2017.
 */
public class MainPanel extends Application {

    private Pane root;
    private HBox hBox;
    private VBox vBox;
    private TabPane tabPane;
    private ScrollPane scrollPane;
    private AnchorPane pane;
    private Tab tab;


    private boolean posFlag;
    private boolean transFlag;

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Petri Nets Editor");
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(450);

        root = FXMLLoader.load(getClass().getResource("panel.fxml"));

        tabPane = (TabPane) root.getChildren().get(1);
        tab = tabPane.getTabs().get(0);
        scrollPane = (ScrollPane) tab.getContent();
        pane = (AnchorPane) scrollPane.getContent();

        pane.setPrefHeight(2000);
        pane.setPrefWidth(5000);


        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    if (posFlag) {
                        Position pos = new Position(pane, event.getX(), event.getY());
                        pane.getChildren().addAll(pos.getNode());
                        posFlag = false;
                    } else if (transFlag) {
                        Transition trans = new Transition(pane, event.getX(), event.getY() - 20);
                        pane.getChildren().addAll(trans.getNode());
                        transFlag = false;
                    }
                }
            }
        });

        final Button btn = new Button("Click me Pos!");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                posFlag = true;
                //Position pos = new Position(pane, 150, 300);
                //pane.getChildren().addAll(pos.getNode());
            }
        });
        pane.getChildren().add(btn);

        final Button btn1 = new Button("Click me Trans!");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                transFlag = true;
                //Transition trans = new Transition(pane, 150, 300);
                //pane.getChildren().addAll(trans.getNode());
            }
        });
        btn1.setLayoutX(100);
        pane.getChildren().add(btn1);



        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
