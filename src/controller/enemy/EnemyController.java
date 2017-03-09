package controller.enemy;

import controller.collsion.Collision;
import controller.controllermanager.GameController;
import controller.collsion.PoolController;
import controller.enemy.stralogies.*;
import controller.player.PlayerBulletController;
import models.EnemyModel;
import models.GameModel;
import util.Utils;
import view.GameView;

/**
 * Created by EDGY on 2/27/2017.
 */
public class EnemyController extends GameController implements Collision{
    private static String img = "";
    private static int hp;
    private static int damage;
    private EnemyMoveBehavior moveBehavior;
    private ShootBehaivor shootBehaivor;
    private static EnemyController enemyController;

    public EnemyController(GameModel model, GameView view, ShootBehaivor shootBehaivor, EnemyMoveBehavior moveBehavior){
        super(model, view);
        this.shootBehaivor = shootBehaivor;
        this.moveBehavior = moveBehavior;
        PoolController.pool.add(this);
    }

    public static EnemyController enemyCreate(EnemyType enemyType, int enemyX, int enemyY){

        if(enemyType == EnemyType.GREEN){
            img = "resources/plane1.png";
            hp = 15;
            damage = 3;
            EnemyLoading(img,new GreenShootBehavior(),new GreenMoveBehavior(),enemyX,enemyY,hp,damage);
        }
        else if(enemyType == EnemyType.WHITE){
            img = "resources/enemy_plane_white_3.png";
            hp = 5;
            damage = 2;
            EnemyLoading(img,new ShootStraightBehavior(),new MoveStraightBehavior(),enemyX,enemyY,hp,damage);
        }
        else if(enemyType == EnemyType.YELLOW){
            img = "resources/enemy_plane_yellow_3.png";
            hp = 10;
            damage = 2;
            EnemyLoading(img,new ShootStraightBehavior(),new MoveStraightBehavior(),enemyX,enemyY,hp,damage);
        }
        return enemyController;
    }

    public static void EnemyLoading(String img,ShootBehaivor shootBehaivor, EnemyMoveBehavior moveBehavior,int enemyX, int enemyY, int hp, int damage){
        enemyController = new EnemyController(
                new GameModel(enemyX,enemyY,Utils.getWidth(img),Utils.getHeight(img),hp,damage),
                new GameView(Utils.loadImage(img)),
                shootBehaivor,
                moveBehavior
        );
    }

    //move enemy
    @Override
    public void run(){
        super.run();
        if(moveBehavior != null){
            moveBehavior.move(this);
        }
        if(shootBehaivor != null){
            shootBehaivor.shoot(this);
        }
    }

    @Override
    public GameModel getGameModel() {
        return model;
    }

    @Override
    public void onContact(Collision collision) {
        if(collision instanceof PlayerBulletController){
            this.getModel().getHit(collision.getGameModel().getDamage());
        }
    }
}
