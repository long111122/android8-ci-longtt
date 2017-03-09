package models;

import controller.player.PlayerBulletType;
import gui.GameWindow;


/**
 * Created by EDGY on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel{
    public static int SPEED = 10;

    public PlayerBulletModel(int x, int y, int width, int height, int hp, int damage) {
        super(x, y, width, height, hp, damage);
    }

    public void run(PlayerBulletType playerBulletType) {
        switch (playerBulletType){
            case MIDDLE:
                y -= SPEED;
                break;

            case RIGHT:
                y -= SPEED;
                x += SPEED;
                break;

            case LEFT:
                y -= SPEED;
                x -= SPEED;
                break;
        }
        if(x > GameWindow.SCREEN_WIDTH
                || x < 0
                || y > GameWindow.SCREEN_HEIGHT
                || y < 0){
            this.Death();
        }
    }
}
