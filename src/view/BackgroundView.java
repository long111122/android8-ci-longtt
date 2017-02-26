package view;

import models.BackgroundModel;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class BackgroundView {
    private Image img;

    public BackgroundView(Image img) {
        this.img = img;
    }

    public void draw(Graphics g, BackgroundModel model){
        g.drawImage(img,model.getX1(),model.getY1(),model.getWidth(),model.getHeight(), null );
        g.drawImage(img,model.getX2(),model.getY2(),model.getWidth(),model.getHeight(), null);
    }
}
