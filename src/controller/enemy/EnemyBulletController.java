package controller.enemy;

import controller.collsion.Collision;
import controller.controllermanager.GameController;
import controller.collsion.PoolController;
import models.GameModel;
import util.Utils;
import view.GameView;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyBulletController extends GameController implements Collision {
    private static String img = "";

    public EnemyBulletController(GameModel model, GameView view) {
        super(model, view);
        PoolController.pool.add(this);
    }

    public static EnemyBulletController BulletCreate(EnemyBulletType enemyBulletType, int bulletX, int bulletY){
        EnemyBulletController enemyBulletManager = null;
        switch (enemyBulletType){
            case NORMAL :
                img = "resources/bullet-round.png";
                break;

            case LAZE :
                img = "resources/lazer.png";
                break;
        }
        enemyBulletManager = new EnemyBulletController(
                new GameModel(bulletX, bulletY, Utils.getWidth(img), Utils.getHeight(img)),
                new GameView(Utils.loadImage(img))
        );
        return enemyBulletManager;
    }

    public void draw(Graphics g){
        view.draw(g,model);
    }

    @Override
    public GameModel getGameModel() {
        return model;
    }

    @Override
    public void onContact(Collision collision) {

    }
}
