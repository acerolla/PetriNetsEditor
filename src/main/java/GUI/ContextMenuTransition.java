package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by Acerolla on 23.03.2017.
 */
public class ContextMenuTransition extends ContextMenu{

    private TransitionGUI parent;

    public ContextMenuTransition(TransitionGUI parent) {
        super();
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        Menu removeMenu = new Menu("Remove");
        MenuItem itemSelfRemove = new MenuItem("Remove This Node");
        itemSelfRemove.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                parent.removeNode();
                MainPanel.lastAction( parent.toString() + " successfully removed");
            }
        });

        removeMenu.getItems().addAll(itemSelfRemove);

        this.getItems().addAll(removeMenu);
    }


}
