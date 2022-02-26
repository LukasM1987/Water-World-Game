package com.waterworld.game_objects;

import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_engine.StringObjectValue;

import java.awt.*;
import java.util.Random;

public class LevelSeaweed {

    private static final Random random = new Random();
    private static final int MINUS_ONE_HUNDRED = -100;
    private static final int MINUS_TWO_HUNDRED = -200;

    private GameObject seaweedOne1;
    private GameObject seaweedOne2;
    private GameObject seaweedOne3;
    private GameObject seaweedOne4;
    private GameObject seaweedOne5;
    private GameObject seaweedOne6;
    private GameObject seaweedOne7;
    private GameObject seaweedOne8;
    private GameObject seaweedOne9;
    private GameObject seaweedOne10;
    private GameObject seaweedOne11;
    private GameObject seaweedOne12;
    private GameObject seaweedOne13;
    private GameObject seaweedOne14;
    private GameObject seaweedTwo1;
    private GameObject seaweedTwo2;

    public void init() {
        seaweedOne1 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 150, 315,100,85);
        seaweedOne2 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 65 + 150, 315,100,85);
        seaweedOne3 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 130 + 150, 315,100,85);
        seaweedOne4 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 195 + 150, 315,100,85);
        seaweedOne5 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 260 + 150, 315,100,85);
        seaweedOne6 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 325 + 150, 315,100,85);
        seaweedOne7 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 390 + 150, 315,100,85);
        seaweedOne8 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 455 + 150, 315,100,85);
        seaweedOne9 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 520 + 150, 315,100,85);
        seaweedOne10 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 585 + 150,315,100,85);
        seaweedOne11 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 650 + 150, 315,100,85);
        seaweedOne12 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 715 + 150, 315,100,85);
        seaweedOne13 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 780 + 150, 315,100,85);
        seaweedOne14 = new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 845 + 150, 315,100,85);
        seaweedTwo1 = new GameObject(1, StringObjectValue.SEAWEED_ONE.getValue(), StringObjectValue.RIGHT.getValue(),0,0, GameEngine.WIDTH + 250, 200, 200,200);
        seaweedTwo2 = new GameObject(1, StringObjectValue.SEAWEED_ONE.getValue(), StringObjectValue.RIGHT.getValue(),0,0, GameEngine.WIDTH + 850, 200, 200,200);

    }

    public void move() {
        seaweedOne1.move();
        seaweedOne2.move();
        seaweedOne3.move();
        seaweedOne4.move();
        seaweedOne5.move();
        seaweedOne6.move();
        seaweedOne7.move();
        seaweedOne8.move();
        seaweedOne9.move();
        seaweedOne10.move();
        seaweedOne11.move();
        seaweedOne12.move();
        seaweedOne13.move();
        seaweedOne14.move();
        seaweedTwo1.move();
        seaweedTwo2.move();
    }

    public void drawBack(Graphics g) {
        seaweedTwo1.draw(g);
    }

    public void drawFront(Graphics g) {
        seaweedOne1.draw(g);
        seaweedOne2.draw(g);
        seaweedOne3.draw(g);
        seaweedOne4.draw(g);
        seaweedOne5.draw(g);
        seaweedOne6.draw(g);
        seaweedOne7.draw(g);
        seaweedOne8.draw(g);
        seaweedOne9.draw(g);
        seaweedOne10.draw(g);
        seaweedOne11.draw(g);
        seaweedOne12.draw(g);
        seaweedOne13.draw(g);
        seaweedOne14.draw(g);
        seaweedTwo2.draw(g);
    }

    public void transfer() {
        if (seaweedOne1.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne1.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne2.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne2.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne3.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne3.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne4.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne4.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne5.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne5.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne6.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne6.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne7.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne7.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne8.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne8.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne9.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne9.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne10.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne10.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne11.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne11.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne12.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne12.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne13.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne13.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedOne14.getHorizontalPos() <= MINUS_ONE_HUNDRED) {
            seaweedOne14.setHorizontalPosition(GameEngine.WIDTH);
        }
        if (seaweedTwo1.getHorizontalPos() <= MINUS_ONE_HUNDRED * 2) {
            seaweedTwo1.setHorizontalPosition(random.nextInt(800) + 800);
        }
        if (seaweedTwo2.getHorizontalPos() <= MINUS_ONE_HUNDRED * 2) {
            seaweedTwo2.setHorizontalPosition(random.nextInt(800) + 800);
        }
    }

    public void moveOutOfFrame() {
        seaweedOne1.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne2.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne3.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne4.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne5.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne6.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne7.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne8.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne9.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne10.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne11.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne12.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne13.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedOne14.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedTwo1.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedTwo2.setVerticalPosition(MINUS_TWO_HUNDRED);
    }
}
