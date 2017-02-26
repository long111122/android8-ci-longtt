package controller;

import models.IslandModel;
import util.Utils;
import view.IslandView;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class IslandController {
    private IslandModel model;
    private IslandView view;

    public IslandController(IslandModel model, IslandView view) {
        this.model = model;
        this.view = view;
    }

    public IslandController(int x, int y) {
        this(new IslandModel(x,y,50,50), new IslandView());
    }

    //GETTER model
    public IslandModel getModel() {
        return model;
    }

    public void setDefaultLocation(){
        model.setDefaultLocation();
    }

    public void run(){
        model.run();
    }

    public void draw(Graphics g){
        view.draw(g,model);
    }

}
