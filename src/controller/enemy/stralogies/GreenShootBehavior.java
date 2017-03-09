package controller.enemy.stralogies;

import controller.enemy.EnemyBulletController;
import controller.enemy.EnemyBulletManager;
import controller.enemy.EnemyBulletType;
import controller.enemy.EnemyController;
import gui.GameWindow;
import models.EnemyModel;
import models.GameModel;
import util.Utils;

/**
 * Created by EDGY on 3/9/2017.
 */
public class GreenShootBehavior extends ShootBehaivor{
    private int count = 0;
    private static boolean isShoot = false;
    private static int DELAY = 100;
    private String img = "resources/lazer.png";

    public static void Loading(){
        isShoot = true;
    }

    public boolean isShoot() {
        return isShoot;
    }

    @Override
    public void shoot(EnemyController enemyController) {
        count++;
        if(count == DELAY){
            GameModel enemyModel = enemyController.getModel();
            count = 0;
            EnemyBulletController enemyBulletController = EnemyBulletController.BulletCreate(EnemyBulletType.LAZE,
                    enemyModel.getX() + enemyModel.getWidth()/2 - Utils.getWidth(img)/2 ,
                    enemyModel.getHeight() + enemyModel.getY()
                    );
            EnemyBulletManager.news.add(enemyBulletController);
        }
    }
}
