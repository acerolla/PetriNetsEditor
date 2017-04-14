package GUI;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.Place;

import java.net.URL;

/**
 * Created by Acerolla on 22.03.2017.
 */
public class PlaceWithGUI {

    private Group node;

    private Pane root;
    ContextMenuPlace contextMenu;

    private Place place;

    private boolean isDragDetected;
    private double
            startX,
            startY;

   /* public PlaceWithGUI(Pane root, Place place) {
        try {
            this.root = root;

            //node = FXMLLoader.load(getClass().getResource("GUI/place.fxml"));
            node = FXMLLoader.load(new URL(
                    "file",
                    "",
                    -1,
                    "src\\main\\resources\\GUI\\place.fxml"));
            this.place = place;
            node.setLayoutX(this.place.getStartPoint().getX());
            node.setLayoutY(this.place.getStartPoint().getY());

            ((Label) node.getChildren().get(2)).setText("P" + this.place.getId());

            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    private void initialize() {

        //contextMenu = new ContextMenuPlace(this);

        node.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            public void handle(ContextMenuEvent event) {
                contextMenu.show(node, event.getScreenX(), event.getScreenY());
            }
        });

        node.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                isDragDetected = true;
                //System.out.println("DRAGGED");

                /*
                if (pane.getPrefWidth() - 200 < event.getSceneX()) {
                    pane.setPrefWidth(event.getSceneX() + 200);
                }
                if (pane.getPrefHeight() - 200 < event.getSceneY()) {
                    pane.setPrefHeight(event.getSceneY() + 200);
                }*/
            }
        });
        node.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (isDragDetected) {
                    node.setLayoutX(event.getSceneX() - startX);
                    node.setLayoutY(event.getSceneY() - startY);
                    isDragDetected = false;
                }
                //System.out.println("RELEASED");
            }
        });
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                startX = event.getSceneX() - node.getLayoutX();
                startY = event.getSceneY() - node.getLayoutY();




                //System.out.println("PRESSED");
            }
        });
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //btn.setLayoutX(btn.getLayoutY() + 100);
                //btn.setLayoutY(btn.getLayoutX() + 150);
                //pane.setPrefWidth(btn.getLayoutX() + btn.getWidth());
                //pane.setPrefHeight(btn.getLayoutY() + btn.getHeight());
                //System.out.println("CLICKED");
            }
        });
    }

    public Place getPlace() {
        return place;
    }

    public Group getNode() {
        return node;
    }
}