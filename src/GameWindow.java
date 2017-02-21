import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by EDGY on 2/19/2017.
 */
public class GameWindow extends Frame {
    private static final int SPEED = 10;
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_WIDTH = 400;

    private BufferedImage backBufferImage;
    private Graphics backGraphics;

    Image background;
    Image player;
    Image island;
    boolean isShoot = false;
    private int planeX = SCREEN_WIDTH /2;
    private int planeY = SCREEN_HEIGHT - 35;
    private int countBullet;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean moveUp = false;
    private boolean moveDown = false;

    Thread thread;

    private ArrayList<Bullet> bullets;
    private Bullet bullet;
    private Timer timer;

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
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT)  {
                        //move plane to right
                        moveRight = true;
//                        planeX += 5;
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT)  {
                        //move plane to right
                        moveLeft = true;
//                        planeX -= 5;
//                        System.out.println(planeX);
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        //move plane to right
                        moveUp = true;
//                        planeY -= 5;
//                        System.out.println(planeY);
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        //move plane to right
                        moveDown = true;
//                        planeY += 5;
//                        System.out.println(planeY);
                        repaint();
                    }

                movePlane();

                    if(e.getKeyCode() == KeyEvent.VK_SPACE){
                        //TODO : shoot bullets

                        isShoot = true;
                        ShootBullet shoot = new ShootBullet();
                        shoot.start();

                        repaint();
                    }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    //move plane to right
                    moveRight = false;

                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    //move plane to right
                    moveLeft = false;

                }
                if (e.getKeyCode() == KeyEvent.VK_UP ) {
                    //move plane to right
                    moveUp = false;

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
                    //move plane to right
                    moveDown = false;

                }
            }


        });

        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        });


        backBufferImage = new BufferedImage(
                SCREEN_WIDTH,
                SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);


    }

    public void start(){
        thread.start();
    }

    public class ShootBullet extends Thread {
        @Override
        public void run() {
            Image img = loadImage("resources/bullet.png");
            bullet = new Bullet(planeX + 35 / 2, planeY, img);
            bullets = new ArrayList<>();
            bullets.add(bullet);
           for (int i = 0; i <= bullets.size();i++){
               bullets.get(i).move();
               repaint();
           }

        }
    }

    private void movePlane(){

        if(moveRight && planeX <= (SCREEN_WIDTH-35)){
            planeX += SPEED;
        }
        if(moveLeft && planeX >= 0){
            planeX -= SPEED;
        }
        if(moveUp  && planeY >= 35){
            planeY -= SPEED;
        }
        if(moveDown  && planeY <= (SCREEN_HEIGHT-35-10)){
            planeY += SPEED;
        }
    }


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


    @Override
    public void update(Graphics g) {
        if(backBufferImage != null) {
            backGraphics = backBufferImage.getGraphics();

            backGraphics.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
//        g.drawImage(player,(400-35)/2,600-28,35,28,null);

            backGraphics.drawImage(island, 60, 300, 50, 50, null);
            backGraphics.drawImage(player, planeX, planeY, 35, 28, null);
            g.drawImage(backBufferImage, 0, 0, null);
        }
                                                                                        }


    }



