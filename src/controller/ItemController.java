package controller;

import models.GameModel;
import models.ItemModel;
import view.GameView;
import view.ItemView;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class ItemController extends  GameController{
    private int type;
    public ItemController(GameModel model, GameView view) {
        super(model, view);
    }

    public ItemController(int x, int y, Image img, int type) {
        this(new ItemModel(x,y), new ItemView(img));
        this.type = type;
    }

    @Override
    public void run(int status){
       if(model instanceof ItemModel) {
           ((ItemModel) model).run(status);
       }
    }

    public void draw(Graphics g){
        view.draw(g,model);
    }

    public int getType() {
        return type;
    }
}
