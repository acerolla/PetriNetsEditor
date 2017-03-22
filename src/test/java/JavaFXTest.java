import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MoveTo;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * Created by Acerolla on 21.03.2017.
 */
public class JavaFXTest extends Application{

    private void changeText(Button button){
        if (button.getText().equalsIgnoreCase("Click me")) {
            button.setText("Thanks");
        } else {
            button.setText("Click me");
        }
    }

    public void start(Stage primaryStage) throws Exception {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 100, 100);
        primaryStage.setScene(scene);


        final ContextMenu contextMenu = new ContextMenu();


        MenuItem item1 = new MenuItem("About");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("About");
            }
        });
        final MenuItem item2 = new MenuItem("Preferences");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("Preferences");

            }
        });
        contextMenu.getItems().addAll(item1, item2);




        final Button button = new Button("Click me");
        button.setLayoutX(40);
        button.setLayoutY(40);
        button.setContextMenu(contextMenu);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    changeText(button);
                } else if (event.getButton() == MouseButton.SECONDARY){

                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Another action").show();
                }
            }
        });
        button.setPrefSize(70, 20);
        Label label = new Label ("Don't touch it");
        label.setContextMenu(contextMenu);




        pane.getChildren().addAll(label, button);


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
