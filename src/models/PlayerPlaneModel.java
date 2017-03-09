package models;

import controller.player.PlayerRun;
import gui.GameWindow;

/**
 * Created by EDGY on 2/27/2017.
 */
public class PlayerPlaneModel extends GameModel{
    private static final int SPEED = 5;
    public static final int WIDTH = 35;
    public static final int HEIGHT = 30;
    protected int hp = 10;

    public PlayerPlaneModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    public void run(PlayerRun playerRun){
        switch (playerRun){
            case UP:
                //move up
                if(y - SPEED - HEIGHT> 0)
                y -= SPEED;
                break;

            case DOWN:
                //move down
                if(y + SPEED < GameWindow.SCREEN_HEIGHT - height)
                y += SPEED;
                break;

            case LEFT:
                //move left
                if(x - SPEED > 0) {
                    x -= SPEED;
                }
                break;

            case RIGHT:
                //move right
                if(x + SPEED < GameWindow.SCREEN_WIDTH - width) {
                    x += SPEED;
                }
                break;
        }
    }

    public void getHit(int damage){
        hp -= damage;
        if(hp <= 0){
            this.Death();
        }
    }

}
