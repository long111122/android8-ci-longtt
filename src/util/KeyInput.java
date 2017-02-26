package util;

import gui.GameWindow;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by EDGY on 2/25/2017.
 */
public class KeyInput extends KeyAdapter{
    GameWindow gameWindow;

    public KeyInput(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void keyPressed(KeyEvent e){
        gameWindow.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        gameWindow.keyReleased(e);
    }
}
