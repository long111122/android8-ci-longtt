package models;

import controller.EnemyBulletController;
import gui.GameWindow;

import java.util.ArrayList;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyModel {
    private int enemyX;
    private int enemyY;
    private int width;
    private int height;
    private ArrayList<EnemyBulletController> bullets = new ArrayList<>();
    private static final int SPEED = 2;

    public EnemyModel(int enemyX, int enemyY, int width, int height) {
        this.enemyX = enemyX;
        this.enemyY = enemyY;
        this.width = width;
        this.height = height;
    }

    public int getEnemyX() {
        return enemyX;
    }

    public int getEnemyY() {
        return enemyY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<EnemyBulletController> getBullets() {
        return bullets;
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
                enemyY += SPEED;
                break;

            case 2 :

                break;
        }
    }

    public void addBullet(EnemyBulletController enemyBullet){
        if(enemyY > height) {
            bullets.add(enemyBullet);
        }
    }
}
