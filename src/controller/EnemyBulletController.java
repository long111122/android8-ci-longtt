package controller;

import models.EnemyBulletModel;
import models.GameModel;
import util.Utils;
import view.EnemyBulletView;
import view.GameView;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyBulletController extends GameController{
    public EnemyBulletController(GameModel model, GameView view) {
        super(model, view);
    }


    public EnemyBulletController(int x, int y){
        this(new EnemyBulletModel(x,y), new EnemyBulletView(Utils.loadImage("resources/bullet-round.png")));
    }

    //shoot bullet
    @Override
    public void run(int status) {
        if(model instanceof  EnemyBulletModel){
            ((EnemyBulletModel) model).run(status);
        }
    }

    public void draw(Graphics g){
        view.draw(g,model);
    }
}
