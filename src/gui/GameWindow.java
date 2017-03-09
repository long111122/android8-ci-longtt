package gui;

import controller.*;
import models.EnemyModel;
import models.IslandModel;
import models.ItemModel;
import models.PlayerPlaneModel;
import util.KeyInput;
import util.Utils;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;


/**
 * Created by EDGY on 2/19/2017.
 */
public class GameWindow extends Frame {
    private static final int SPEED = 3;
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 400;
    private static final double AOT = 60.0;

    private BufferedImage backBufferImage;
    private Graphics backGraphics;
    private boolean isDeath = false;
    private PlayerPlaneController playerPlaneController;
    private int randomEnemyFast;
    private int typeBullet = 1;
    private boolean isBomb = false;
    private boolean isPowerUp = false;
    private long lastTimePress = 0;
    private int delayEnemyMove;
    private int delayIsland;
    private int delayItems;
    Thread thread;
//bullet

    private Image playerBullet;

    private boolean isMoveRight;
    private boolean isMoveLeft;
    private boolean isMoveUp;
    private boolean isMoveDown;
    private boolean isShoot;

    private Vector<PlayerBulletController> playerBullets;
    private Vector<EnemyBulletController> enemyBullets;
    private Vector<EnemyBulletController> enemyBulletsYellow;
    private Vector<EnemyController> enemies;
    private Vector<IslandController> islands;
    private BackgroundController background;
    private Vector<ItemController> items;
    private Vector<EnemyController> enemiesFast;

    public GameWindow(){
        setVisible(true);
        setSize(400,600);
        playerBullets = new Vector<>();
        enemyBullets = new Vector<>();
        enemies = new Vector<>();
        islands = new Vector<>();
        items = new Vector<>();
        enemiesFast = new Vector<>();
        //35 : width of playerPlane;
        //30 : height of playerPlane

        //TO DO : fix hash code

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("WindowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                System.out.println("WindowClosed");
            }
        });

        //1: load image
