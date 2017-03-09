package models;

/**
 * Created by EDGY on 3/6/2017.
 */
public class PowerUpModel extends GameModel{
    public static final int SPEED = 2;
    public static int WIDTH = 25;
    private static int HEIGHT = 25;
    public PowerUpModel(int x, int y) {
        super(x, y, WIDTH,HEIGHT);
    }

    public void run(){
        y += SPEED;
    }


}
