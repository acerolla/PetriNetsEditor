package GUI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Acerolla on 23.03.2017.
 */
public class ContextMenuAnchorPane extends ContextMenu {

    private AnchorPane parent;
    private double
            screenX,
            screenY;

    public ContextMenuAnchorPane(AnchorPane parent, double x, double y) {
        super();
        this.parent = parent;
        initialize();
        screenX = x;
        screenY = y;
    }

    private void initialize() {
        Menu addMenu = new Menu("Add");
        MenuItem itemAddPosition = new MenuItem("Add GUI.PlaceWithGUI Node");
        itemAddPosition.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            public void handle(Event event) {
                //PlaceWithGUI pos = new PlaceWithGUI(parent, screenX, screenY);
                //parent.getChildren().addAll(pos.getNode());
            }
        });
        MenuItem itemAddTransition = new MenuItem("Add GUI.TransitionWithGUI Node");
        itemAddTransition.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            public void handle(Event event) {
                //TransitionWithGUI trans = new TransitionWithGUI(parent, screenX, screenY);
                //parent.getChildren().addAll(trans.getNode());
            }
        });

        addMenu.getItems().addAll(itemAddPosition, itemAddTransition);

        this.getItems().addAll(addMenu);
    }

}
