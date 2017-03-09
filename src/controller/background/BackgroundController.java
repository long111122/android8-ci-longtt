package controller.background;

import models.BackgroundModel;
import util.Utils;
import view.BackgroundView;

import java.awt.*;

/**
 * Created by EDGY on 2/27/2017.
 */
public class BackgroundController {
    private BackgroundModel model;
    private BackgroundView view;

    public BackgroundController(BackgroundModel model, BackgroundView view) {
        this.model = model;
        this.view = view;
    }

    public BackgroundController() {
        this(new BackgroundModel(),new BackgroundView(Utils.loadImage("resources/background.png")));
    }

    public void run(){
        model.run();
    }

    public void draw(Graphics g){
        view.draw(g,model);
    }
}
