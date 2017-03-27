import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class TestModel {
    public static void main(String[] args) {

        Node
                n0 = new Place(0),
                n1 = new Transition(1),
                n2 = new Place(2),
                n3 = new Place(3),
                n4 = new Transition(4),
                n5 = new Place(5);

        Net net = new Net();

        net.addNode(n0);
        net.addNode(n1);
        net.addNode(n2);
        net.addNode(n3);
        net.addNode(n4);
        net.addNode(n5);

        try {
            net.setRelations(n0, n1);
            net.setRelations(n1, n2);
            net.setRelations(n1, n3);
            net.setRelations(n2, n4);
            net.setRelations(n3, n4);
            net.setRelations(n4, n5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        net.removeNode(n3);
        //n1.getArcs();

        System.out.print("Break");



    }
}
