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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
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

    private static TextField textField;


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

        textField = (TextField) root.getChildren().get(3);
        textField.setEditable(false);


        final Button btn1 = (Button) ((HBox)((BorderPane)root.getChildren().get(0)).getChildren().get(1)).getChildren().get(0);
        Circle circle = new Circle(5);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        //btn1.setText("add");
        btn1.setGraphic(circle);
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MainPanel.lastAction("Adding new Position.");
                activeTab.addPlaceClicked();

            }
        });

        final Button btn2 = (Button) ((HBox)((BorderPane)root.getChildren().get(0)).getChildren().get(1)).getChildren().get(1);
        Rectangle rectangle = new Rectangle(4, 15);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        btn2.setGraphic(rectangle);
        //btn2.setText("add");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MainPanel.lastAction("Adding new Transition.");
                activeTab.addTransitionClicked();
            }
        });

        final Button btn3 = (Button) ((HBox)((BorderPane)root.getChildren().get(0)).getChildren().get(1)).getChildren().get(2);
        Group group = new Group();
        Line line = new Line();
        line.setStartX(5);
        line.setStartY(5);
        line.setEndX(15);
        line.setEndY(5);
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                15.0, 2.0,
                15.0, 8.0,
                18.0, 5.0
        );
        group.getChildren().addAll(line, polygon);
        btn3.setGraphic(group);
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MainPanel.lastAction("Selecting 2 nodes to connect.");
                activeTab.connectorClicked();
            }
        });

        MenuBar menuBar = (MenuBar) ((BorderPane)root).getTop();
        Menu fileMenu = menuBar.getMenus().get(0);
        Menu editMenu = menuBar.getMenus().get(1);

        MenuItem saveFileMenuItem = fileMenu.getItems().get(0);
        saveFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                save(primaryStage);
                MainPanel.lastAction("Net successfully saved!");
            }
        });

        MenuItem openFileMenuItem = fileMenu.getItems().get(1);
        openFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                open(primaryStage);
                MainPanel.lastAction("Net successfully opened!");
            }
        });

        MenuItem closeMenuItem = fileMenu.getItems().get(3);
        closeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        MenuItem refreshEditMenuItem = editMenu.getItems().get(0);
        refreshEditMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                refresh();
            }
        });




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
            try {
                NetWriter.write(file, net);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setHeaderText("Alarm!!! IOException!!!");
                alert.show();
            }
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
            try {
                Net net = NetReader.read(file);
                TabExtension tab = (TabExtension) tabPane.getTabs().get(0);
                tab.setNet(net);
                tab.drawNet();
                createTree(treeView.getRoot(), net);

                Place.setPlaceId(net.getLastPlace(0));
                Transition.setTransitionId(net.getLastTransition(0));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setHeaderText("Alarm!!! IOException!!!");
                alert.show();
            }
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

    public static void lastAction(String text) {
        textField.setText("Last action:   " + text);
    }
}
