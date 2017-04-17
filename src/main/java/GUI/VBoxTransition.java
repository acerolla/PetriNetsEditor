package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.Arc;
import model.Place;
import model.Token;
import model.Transition;

/**
 * Created by Acerolla on 16.04.2017.
 */
public class VBoxTransition extends VBox {

    Transition transition;

    Label transitionIdLabel;
    TextField transitionIdField;

    Label neededTokenLabel;
    TextField neededTokenField;
    Button submitNeededToken;

    public VBoxTransition(Transition transition) {
        super(5);

        setPrefWidth(150);
        this.transition = transition;

        getChildren().add(new Label("     <----PROPERTIES---->"));
        getChildren().add(new Label());

        initialize();
    }

    private void initialize() {
        transitionIdLabel = new Label("Transition ID:");
        getChildren().add(transitionIdLabel);
        transitionIdField = new TextField("T" + transition.getId());
        transitionIdField.setEditable(false);
        //netIdField.setStyle("-fx-background-color: lightgrey;");
        getChildren().add(transitionIdField);

        getChildren().add(new Label());

        neededTokenLabel = new Label("Tokens needed:");
        getChildren().add(neededTokenLabel);
        neededTokenField = new TextField("" + transition.getCountNeededToken());
        getChildren().add(neededTokenField);
        submitNeededToken = new Button("Submit");
        submitNeededToken.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    transition.setCountNeededToken(Integer.parseInt(neededTokenField.getText()));
                    if (transition.getCountNeededToken() < 1) {
                        throw new Exception("");
                    }
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Alarm!!!");
                    alert.setContentText("Wrong format");
                    alert.show();
                }
            }
        });
        getChildren().add(submitNeededToken);

    }
}
