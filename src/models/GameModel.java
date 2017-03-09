package models;

import controller.controllermanager.GameMovement;
import gui.GameWindow;

import java.awt.*;

/**
 * Created by EDGY on 2/28/2017.
 */
public class GameModel {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean isAlive;
    protected int hp;
    protected int damage;

    public GameModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isAlive = true;
    }

    public GameModel(int x, int y, int width, int height, int hp, int damage) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hp = hp;
        this.damage = damage;
        isAlive = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void Death(){
        isAlive = false;
    }

    public int getDamage() {
        return damage;
    }

    public boolean IsIntersect(GameModel gameModel){
        Rectangle r1 = new Rectangle(x,y,width,height);
        Rectangle r2 = new Rectangle(gameModel.x,gameModel.y,gameModel.width,gameModel.height);
        return r1.intersects(r2);
    }

    public void move(GameMovement gameMovement){
        this.x += gameMovement.x;
        this.y += gameMovement.y;
        if(this.x > GameWindow.SCREEN_WIDTH ||
                this.x < 0 ||
                this.y > GameWindow.SCREEN_HEIGHT ||
                this.y < 0)this.Death();
    }

    public void getHit(int damage){
        hp -= damage;
        if(hp <= 0){
            this.Death();
        }
    }

    public void DamageUpdate(int damage){
        this.damage += damage;
    }

    public void HpUpdate(int hp){
        this.hp += hp;
    }
}
