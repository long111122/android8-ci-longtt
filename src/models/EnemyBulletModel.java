package models;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyBulletModel extends GameModel{
    private static int SPEED = 8;
    public static int WIDTH = 9;
    public static int HEIGHT = 9;

    public EnemyBulletModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    /**
     *
     * @param status
     * 1 : AUTO
     * 2 : LEFT TO RIGHT
     * 3 : RIGHT TO LEFT
     */
    public void run(int status){
        switch (status){
            case 1 :
                y += SPEED;
                break;

            case 2 :
                y += SPEED;
                x += SPEED/3;
                break;

            case 3 :
                y += SPEED;
                x -= SPEED;
                break;
        }
    }


}
