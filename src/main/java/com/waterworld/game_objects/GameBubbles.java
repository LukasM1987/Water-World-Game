package com.waterworld.game_objects;

import com.waterworld.game_engine.StringObjectValue;

import java.awt.*;
import java.util.Random;

public class GameBubbles {

    private static final Random random = new Random();

    private static final int MINUS_SIXTEEN = -16;
    private static final int MINUS_THIRTY_TWO = -32;
    private static final int MINUS_SIXTY_FOUR = -64;

    private GameObject bubble8x8One;
    private GameObject bubble8x8Two;
    private GameObject bubble8x8Three;
    private GameObject bubble8x8Four;
    private GameObject bubble16x16One;
    private GameObject bubble16x16Two;
    private GameObject bubble24x24One;
    private GameObject bubble24x24Two;
    private GameObject bubble36x36One;

    private boolean isLevel;

    public GameBubbles(boolean isLevel) {
        this.isLevel = isLevel;
    }

    public void init() {
        bubble8x8One = new GameObject(3, StringObjectValue.BUBBLE_8X8.getValue(), StringObjectValue.UP.getValue(), 0, 0, 123, 423, 8, 8);
        bubble8x8Two = new GameObject(3, StringObjectValue.BUBBLE_8X8.getValue(), StringObjectValue.UP.getValue(), 0, 0, 213, 431, 8, 8);
        bubble8x8Three = new GameObject(3, StringObjectValue.BUBBLE_8X8.getValue(), StringObjectValue.UP.getValue(), 0, 0, 437, 411, 8, 8);
        bubble8x8Four = new GameObject(3, StringObjectValue.BUBBLE_8X8.getValue(), StringObjectValue.UP.getValue(), 0, 0, 692, 452, 8, 8);
        bubble16x16One = new GameObject(2, StringObjectValue.BUBBLE_16X16.getValue(), StringObjectValue.UP.getValue(), 0, 0, 246, 462, 16, 16);
        bubble16x16Two = new GameObject(2, StringObjectValue.BUBBLE_16X16.getValue(), StringObjectValue.UP.getValue(), 0, 0, 576, 452, 16, 16);
        bubble24x24One = new GameObject(2, StringObjectValue.BUBBLE_24X24.getValue(), StringObjectValue.UP.getValue(), 0, 0, 146, 572, 16, 16);
        bubble24x24Two = new GameObject(2, StringObjectValue.BUBBLE_24X24.getValue(), StringObjectValue.UP.getValue(), 0, 0, 746, 523, 16, 16);
        if (isLevel) {
            bubble36x36One = new GameObject(1, StringObjectValue.BUBBLE_36X36.getValue(), StringObjectValue.UP.getValue(), 0, 0, 398, 532, 36, 36);
        }
    }

    public void drawBack(Graphics g) {
        bubble8x8One.draw(g);
        bubble8x8Two.draw(g);
        bubble8x8Three.draw(g);
        bubble8x8Four.draw(g);
        bubble16x16One.draw(g);
        bubble16x16Two.draw(g);
    }

    public void drawFront(Graphics g) {
        bubble24x24One.draw(g);
        bubble24x24Two.draw(g);
        if (isLevel) {
            bubble36x36One.draw(g);
        }
    }

    public void move() {
        bubble8x8One.move();
        bubble8x8Two.move();
        bubble8x8Three.move();
        bubble8x8Four.move();
        bubble16x16One.move();
        bubble16x16Two.move();
        bubble24x24One.move();
        bubble24x24Two.move();
        if (isLevel) {
            bubble36x36One.move();
        }
    }

    public void transfer() {
        if (bubble8x8One.getVerticalPos() <= MINUS_SIXTEEN) {
            bubble8x8One.setVerticalPosition(442);
            bubble8x8One.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble8x8Two.getVerticalPos() <= MINUS_SIXTEEN) {
            bubble8x8Two.setVerticalPosition(412);
            bubble8x8Two.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble8x8Three.getVerticalPos() <= MINUS_SIXTEEN) {
            bubble8x8Three.setVerticalPosition(482);
            bubble8x8Three.setHorizontalPosition(random.nextInt(400) + 390);
        }
        if (bubble8x8Four.getVerticalPos() <= MINUS_SIXTEEN) {
            bubble8x8Four.setVerticalPosition(452);
            bubble8x8Four.setHorizontalPosition(random.nextInt(400) + 390);
        }
        if (bubble16x16One.getVerticalPos() <= MINUS_THIRTY_TWO) {
            bubble16x16One.setVerticalPosition(450);
            bubble16x16One.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble16x16Two.getVerticalPos() <= MINUS_THIRTY_TWO) {
            bubble16x16Two.setVerticalPosition(random.nextInt(400) + 480);
            bubble16x16Two.setHorizontalPosition(random.nextInt(400) + 380);
        }
        if (bubble24x24One.getVerticalPos() <= MINUS_SIXTY_FOUR) {
            bubble24x24One.setVerticalPosition(random.nextInt(400) + 460);
            bubble24x24One.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble24x24Two.getVerticalPos() <= MINUS_SIXTY_FOUR) {
            bubble24x24Two.setVerticalPosition(random.nextInt(400) + 430);
            bubble24x24Two.setHorizontalPosition(random.nextInt(400) + 370);
        }
        if (isLevel) {
            if (bubble36x36One.getVerticalPos() <= MINUS_SIXTY_FOUR) {
                bubble36x36One.setVerticalPosition(523);
                bubble36x36One.setHorizontalPosition(random.nextInt(740));
            }
        }
    }

    public void moveOutOfFrame() {
        bubble8x8One.setVerticalPosition(-100);
        bubble8x8Two.setVerticalPosition(-100);
        bubble8x8Three.setVerticalPosition(-100);
        bubble8x8Four.setVerticalPosition(-100);
        bubble16x16One.setVerticalPosition(-100);
        bubble16x16Two.setVerticalPosition(-100);
        bubble24x24One.setVerticalPosition(-100);
        bubble24x24Two.setVerticalPosition(-100);
        bubble36x36One.setVerticalPosition(-100);
    }
}
