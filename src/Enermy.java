import java.awt.*;

/**
 * Created by EDGY on 2/21/2017.
 */
public class Enermy {
    private int enermyX;
    private int enermyY;
    private Image img;


    public Enermy(int enermyX, int enermyY, Image img) {
        this.enermyX = enermyX;
        this.enermyY = enermyY;
        this.img = img;
    }

    public int getEnermyX() {
        return enermyX;
    }

    public void setEnermyX(int enermyX) {
        this.enermyX = enermyX;
    }

    public int getEnermyY() {
        return enermyY;
    }

    public void setEnermyY(int enermyY) {
        this.enermyY = enermyY;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void Move(int speed){
        this.enermyY += speed;
    }

}
