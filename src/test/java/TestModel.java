import com.thoughtworks.xstream.XStream;
import model.*;
import parser.Writer;

import java.io.File;
import java.io.FileOutputStream;
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
            net.setRelation(n0, n1);
            net.setRelation(n1, n2);
            net.setRelation(n1, n3);
            net.setRelation(n2, n4);
            net.setRelation(n3, n4);
            net.setRelation(n4, n5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        net.removeNode(n3);

        /*Place pl =(Place) net.getNodes().get(0);
        pl.addToken(new NetToken());
        NetToken nt = (NetToken) pl.getTokens().get(0);
        System.out.println(nt.getInnerNet());
        nt.setInnerNet(new Net());
        nt.getInnerNet().addNode(new Place(6));
        nt.getInnerNet().addNode(new Transition(7));
        try {
            nt.getInnerNet().setRelation(nt.getInnerNet().getNodes().get(0), nt.getInnerNet().getNodes().get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

        //Writer.write(new File("temp.xml"), net);
        System.out.print("Break");

    }
}
