import java.awt.*;

/**
 * Created by EDGY on 2/25/2017.
 */
public class Background {
    private int x;
    private int y;
    private int x1;
    private int y1;
    private Image img;

    public Background(Image img) {
        x = 0;
        y = 0;
        x1 = 0;
        y1 = y-600;
        this.img = img;
    }

    public void Tick() {
        y+=2;
        y1+=2;
        if (y1 >600) {
            y1 = y - 600;
        }
        if (y>600) {
            y = y1 -600;
        }
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics g){
        g.drawImage(img,x,y,400,600,null);
        g.drawImage(img,x1,y1,400,600,null);
    }

}
