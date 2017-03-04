package models;

import gui.GameWindow;
import util.Utils;

/**
 * Created by EDGY on 2/27/2017.
 */
public class IslandModel extends GameModel{
    private static int SPEED = 1;
    public static int WIDTH = 40;
    public static int HEIGHT = 40;

    public IslandModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    public void setDefaultLocation(){
        y = 0;
        x = Utils.RandomAll(GameWindow.SCREEN_WIDTH-width*2,0);
    }

    public void run(){
        y += SPEED;
    }
}
