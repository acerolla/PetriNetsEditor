import com.thoughtworks.xstream.XStream;
import model.Net;
import parser.Reader;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Acerolla on 28.03.2017.
 */
public class TestSerialize {
    public static void main(String[] args) {


        Net net = new Net();

        Reader.read(new File("temp.xml"), net);


        System.out.println("Break");
    }
}
