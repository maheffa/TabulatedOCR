// File:    Util.java
// Created: 29/04/2015

import java.io.File;
import java.io.IOException;

/**
 * @author mahefa
 */
public class SerializerUtil {

    public SerializerUtil() {
    }

    public static String[] getPathBaseExtension(String path) {
        String[] res = new String[3];
        File f = new File(path);
        String pathN;
        try {
            pathN = f.getCanonicalPath();
            int i0 = pathN.lastIndexOf(File.separator);
            res[0] = pathN.substring(0, i0);
            String name = pathN.substring(i0 + 1, pathN.length());
            int i1 = name.lastIndexOf('.');
            if (i1 > 0) {
                res[1] = name.substring(0, i1);
                res[2] = name.substring(i1 + 1);
            } else {
                res[1] = name;
                res[2] = "";
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
