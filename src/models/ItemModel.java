package models;

/**
 * Created by EDGY on 2/27/2017.
 */
public class ItemModel extends GameModel {
    private static int SPEED = 2;
    public static int WIDTH = 20;
    public static int HEIGHT = 20;

    public ItemModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    public void run(int status){
        y += SPEED;
    }

}
