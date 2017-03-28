import com.thoughtworks.xstream.XStream;
import model.Net;

import java.io.FileInputStream;

/**
 * Created by Acerolla on 28.03.2017.
 */
public class TestSerialize {
    public static void main(String[] args) {

        XStream xs = new XStream();
        Net net = new Net();

        try {
            FileInputStream fis = new FileInputStream("D://temp.xml");
            xs.fromXML(fis, net);
        } catch(Exception e) {
            e.printStackTrace();
        }


        System.out.println("Break");
    }
}
