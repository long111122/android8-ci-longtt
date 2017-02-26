package controller;

import models.EnemyBulletModel;
import util.Utils;
import view.EnemyBulletView;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyBulletController {
    private EnemyBulletModel model;
    private EnemyBulletView view;

    public EnemyBulletController(EnemyBulletModel model, EnemyBulletView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyBulletController(int x, int y){
        this(new EnemyBulletModel(x,y,9,9), new EnemyBulletView(Utils.loadImage("resources/bullet-round.png")));
    }

    //GETTER model
    public EnemyBulletModel getModel() {
        return model;
    }

    //shoot bullet
    public void run(int status){
        model.run(1);
    }

    public void draw(Graphics g){
        view.draw(g,model);
    }
}
