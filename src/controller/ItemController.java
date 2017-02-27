package controller;

import models.ItemModel;
import view.ItemView;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class ItemController {
    private ItemModel model;
    private ItemView view;

    public ItemController(ItemModel model, ItemView view) {
        this.model = model;
        this.view = view;
    }

    public ItemController(int x, int y, Image img, int type) {
        this(new ItemModel(x,y,20,20,type), new ItemView(img));
    }

    public ItemModel getModel() {
        return model;
    }

    public void run(){
        model.run();
    }

    public void draw(Graphics g){
        view.draw(g,model);
    }
}
