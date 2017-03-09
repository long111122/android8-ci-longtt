package controller;

import gui.GameWindow;
import models.EnemyBulletModel;
import models.EnemyModel;
import models.GameModel;
import models.PlayerBulletModel;
import util.Utils;
import view.EnemyView;
import view.GameView;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyController extends GameController{
    private boolean isAlive = true;
    private Vector<EnemyBulletController> enemyBulletControllers;

    public EnemyController(GameModel model, GameView view,Vector<EnemyBulletController> enemyBulletControllers) {
        super(model, view);
        this.enemyBulletControllers = enemyBulletControllers;
//        PoolController.vector.add(this);
    }

    public EnemyController(int x, int y,Vector<EnemyBulletController> enemyBulletControllers,Image img){
        this(new EnemyModel(x,y),new EnemyView(img),enemyBulletControllers);
    }

    //move enemy
    @Override
    public void run(int status){
        if(model instanceof EnemyModel){
            ((EnemyModel) model).run(status);
            if(view instanceof  EnemyView){
                ((EnemyView) view).Destroy(isAlive);
            }
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void isDeath(){
        isAlive = false;
    }

    //auto shoot enemy
    public void shoot(){
        EnemyBulletController enemyBulletController = new EnemyBulletController(model.getX() + model.getWidth() / 2 - EnemyBulletModel.WIDTH / 2, model.getY() + model.getHeight());
        enemyBulletControllers.add(enemyBulletController);
    }

    public void RemoveOutSide(){
        Iterator<EnemyBulletController> iterator = enemyBulletControllers.iterator();
        while (iterator.hasNext()){
            if(iterator.next().getModel().getY() >= GameWindow.SCREEN_HEIGHT)iterator.remove();
        }
    }

    public void draw(Graphics g){
        view.draw(g,model);
    }

}
