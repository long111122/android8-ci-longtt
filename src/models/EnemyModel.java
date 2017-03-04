package models;

import controller.EnemyBulletController;
import gui.GameWindow;
import util.Utils;

import java.util.ArrayList;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyModel extends GameModel{
    private static final int SPEED = 3;
    public static int WIDTH = 35;
    public static int HEIGHT = 30;

    public EnemyModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    /**
     *
     * @param status
     * 1 : AUTO
     * 2 : LEFT TO RIGHT
     * 3 : RIGHT TO LEFT
     */
    public void run(int status){
        switch (status){
            case 1 :
                y += SPEED;
                break;
            case 2 :
                y += SPEED*2;
                x += SPEED*2;
                break;
            case 3 :
                y += SPEED*2;
                x -= SPEED*2;
                break;
        }
    }
}
