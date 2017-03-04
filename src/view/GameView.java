package view;

import models.GameModel;

import java.awt.*;

/**
 * Created by EDGY on 2/28/2017.
 */
public class GameView {
    protected Image img;

    public GameView(Image img) {
        this.img = img;
    }

    public void draw(Graphics g, GameModel model){
        g.drawImage(img,model.getX(),model.getY(),model.getWidth(),model.getHeight(), null);
    }
}
