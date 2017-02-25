import java.awt.*;

/**
 * Created by EDGY on 2/25/2017.
 */
public class Island {
    private int islandX;
    private int islandY;
    private int width;
    private int height;
    private int moveY;
    private Image img;

    public Island(int islandX, int islandY, int width, int height, Image img) {
        this.islandX = islandX;
        this.islandY = islandY;
        this.img = img;
        this.width = width;
        this.height = height;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    public int getIslandY() {
        return islandY;
    }

    public void setIslandY(int islandY) {
        this.islandY = islandY;
    }

    public void setIslandX(int islandX) {
        this.islandX = islandX;
    }

    public void move(){
        islandY += 2;
    }

    public void draw(Graphics g){
       g.drawImage(img, islandX, islandY,width,height, null ) ;
    }
}
