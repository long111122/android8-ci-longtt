package controller.collsion;

import controller.controllermanager.Manager;
import models.GameModel;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by EDGY on 3/4/2017.
 */
public class PoolController implements Manager {

    private Vector<Collision> vectorPool;

    public PoolController() {
        vectorPool = new Vector<>();
    }

    public static final PoolController pool = new PoolController();

    public void add(Collision collision){
        this.vectorPool.add(collision);
    }

    //auto check collision
    @Override
    public void run() {
        for (int i = 0; i < vectorPool.size() - 1; i++){
            for (int j = i + 1; j < vectorPool.size(); j++){
                Collision vectorI = vectorPool.get(i);
                Collision vectorJ = vectorPool.get(j);
                GameModel gameModelI = vectorI.getGameModel();
                GameModel gameModelJ = vectorJ.getGameModel();
                if(gameModelI.IsIntersect(gameModelJ)){
                    vectorI.onContact(vectorJ);
                    vectorJ.onContact(vectorI);
                }
            }
        }


        Iterator<Collision> iterator = vectorPool.iterator();
        while(iterator.hasNext()){
            if(!iterator.next().getGameModel().isAlive()){
                iterator.remove();
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {

    }
}
