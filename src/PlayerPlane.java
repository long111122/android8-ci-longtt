import java.awt.*;

/**
 * Created by EDGY on 2/24/2017.
 */
public class PlayerPlane {
    private int planeX;
    private int planeY;
    private int moveX;
    private int moveY;
    private Image img;

    public PlayerPlane(int planeX, int planeY, Image img) {
        this.planeX = planeX;
        this.planeY = planeY;
        this.img = img;
    }

    public void setPlaneX(int planeX) {
        this.planeX = planeX;
    }

    public void setPlaneY(int planeY) {
        this.planeY = planeY;
    }

    public int getPlaneX() {
        return planeX;
    }

    public int getPlaneY() {
        return planeY;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    public void move(){
        planeX += moveX;
        planeY += moveY;
    }

    public void draw(Graphics g){
        g.drawImage(img,planeX,planeY,35,28,null);
    }
}
