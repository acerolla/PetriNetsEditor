package GUI;

import GUI.PlaceWithGUI;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by Acerolla on 22.03.2017.
 */
public class ContextMenuPosition extends ContextMenu {

    private PlaceWithGUI parent;

    public ContextMenuPosition(PlaceWithGUI parent) {
        super();
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        Menu addMenu    = new Menu("Add");
        Menu removeMenu = new Menu("Remove");
        MenuItem itemRemoveInnerNodes = new MenuItem("Remove Inner Nodes");
        MenuItem itemAddInnerNodes = new MenuItem("Add Inner Nodes");
        MenuItem itemAddToken = new MenuItem("Add Token");
        MenuItem itemRemoveToken = new MenuItem("Remove Token");
        MenuItem itemSelfRemove = new MenuItem("Remove This Node");


        addMenu.getItems().addAll(itemAddInnerNodes, itemAddToken);
        removeMenu.getItems().addAll(itemSelfRemove, itemRemoveInnerNodes, itemRemoveToken);

        this.getItems().addAll(addMenu, removeMenu);
    }

}
