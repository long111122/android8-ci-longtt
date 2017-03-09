package controller.collsion;

import models.GameModel;

/**
 * Created by EDGY on 3/4/2017.
 */
public interface Collision {
    GameModel getGameModel();
    void onContact(Collision collision);
}
