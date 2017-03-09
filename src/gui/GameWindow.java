package gui;

import controller.background.BackgroundController;;
import controller.collsion.PoolController;
import controller.enemy.EnemyBulletManager;
import controller.enemy.EnemyManager;
import controller.island.IslandManager;
import controller.item.PowerUpManager;
import controller.player.PlayerPlaneController;
import util.KeyInput;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
/**
 * Created by EDGY on 2/19/2017.
 */
public class GameWindow extends Frame {
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 400;
    private static final double AOT = 60.0;
    private BufferedImage backBufferImage;
    private Graphics backGraphics;
    Thread thread;
    public static boolean isMoveRight;
    public static boolean isMoveLeft;
    public static boolean isMoveUp;
    public static boolean isMoveDown;
    public static boolean isShoot;
    private BackgroundController background;

    public GameWindow(){
        setVisible(true);
        setSize(400,600);
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
        background = new BackgroundController();
        update(getGraphics());
        addKeyListener(new KeyInput(this));
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
//                double ns = 1000000000/AOT;
                while(true) {
                    background.run();
//                    IslandManager.news.run();
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                    PlayerPlaneController.news.run();
                    EnemyManager.news.run();
                    EnemyBulletManager.news.run();
                    PowerUpManager.news.run();
                    PoolController.pool.run();
                }
            }
        });
        backBufferImage = new BufferedImage(
                SCREEN_WIDTH,
                SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
    }

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
            isMoveRight = false;
        }
        else if (key == KeyEvent.VK_LEFT)  {
            //move plane to left
            isMoveLeft = false;
        }
        else if (key == KeyEvent.VK_UP) {
            //move plane to up
            isMoveUp = false;
        }
        else if (key == KeyEvent.VK_DOWN) {
            //move plane to down
            isMoveDown = false;
        }
        else if(key == KeyEvent.VK_SPACE){
            isShoot = false;
        }
    }

    public void start(){
        thread.start();
    }

    @Override
    public synchronized void update(Graphics g) {
        if(backBufferImage != null) {
            backGraphics = backBufferImage.getGraphics();
            background.draw(backGraphics);
            //player
            PlayerPlaneController.news.draw(backGraphics);
            //enemy
            EnemyManager.news.draw(backGraphics);
            //enemybullet
            EnemyBulletManager.news.draw(backGraphics);
            //powerup
            PowerUpManager.news.draw(backGraphics);
//            IslandManager.news.draw(backGraphics);
            g.drawImage(backBufferImage, 0, 0, null);
        }
    }
}



