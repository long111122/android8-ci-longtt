package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by EDGY on 2/26/2017.
 */
public class Utils {
    public static Image loadImage(String url){
        try {
            Image image = ImageIO.read(new File(url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int RandomAll(int max, int min){
        Random rd = new Random();
        return rd.nextInt(max - min + 1) + min;
    }

    public static Image RandomImage(ArrayList<String> urls){
        int random = RandomAll(urls.size()-1,0);
        return loadImage(urls.get(random));
    }

    public static int getWidth(String img){
        return loadImage(img).getWidth(null);
    }

    public static int getHeight(String img){
        return loadImage(img).getHeight(null);
    }

    //current time
    public static long getNow(){
        return System.currentTimeMillis();
    }
}
