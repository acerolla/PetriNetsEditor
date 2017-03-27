import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


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

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Petri Nets Editor");
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(450);
        root = FXMLLoader.load(getClass().getResource("GUI/panel.fxml"));

        tabPane = (TabPane) root.getChildren().get(1);
        tab = tabPane.getTabs().get(0);
        scrollPane = (ScrollPane) tab.getContent();
        pane = (AnchorPane) scrollPane.getContent();

        pane.setPrefHeight(2000);
        pane.setPrefWidth(5000);


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



        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
