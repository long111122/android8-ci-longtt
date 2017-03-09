package controller.island;

import controller.controllermanager.GameController;
import models.GameModel;
import models.IslandModel;
import util.Utils;
import view.GameView;

import java.awt.*;

/**
 * Created by EDGY on 3/10/2017.
 */
public class IslandController  extends GameController{
    private static String image = "";

    public IslandController(GameModel model, GameView view) {
        super(model, view);
    }

    public static IslandController IslandCreate(IslandType islandType, int islandX, int islandY){
        switch (islandType){
            case GREEN:
                image = "resources/island.png";
                break;

            case GREENFIX:
                image = "resources/island1.png";
                break;
        }
        return IslandLoading(image, islandX, islandY);
    }

    public static IslandController IslandLoading(String img, int islandX, int islandY){
        IslandController islandController = new IslandController(
                new GameModel(islandX,islandY, Utils.getWidth(img), Utils.getHeight(img)),
                new GameView(Utils.loadImage(img))
        );
        return islandController;
    }

    @Override
    public void run() {
        super.run();
        if(model instanceof IslandModel){
            ((IslandModel) model).run();
        }
    }
}
