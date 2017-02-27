package controller;

import models.PlayerBulletModel;
import util.Utils;
import view.PlayerBulletView;

import java.awt.*;

/**
 * Created by EDGY on 2/26/2017.
 */
public class PlayerBulletController {
    private PlayerBulletModel model;
    private PlayerBulletView view;

    public PlayerBulletModel getModel() {
        return model;
    }

    public PlayerBulletController(PlayerBulletModel model, PlayerBulletView view) {
        this.model = model;
        this.view = view;
    }

    public PlayerBulletController(int x, int y, Image img){
        this(
                new PlayerBulletModel(x,y,7,15),
                new PlayerBulletView(img));
    }

    public void run(){
        model.fly();
    }

    public void draw(Graphics g){
       view.draw(g,model);
    }


}
