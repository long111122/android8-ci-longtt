package models;

import java.awt.*;

/**
 * Created by EDGY on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel{
    public static int SPEED = 10;
    public static int WIDTH = 9;
    public static int HEIGHT = 15;
    public static final int WIDTHHALF = WIDTH/2;
    public static final int HEIGHTHALF = HEIGHT/2;

    public PlayerBulletModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    public void fly(int status){
        y -= SPEED;
    }
}
