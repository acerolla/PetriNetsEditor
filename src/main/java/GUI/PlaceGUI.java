package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 03.04.2017.
 */
public class PlaceGUI extends NodeGUI {



    private Label id;
    private Circle circle;

    private ContextMenuPlace contextMenu;





    public PlaceGUI(TabExtension tabExtension, Node node) {
        super(tabExtension, node);



        circle = new Circle(0, 0, 40);
        circle.setFill(Paint.valueOf("WHITE"));
        circle.setStroke(Paint.valueOf("BLACK"));
        circle.setStrokeType(StrokeType.OUTSIDE);

        id = new Label("P" + node.getId());
        id.setLayoutY(-15 - circle.getRadius());
        id.setLayoutX(-circle.getRadius());

        root.setLayoutX(node.getStartPoint().getX());
        root.setLayoutY(node.getStartPoint().getY());

        root.getChildren().add(id);
        root.getChildren().add(circle);

        initialize();
    }

/*    public int getCountNetToken() {
        return countNetToken;
    }

    public int getCountBasicToken() {
        return countBasicToken;
    }
*/
    public void addToken(Token token) {
        ((Place)node).addToken(token);
        if (token.getClass() == BasicToken.class) {
            ((Place)node).incCountBasicToken();
            drawTokens();
        } else {
            ((Place) node).incCountNetToken();
        }
    }

    public void findAllTabs(List<TabExtension> tabs) {


        for (Token token : ((Place)getNode()).getTokens() ) {
            if (token.getClass() == NetToken.class) {
                for (Tab tab : getTab().getTabPane().getTabs()) {
                    if (((TabExtension)tab).getNet() == ((NetToken) token).getInnerNet()) {
                        tabs.add((TabExtension) tab);
                        for (NodeGUI nodeGUI : ((TabExtension) tab).getNodesGUI()) {
                            if (nodeGUI.getClass() == PlaceGUI.class) {
                                ((PlaceGUI)nodeGUI).findAllTabs(tabs);
                            }
                        }
                    }
                }
            }
        }

    }

    public void removeToken() {
        if (root.getChildren().size() == 2) {
            return;
        } else {
            Label label = (Label)root.getChildren().get(2);
            if (label.getText().equals("1")) {
                root.getChildren().remove(label);
            } else {
                ((Place)node).decCountBasicToken();
                label.setText("" + ((Place) node).getCountBasicToken());
            }
            for (Token token : ((Place)getNode()).getTokens()) {
                if (token.getClass() == BasicToken.class) {
                    ((Place)getNode()).removeToken(token);
                    return;
                }
            }
        }
    }

    private void drawTokens() {
        int bCount = 0;
        int nCount = 0;
        for (Token token : ((Place)getNode()).getTokens()) {
            if (token.getClass() == BasicToken.class) {
                bCount ++ ;
            } else {
                nCount ++ ;
            }
        }

        ((Place) node).setCountBasicToken(bCount);
        ((Place) node).setCountNetToken(nCount);

        if (((Place) node).getCountBasicToken() == 1) {
            Label label = new Label("1");
            label.setLayoutX(-5);
            label.setLayoutY(-8);
            Circle circle = new Circle(5);
            circle.setFill(Paint.valueOf("BLACK"));
            label.setGraphic(circle);
            root.getChildren().add(label);
        } else if (((Place) node).getCountBasicToken() > 1 && root.getChildren().size() == 3) {
            Label label = (Label)root.getChildren().get(2);
            label.setText("" + ((Place) node).getCountBasicToken());
        } else if (((Place) node).getCountBasicToken() > 1 && root.getChildren().size() == 2) {
            Label label = new Label("" + ((Place) node).getCountBasicToken());
            label.setLayoutX(-5);
            label.setLayoutY(-8);
            Circle circle = new Circle(5);
            circle.setFill(Paint.valueOf("BLACK"));
            label.setGraphic(circle);
            root.getChildren().add(label);
        }
    }


    void initialize() {
        super.initialize();

        contextMenu = new ContextMenuPlace(this);

        root.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            public void handle(ContextMenuEvent event) {
                contextMenu.show(root, event.getScreenX(), event.getScreenY());
            }
        });

        drawTokens();

    }


}
