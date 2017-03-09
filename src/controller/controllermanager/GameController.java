package controller.controllermanager;

import models.GameModel;
import view.GameView;

import java.awt.*;

/**
 * Created by EDGY on 2/28/2017.
 */
public class GameController implements Manager{
    protected GameModel model;
    protected GameView view;
    protected GameMovement movement;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.movement = new GameMovement();
    }

    public GameModel getModel() {
        return model;
    }

    public GameMovement getMovement() {
        return movement;
    }

    public void run() {
        model.move(this.movement);
    }

    @Override
    public void draw(Graphics graphics) {
        view.draw(graphics,model);
    }
}
