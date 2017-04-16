package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

    private Label
            basicTokenLabel,
            netTokenLabel;





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

    public void addToken(Token token) {
        ((Place)node).addToken(token);
        if (token.getClass() == BasicToken.class) {
            ((Place)node).incCountBasicToken();

        } else {
            ((Place) node).incCountNetToken();
        }
        drawTokens();
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
        if (((Place)node).getCountBasicToken() == 0) {
            return;
        }
        ((Place)node).decCountBasicToken();

        for (Token token : ((Place)getNode()).getTokens()) {
            if (token.getClass() == BasicToken.class) {
                ((Place)getNode()).removeToken(token);
                drawTokens();
                return;
            }
        }

        /*
        if (basicTokenLabel == null) {
            return;
        } else {
            if (basicTokenLabel.getText().equals("1")) {
                root.getChildren().remove(basicTokenLabel);
                basicTokenLabel = null;
            } else {
                ((Place)node).decCountBasicToken();
                basicTokenLabel.setText("" + ((Place) node).getCountBasicToken());
            }
            for (Token token : ((Place)getNode()).getTokens()) {
                if (token.getClass() == BasicToken.class) {
                    ((Place)getNode()).removeToken(token);
                    return;
                }
            }
        }*/
    }

    public void drawTokens() {
        int bCount = 0;
        int nCount = 0;
        for (Token token : ((Place) getNode()).getTokens()) {
            if (token.getClass() == BasicToken.class) {
                bCount++;
            } else {
                nCount++;
            }
        }

        ((Place) node).setCountBasicToken(bCount);
        ((Place) node).setCountNetToken(nCount);

        if (((Place) node).getCountNetToken() == 0) {
            root.getChildren().remove(netTokenLabel);
        } else if (((Place) node).getCountNetToken() == 1) {
            root.getChildren().remove(netTokenLabel);
            netTokenLabel = new Label("1");
            netTokenLabel.setLayoutX(-5);
            netTokenLabel.setLayoutY(-1);
            Circle circle = new Circle(5);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            netTokenLabel.setGraphic(circle);
            root.getChildren().add(netTokenLabel);
        } else if (((Place) node).getCountNetToken() > 1 && netTokenLabel != null) {
            netTokenLabel.setText("" + ((Place) node).getCountNetToken());

        } else if (((Place) node).getCountNetToken() > 1 && netTokenLabel == null) {
            netTokenLabel = new Label("" + ((Place) node).getCountNetToken());
            netTokenLabel.setId("basic");
            netTokenLabel.setLayoutX(-5);
            netTokenLabel.setLayoutY(-1);
            Circle circle = new Circle(5);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            netTokenLabel.setGraphic(circle);
            root.getChildren().add(netTokenLabel);
        }
        if (((Place) node).getCountBasicToken() == 0) {
            root.getChildren().remove(basicTokenLabel);
        } else if (((Place) node).getCountBasicToken() == 1) {
            root.getChildren().remove(basicTokenLabel);
            basicTokenLabel = new Label("1");
            basicTokenLabel.setLayoutX(-5);
            basicTokenLabel.setLayoutY(-15);
            Circle circle = new Circle(5);
            circle.setFill(Paint.valueOf("BLACK"));
            basicTokenLabel.setGraphic(circle);
            root.getChildren().add(basicTokenLabel);
        } else if (((Place) node).getCountBasicToken() > 1 && basicTokenLabel != null) {
            basicTokenLabel.setText("" + ((Place) node).getCountBasicToken());

        } else if (((Place) node).getCountBasicToken() > 1 && basicTokenLabel == null) {
            basicTokenLabel = new Label("" + ((Place) node).getCountBasicToken());
            basicTokenLabel.setId("basic");
            basicTokenLabel.setLayoutX(-5);
            basicTokenLabel.setLayoutY(-15);
            Circle circle = new Circle(5);
            circle.setFill(Paint.valueOf("BLACK"));
            basicTokenLabel.setGraphic(circle);
            root.getChildren().add(basicTokenLabel);
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
