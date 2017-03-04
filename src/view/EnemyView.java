package view;

import models.EnemyModel;
import util.Utils;

import javax.imageio.ImageIO;
import javax.lang.model.type.ArrayType;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyView extends GameView{
    private static int WIDTH = 33;
    private static int HEIGHT = 34;

    public EnemyView(Image img) {
        super(img);
    }

    public void Destroy(boolean isAlive){
        if(!isAlive) {
            int delayImage = 0;
            BufferedImage bigImg = null;
            try {
                bigImg = ImageIO.read(new File("resources/explosion.png"));
                for (int i = 0; i < 6; i++)
                {
                    delayImage++;
                    if(delayImage > 2) {
                        img = bigImg.getSubimage(i * WIDTH, 0, WIDTH, HEIGHT);
                        System.out.println(img);
                        delayImage = 0;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
