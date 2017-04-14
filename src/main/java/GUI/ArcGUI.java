package GUI;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import model.Arc;
import model.Point;
import model.Transition;

/**
 * Created by Acerolla on 02.04.2017.
 */
public class ArcGUI {
    private Group group;

    private Line
        line;

    private Polygon arrow;

    private Arc arc;

    private Point
            pWest,
            pEast,
            pNorth,
            pSouth,
            pMin;

    private Pane root;

    public ArcGUI(Pane root, Arc arc) {

        this.root = root;
        this.arc = arc;
        this.group = new Group();


        line = new Line();
        arrow = new Polygon();
        arrow.setFill(Paint.valueOf("BLACK"));

        group.getChildren().addAll(line, arrow);
        drawArrow(arc.getSource().getStartPoint(), arc.getTarget().getStartPoint());




        line.setStrokeType(StrokeType.CENTERED);

        initialize();
    }

    private void initialize() {

    }

    private Point findMin(Point p1, Point p2, boolean transition) {


        if (transition) {
            pWest  = new Point(p2.getX() - 10, p2.getY());
            pEast  = new Point(p2.getX() + 10, p2.getY());
            pNorth = new Point(p2.getX(), p2.getY() - 40);
            pSouth = new Point(p2.getX(), p2.getY() + 40);


        } else {
            pWest  = new Point(p2.getX() - 40, p2.getY());
            pEast  = new Point(p2.getX() + 40, p2.getY());
            pNorth = new Point(p2.getX(), p2.getY() - 40);
            pSouth = new Point(p2.getX(), p2.getY() + 40);
        }


        pMin = (p1.distance(pWest) < p1.distance(pEast) ? pWest : pEast);
        pMin = (p1.distance(pMin) < p1.distance(pNorth) ? pMin : pNorth);
        pMin = (p1.distance(pMin) < p1.distance(pSouth) ? pMin : pSouth);

        return pMin;
    }

    public void drawArrow(Point p1, Point p2) {
        boolean transition;
        if (arc.getTarget().getClass() == Transition.class) {
            transition = true;
        } else {
            transition = false;
        }

        Point start = findMin(p2, p1, !transition);
        Point end = findMin(p1, p2, transition);

        //root.getChildren().remove(arrow);

        arrow.getPoints().clear();
        if (end == pWest) {

            arrow.getPoints().addAll(
                    end.getX(), end.getY(),
                    end.getX() - 10, end.getY() + 5,
                    end.getX() - 10, end.getY() - 5);
            end.setX(end.getX() - 10);

        } else if (end == pEast) {
            arrow.getPoints().addAll(
                    end.getX(), end.getY(),
                    end.getX() + 10, end.getY() + 5,
                    end.getX() + 10, end.getY() - 5);
            end.setX(end.getX() + 10);
        } else if (end == pNorth) {
            arrow.getPoints().addAll(
                    end.getX(), end.getY(),
                    end.getX() + 5, end.getY() - 10,
                    end.getX() - 5, end.getY() - 10);
            end.setY(end.getY() - 10);
        } else {
            arrow.getPoints().addAll(
                    end.getX(), end.getY(),
                    end.getX() + 5, end.getY() + 10,
                    end.getX() - 5, end.getY() + 10);
            end.setY(end.getY() + 10);
        }

        //root.getChildren().add(arrow);


        line.setStartX(start.getX());
        line.setStartY(start.getY());

        line.setEndX(end.getX());
        line.setEndY(end.getY());


    }



    public Arc getArc() {
        return arc;
    }

    public Group getGroup() {
        return group;
    }
}
