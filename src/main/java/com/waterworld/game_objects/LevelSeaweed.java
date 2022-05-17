package com.waterworld.game_objects;

import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_engine.StringObjectValue;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelSeaweed {

    private static final Random random = new Random();
    private static final int MINUS_ONE_HUNDRED = -100;
    private static final int MINUS_TWO_HUNDRED = -200;
    private static final int LOOP_LAPS = 14;

    private final List<GameObject> seaweeds = new ArrayList<>();

    private GameObject seaweedTwo1;
    private GameObject seaweedTwo2;

    public void init() {
        seaweeds.clear();
        for (int i = 0; i < LOOP_LAPS; i++) {
            seaweeds.add(new GameObject(1, StringObjectValue.SEAWEED_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0,GameEngine.WIDTH + 150 + (65 * i), 315,100,85));
        }
        seaweedTwo1 = new GameObject(1, StringObjectValue.SEAWEED_ONE.getValue(), StringObjectValue.RIGHT.getValue(),0,0, GameEngine.WIDTH + 250, 200, 200,200);
        seaweedTwo2 = new GameObject(1, StringObjectValue.SEAWEED_ONE.getValue(), StringObjectValue.RIGHT.getValue(),0,0, GameEngine.WIDTH + 850, 200, 200,200);
    }

    public void move() {
        for (int i = 0; i < seaweeds.size(); i++) {
            seaweeds.get(i).move();
        }
        seaweedTwo1.move();
        seaweedTwo2.move();
    }

    public void drawBack(Graphics g) {
        seaweedTwo1.draw(g);
    }

    public void drawFront(Graphics g) {
        for (int i = 0; i < seaweeds.size(); i++) {
            seaweeds.get(i).draw(g);
        }
        seaweedTwo2.draw(g);

    }

    public void transfer() {
        for (int i = 0; i < seaweeds.size(); i++) {
            if (seaweeds.get(i).getHorizontalPos() <= MINUS_ONE_HUNDRED) {
                seaweeds.get(i).setHorizontalPosition(GameEngine.WIDTH);
            }
        }
        if (seaweedTwo1.getHorizontalPos() <= MINUS_ONE_HUNDRED * 2) {
            seaweedTwo1.setHorizontalPosition(random.nextInt(800) + 800);
        }
        if (seaweedTwo2.getHorizontalPos() <= MINUS_ONE_HUNDRED * 2) {
            seaweedTwo2.setHorizontalPosition(random.nextInt(800) + 800);
        }
    }

    public void moveOutOfFrame() {
        for (int i = 0; i < LOOP_LAPS; i++) {
            seaweeds.get(i).setVerticalPosition(MINUS_TWO_HUNDRED);
        }
        seaweedTwo1.setVerticalPosition(MINUS_TWO_HUNDRED);
        seaweedTwo2.setVerticalPosition(MINUS_TWO_HUNDRED);
    }
}
