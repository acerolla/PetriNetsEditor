package GUI;

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
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import model.Arc;

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

    TabExtension activeTab;



    public void start(Stage primaryStage) throws Exception {
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
                return new TreeItemImpl();
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


    public TabPane getTabPane() {
        return tabPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
