package controller.item;

import controller.controllermanager.ControllerManager;
import gui.GameWindow;
import models.PowerUpModel;
import util.Utils;

/**
 * Created by EDGY on 3/9/2017.
 */
public class PowerUpManager extends ControllerManager{
    private static int DELAY = 400;
    private int count;

    public static PowerUpManager news = new PowerUpManager();

    @Override
    public void run() {
        super.run();
        count++;
        if(count == DELAY){
            count = 0;
            PowerUpController powerUpController = PowerUpController.PowerUpCreate(Utils.RandomAll(GameWindow.SCREEN_WIDTH - PowerUpModel.WIDTH*2,0),0);
            this.add(powerUpController);
        }
    }
}
