import java.awt.*;

/**
 * Created by EDGY on 2/21/2017.
 */
public class PlayerBullet {
    public Image img;
    public int bulletX;
    public int bulletY;

    public void move(){
        bulletY -= 5;
    }
}
