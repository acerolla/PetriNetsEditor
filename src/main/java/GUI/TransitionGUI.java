package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Node;
import model.Place;
import model.Transition;

/**
 * Created by Acerolla on 03.04.2017.
 */
public class TransitionGUI extends NodeGUI {

    private Label id;
    private Rectangle rectangle;

    private ContextMenuTransition contextMenu;


    public TransitionGUI(TabExtension tabExtension, Node node) {
        super(tabExtension, node);


        rectangle = new Rectangle(-10, -40, 20, 80);
        rectangle.setFill(Paint.valueOf("WHITE"));
        rectangle.setStroke(Paint.valueOf("BLACK"));
        rectangle.setStrokeType(StrokeType.OUTSIDE);

        id = new Label("T" + node.getId());
        id.setLayoutX(rectangle.getX());
        id.setLayoutY(rectangle.getY() - 15);


        root.setLayoutX(node.getStartPoint().getX());
        root.setLayoutY(node.getStartPoint().getY());

        root.getChildren().add(id);
        root.getChildren().add(rectangle);



        initialize();
    }

    void initialize() {

        super.initialize();

        contextMenu = new ContextMenuTransition(this);

        root.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            public void handle(ContextMenuEvent event) {
                contextMenu.show(root, event.getScreenX(), event.getScreenY());
            }
        });


    }
}
