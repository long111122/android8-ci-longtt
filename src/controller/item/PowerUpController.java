package controller.item;

import controller.collsion.Collision;
import controller.controllermanager.GameController;
import controller.collsion.PoolController;
import controller.item.powerupstralogies.MoveStraightBehavior;
import controller.item.powerupstralogies.PowerUpMoveBehavior;
import controller.player.PlayerPlaneController;
import models.GameModel;
import util.Utils;
import view.GameView;

/**
 * Created by EDGY on 3/6/2017.
 */
public class PowerUpController extends GameController implements Collision{
    private static String img;
    private PowerUpMoveBehavior moveBehaivor;

    public PowerUpController(GameModel model, GameView view, PowerUpMoveBehavior moveBehaivor) {
        super(model, view);
        PoolController.pool.add(this);
        this.moveBehaivor = moveBehaivor;
    }

    public static PowerUpController PowerUpCreate(int powerX, int powerY){
        img = "resources/power-up.png";
        PowerUpController powerUpController = new PowerUpController(
                new GameModel(powerX,powerY,Utils.getWidth(img), Utils.getHeight(img)),
                new GameView(Utils.loadImage(img)),
                new MoveStraightBehavior()
        );
        return powerUpController;
    }

    @Override
    public void run() {
        super.run();
        if(moveBehaivor != null){
           moveBehaivor.move(this);
        }
    }

    @Override
    public GameModel getGameModel() {
        return model;
    }

    @Override
    public void onContact(Collision collision) {
        if(collision instanceof PlayerPlaneController){
            this.getModel().Death();
        }
    }
}
