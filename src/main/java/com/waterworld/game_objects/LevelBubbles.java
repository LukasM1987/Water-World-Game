package com.waterworld.game_objects;

import java.awt.*;
import java.util.Random;

public class LevelBubbles {

    private static final Random random = new Random();

    private static final String BUBBLE_8X8 = "bubble8x8";
    private static final String BUBBLE_16X16 = "bubble16x16";
    private static final String BUBBLE_24X24 = "bubble24x24";
    private static final String BUBBLE_36X36 = "bubble36x36";
    private static final int MINUS_SIXTEEN = -16;
    private static final int MINUS_THIRTY_TWO = -32;
    private static final int MINUS_SIXTY_FOUR = -64;

    private static final String UP = "up";

    private GameObject bubble8x8One;
    private GameObject bubble8x8Two;
    private GameObject bubble8x8Three;
    private GameObject bubble8x8Four;
    private GameObject bubble16x16One;
    private GameObject bubble16x16Two;
    private GameObject bubble24x24One;
    private GameObject bubble24x24Two;
    private GameObject bubble36x36One;

    public void init() {
        bubble8x8One = new GameObject(3, BUBBLE_8X8, UP, 0, 0, 123, 423, 8, 8);
        bubble8x8Two = new GameObject(3, BUBBLE_8X8, UP, 0, 0, 213, 431, 8, 8);
        bubble8x8Three = new GameObject(3, BUBBLE_8X8, UP, 0, 0, 437, 411, 8, 8);
        bubble8x8Four = new GameObject(3, BUBBLE_8X8, UP, 0, 0, 692, 452, 8, 8);
        bubble16x16One = new GameObject(2, BUBBLE_16X16, UP, 0, 0, 246, 462, 16, 16);
        bubble16x16Two = new GameObject(2, BUBBLE_16X16, UP, 0, 0, 576, 452, 16, 16);
        bubble24x24One = new GameObject(2, BUBBLE_24X24, UP, 0, 0, 146, 572, 16, 16);
        bubble24x24Two = new GameObject(2, BUBBLE_16X16, UP, 0, 0, 746, 523, 16, 16);
        bubble36x36One = new GameObject(1, BUBBLE_36X36, UP, 0, 0, 398, 532, 36, 36);

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
        bubble36x36One.draw(g);
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
        bubble36x36One.move();
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
        if (bubble36x36One.getVerticalPos() <= MINUS_SIXTY_FOUR) {
            bubble36x36One.setVerticalPosition(523);
            bubble36x36One.setHorizontalPosition(random.nextInt(740));
        }
    }
}
