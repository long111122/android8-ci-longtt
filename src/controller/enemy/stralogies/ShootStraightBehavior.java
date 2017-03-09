package controller.enemy.stralogies;

import controller.enemy.EnemyBulletController;
import controller.enemy.EnemyBulletManager;
import controller.enemy.EnemyBulletType;
import controller.enemy.EnemyController;
import models.EnemyBulletModel;
import models.GameModel;

/**
 * Created by EDGY on 3/8/2017.
 */
public class ShootStraightBehavior extends ShootBehaivor{
    private static int SPEED = 8;
    private static int DELAYBULLET = 50;
    private int count = 0;

    @Override
    public void shoot(EnemyController enemyController) {
        GameModel enemyModel = enemyController.getModel();
        count++;
        if(count == DELAYBULLET) {
            count = 0;
            EnemyBulletController enemyBulletController = EnemyBulletController.BulletCreate(
                    EnemyBulletType.NORMAL,
                    enemyModel.getX() + enemyModel.getWidth() / 2 - EnemyBulletModel.WIDTH / 2,
                    enemyModel.getY() + enemyModel.getHeight()
            );
            enemyBulletController.getMovement().y = SPEED;
            EnemyBulletManager.news.add(enemyBulletController);
        }
    }
}
