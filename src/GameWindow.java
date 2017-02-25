import com.sun.javafx.css.StyleCache;
import com.sun.javafx.scene.KeyboardShortcutsHandler;
import com.sun.org.apache.xml.internal.resolver.Catalog;
import com.sun.org.apache.xml.internal.security.keys.KeyInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by EDGY on 2/19/2017.
 */
public class GameWindow extends Frame {
    private static final int SPEED = 3;
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_WIDTH = 400;
    private static final double AOT = 60.0;

    private BufferedImage backBufferImage;
    private Graphics backGraphics;

    Image backgroundImg;
    Image player;
    Image islandImg;
    Image playerBullet;
    private PlayerPlane playerPlane;
    private Enemy enemy;

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

    private ArrayList<PlayerBullet> bullets;
    private ArrayList<EnemyBullet> enemyBullets;
    private ArrayList<Enemy> enemies;
    private ArrayList<Island> islands;
    private Background background;


    public GameWindow(){
        setVisible(true);
        setSize(400,600);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        islands = new ArrayList<>();

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
        backgroundImg = loadImage("resources/background.png");
        player = loadImage("resources/plane2.png");

        background = new Background(backgroundImg);
        playerBullet = loadImage("resources/bullet.png");
        playerPlane = new PlayerPlane(SCREEN_WIDTH /2,SCREEN_HEIGHT - 35,player);



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
                    background.Tick();
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
                        for (PlayerBullet bullet : bullets)
                        bullet.move();
//                        System.out.println(bullets.size());
                    }
                    //remove bullets out side
                    Iterator<PlayerBullet> iterator = bullets.iterator();
                    while(iterator.hasNext()){
                        if(iterator.next().getBulletY() < 0){
                            iterator.remove();
                        }
                    }



                    //enemy auto shoot
                    now = getNow();
                    delta += (now - lastTime);
                    lastTime = now;




//                    int x = 0,y = 0;
                    int delayEnemyMove = randomDelay(5000,2000);
                    int delayIsland = randomDelay(7000,1200);
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
                        for (Enemy enemy : enemies){
                            enemy.Move(1);
                        }
                    }

                    for (Enemy enemy : enemies){
                        if(enemy.getEnemyBullets() != null){
                            ArrayList<EnemyBullet> lists = enemy.getEnemyBullets();
                            for (EnemyBullet enemyBullet : lists){
                                System.out.println(enemyBullet.getBulletY());
                                enemyBullet.move();
                            }
                        }
                    }

                    Iterator<Enemy> iteratorEnemy = enemies.iterator();
                    while(iteratorEnemy.hasNext()){
                        if(iteratorEnemy.next().getEnermyY() > SCREEN_HEIGHT)iteratorEnemy.remove();
                    }
