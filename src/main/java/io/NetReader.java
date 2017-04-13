package io;

import com.thoughtworks.xstream.XStream;
import model.Net;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Acerolla on 28.03.2017.
 */
public class NetReader {

    public static Net read(File file) {

        Net net = new Net();
        XStream xs = new XStream();


        try {
            FileInputStream fis = new FileInputStream(file);
            xs.fromXML(fis, net);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return net;
    }
}
