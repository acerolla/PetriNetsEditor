package GUI;

import io.NetReader;
import io.NetWriter;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import model.Arc;
import model.Node;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 21.03.2017.
 */
public class MainPanel extends Application {

    private Pane root;

    private TabPane tabPane;


    private TreeView<String> treeView;
    private TreeItem<String> rootItem;

    private TabExtension activeTab;



    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Petri Nets Editor");
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(450);



        root = FXMLLoader.load(new URL(
                "file",
                "",
                -1,
                "src\\main\\resources\\GUI\\panel.fxml"));


        tabPane = (TabPane) ((BorderPane) root.getChildren().get(0)).getChildren().get(0);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);

        tabPane.getTabs().clear();

        activeTab = new TabExtension();
        activeTab.setText("Outer Net");
        activeTab.setClosable(false);
        tabPane.getTabs().add(activeTab);

        treeView = (TreeView<String>) root.getChildren().get(2);
        rootItem = new TreeItem<String>("Outer Net");
        rootItem.setExpanded(true);
        treeView.setRoot(rootItem);
        treeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            public TreeCell<String> call(TreeView<String> param) {
                return new TreeItemImpl(MainPanel.this);
            }
        });




        final Button btn1 = (Button) ((HBox)((BorderPane)root.getChildren().get(0)).getChildren().get(1)).getChildren().get(0);
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                activeTab.addPlaceClicked();

            }
        });

        final Button btn2 = (Button) ((HBox)((BorderPane)root.getChildren().get(0)).getChildren().get(1)).getChildren().get(1);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                activeTab.addTransitionClicked();
            }
        });


        MenuBar menuBar = (MenuBar) ((BorderPane)root).getTop();
        Menu fileMenu = menuBar.getMenus().get(0);
        Menu editMenu = menuBar.getMenus().get(1);

        MenuItem saveFileMenuItem = fileMenu.getItems().get(0);
        saveFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                save(primaryStage);
            }
        });

        MenuItem openFileMenuItem = fileMenu.getItems().get(1);
        openFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                open(primaryStage);
            }
        });

        MenuItem refreshEditMenuItem = editMenu.getItems().get(0);
        refreshEditMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                refresh();
            }
        });

        Button btn = new Button("click me");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                int i = 0;
            }
        });
        activeTab.getAnchorPane().getChildren().add(btn);


        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                activeTab = (TabExtension) newValue;
            }
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void refresh() {
        tabPane.getTabs().clear();
        activeTab = new TabExtension();
        activeTab.setText("Outer Net");
        activeTab.setClosable(false);
        tabPane.getTabs().add(activeTab);

        rootItem = new TreeItem<String>("Outer Net");
        rootItem.setExpanded(true);
        treeView.setRoot(rootItem);
        treeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            public TreeCell<String> call(TreeView<String> param) {
                return new TreeItemImpl(MainPanel.this);
            }
        });
        Place.setPlaceId(0);
        Transition.setTransitionId(0);
    }

    private void save(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            Net net = ((TabExtension)tabPane.getTabs().get(0)).getNet();
            NetWriter.write(file, net);
        }
    }

    private void open(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            if (!file.getName().contains(".xml")) {
                return;
            }
            refresh();
            Net net = NetReader.read(file);
            TabExtension tab = (TabExtension) tabPane.getTabs().get(0);
            tab.setNet(net);
            tab.drawNet();
            createTree(treeView.getRoot(), net);

            Place.setPlaceId(net.getLastPlace());
            Transition.setTransitionId(net.getLastTransition());
        }
    }

    private void createTree(TreeItem<String> treeItem, Net net) {
        for (Node node : net.getNodes()) {
            if (node.getClass() == Place.class) {
                for (Token token : ((Place)node).getTokens()) {
                    if (token.getClass() == NetToken.class) {
                        TreeItem<String> newItem = new TreeItem<String>();
                        newItem.setValue(((NetToken)token).getLabel());
                        treeItem.getChildren().add(newItem);
                        createTree(newItem,((NetToken) token).getInnerNet());
                    }
                }
            }
        }
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public TabExtension getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(TabExtension tab) {
        this.activeTab = tab;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
