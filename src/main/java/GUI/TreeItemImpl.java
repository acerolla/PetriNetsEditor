package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 12.04.2017.
 */
public class TreeItemImpl extends TreeCell<String> {

    private TextField textField;
    private ContextMenu menu = new ContextMenu();
    private Net net;
    private String text;
    private NetToken netToken;
    private Place place;
    private MainPanel main;

    public TreeItemImpl(MainPanel main) {
        this.main = main;
        MenuItem removeItem = new MenuItem("Remove Net");
        menu.getItems().add(removeItem);
        removeItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Remove");
                if (getTreeItem().getValue().equalsIgnoreCase("Outer Net")) {
                    return;
                }


                TabExtension outerTab = (TabExtension) TreeItemImpl.this.main.getTabPane().getTabs().get(0);
                Net net = outerTab.getNet();
                for (Node node : net.getNodes()) {
                    if (node.getClass() == Place.class) {
                        findToken((Place)node);
                    }
                }
                List<Tab> forRemove = new ArrayList<Tab>();
                for (Node node : netToken.getInnerNet().getNodes()) {
                    if (node.getClass() == Place.class) {
                        findAllTabs(forRemove, (Place)node);
                    }
                }
                for (Tab tab : TreeItemImpl.this.main.getTabPane().getTabs()) {
                    if (tab.getText().equals(getTreeItem().getValue())) {
                        forRemove.add(tab);
                    }
                }
                TreeItemImpl.this.main.getTabPane().getTabs().removeAll(forRemove);



                place.removeToken(netToken);
                getTreeItem().getParent().getChildren().remove(getTreeItem());
            }
        });

        MenuItem openItem = new MenuItem("Open Net");
        menu.getItems().add(openItem);
        openItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Open");
                boolean found = false;
                for (Tab tab : TreeItemImpl.this.main.getTabPane().getTabs()) {
                    if (tab.getText().equals(getTreeItem().getValue())) {
                        TreeItemImpl.this.main.getTabPane().getSelectionModel().select(tab);
                        found = true;
                    }
                }
                if (!found) {
                    drawNet();
                }
            }
        });
        setContextMenu(menu);

    }

    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }
        setText(null);
        setGraphic(textField);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(getTreeItem().getGraphic());
                if (
                        !getTreeItem().isLeaf()&&getTreeItem().getParent()!= null
                        ){
                    //setContextMenu(addMenu);
                }
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

            //@Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(textField.getText());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });

    }

    private void drawNet() {
        TabExtension outer = (TabExtension) main.getTabPane().getTabs().get(0);
        for (NodeGUI node : outer.getNodesGUI()) {
            if (node.getClass() == PlaceGUI.class) {
                findNet((Place) node.getNode());
            }
        }

        TabExtension newTab = new TabExtension();
        newTab.setNet(net);
        newTab.setText(text);
        main.getTabPane().getTabs().add(newTab);
        newTab.drawNet();
    }

    private void findNet(Place place) {
        for (Token token : place.getTokens()) {
            if (token.getClass() == NetToken.class) {
                if (((NetToken)token).getLabel().equals(getTreeItem().getValue())) {
                    net = ((NetToken)token).getInnerNet();
                    text = ((NetToken)token).getLabel();
                } else {
                    for (Node node : ((NetToken)token).getInnerNet().getNodes()) {
                        if (node.getClass() == Place.class) {
                            findNet((Place) node);
                        }
                    }
                }
            }
        }

    }

    private void findToken(Place place) {
        for (Token token : place.getTokens()) {
            if (token.getClass() == NetToken.class) {
                if (((NetToken)token).getLabel().equals(getTreeItem().getValue())) {
                    netToken = (NetToken) token;
                    this.place = place;
                } else {
                    for (Node node : ((NetToken)token).getInnerNet().getNodes()) {
                        if (node.getClass() == Place.class) {
                            findToken((Place) node);
                        }
                    }
                }
            }
        }
    }

    private void findAllTabs(List<Tab> forRemove, Place place) {
        for (Token token : place.getTokens()) {
            if (token.getClass() == NetToken.class) {
                for (Tab tab : main.getTabPane().getTabs()) {
                    if (tab.getText() == ((NetToken)token).getLabel()) {
                        forRemove.add(tab);
                    }
                }
                for (Node node : ((NetToken)token).getInnerNet().getNodes()) {
                    if (node.getClass() == Place.class) {
                        findAllTabs(forRemove, (Place)node);
                    }
                }
            }
        }
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}


