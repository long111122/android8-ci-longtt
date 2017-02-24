import java.awt.*;

/**
 * Created by EDGY on 2/22/2017.
 */
public class EnemyBullet {
    private int bulletX;
    private int bulletY;
    private int power;
    private int speed = 100;
    private Image img;

    public EnemyBullet(int bulletX, int bulletY, Image img) {
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.img = img;
    }

    public int getBulletX() {
        return bulletX;
    }

    public void setBulletX(int bulletX) {
        this.bulletX = bulletX;
    }

    public int getBulletY() {
        return bulletY;
    }

    public void setBulletY(int bulletY) {
        this.bulletY = bulletY;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void move(){
        bulletY += speed;
        bulletX += 5;
    }
}
