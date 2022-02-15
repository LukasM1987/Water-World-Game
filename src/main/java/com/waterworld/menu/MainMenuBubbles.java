package com.waterworld.menu;

import com.waterworld.game_objects.GameObject;

import java.awt.*;
import java.util.Random;

public class MainMenuBubbles {

    private static final Random random = new Random();

    private static final String BUBBLE_8X8 = "bubble8x8";
    private static final String BUBBLE_16X16 = "bubble16x16";
    private static final String BUBBLE_24X24 = "bubble24x24";
    private static final String UP = "up";
    private static final String ANY_DIRECTION = "any direction";
    private static final int VERTICAL_MINUS_THIRTY_TWO = -32;
    private static final int VERTICAL_MINUS_SIXTY_FOUR = -64;

    private GameObject bubble8x8One;
    private GameObject bubble8x8Two;
    private GameObject bubble8x8Three;
    private GameObject bubble8x8Four;
    private GameObject bubble8x8Five;
    private GameObject bubble8x8Six;
    private GameObject bubble8x8Seven;
    private GameObject bubble8x8Eight;
    private GameObject bubble8x8Nine;
    private GameObject bubble8x8Ten;
    private GameObject bubble8x8Eleven;
    private GameObject bubble8x8Twelve;
    private GameObject bubble8x8Thirteen;
    private GameObject bubble8x8Fourteen;
    private GameObject bubble8x8Fifteen;
    private GameObject bubble8x8Sixteen;
    private GameObject bubble8x8Seventeen;
    private GameObject bubble8x8Eighteen;
    private GameObject bubble8x8Nineteen;
    private GameObject bubble8x8Twenty;
    private GameObject bubble16x16One;
    private GameObject bubble16x16Two;
    private GameObject bubble24x24One;
    private GameObject bubble24x24Two;

    public void drawBubbles(Graphics g) {
        bubble8x8One.draw(g);
        bubble8x8Two.draw(g);
        bubble8x8Three.draw(g);
        bubble8x8Four.draw(g);
        bubble8x8Five.draw(g);
        bubble8x8Six.draw(g);
        bubble8x8Seven.draw(g);
        bubble8x8Eight.draw(g);
        bubble8x8Nine.draw(g);
        bubble8x8Ten.draw(g);
        bubble8x8Eleven.draw(g);
        bubble8x8Twelve.draw(g);
        bubble8x8Thirteen.draw(g);
        bubble8x8Fourteen.draw(g);
        bubble8x8Fifteen.draw(g);
        bubble8x8Sixteen.draw(g);
        bubble8x8Seventeen.draw(g);
        bubble8x8Eighteen.draw(g);
        bubble8x8Nineteen.draw(g);
        bubble8x8Twenty.draw(g);
        bubble16x16One.draw(g);
        bubble16x16Two.draw(g);
        bubble24x24One.draw(g);
        bubble24x24Two.draw(g);
    }

    public void moveBubbles() {
        bubble8x8One.move();
        bubble8x8Two.move();
        bubble8x8Three.move();
        bubble8x8Four.move();
        bubble8x8Five.move();
        bubble8x8Six.move();
        bubble8x8Seven.move();
        bubble8x8Eight.move();
        bubble8x8Nine.move();
        bubble8x8Ten.move();
        bubble8x8Eleven.move();
        bubble8x8Twelve.move();
        bubble8x8Thirteen.move();
        bubble8x8Fourteen.move();
        bubble8x8Fifteen.move();
        bubble8x8Sixteen.move();
        bubble8x8Seventeen.move();
        bubble8x8Eighteen.move();
        bubble8x8Nineteen.move();
        bubble8x8Twenty.move();
        bubble16x16One.move();
        bubble16x16Two.move();
        bubble24x24One.move();
        bubble24x24Two.move();
    }

