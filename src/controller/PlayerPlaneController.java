package controller;

import models.EnemyBulletModel;
import models.GameModel;
import models.PlayerBulletModel;
import models.PlayerPlaneModel;
import util.Utils;
import view.GameView;
import view.PlayerPlaneView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by EDGY on 2/27/2017.
 */
public class PlayerPlaneController extends GameController{

    private Vector<PlayerBulletController> playerBulletModels;

    public PlayerPlaneController(GameModel model, GameView view, Vector<PlayerBulletController> playerBulletModels) {
        super(model, view);
        this.playerBulletModels = playerBulletModels;
    }

    public PlayerPlaneController(int planeX, int planeY, Vector<PlayerBulletController> playerBulletModels) {
        this(new PlayerPlaneModel(planeX,planeY), new PlayerPlaneView(Utils.loadImage("resources/plane2.png")),playerBulletModels);
    }

    //move plane
    @Override
    public void run(int status){
       if(model instanceof PlayerPlaneModel){
           ((PlayerPlaneModel) model).run(status);
       }
    }

    public void shoot(int status) {
        String img = "resources/bullet-single.png";
        if (status == 1) {
            img = "resources/bullet-single.png";
        }
        else if(status == 2){
            img = "resources/bullet-double.png";
            PlayerBulletModel.WIDTH = 17;
            PlayerBulletModel.HEIGHT = 16;
        }
        else if(status == 3){
            img = "resources/bullet-double.png";
            PlayerBulletModel.WIDTH = 17;
            PlayerBulletModel.HEIGHT = 16;
            PlayerBulletModel.SPEED = 13;
//            String imgLeft = "resources/bullet-left";
//            String imgRight = "resources/bullet-right";
//            PlayerBulletModel.WIDTH = 9;
//            PlayerBulletModel.HEIGHT = 15;
//            PlayerBulletController PlayerBulletControllerLeft = new PlayerBulletController(model.getX() + model.getWidth() / 2 - PlayerBulletModel.WIDTHHALF, model.getY() - PlayerBulletModel.HEIGHT, Utils.loadImage(imgLeft));
//            PlayerBulletController PlayerBulletControllerRight = new PlayerBulletController(model.getX() + model.getWidth() / 2 - PlayerBulletModel.WIDTHHALF, model.getY() - PlayerBulletModel.HEIGHT, Utils.loadImage(imgRight));
//
        }
        PlayerBulletController PlayerBulletController = new PlayerBulletController(model.getX() + model.getWidth() / 2 - PlayerBulletModel.WIDTHHALF, model.getY() - PlayerBulletModel.HEIGHT, Utils.loadImage(img));
        playerBulletModels.add(PlayerBulletController);
    }

    public void draw(Graphics g){
       view.draw(g, model);
    }


}
