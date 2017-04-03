package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import model.Node;
import model.Place;

/**
 * Created by Acerolla on 03.04.2017.
 */
public class PlaceGUI extends NodeGUI {



    private Label id;
    private Circle circle;

    private ContextMenuPlace contextMenu;


    public PlaceGUI(MainPanel mainPanel, Node node) {
        super(mainPanel, node);



        circle = new Circle(0, 0, 40);
        circle.setFill(Paint.valueOf("WHITE"));
        circle.setStroke(Paint.valueOf("BLACK"));
        circle.setStrokeType(StrokeType.OUTSIDE);

        id = new Label("P" + node.getId());
        id.setLayoutY(-15 - circle.getRadius());
        id.setLayoutX(-circle.getRadius());

        root.setLayoutX(node.getStartPoint().getX());
        root.setLayoutY(node.getStartPoint().getY());

        root.getChildren().add(id);
        root.getChildren().add(circle);

        initialize();
    }

    void initialize() {
        super.initialize();

        contextMenu = new ContextMenuPlace(this);

        root.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            public void handle(ContextMenuEvent event) {
                contextMenu.show(root, event.getScreenX(), event.getScreenY());
            }
        });

    }
}
