package controller.player;

import controller.collsion.Collision;
import controller.controllermanager.GameController;
import controller.collsion.PoolController;
import controller.enemy.EnemyController;
import models.GameModel;
import models.PlayerBulletModel;
import view.GameView;
import view.PlayerBulletView;

import java.awt.*;

/**
 * Created by EDGY on 2/26/2017.
 */
public class PlayerBulletController extends GameController implements Collision{
    private PlayerBulletType playerBulletType;

    public PlayerBulletController(GameModel model, GameView view) {
        super(model, view);
        PoolController.pool.add(this);
    }

    public PlayerBulletController(int x, int y,int width, int height,int hp, int damage, Image image,PlayerBulletType playerBulletType){
        this(
                new PlayerBulletModel(x,y,width,height,hp,damage),
                new PlayerBulletView(image));
        this.playerBulletType = playerBulletType;
    }

    @Override
    public void run(){
        if(model instanceof PlayerBulletModel){
            ((PlayerBulletModel) model).run(playerBulletType);
        }
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
        if(collision instanceof EnemyController){
            this.getModel().Death();
        }
    }
}
