package models;

import gui.GameWindow;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyModel extends GameModel{
    public static final int SPEED = 3;
    public static final int GREENSPEED = 1;
    public static int WIDTH = 35;
    public static int HEIGHT = 30;

    public EnemyModel(int x, int y, int width, int height, int hp, int damage) {
        super(x, y, width, height, hp, damage);
    }
}
