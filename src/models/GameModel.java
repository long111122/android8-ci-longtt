package models;

import java.awt.*;

/**
 * Created by EDGY on 2/28/2017.
 */
public class GameModel {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public GameModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean IsIntersect(GameModel gameModel){
        Rectangle r1 = new Rectangle(x,y,width,height);
        Rectangle r2 = new Rectangle(gameModel.x,gameModel.y,gameModel.width,gameModel.height);
        return r1.intersects(r2);
    }
}
