package controller;

import models.GameModel;
import models.IslandModel;
import util.Utils;
import view.GameView;
import view.IslandView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by EDGY on 2/27/2017.
 */
public class IslandController extends GameController{
    public IslandController(GameModel model, GameView view) {
        super(model, view);
//        this.urlImage = urlImage;
    }

    public IslandController(int x, int y, Image img) {
        this(new IslandModel(x,y), new IslandView(img));
    }

    public void setDefaultLocation(){
        if(model instanceof IslandModel) {
            ((IslandModel) model).setDefaultLocation();
        }
    }

    @Override
    public void run(int status){
       if(model instanceof IslandModel){
           ((IslandModel) model).run();
       }
    }

    public void draw(Graphics g){
        view.draw(g,model);
    }
}
