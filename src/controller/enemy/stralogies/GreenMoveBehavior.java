package controller.enemy.stralogies;

import controller.enemy.EnemyController;
import gui.GameWindow;
import models.BackgroundModel;
import models.EnemyModel;

/**
 * Created by EDGY on 3/9/2017.
 */
public class GreenMoveBehavior extends EnemyMoveBehavior{
    private boolean isMoveLeft = true;
    private boolean isMoveRight = true;

    @Override
    public void move(EnemyController enemyController) {
        if(enemyController.getModel().getY() < 100) {
            enemyController.getMovement().y = EnemyModel.GREENSPEED;
        }
        else {
            enemyController.getMovement().y = -BackgroundModel.SPEED;
            GreenShootBehavior.Loading();
            if (isMoveLeft) {
                enemyController.getMovement().x = -BackgroundModel.SPEED;
                if (enemyController.getGameModel().getX() <= 0) {
                    isMoveLeft = false;
                    isMoveRight = true;
                    if (isMoveRight) {
                        enemyController.getMovement().x = BackgroundModel.SPEED;
                        if (enemyController.getGameModel().getX() >= GameWindow.SCREEN_WIDTH) {
                            isMoveRight = false;
                            isMoveLeft = true;
                        }
                    }
                }
            }
        }
    }
}
