package GUI;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
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
        line,
        arrow1,
        arrow2;

    private Arc arc;

    private Pane root;

    public ArcGUI(Pane root, Arc arc) {

        this.root = root;
        this.arc = arc;
        this.group = new Group();


        line = new Line();
        //arrow1 = new Line();
        //arrow2 = new Line();
        group.getChildren().add(line);
        drawArrow(arc.getSource().getStartPoint(), arc.getTarget().getStartPoint());




        line.setStrokeType(StrokeType.CENTERED);

        initialize();
    }

    private void initialize() {

    }

    private Point findMin(Point p1, Point p2, boolean transition) {

        Point
                pWest,
                pEast,
                pNorth,
                pSouth,
                pMin;

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
        Point end = findMin(p1, p2, transition);
        Point start = findMin(p2, p1, !transition);

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
