package view;

import models.IslandModel;
import util.Utils;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class IslandView {
    private Image img;

    public IslandView() {
        if(Utils.RandomAll(2,1)/2 == 0){
            img = Utils.loadImage("resources/island.png");
        } else {
            img = Utils.loadImage("resources/island-2.png");
        }
    }

    public void draw(Graphics g, IslandModel model){
        g.drawImage(img, model.getIslandX(),model.getIslandY(), model.getWidth(), model.getHeight(), null);
    }
}
