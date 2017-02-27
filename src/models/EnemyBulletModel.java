package models;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyBulletModel {
    private int bulletX;
    private int bulletY;
    private int width;
    private int height;
    private int power;
    private static int SPEED = 4;

    //ctor
    public EnemyBulletModel(int bulletX, int bulletY, int width, int height) {
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.width = width;
        this.height = height;
    }

    public int getBulletX() {
        return bulletX;
    }

    public int getBulletY() {
        return bulletY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
                bulletY += SPEED;
                break;

            case 2 :
                bulletY += SPEED;
                bulletX += SPEED/3;
                break;

            case 3 :
                bulletY += SPEED;
                bulletX -= SPEED;
                break;
        }
    }


}
