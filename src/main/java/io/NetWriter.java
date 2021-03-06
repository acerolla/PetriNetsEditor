package io;

import com.thoughtworks.xstream.XStream;
import model.Net;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Acerolla on 28.03.2017.
 */
public class NetWriter {
    public static void write(File file, Net net) throws Exception{
        XStream xs = new XStream();

        try {
            FileOutputStream fos = new FileOutputStream(file);
            xs.toXML(net, fos);
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Cannot write to file ):");
        }
    }
}
