package GUI;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

        

    }
}
