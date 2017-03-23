import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by Acerolla on 23.03.2017.
 */
public class ContextMenuTransition extends ContextMenu{

    private Transition parent;

    public ContextMenuTransition(Transition parent) {
        super();
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        Menu removeMenu = new Menu("Remove");
        MenuItem itemSelfRemove = new MenuItem("Remove This Node");

        removeMenu.getItems().addAll(itemSelfRemove);

        this.getItems().addAll(removeMenu);
    }

}
