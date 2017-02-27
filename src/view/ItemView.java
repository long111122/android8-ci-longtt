package view;

import models.ItemModel;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class ItemView {
    Image img;

    public ItemView(Image img) {
        this.img = img;
    }

    public void draw(Graphics g, ItemModel model){
        g.drawImage(img,model.getX(),model.getY(),model.getWidth(),model.getHeight(),null);
    }
}
