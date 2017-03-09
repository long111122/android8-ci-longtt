package controller;

import java.util.Vector;

/**
 * Created by EDGY on 3/4/2017.
 */
public class PoolController {
    public static Vector<Collision> vector;

    public PoolController() {
        vector = new Vector<>();
    }

    public void Collision(){
        for (int i = 0; i < vector.size(); i++){
            for (int j = 0; i < vector.size() - 1; j++){
                if(vector.get(i).getGameModel().IsIntersect(vector.get(j).getGameModel())){
                    vector.get(i).Intersect(vector.get(j));
                    vector.get(j).Intersect(vector.get(j));
                }
            }
        }
    }
}
