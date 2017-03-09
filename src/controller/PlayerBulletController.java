package controller;

import models.GameModel;
import models.PlayerBulletModel;
import util.Utils;
import view.GameView;
import view.PlayerBulletView;

import javax.rmi.CORBA.Util;
import java.awt.*;

/**
 * Created by EDGY on 2/26/2017.
 */
public class PlayerBulletController extends GameController{
    public PlayerBulletController(GameModel model, GameView view) {
        super(model, view);
    }

    public PlayerBulletController(int x, int y, Image image){
        this(
                new PlayerBulletModel(x,y),
                new PlayerBulletView(image));
    }

    @Override
    public void run(int status){
        if(model instanceof PlayerBulletModel){
            ((PlayerBulletModel) model).fly(status);
        }
    }

    public void draw(Graphics g){
        view.draw(g,model);
    }


}
