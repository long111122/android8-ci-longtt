package controller.enemy;

import controller.controllermanager.ControllerManager;
import gui.GameWindow;
import models.EnemyModel;
import util.Utils;

/**
 * Created by EDGY on 3/7/2017.
 */
public class EnemyManager extends ControllerManager{
    private static final int YELLOW_DELAY = 400;
    private static final int WHITE_DELAY = 100;
    private static final int GREEN_DELAY = 300;
    private int enemyX;
    private int countWhite = 0;
    private int countYellow = 0;
    private int countGreen = 0;

    public static final EnemyManager news = new EnemyManager();

    @Override
    public void run() {
        super.run();
        countWhite++;
        countGreen++;
        countYellow++;
        enemyX = Utils.RandomAll(GameWindow.SCREEN_WIDTH - EnemyModel.WIDTH,0);
        if (countWhite >= WHITE_DELAY){
            EnemyLoading(EnemyType.WHITE);
            countWhite = 0;
        } else if (countYellow >= YELLOW_DELAY){
//            EnemyLoading(EnemyType.YELLOW);
            countYellow = 0;
        } else if (countGreen >= GREEN_DELAY){
            EnemyLoading(EnemyType.GREEN);
            countGreen = 0;
        }
    }

    public void EnemyLoading(EnemyType enemyType){
        for (int i = 0; i < Utils.RandomAll(3,1); i++){
            EnemyController enemyController = EnemyController.enemyCreate(enemyType,enemyX,0);
            enemyX += EnemyModel.WIDTH*3;
            this.gameControllers.add(enemyController);
        }
    }

}
