package controller.controllermanager;

import controller.enemy.EnemyController;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by EDGY on 3/6/2017.
 */
public class ControllerManager implements Manager{

    protected Vector<GameController> gameControllers;

    public ControllerManager() {
        gameControllers = new Vector<>();
    }

    public void add(GameController gameController){
        gameControllers.add(gameController);

    }

    @Override
    public void run() {
        synchronized (this.gameControllers) {
            Iterator<GameController> iterator = this.gameControllers.iterator();
            while (iterator.hasNext()) {
                GameController runController = iterator.next();
                if (!runController.getModel().isAlive()) {
                    iterator.remove();
                } else {
                    runController.run();
                }
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        synchronized (this.gameControllers) {
            Iterator<GameController> iterator = gameControllers.iterator();
            while (iterator.hasNext()) {
                GameController drawController = iterator.next();
                if (drawController.getModel().isAlive()) {
                    drawController.draw(graphics);
                }
            }
        }
    }
}
