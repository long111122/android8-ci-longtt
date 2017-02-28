package models;

import gui.GameWindow;

/**
 * Created by EDGY on 2/27/2017.
 */
public class PlayerPlaneModel {
    private int planeX;
    private int planeY;
    private int width;
    private int height;
    private static final int SPEED = 5;

    public PlayerPlaneModel(int x, int y, int width, int height) {
        this.planeX = x;
        this.planeY = y;
        this.width = width;
        this.height = height;
    }

    public int getPlaneX() {
        return planeX;
    }
    public int getPlaneY() {
        return planeY;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    /**
     *    move playerPlane
     * @param status : 1 : up, 2 : right, 3 : left, 4 : right
     */
    public void run(int status){
        switch (status){
            case 1 :
                //move up
                if(planeY - SPEED > 0)
                planeY -= SPEED;
                break;

            case 2 :
                //move down
                if(planeY + SPEED < GameWindow.SCREEN_HEIGHT - height)
                planeY += SPEED;
                break;

            case 3 :
                //move left
                if(planeX - SPEED > 0) {
                    planeX -= SPEED;
                }
                break;

            case 4 :
                //move right
                if(planeX + SPEED < GameWindow.SCREEN_WIDTH - width) {
                    planeX += SPEED;
                }
                break;
        }
    }
}
