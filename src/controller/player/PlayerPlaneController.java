package controller.player;

import controller.collsion.Collision;
import controller.controllermanager.ControllerManager;
import controller.controllermanager.GameController;
import controller.collsion.PoolController;
import controller.item.PowerUpController;
import gui.GameWindow;
import models.GameModel;
import models.PlayerPlaneModel;
import util.Utils;
import view.GameView;


import java.awt.*;


/**
 * Created by EDGY on 2/27/2017.
 */
public class PlayerPlaneController extends GameController implements Collision {

    private long lastTimePress = 0;
    private ControllerManager bulletManager;
    private int levelBullet = 1;
    private int bulletDelay;

    public PlayerPlaneController(GameModel model, GameView view) {
        super(model, view);
        bulletManager = new ControllerManager();
        PoolController.pool.add(this);
    }

    public final static PlayerPlaneController news = new PlayerPlaneController(
            new PlayerPlaneModel(GameWindow.SCREEN_WIDTH/2-PlayerPlaneModel.WIDTH/2, GameWindow.SCREEN_HEIGHT-PlayerPlaneModel.HEIGHT),
            new GameView(Utils.loadImage("resources/plane3.png"))
    );

    public void AddBullet(int x, int y,int width, int height,int hp,int damage, Image img, PlayerBulletType playerBulletType){
        PlayerBulletController playerBulletController = new PlayerBulletController(x,y,width,height,hp,damage,img,playerBulletType);
        bulletManager.add(playerBulletController);
    }

    public void shoot() {
        /**
         * status :
         *  1 : middle bullet
         *  2 : left bullet
         *  3 : right bullet
         */
        bulletDelay = 350;
        int damage = 3;
        int hp = 30;
        String img = "resources/bullet-single.png";
        int widthImg = Utils.getWidth(img);
        int heightImg = Utils.getHeight(img);

        String imgDouble = "resources/bullet-double.png";
        int xDouble = model.getX() + model.getWidth() / 2 - Utils.getWidth(imgDouble)/2;
        int yDouble = model.getY() - Utils.getHeight(imgDouble);
        int widthDouble = Utils.getWidth(imgDouble);
        int heightDouble = Utils.getHeight(imgDouble);

        if(levelBullet > 0 && levelBullet <= 2){
            AddBullet(model.getX() + model.getWidth() / 2 - widthImg/2,
                    model.getY() - heightImg,
                    widthImg,
                    heightImg,
                    hp,
                    damage,
                    Utils.loadImage(img),
                    PlayerBulletType.MIDDLE
            );
        }
        if(levelBullet > 1){
            bulletDelay /= 2;
        }
        if(levelBullet > 2){
            damage += 2;
            AddBullet(xDouble, yDouble,widthDouble,heightDouble,hp,damage, Utils.loadImage(imgDouble), PlayerBulletType.MIDDLE);
        }
        if(levelBullet > 3) {
            img = "resources/bullet-single.png";
            AddBullet(
                    (xDouble - model.getX()) / 2 - widthImg / 2 + model.getX(),
                    yDouble,
                    widthImg,
                    heightImg,
                    hp,damage,
                    Utils.loadImage(img),
                    PlayerBulletType.MIDDLE
            );
            AddBullet(
                    (xDouble - model.getX()) / 2 - widthImg/2 + xDouble + widthDouble ,
                    yDouble,
                    Utils.getWidth(img),
                    Utils.getHeight(img),
                    hp,damage,
                    Utils.loadImage(img),
                    PlayerBulletType.MIDDLE
            );
        }
        if(levelBullet > 4){
            img = "resources/bullet-right.png";
            AddBullet(model.getX(),
                    model.getY() - Utils.getHeight(img),
                    Utils.getWidth(img),
                    Utils.getHeight(img),
                    hp,damage,
                    Utils.loadImage(img),
                    PlayerBulletType.LEFT
            );
            img = "resources/bullet-left.png";
            AddBullet(model.getX() + model.getWidth(),
                    model.getY() - Utils.getHeight(img),
                    Utils.getWidth(img),
                    Utils.getHeight(img),
                    hp,damage,
                    Utils.loadImage(img),
                    PlayerBulletType.RIGHT
            );
        }

    }

    @Override
    public void run() {
        /**
         *  1 : UP
         *  2 : DOWN
         *  3 : LEFT
         *  4 : RIGHT
         */
        //move plane to up
        if(GameWindow.isMoveUp){
           if(model instanceof PlayerPlaneModel){
               ((PlayerPlaneModel) model).run(PlayerRun.UP);
           }
        }
        //move plane to down
        if(GameWindow.isMoveDown){
            if(model instanceof PlayerPlaneModel){
                ((PlayerPlaneModel) model).run(PlayerRun.DOWN);
            }
        }
        //move plane to left
        if(GameWindow.isMoveLeft){
            if(model instanceof PlayerPlaneModel){
                ((PlayerPlaneModel) model).run(PlayerRun.LEFT);
            }
        }
        //move plane to right
        if(GameWindow.isMoveRight){
            if(model instanceof PlayerPlaneModel){
                ((PlayerPlaneModel) model).run(PlayerRun.RIGHT);
            }
        }
        if(GameWindow.isShoot && Utils.getNow() - lastTimePress > bulletDelay){
            shoot();
            lastTimePress = Utils.getNow();
        }
        bulletManager.run();
    }

    @Override
    public void draw(Graphics g) {
        bulletManager.draw(g);
        super.draw(g);
    }

    @Override
    public GameModel getGameModel() {
        return model;
    }

    @Override
    public void onContact(Collision collision) {
        if(collision instanceof PowerUpController){
            levelBullet++;
        }
    }
}