//
        //2: Draw image
        background = new BackgroundController();
        playerPlaneController = new PlayerPlaneController(SCREEN_WIDTH/2, SCREEN_HEIGHT - PlayerPlaneModel.HEIGHT,playerBullets);
        playerBullet = Utils.loadImage("resources/bullet-single.png");
        update(getGraphics());
        //repaint();

        addKeyListener(new KeyInput(this));

        thread = new Thread(new Runnable(){
            @Override
            public void run() {
//                double ns = 1000000000/AOT;
                int countEnemy = 0;
                int countEnemyFast = 0;
                int count = 0;
                int countIsland = 0;
                int countItem = 0;
                while(true) {
                    background.run();
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();

                    //random delay
                    delayEnemyMove = Utils.RandomAll(200, 50);
                    delayIsland = Utils.RandomAll(1500, 1200);
                    delayItems = Utils.RandomAll(3000, 1000);

                    // create enemy
                    countEnemy++;
                    countEnemyFast++;
                    if (countEnemy >= delayEnemyMove) {
                        if(countEnemyFast >= delayEnemyMove){
                            EnemyCreate(2);
                        }
                        EnemyCreate(1);
                        countEnemy = 0;
                    }

                    //create island
                    countIsland++;
                    if (countIsland >= delayIsland) {
                        IslandCreate();
                        countIsland = 0;
                    }

                    //create items
                    countItem++;
                    if (countItem >= delayItems) {
                        ItemsCreate();
                        countItem = 0;
                    }

                    //item run
                    for (ItemController item : items) {
                        item.run(1);
                    }


                    //smoother movement
                    movePlane();

                    //enemy run and create enemyBullet
                    if(enemies != null){
                        for (EnemyController enemy : enemies){
                            enemy.run(1);
                            if(!enemy.isAlive()) isDeath = true;
                            enemy.RemoveOutSide();
                        }
                    }

                    if(enemies != null){
                        for (EnemyController enemy : enemies){
                            count++;
                            if(count >= 35){
                                enemy.shoot();
                                count = 0;
                            }
                        }
                    }

                    if(enemiesFast != null){
                        for (EnemyController enemyController : enemiesFast){
                            if(randomEnemyFast == 1) {
                                enemyController.run(2);
                            } else {
                                enemyController.run(3);
                            }
                            if(!enemyController.isAlive()) isDeath = true;
                            enemyController.RemoveOutSide();
                        }
                    }

                    if(playerBullets != null){
                        for (PlayerBulletController playerBulletController : playerBullets){
                            playerBulletController.run(1);
                        }
                    }
                    //intersects
                    GetItems();
                    enemyDestroy();
                    enemyFastDestroy();
                    //enemy auto shoot
                    for (EnemyBulletController enemyBulletController : enemyBullets){
                        enemyBulletController.run(1);
                    }

                    //move island
                    for (IslandController island : islands) {
                        island.run(1);
                        if (island.getModel().getY() >= SCREEN_HEIGHT){
                           island.setDefaultLocation();
                        }
                    }
                    removeByIterator();
                }
            }
        });


        backBufferImage = new BufferedImage(
                SCREEN_WIDTH,
                SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
    }

    public void GetItems() {
        if (items != null) {
            Iterator itemIterator = items.iterator();
            while(itemIterator.hasNext()){
                ItemController itemController = (ItemController)itemIterator.next();
                if(itemController.getModel().IsIntersect(playerPlaneController.getModel())){
                    itemIterator.remove();
                    if(isPowerUp){
                        typeBullet++;
                        isPowerUp = false;
                    }
                    else if(isBomb){
                        enemies.clear();
                        enemiesFast.clear();
                        isBomb = false;
                    }
                }
            }
        }
    }

    public void ItemsCreate(){
        ItemController item = null;
        int random = Utils.RandomAll(2,1);
        /**
         * type of items
         * 2 : bomb
         * 1 : power up
         */
        switch (random){
            case 1 :
                item = new ItemController(Utils.RandomAll(SCREEN_WIDTH - ItemModel.WIDTH,0),0,Utils.loadImage("resources/bomb.png"),1);
                isBomb = true;
                break;

            case 2 :
                item = new ItemController(Utils.RandomAll(SCREEN_WIDTH - ItemModel.WIDTH,0),0,Utils.loadImage("resources/power-up.png"),2);
                isPowerUp = true;
                break;
        }
        items.add(item);
    }

    public void removeByIterator(){
        //remove bullets out side
        Iterator<PlayerBulletController> iterator = playerBullets.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getModel().getHeight() < 0){
                iterator.remove();
            }
        }

        //remove enemy out side
        Iterator<EnemyController> iteratorEnemy = enemies.iterator();
        while(iteratorEnemy.hasNext()){
            if(iteratorEnemy.next().getModel().getX() > SCREEN_HEIGHT)iteratorEnemy.remove();
        }

        Iterator<ItemController> iteratorItem = items.iterator();
        while(iteratorItem.hasNext()){
            if(iteratorItem.next().getModel().getY() >= SCREEN_HEIGHT)iteratorItem.remove();
        }

        //remove enemybullet


    }

    public void enemyDestroy(){
        if(playerBullets != null && enemies != null) {
              Iterator<EnemyController> enemyControllerIterator = enemies.iterator();
            while (enemyControllerIterator.hasNext()) {
                EnemyController enemyController = enemyControllerIterator.next();
                Iterator<PlayerBulletController> iteratorBullet = playerBullets.iterator();
                while (iteratorBullet.hasNext()) {
                    PlayerBulletController pbc = iteratorBullet.next();
                    if(enemyController.getModel().IsIntersect(pbc.getModel())){
                        enemyController.isDeath();
                        if(isDeath) {
                            enemyControllerIterator.remove();
                            iteratorBullet.remove();
                        }
                    }
                }
            }
        }
    }

    public void enemyFastDestroy(){
        if(playerBullets != null && enemiesFast != null) {
            Iterator<EnemyController> enemyControllerIterator = enemiesFast.iterator();
            while (enemyControllerIterator.hasNext()) {
                EnemyController enemyController = enemyControllerIterator.next();
                Iterator<PlayerBulletController> iteratorBullet = playerBullets.iterator();
                while (iteratorBullet.hasNext()) {
                    PlayerBulletController pbc = iteratorBullet.next();
                    if(enemyController.getModel().IsIntersect(pbc.getModel())){
                        enemyController.isDeath();
                        if(isDeath) {
                            enemyControllerIterator.remove();
                            iteratorBullet.remove();

                        }
                    }
                }
            }
        }
    }

    private void IslandCreate() {
        ArrayList<String> urlImage = new ArrayList<>();
        urlImage.add("resources/island.png");
        urlImage.add("resources/island-2.png");
        IslandController island = new IslandController(Utils.RandomAll(SCREEN_WIDTH - IslandModel.WIDTH,0),0,Utils.RandomImage(urlImage));
        islands.add(island);
    }

    //key input
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT)  {
            //move plane to right
             isMoveRight = true;
        }
        else if (key == KeyEvent.VK_LEFT)  {
            //move plane to left
            isMoveLeft = true;
        }
        else if (key == KeyEvent.VK_UP) {
            //move plane to up
           isMoveUp = true;
        }
        else if (key == KeyEvent.VK_DOWN) {
            //move plane to down
             isMoveDown = true;
        }
        if (key == KeyEvent.VK_SPACE ){
                isShoot = true;
            }
        }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT)  {
            //move plane to right
//            playerPlane.setMoveX(0);
                    isMoveRight = false;
        }
        else if (key == KeyEvent.VK_LEFT)  {
            //move plane to left
//            playerPlane.setMoveX(0);
                    isMoveLeft = false;
        }
        else if (key == KeyEvent.VK_UP) {
            //move plane to up
//            playerPlane.setMoveY(0);
                    isMoveUp = false;
        }
        else if (key == KeyEvent.VK_DOWN) {
            //move plane to down
//            playerPlane.setMoveY(0);
                    isMoveDown = false;
        }
        else if(key == KeyEvent.VK_SPACE){
            isShoot = false;
        }
    }

    //current time
    private long getNow(){
        return System.currentTimeMillis();
    }

    private void movePlane(){
        /**
         *  1 : UP
         *  2 : DOWN
         *  3 : LEFT
         *  4 : RIGHT
         */
        //move plane to up
        if(isMoveUp){
           playerPlaneController.run(1);
        }
        //move plane to down
        if(isMoveDown){
            playerPlaneController.run(2);
        }
        //move plane to left
        if(isMoveLeft){
            playerPlaneController.run(3);
        }
        //move plane to right
        if(isMoveRight){
            playerPlaneController.run(4);
        }
        if(isShoot && getNow() - lastTimePress > 300){
//            playerBullets.add(new PlayerBulletController(playerPlaneController.getModel().getX() + 30/2,playerPlaneController.getModel().getY() - 15,playerBullet));
            playerPlaneController.shoot(typeBullet);
            lastTimePress = getNow();
        }

    }

    public void start(){
        thread.start();
    }

    //load image with path


    /**
     *
     * @param typeEnemy
     * 1 : WHITE ENEMY PLANE
     * 2 : YELLOW ENEMY PLANE
     */
    private void EnemyCreate(int typeEnemy){
        switch (typeEnemy){
            case 1 :
                EnemyController enemy = new EnemyController(Utils.RandomAll(SCREEN_WIDTH - EnemyModel.WIDTH,0),0,enemyBullets,Utils.loadImage("resources/enemy_plane_white_3.png"));
                enemies.add(enemy);
                break;

            case 2 :
                EnemyController enemyFast;
                randomEnemyFast = Utils.RandomAll(2,1);
                if(randomEnemyFast == 1){
                    enemyFast = new EnemyController(0,Utils.RandomAll(SCREEN_HEIGHT/2,0),enemyBullets,Utils.loadImage("resources/enemy-green-1.png"));
                } else {
                    enemyFast = new EnemyController(SCREEN_WIDTH,Utils.RandomAll(SCREEN_HEIGHT/2,0),enemyBullets,Utils.loadImage("resources/enemy-green-2.png"));
                }
                enemiesFast.add(enemyFast);
        }


    }

    @Override
    public synchronized void update(Graphics g) {
        if(backBufferImage != null) {
            backGraphics = backBufferImage.getGraphics();
            background.draw(backGraphics);

            for (IslandController island : islands) {
                island.draw(backGraphics);
            }
            //player
            playerPlaneController.draw(backGraphics);
            //enemy

            //playerbullet
            if(playerBullets != null) {
                for (PlayerBulletController bullet : playerBullets) {
                   bullet.draw(backGraphics);
                }
            }
            //enemy bullet
            if(enemyBullets != null){
                for (EnemyBulletController bullet : enemyBullets){
                   bullet.draw(backGraphics);
                }
            }

            if(items != null){
                for (ItemController item : items){
                    item.draw(backGraphics);
                }
            }

            if(enemies != null){
                for (EnemyController enemy : enemies){
                    enemy.draw(backGraphics);
                }
            }

           if(enemiesFast != null){
               for (EnemyController enemyController : enemiesFast){
                   enemyController.draw(backGraphics);
               }
           }

            for (EnemyController enemy : enemies){
                for (EnemyBulletController bullets : enemyBullets){
                    bullets.draw(backGraphics);
                }
            }

            g.drawImage(backBufferImage, 0, 0, null);
        }
    }
}



