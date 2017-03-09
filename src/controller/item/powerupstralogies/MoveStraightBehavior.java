package controller.item.powerupstralogies;

import controller.item.PowerUpController;
import models.PowerUpModel;

/**
 * Created by EDGY on 3/9/2017.
 */
public class MoveStraightBehavior extends PowerUpMoveBehavior{
    @Override
    public void move(PowerUpController powerUpController) {
        powerUpController.getMovement().y = PowerUpModel.SPEED;
    }
}
