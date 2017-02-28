package models;

import gui.GameWindow;

/**
 * Created by EDGY on 2/27/2017.
 */
public class BackgroundModel {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private static int SPEED = 1;
    private int width = GameWindow.SCREEN_WIDTH;
    private int height = GameWindow.SCREEN_HEIGHT;

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BackgroundModel() {
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = y1 - height;
    }

    public void run() {
        y1 += SPEED;
        y2 += SPEED;
        if (y2 > height) {
            y2 = y1 - height;
        }
        if (y1 > height) {
            y1 = y2 - height;
        }
    }
}
