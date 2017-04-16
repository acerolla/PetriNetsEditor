package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Net;

/**
 * Created by Acerolla on 16.04.2017.
 */
public class VBoxNet extends VBox {

    private Net net;

    private Label netIdLabel;
    private TextField netIdField;

    private TextField netLabelField;
    private Label netLabelLabel;
    private Button editButton;


    public VBoxNet(Net net) {
        super(5);
        this.net = net;
        prefWidth(150);

        getChildren().add(new Label("     <----PROPERTIES---->"));
        getChildren().add(new Label());

        initialize();
    }

    private void initialize() {
        netIdLabel = new Label("Net ID:");
        getChildren().add(netIdLabel);
        netIdField = new TextField(net.getId());
        netIdField.setEditable(false);
        //netIdField.setStyle("-fx-background-color: lightgrey;");
        getChildren().add(netIdField);

        getChildren().add(new Label());
        netLabelLabel = new Label("Net label:");
        getChildren().add(netLabelLabel);
        netLabelField = new TextField(net.getLabel());
        getChildren().add(netLabelField);
        editButton = new Button("Submit");
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (net.getLabel().equals(netLabelField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Just to inform you...");
                    alert.setContentText("Net has the same label");
                    alert.show();
                    return;
                }
                if (netLabelField.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Just to inform you...");
                    alert.setContentText("No info to submit.");
                    alert.show();
                    return;
                }
                net.setLabel(netLabelField.getText());
                MainPanel.lastAction("Changes in Net(" + net.getId() + ") submitted.");
            }
        });
        getChildren().add(editButton);
    }
}
