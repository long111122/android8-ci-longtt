package models;

/**
 * Created by EDGY on 2/27/2017.
 */
public class ItemModel {
    private int x;
    private int y;
    private int width;
    private int height;
    private int type;
    private static int SPEED = 2;

    public ItemModel(int x, int y, int width, int height, int type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void run(){
        y += SPEED;
    }

}
