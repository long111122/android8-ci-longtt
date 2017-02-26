package view;

import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by EDGY on 2/26/2017.
 */
public class PlayerBulletView {
    private Image image;

    public PlayerBulletView(Image image) {
        this.image = image;
    }

    public void draw(Graphics g, PlayerBulletModel model){
        g.drawImage(image,model.getX(),model.getY(),model.getWidth(),model.getHeight(), null);
    }
}
