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
        initialize();
    }

    private void initialize() {

        ContextMenu rootContextMenu
                = ContextMenuBuilder.create()
                .items(
                        MenuItemBuilder.create()
                                .text("Menu Item")
                                .onAction(
                                        new EventHandler<ActionEvent>()
                                        {
                                            //@Override
                                            public void handle(ActionEvent arg0)
                                            {
                                                System.out.println(arg0.getSource());
                                                System.out.println(arg0.getTarget());
                                            }
                                        }
                                )
                                .build()
                )
                .build();


        parent.setContextMenu(rootContextMenu);
    }
}
