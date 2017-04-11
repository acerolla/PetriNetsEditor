package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

/**
 * Created by Acerolla on 11.04.2017.
 */
public class ContextMenuTreeItem extends ContextMenu {

    TreeView<String> parent;

    public ContextMenuTreeItem(TreeView<String> parent) {
        super();
        this.parent = parent;
        //initialize();
    }

    private void initialize() {

        show(parent, 0 ,0);


    }
}
