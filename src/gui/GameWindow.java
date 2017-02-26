package gui;

import controller.*;
import util.KeyInput;
import util.Utils;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by EDGY on 2/19/2017.
 */
public class GameWindow extends Frame {
    private static final int SPEED = 3;
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_WIDTH = 400;
    private static final double AOT = 60.0;

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    private BufferedImage backBufferImage;
    private Graphics backGraphics;

    private PlayerPlaneController playerPlaneController;
    private EnemyController enemy;

    private boolean isCount = false;
    private long now;
    private long lastTime;
    private long lastTime2;
    private long lastTime3;
    private long lastTimePress = 0;
    Thread thread;
//bullet
    private boolean isMoveRight;
    private boolean isMoveLeft;
    private boolean isMoveUp;
    private boolean isMoveDown;
    private boolean isShoot;

    private ArrayList<PlayerBulletController> bullets;
    private ArrayList<EnemyBulletController> enemyBullets;
    private ArrayList<EnemyController> enemies;
    private ArrayList<IslandController> islands;
    private BackgroundController background;

    public GameWindow(){
        setVisible(true);
        setSize(400,600);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        islands = new ArrayList<>();

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
        playerPlaneController = new PlayerPlaneController(SCREEN_WIDTH /2,SCREEN_HEIGHT - 35);
        update(getGraphics());
        //repaint();

        addKeyListener(new KeyInput(this));

        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                lastTime = getNow();
                lastTime2 = getNow();
                double ns = 1000000000/AOT;
                double delta = 0;
                double alpha = 0;

                while(true){
                    background.run();
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //smoother movement
                    movePlane();
                    repaint();
                    //enemy move
                    //shoot bullets
                    if(bullets != null){
                        for (PlayerBulletController bullet : bullets)
                        bullet.run();
                    }

                    //enemy auto shoot
                    now = getNow();
                    delta += (now - lastTime);
                    lastTime = now;

//                    int x = 0,y = 0;
                    int delayEnemyMove = Utils.RandomAll(5000,2000);
                    int delayIsland = Utils.RandomAll(7000,1200);
                    if(delta >= delayEnemyMove){
                        EnemyCreate();
                        delta-=delayEnemyMove;
                    }
                    now = getNow();
                    alpha += (now - lastTime);
                    lastTime3 = now;
                    if(alpha >= delayIsland){
                        IslandCreate();
                        alpha -= delayIsland;
                    }
                    now = getNow();
                    alpha += now - lastTime2;
                    lastTime2 = now;
//                    System.out.println(alpha);
                    //show enemy
                    if(enemies != null){
                        for (EnemyController enemy : enemies){
                            enemy.run(1);
                        }
                    }

                    for (EnemyController enemy : enemies){
                        if(enemy.getModel().getBullets() != null){
                            ArrayList<EnemyBulletController> lists = enemy.getModel().getBullets();
                            for (EnemyBulletController enemyBullet : lists){
                                enemyBullet.run(1);
                            }
                        }
                    }



                    //move island
                    for (IslandController island : islands) {
                        island.run();
                        if (island.getModel().getIslandY() >= SCREEN_HEIGHT){
                           island.setDefaultLocation();
                        }
                    }

                    isDestroyEnemy();
                    removeByIterator();
                }
            }
        });


        backBufferImage = new BufferedImage(
                SCREEN_WIDTH,
                SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);


    }

    public void removeByIterator(){
        //remove bullets out side
        Iterator<PlayerBulletController> iterator = bullets.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getModel().getHeight() < 0){
                iterator.remove();
            }
        }

        //remove enemy out side
        Iterator<EnemyController> iteratorEnemy = enemies.iterator();
        while(iteratorEnemy.hasNext()){
            if(iteratorEnemy.next().getModel().getEnemyX() > SCREEN_HEIGHT)iteratorEnemy.remove();
        }

        //remove plaerbullet out side
        Iterator<PlayerBulletController> iteratorBullet = bullets.iterator();
        while(iteratorBullet.hasNext()){
            if(iteratorBullet.next().getModel().getY() <= 0)iteratorBullet.remove();
        }
    }

    private void IslandCreate(){
        if(islands.size() < 4) {
            islands.add(new IslandController(Utils.RandomAll(SCREEN_WIDTH - 50, 0), 0));
        }
    }

    private void isDestroyEnemy() {
        Iterator<EnemyController> enemyItr = enemies.iterator();
        Iterator<PlayerBulletController> playerBulletItr = bullets.iterator();
        while (enemyItr.hasNext()) {
            EnemyController enemy = enemyItr.next();
            while (playerBulletItr.hasNext()) {
                PlayerBulletController playerBullet = playerBulletItr.next();
                if ((playerBullet.getModel().getX() > enemy.getModel().getEnemyX() && playerBullet.getModel().getX() < (enemy.getModel().getEnemyX() + enemy.getModel().getWidth())
                        && ((enemy.getModel().getEnemyY() + enemy.getModel().getHeight() / 2) > playerBullet.getModel().getX()))) {
                    playerBulletItr.remove();
                    enemyItr.remove();
                    break;
                }
            }
        }
    }


    //key input
    public void keyPressed(KeyEvent e)                                                                              {
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

    //random



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
        if(isShoot && getNow() - lastTimePress > 200){
            bullets.add(new PlayerBulletController(playerPlaneController.getModel().getPlaneX() + 30/2,playerPlaneController.getModel().getPlaneY() - 15));
            System.out.println(bullets.size());
            lastTimePress = getNow();
        }

    }

    public void removeBullet(){

    }

    public void start(){
        thread.start();
    }

    //load image with path




    private void EnemyCreate(){
        //rd.nextInt(SCREEN_WIDTH - 80 + 1)
        //
        enemy = new EnemyController(Utils.RandomAll(SCREEN_WIDTH,80),0);
        enemies.add(enemy);
        for (EnemyController enemy : enemies){
            enemy.addBullet(new EnemyBulletController(enemy.getModel().getEnemyX() + enemy.getModel().getWidth()/2 - 9/2,enemy.getModel().getHeight() + enemy.getModel().getEnemyY()));
        }
    }

    @Override
    public void update(Graphics g) {
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
            if(bullets != null) {
                for (PlayerBulletController bullet : bullets) {
                   bullet.draw(backGraphics);
                }
            }
            //enemy bullet
            if(enemyBullets != null){
                for (EnemyBulletController bullet : enemyBullets){
                   bullet.draw(backGraphics);
                }
            }


            if(enemies != null){
                for (EnemyController enemy : enemies){
                    enemy.draw(backGraphics);
                }
            }


            for (EnemyController enemy : enemies){
                for (EnemyBulletController bullets : enemy.getModel().getBullets()){
                    bullets.draw(backGraphics);
                }
            }

            g.drawImage(backBufferImage, 0, 0, null);
        }
    }
}



