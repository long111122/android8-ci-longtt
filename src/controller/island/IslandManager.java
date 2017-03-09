package controller.island;
import controller.controllermanager.ControllerManager;
import gui.GameWindow;
import models.IslandModel;
import util.Utils;

/**
 * Created by EDGY on 3/10/2017.
 */
public class IslandManager extends ControllerManager{
    private int countGreen = 0;
    private int countGreenFix = 0;
    private static final int DELAYISLANDGREEN = 150;
    private static final int DELAYISLANDGREENFIX = 340;
    private IslandController islandController;

    public static IslandManager news = new IslandManager();

    @Override
    public void run() {
        super.run();
        countGreen++;
        countGreenFix++;
        if(countGreen >= DELAYISLANDGREEN){
            countGreen = 0;
            islandController = IslandController.IslandCreate(IslandType.GREEN, Utils.RandomAll(GameWindow.SCREEN_WIDTH - IslandModel.WIDTH,0),0);
        } else if(countGreenFix >= DELAYISLANDGREENFIX){
            countGreenFix = 0;
            islandController = IslandController.IslandCreate(IslandType.GREENFIX, Utils.RandomAll(GameWindow.SCREEN_WIDTH - IslandModel.WIDTH,0),0);
        }
        this.add(islandController);
    }
}
