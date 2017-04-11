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

    private int countNetToken,
                countBasicToken;



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

    public int getCountNetToken() {
        return countNetToken;
    }

    public int getCountBasicToken() {
        return countBasicToken;
    }

    public void addToken(Token token) {
        ((Place)node).addToken(token);
        if (token.getClass() == BasicToken.class) {
            countBasicToken ++ ;
        } else {
            countNetToken ++ ;
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


    void initialize() {
        super.initialize();

        contextMenu = new ContextMenuPlace(this);

        root.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            public void handle(ContextMenuEvent event) {
                contextMenu.show(root, event.getScreenX(), event.getScreenY());
            }
        });

    }


}
