package view;

import models.EnemyBulletModel;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyBulletView {
    private Image img;

    public EnemyBulletView(Image img) {
        this.img = img;
    }

    public void draw(Graphics g, EnemyBulletModel model){
        g.drawImage(img,model.getBulletX(),model.getBulletY(),model.getWidth(), model.getHeight(), null);
    }
}
