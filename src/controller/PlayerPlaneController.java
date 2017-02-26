package controller;

import models.PlayerPlaneModel;
import util.Utils;
import view.PlayerPlaneView;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class PlayerPlaneController {
    private PlayerPlaneModel model;
    private PlayerPlaneView view;

    //ctor
    public PlayerPlaneController(PlayerPlaneModel model, PlayerPlaneView view) {
        this.model = model;
        this.view = view;
    }
    //ctor  |  set x,y, width, height
    public PlayerPlaneController(int planeX, int planeY) {
        this(new PlayerPlaneModel(planeX,planeY,35,30), new PlayerPlaneView(Utils.loadImage("resources/plane2.png")) );
    }

    //GETTER model


    public PlayerPlaneModel getModel() {
        return model;
    }

    //move plane
    public void run(int status){
        model.run(status);
    }

    //draw playerPlane
    public void draw(Graphics g){
        view.draw(g, model);
    }
}
