package controller;

import models.EnemyModel;
import util.Utils;
import view.EnemyView;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyController {
    private EnemyModel model;
    private EnemyView view;

    public EnemyController(EnemyModel model, EnemyView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyController(int x, int y){
        this(new EnemyModel(x,y,30,30),new EnemyView(Utils.loadImage("resources/enemy_plane_white_3.png")));
    }

    //GETTER model
    public EnemyModel getModel() {
        return model;
    }

    //move enemy
    public void run(int status){
        model.run(status);
    }

    //auto shoot enemy
    public void addBullet(EnemyBulletController bullet){
        model.addBullet(bullet);
    }

    public void draw(Graphics g){
        view.draw(g, model);
    }
}
