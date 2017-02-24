import java.awt.*;

/**
 * Created by EDGY on 2/21/2017.
 */
public class Enemy {
    private int enemyX;
    private int enemyY;
    private Image img;

    public Enemy(int enemyX, int enemyY, Image img) {
        this.enemyX = enemyX;
        this.enemyY = enemyY;
        this.img = img;
    }

    public int getEnermyX() {
        return enemyX;
    }

    public void setEnermyX(int enermyX) {
        this.enemyX = enermyX;
    }

    public int getEnermyY() {
        return enemyY;
    }

    public void setEnermyY(int enermyY) {
        this.enemyY = enermyY;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void Move(int speed){
        this.enemyY += speed;
    }

}
