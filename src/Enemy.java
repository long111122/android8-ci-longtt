import java.awt.*;
import java.util.ArrayList;


/**
 * Created by EDGY on 2/21/2017.
 */
public class Enemy {
    private int enemyX;
    private int enemyY;
    private Image img;
    private int width;
    private int height;
    private ArrayList<EnemyBullet> enemyBullets;

    public Enemy(int enemyX, int enemyY, Image img, int width, int height) {
        this.enemyX = enemyX;
        this.enemyY = enemyY;
        this.img = img;
        this.width = width;
        this.height = height;
        enemyBullets = new ArrayList<>();
    }

    public void addBullet(EnemyBullet bullet){
        enemyBullets.add(bullet);
    }

    public void setEnemyBullets(ArrayList<EnemyBullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }

    public ArrayList<EnemyBullet> getEnemyBullets() {
        return enemyBullets;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public void draw(Graphics g){
       g.drawImage(img, enemyX,enemyY, width, height , null);
    }

}
