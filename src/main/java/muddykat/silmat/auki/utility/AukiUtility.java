package muddykat.silmat.auki.utility;

import java.io.InputStream;

public class AukiUtility {
    public static final String VERSION = "V0.3";

    public static InputStream getResource(Class<?> clazz, String path){

        InputStream inputStream = clazz.getResourceAsStream(path);
        if (inputStream == null) {
            System.out.println("No file found in specified path (" + path + ")");
        }

        return inputStream;
    }
}
