import java.awt.*;

/**
 * Created by EDGY on 2/21/2017.
 */
public class PlayerBullet {
    private Image img;
    private int bulletX;
    private int bulletY;
    private int width;
    private int height;

    public PlayerBullet(Image img, int bulletX, int bulletY, int width, int height) {
        this.img = img;
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

    public void move(){
        bulletY -= 5;
    }

    public void draw(Graphics g){
        g.drawImage(img, bulletX, bulletY,width, height, null );
    }
}
