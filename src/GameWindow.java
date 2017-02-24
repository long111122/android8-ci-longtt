import com.sun.org.apache.xml.internal.resolver.Catalog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by EDGY on 2/19/2017.
 */
public class GameWindow extends Frame {
    private static final int SPEED = 10;
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_WIDTH = 400;
    private static final double AOT = 60.0;

    private BufferedImage backBufferImage;
    private Graphics backGraphics;

    Image background;
    Image player;
    Image island;
    Image playerBullet;
    private PlayerPlane playerPlane;
    private Enemy enemy;


    Thread thread;
//bullet
    private ArrayList<PlayerBullet> bullets;
    private ArrayList<EnemyBullet> enemyBullets;
//    private Bullet bullet;
    private Timer timer;

    public GameWindow(){
        setVisible(true);
        setSize(400,600);
        bullets = new ArrayList<>();
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
//        try {
//            background = ImageIO.read(new File("resources/background.png"));
//            player = ImageIO.read(new File("resources/plane2.png"));
//            island = ImageIO.read(new File("resources/island.png"));
//            bullet = ImageIO.read(new File("resources/bullet.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        background = loadImage("resources/background.png");
        player = loadImage("resources/plane2.png");
        island = loadImage("resources/island.png");
        playerBullet = loadImage("resources/bullet.png");
        playerPlane = new PlayerPlane(SCREEN_WIDTH /2,SCREEN_HEIGHT - 35,player);



        //2: Draw image
        update(getGraphics());
        //repaint();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_RIGHT)  {
                    //move plane to right
                    System.out.println(playerPlane.getPlaneX());
//                    isMoveRight = true;
                    playerPlane.setMoveX(3);

                }
                else if (key == KeyEvent.VK_LEFT)  {
                    //move plane to left
                    playerPlane.setMoveX(-3);
//                    isMoveLeft = true;
                }
                else if (key == KeyEvent.VK_UP) {
                    //move plane to up
                    playerPlane.setMoveY(-3);
//                    isMoveUp = true;
                }
                else if (key == KeyEvent.VK_DOWN) {
                    //move plane to down
                    playerPlane.setMoveY(3);
//                    isMoveDown = true;
                }
                if (key == KeyEvent.VK_SPACE ){
                    bullets.add(new PlayerBullet(playerBullet,playerPlane.getPlaneX() + 35/2 - 13/2,playerPlane.getPlaneY() - 33,13,33));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_RIGHT)  {
                    //move plane to right
                    playerPlane.setMoveX(0);
//                    isMoveRight = false;
                }
                else if (key == KeyEvent.VK_LEFT)  {
                    //move plane to left
                    playerPlane.setMoveX(0);
//                    isMoveLeft = false;
                }
                else if (key == KeyEvent.VK_UP) {
                    //move plane to up
                    playerPlane.setMoveY(0);
//                    isMoveUp = false;
                }
                else if (key == KeyEvent.VK_DOWN) {
                    //move plane to down
                    playerPlane.setMoveY(0);
//                    isMoveDown = false;
                }
            }

        });
        //old move

        //new move



        EnermyCreate();
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                long lastTime = System.nanoTime();
                double ns = 1000000000/AOT;
                double delta = 0;
                while(true){
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //smoother movement
                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    lastTime = now;

                    if(delta >= 1){
                        movePlane();
                        delta--;
                    }

                    repaint();
                    //enemy move
                    if(enemy != null)enemy.Move(3);
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

                    EnemyBullet enemyBullet = new EnemyBullet(enemy.getEnermyX() + 12,enemy.getEnermyY() + 33,loadImage("resources/bullet-round.png"));
                    enemyBullets.add(enemyBullet);
                    if(enemyBullets != null){
                        for (EnemyBullet bullet : enemyBullets){
                            bullet.move();
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


    public void movePlane(){
        if(playerPlane.getPlaneX() >= SCREEN_WIDTH - 35) {
           playerPlane.setPlaneX(SCREEN_WIDTH - 35);
        }
        if(playerPlane.getPlaneX() <= 0){
            playerPlane.setPlaneX(0);
        }
        if(playerPlane.getPlaneY() <= 30){
            playerPlane.setPlaneY(30);
        }
        if(playerPlane.getPlaneY() >= SCREEN_HEIGHT - 33){
            playerPlane.setPlaneY(SCREEN_HEIGHT - 33);
        }
        playerPlane.move();
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


    private void EnermyCreate(){
        enemy = new Enemy(30,30,loadImage("resources/enemy_plane_white_3.png"));
        enemyBullets = new ArrayList<>();
    }

//    private void destroy(){
//        if(enermy.getEnermyY() + enermy.getImg().getHeight())
//    }

    @Override
    public void update(Graphics g) {
        if(backBufferImage != null) {
            backGraphics = backBufferImage.getGraphics();

            backGraphics.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

            backGraphics.drawImage(island, 60, 300, 50, 50, null);
            //player
            //backGraphics.drawImage(player, planeX, planeY, 35, 28, null);
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

            backGraphics.drawImage(enemy.getImg(),enemy.getEnermyX(),enemy.getEnermyY(),32,32,null);
            g.drawImage(backBufferImage, 0, 0, null);
        }
                                                                                        }


    }



