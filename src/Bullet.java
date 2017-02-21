import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by EDGY on 2/21/2017.
 */
public class Bullet {
    private int bulletX;
    private int bulletY;
    private Image img;

    public Bullet(int bulletX, int bulletY, Image img) {
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.img = img;
    }

    public int getBulletX() {
        return bulletX;
    }

    public int getBulletY() {
        return bulletY;
    }

   public  void move(){
        this.bulletY -= 50;
    }

    public void update(int x, int y){
       this.bulletX = x;
        this.bulletY = y;
    }

    public void paint(Graphics g){

        g.drawImage(img,bulletX,bulletY,13,33,null);
    }
}