//
                    isDestroyEnemy();

                    //move island
                    for (Island island : islands) {
                        island.move();
                        if (island.getIslandY() >= SCREEN_HEIGHT){
                            island.setIslandY(0);
                            island.setIslandX(randomDelay(300,100));
                        }
                    }
                }
            }
        });


        backBufferImage = new BufferedImage(
                SCREEN_WIDTH,
                SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);


    }

    private void IslandLoad(){
        if(randomDelay(2,1)/2 == 0){
            islandImg = loadImage("resources/island.png");
        } else {
            islandImg = loadImage("resources/island-2.png");
        }
    }

    private void IslandCreate(){
        IslandLoad();
        if(islands.size() < 4) {
            islands.add(new Island(randomDelay(300, 100), 0, 50, 50, islandImg));
        }
    }

    private void isDestroyEnemy() {
        Iterator enemyItr = enemies.iterator();
        Iterator playerBulletItr = bullets.iterator();
        while (enemyItr.hasNext()) {
            Enemy enemy = (Enemy) enemyItr.next();
            while (playerBulletItr.hasNext()) {
                PlayerBullet playerBullet = (PlayerBullet) playerBulletItr.next();
                if ((playerBullet.getBulletX() > enemy.getEnermyX() && playerBullet.getBulletX() < (enemy.getEnermyX() + enemy.getImg().getWidth(null))) && ((enemy.getEnermyY() + enemy.getImg().getHeight(null) / 2) > playerBullet.getBulletY())) {
                    playerBulletItr.remove();
                    enemyItr.remove();
                    break;
                }
            }
        }
    }

    public void keyPressed(KeyEvent e){
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




    public int randomDelay(int max, int min){
        Random rd = new Random();
        return rd.nextInt(max - min + 1) + min;
    }

    private long getNow(){
        return System.currentTimeMillis();
    }

    private void movePlane(){
        //move plane to right
        if(isMoveRight && (playerPlane.planeX + 10)< (SCREEN_WIDTH - 30)){
            playerPlane.planeX+=SPEED;
        }
        //move plane to left
        if(isMoveLeft && (playerPlane.planeX - 10)> -5){
            playerPlane.planeX-=SPEED;
        }
        //move plane to up
        if(isMoveUp && (playerPlane.planeY - 10)>20){
            playerPlane.planeY-=SPEED;
        }
        //move plane to down
        if(isMoveDown && (playerPlane.planeY + 10)<(SCREEN_HEIGHT-35)){
            playerPlane.planeY+=SPEED;
        }
        if(isShoot && getNow() - lastTimePress > 200){
            bullets.add(new PlayerBullet(playerBullet, playerPlane.getPlaneX() + 35 / 2 - 13 / 2, playerPlane.getPlaneY() - 33, 13, 33));
            lastTimePress = getNow();
        }

    }

    public void removeBullet(){

    }

    public void start(){
        thread.start();
    }

//    public class ShootBullet extends Thread {
//        @Override
//        public void run() {
//            Image img = loadImage("resources/bullet.png");
//            bullet = new Bullet(planeX + 35 / 2, planeY, img);
//            bullets = new ArrayList<>();
//            bullets.add(bullet);
//           for (int i = 0; i <= bullets.size();i++){
//               bullets.get(i).move();
//               repaint();
//           }
//
//        }
//    }

    //load image with path
    private Image loadImage(String url){
        try {
            Image image = ImageIO.read(new File(url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    private void EnemyCreate(){
        Random rd = new Random();

        enemy = new Enemy(rd.nextInt(SCREEN_WIDTH - 80 + 1)+30,0,loadImage("resources/enemy_plane_white_3.png"),32,32);
        enemies.add(enemy);
        for (Enemy enemy : enemies){
            enemy.addBullet(new EnemyBullet(enemy.getEnermyX() ,enemy.getHeight() + enemy.getEnermyY() - 15,loadImage("resources/enemy_bullet.png")));
        }
    }

//    private void destroy(){
//        if(enermy.getEnermyY() + enermy.getImg().getHeight())
//    }

    @Override
    public void update(Graphics g) {
        if(backBufferImage != null) {
            backGraphics = backBufferImage.getGraphics();
            background.draw(backGraphics);


            for (Island island : islands) {
                island.draw(backGraphics);
            }
            //player
            playerPlane.draw(backGraphics);
            //enemy

            //bullet
            if(bullets != null) {
                for (PlayerBullet bullet : bullets) {
                   bullet.draw(backGraphics);
                }
            }
            //enemy bullet
            if(enemyBullets != null){
                for (EnemyBullet bullet : enemyBullets){
                    backGraphics.drawImage(bullet.getImg(),bullet.getBulletX(),bullet.getBulletY(),9,9,null);
                }
            }


            if(enemies != null){
                for (Enemy enemy : enemies){
                    enemy.draw(backGraphics);
                }
            }


            for (Enemy enemy : enemies){
                for (EnemyBullet bullets : enemy.getEnemyBullets()){
                    bullets.draw(backGraphics);
                }
            }

            g.drawImage(backBufferImage, 0, 0, null);
        }
    }
}



