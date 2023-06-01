package muddykat.silmat.auki.util;

import javafx.scene.image.Image;
import muddykat.silmat.auki.EyeApplication;
import muddykat.silmat.auki.EyeController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class EyeUtil {
    public static HashMap<Integer, Image> eyeMap = new HashMap<>();

    static {
        new EyeUtil();
    }
    public EyeUtil(){
        eyeMap.put(6, new Image(getResource(getClass(), "eye_empty.png")));
        eyeMap.put(0, new Image(getResource(getClass(), "eye_0.png")));
        eyeMap.put(1, new Image(getResource(getClass(), "eye_1.png")));
        eyeMap.put(2, new Image(getResource(getClass(), "eye_2.png")));
        eyeMap.put(3, new Image(getResource(getClass(), "eye_3.png")));
        eyeMap.put(4, new Image(getResource(getClass(), "eye_4.png")));
    }
    public static Image getImageFromIndex(Integer symbol) {
        if(eyeMap.isEmpty()){
            System.out.println("Eye Map is Empty");
        }
        return eyeMap.get(symbol);
    }

    public static InputStream getResource(Class<?> clazz, String path){
        InputStream inputStream = clazz.getResourceAsStream(path);
        if (inputStream == null) {
            System.out.println("No file found in specified path (" + path + ")");
        }

        return inputStream;
    }
}