    public void setBubbles() {
        bubble8x8One = new GameObject(2, BUBBLE_8X8, ANY_DIRECTION,0,0,300, 200, 8, 8);
        bubble8x8Two = new GameObject(2, BUBBLE_8X8, UP,0,0,350, 205, 8, 8);
        bubble8x8Three = new GameObject(1, BUBBLE_8X8, ANY_DIRECTION, 1,0,450, 190, 8, 8);
        bubble8x8Four = new GameObject(1, BUBBLE_8X8, UP, 0, 0, 380, 235, 8, 8);
        bubble8x8Five = new GameObject(2, BUBBLE_8X8, ANY_DIRECTION, 1, 0, 410, 215, 8, 8);
        bubble8x8Six = new GameObject(1, BUBBLE_8X8, ANY_DIRECTION, 0,0, 280, 243, 8, 8);
        bubble8x8Seven = new GameObject(2, BUBBLE_8X8, UP, 0, 0, 423, 247, 8, 8);
        bubble8x8Eight = new GameObject(1, BUBBLE_8X8, UP, 0, 0, 443, 267, 8, 8);
        bubble8x8Nine = new GameObject(2, BUBBLE_8X8, ANY_DIRECTION, 0,0, 280, 275, 8, 8);
        bubble8x8Ten = new GameObject(1, BUBBLE_8X8, ANY_DIRECTION, 1, 0, 433, 262, 8, 8);
        bubble8x8Eleven = new GameObject(1, BUBBLE_8X8, UP, 0,0, 278, 254, 8, 8);
        bubble8x8Twelve = new GameObject(1, BUBBLE_8X8, ANY_DIRECTION, 0, 0, 306, 306, 8, 8);
        bubble8x8Thirteen = new GameObject(2, BUBBLE_8X8, ANY_DIRECTION, 1, 0, 455, 289, 8, 8);
        bubble8x8Fourteen = new GameObject(3, BUBBLE_8X8, UP, 0, 0, 390, 265, 8, 8);
        bubble8x8Fifteen = new GameObject(3, BUBBLE_8X8, UP, 0, 0, 465, 300, 8, 8);
        bubble8x8Sixteen = new GameObject(3, BUBBLE_8X8, UP, 0, 0, 274, 323, 8, 8);
        bubble8x8Seventeen = new GameObject(2, BUBBLE_8X8, UP,0, 0, 350, 333, 8,8);
        bubble8x8Eighteen = new GameObject(1, BUBBLE_8X8, ANY_DIRECTION, 1,0,450,320, 8,8);
        bubble8x8Nineteen = new GameObject(1, BUBBLE_8X8, ANY_DIRECTION, 0, 0, 380, 210,8, 8);
        bubble8x8Twenty = new GameObject(1, BUBBLE_8X8, ANY_DIRECTION, 1, 0, 398, 230,8, 8);
        bubble16x16One = new GameObject(2, BUBBLE_16X16, UP, 0,0, 200, 616, 16, 16);
        bubble16x16Two = new GameObject(2, BUBBLE_16X16, UP, 0,0, 600, 646, 16, 16);
        bubble24x24One = new GameObject(1, BUBBLE_24X24, UP, 0, 0, 200, 600, 24,24);
        bubble24x24Two = new GameObject(1, BUBBLE_24X24, UP, 0, 0, 500, 670, 24,24);
    }

    public void transferBubbles() {
        if (bubble8x8Fourteen.getVerticalPos() <= VERTICAL_MINUS_THIRTY_TWO) {
            bubble8x8Fourteen.setVerticalPosition(476);
            bubble8x8Fourteen.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble8x8Fifteen.getVerticalPos() <= VERTICAL_MINUS_THIRTY_TWO) {
            bubble8x8Fifteen.setVerticalPosition(490);
            bubble8x8Fifteen.setHorizontalPosition(random.nextInt(400) + 370);
        }
        if (bubble8x8Sixteen.getVerticalPos() <= VERTICAL_MINUS_THIRTY_TWO) {
            bubble8x8Sixteen.setVerticalPosition(510);
            bubble8x8Sixteen.setHorizontalPosition(random.nextInt(788));
        }
        if (bubble16x16One.getVerticalPos() <= VERTICAL_MINUS_THIRTY_TWO) {
            bubble16x16One.setVerticalPosition(450);
            bubble16x16One.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble16x16Two.getVerticalPos() <= VERTICAL_MINUS_THIRTY_TWO) {
            bubble16x16Two.setVerticalPosition(480);
            bubble16x16Two.setHorizontalPosition(random.nextInt(400) + 380);
        }
        if (bubble24x24One.getVerticalPos() <= VERTICAL_MINUS_SIXTY_FOUR) {
            bubble24x24One.setVerticalPosition(random.nextInt(400) + 460);
            bubble24x24One.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble24x24Two.getVerticalPos() <= VERTICAL_MINUS_SIXTY_FOUR) {
            bubble24x24Two.setVerticalPosition(random.nextInt(400) + 430);
            bubble24x24Two.setHorizontalPosition(random.nextInt(400) + 370);
        }
    }

    public void transferInitialBubbles() {
        bubble8x8One.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Two.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Three.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Four.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Five.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Six.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Seven.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Eight.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Nine.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Ten.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Eleven.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Twelve.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Thirteen.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Fourteen.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Fifteen.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Sixteen.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Seventeen.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Eighteen.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Nineteen.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble8x8Twenty.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble16x16One.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble16x16Two.setVerticalPosition(VERTICAL_MINUS_THIRTY_TWO);
        bubble24x24One.setVerticalPosition(VERTICAL_MINUS_SIXTY_FOUR);
        bubble24x24Two.setVerticalPosition(VERTICAL_MINUS_SIXTY_FOUR);
    }
}
