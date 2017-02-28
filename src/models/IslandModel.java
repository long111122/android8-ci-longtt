package models;

import gui.GameWindow;
import util.Utils;

/**
 * Created by EDGY on 2/27/2017.
 */
public class IslandModel {
    private int islandX;
    private int islandY;
    private int width;
    private int height;
    private static int SPEED = 1;

    public IslandModel(int islandX, int islandY, int width, int height) {
        this.islandX = islandX;
        this.islandY = islandY;
        this.width = width;
        this.height = height;
    }

    public int getIslandX() {
        return islandX;
    }

    public int getIslandY() {
        return islandY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDefaultLocation(){
        islandY = 0;
        islandX = Utils.RandomAll(GameWindow.SCREEN_WIDTH-width*2,0);
    }

    public void run(){
        islandY += SPEED;
    }
}
