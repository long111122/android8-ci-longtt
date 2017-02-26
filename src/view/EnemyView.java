package view;

import models.EnemyModel;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyView {
    private Image img;

    public EnemyView(Image img) {
        this.img = img;
    }

    public void draw(Graphics g, EnemyModel model){
        g.drawImage(img, model.getEnemyX(),model.getEnemyY(),model.getWidth(),model.getHeight(),null);
    }
}
