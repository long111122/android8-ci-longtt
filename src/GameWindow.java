import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by EDGY on 2/19/2017.
 */
public class GameWindow extends Frame {
    Image background;
    Image player;
    Image island;
    Image bullet;
    boolean isShoot = false;
    private int planeX = (400-35)/2;
    private int planeY = 600-35;
    private int bulletX;
    private int bulletY;
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
        bullet = loadImage("resources/bullet.png");
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
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT && planeX <= (400-35)) {
                        //move plane to right
                        planeX += 10;
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT && planeX >= 0) {
                        //move plane to right
                        planeX -= 10;
//                        System.out.println(planeX);
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_UP && planeY >= 35) {
                        //move plane to right
                        planeY -= 10;
//                        System.out.println(planeY);
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN && planeY <= (600-35-10)) {
                        //move plane to right
                        planeY += 10;
//                        System.out.println(planeY);
                        repaint();
                    }

                    if(e.getKeyCode() == KeyEvent.VK_SPACE){
                        //TODO : shoot bullets
                        isShoot = true;
                        while(bulletY > 0) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                bulletX = (planeX + 35 / 2);
                                bulletY = planeY - 10;
                                System.out.println(planeY);
                                repaint();
                                try {
                                    Thread.sleep(10000);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }).start();


                        }

                    }
                }


            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

            }


        });
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
        g.drawImage(background,0,0,400,600, null);
//        g.drawImage(player,(400-35)/2,600-28,35,28,null);

        g.drawImage(island,60,300,50,50,null);
        g.drawImage(player,planeX,planeY,35,28,null);

        if(isShoot == true) {
                g.drawImage(bullet, bulletX, bulletY, 13, 33, null);
            isShoot = false;
        }
    }


}
