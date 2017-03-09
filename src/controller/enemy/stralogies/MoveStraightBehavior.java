package controller.enemy.stralogies;

import controller.enemy.EnemyController;
import models.EnemyModel;

/**
 * Created by EDGY on 3/8/2017.
 */
public class MoveStraightBehavior extends EnemyMoveBehavior{
    @Override
    public void move(EnemyController enemyController) {
        enemyController.getMovement().y = EnemyModel.SPEED;
    }
}
