package GUI;

import GUI.PlaceWithGUI;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.BasicToken;
import model.NetToken;
import model.Place;
import model.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 22.03.2017.
 */
public class ContextMenuPlace extends ContextMenu {

    private PlaceGUI parent;
    private TreeItem<String> out;

    public ContextMenuPlace(PlaceGUI parent) {
        super();
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        Menu addMenu    = new Menu("Add");
        Menu removeMenu = new Menu("Remove");
        //MenuItem itemRemoveInnerNodes = new MenuItem("Remove Inner Nodes");

        MenuItem itemAddNetToken = new MenuItem("Add Net Token");
        itemAddNetToken.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {


                TabExtension tab = new TabExtension();

                parent.getTab().getTabPane().getTabs().add(tab);

                NetToken netToken = new NetToken();
                netToken.setInnerNet(tab.getNet());

                parent.addToken(netToken);

                tab.setText("P" + parent.getNode().getId() + "-NT:" + parent.getCountNetToken());
                netToken.setLabel(tab.getText());

                TreeView<String> treeView = (TreeView<String>) ((BorderPane)(parent.getTab().getTabPane().getParent()).getParent()).getLeft();
                findItem(treeView.getRoot(), parent.getTab().getText());
                TreeItem<String> newItem = new TreeItem<String>(tab.getText());
                newItem.setExpanded(true);
                out.getChildren().add(newItem);




            }
        });

        MenuItem itemAddBasicToken = new MenuItem("Add Basic Token");
        itemAddBasicToken.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                parent.addToken(new BasicToken());
            }
        });

        MenuItem itemRemoveToken = new MenuItem("Remove Basic Token");
        itemRemoveToken.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                parent.removeToken();
            }
        });

        MenuItem itemSelfRemove = new MenuItem("Remove This Node");
        itemSelfRemove.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TreeView<String> treeView = (TreeView<String>) ((BorderPane)(parent.getTab().getTabPane().getParent()).getParent()).getLeft();
                findItem(treeView.getRoot(), parent.getTab().getText());

                List<TreeItem<String>> forRemove = new ArrayList<TreeItem<String>>();
                for (TreeItem<String> item: out.getChildren()) {
                    if (item.getValue().contains("P" + parent.getNode().getId())) {
                        forRemove.add(item);
                    }
                }
                out.getChildren().removeAll(forRemove);

                List<TabExtension> tabs = new ArrayList<TabExtension>();
                parent.findAllTabs(tabs);
                parent.getTab().getTabPane().getTabs().removeAll(tabs);
                parent.removeNode();
            }
        });




        addMenu.getItems().addAll(itemAddBasicToken, itemAddNetToken);
        removeMenu.getItems().addAll(itemSelfRemove/*, itemRemoveInnerNodes*/, itemRemoveToken);

        this.getItems().addAll(addMenu, removeMenu);
    }

    private void findItem(TreeItem<String> root, String label) {
        if (root.getValue().equals(label)) {
            out = root;
            return;
        }
        for (TreeItem<String> item : root.getChildren()) {
            findItem(item, label);
        }

    }

}
