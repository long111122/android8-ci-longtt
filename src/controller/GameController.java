package controller;

import models.GameModel;
import view.GameView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by EDGY on 2/28/2017.
 */
public abstract class GameController {
    protected GameModel model;
    protected GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public GameModel getModel() {
        return model;
    }

    public GameView getView() {
        return view;
    }

    public abstract void run(int status);


}
