package models;

import gui.GameWindow;

/**
 * Created by EDGY on 2/27/2017.
 */
public class PlayerPlaneModel extends GameModel{
    private static final int SPEED = 5;
    public static final int WIDTH = 35;
    public static final int HEIGHT = 30;

    public PlayerPlaneModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    /**
     *    move playerPlane
     * @param status : 1 : up, 2 : right, 3 : left, 4 : right
     */
    public void run(int status){
        switch (status){
            case 1 :
                //move up
                if(y - SPEED - HEIGHT> 0)
                y -= SPEED;
                break;

            case 2 :
                //move down
                if(y + SPEED < GameWindow.SCREEN_HEIGHT - height)
                y += SPEED;
                break;

            case 3 :
                //move left
                if(x - SPEED > 0) {
                    x -= SPEED;
                }
                break;

            case 4 :
                //move right
                if(x + SPEED < GameWindow.SCREEN_WIDTH - width) {
                    x += SPEED;
                }
                break;
        }
    }
}
