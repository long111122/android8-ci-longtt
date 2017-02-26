package view;

import models.PlayerPlaneModel;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class PlayerPlaneView {
    private Image img;

    public PlayerPlaneView(Image img) {
        this.img = img;
    }

    public void draw(Graphics g, PlayerPlaneModel model){
       g.drawImage(img,model.getPlaneX(), model.getPlaneY(),model.getWidth(),model.getHeight(), null);
    }
}
