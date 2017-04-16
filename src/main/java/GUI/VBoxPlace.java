package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Place;

/**
 * Created by Acerolla on 16.04.2017.
 */
public class VBoxPlace extends VBox {

    Place place;

    Label placeIdLabel;
    TextField placeIdField;

    Label placeLabelLabel;
    TextField placeLabelField;

    Button editButton;

    public VBoxPlace(Place place) {
        super(5);

        setPrefWidth(150);
        this.place = place;

        getChildren().add(new Label("     <----PROPERTIES---->"));
        getChildren().add(new Label());

        initialize();
    }

    private void initialize() {
        placeIdLabel = new Label("Place ID:");
        getChildren().add(placeIdLabel);
        placeIdField = new TextField("P" + place.getId());
        placeIdField.setEditable(false);
        //netIdField.setStyle("-fx-background-color: lightgrey;");
        getChildren().add(placeIdField);

        getChildren().add(new Label());
        placeLabelLabel = new Label("Place label:");
        getChildren().add(placeLabelLabel);
        placeLabelField = new TextField(place.getLabel());
        getChildren().add(placeLabelField);
        editButton = new Button("Submit");
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (place.getLabel().equals(placeLabelField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Just to inform you...");
                    alert.setContentText("Place has the same label");
                    alert.show();
                    return;
                }
                if (placeLabelField.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Just to inform you...");
                    alert.setContentText("No info to submit ):");
                    alert.show();
                    return;
                }
                place.setLabel(placeLabelField.getText());
                MainPanel.lastAction("Changes in Net(" + place.getId() + ") submitted.");
            }
        });
        getChildren().add(editButton);


    }
}
