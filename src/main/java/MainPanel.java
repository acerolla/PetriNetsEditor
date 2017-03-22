import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
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
    private ScrollPane scrollPane;
    private AnchorPane pane;


    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Petri Nets Editor");
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(450);
        root = FXMLLoader.load(getClass().getResource("panel.fxml"));

        hBox       = (HBox) root.getChildren().get(0);
        vBox       = (VBox) root.getChildren().get(1);
        scrollPane = (ScrollPane) root.getChildren().get(2);
        pane       = (AnchorPane) scrollPane.getContent();

        pane.setPrefHeight(2000);
        pane.setPrefWidth(5000);


        final Button btn = new Button("Click me!");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Position pos = new Position(pane);
                pane.getChildren().addAll(pos.getNode());
            }
        });
        pane.getChildren().add(btn);






        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
