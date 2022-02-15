package com.waterworld.menu;

import com.waterworld.game_engine.GUIState;
import com.waterworld.game_engine.GUIStateManager;
import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_objects.Sounds;
import com.waterworld.game_objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Help extends GUIState {

    private static final Random random = new Random();
    private static final Sounds sounds = new Sounds();
    private static final File backgroundFile = new File("src/main/resources/game_objects/menu/menu_two.jpg");
    private static final String BUBBLE_8X8 = "bubble8x8";
    private static final String BUBBLE_16X16 = "bubble16x16";
    private static final String BUBBLE_24X24 = "bubble24x24";
    private static final String ANY_DIRECTION = "any direction";
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String LEFT_BUTTON = "left button";
    private static final String RIGHT_BUTTON = "right button";
    private static final String DOWN_BUTTON = "down button";
    private static final String UP_BUTTON = "up button";
    private static final String LEFT_INFO = "left info";
    private static final String RIGHT_INFO = "right info";
    private static final String DOWN_INFO = "down info";
    private static final String UP_INFO = "up info";
    private static final int VERTICAL_MINUS_THIRTY_TWO = -32;
    private static final int VERTICAL_MINUS_SIXTY_FOUR = -64;

    private BufferedImage backgroundImage;
    private GameObject bubble8x8One;
    private GameObject bubble8x8Two;
    private GameObject bubble8x8Three;
    private GameObject bubble8x8Four;
    private GameObject bubble16x16One;
    private GameObject bubble16x16Two;
    private GameObject bubble24x24One;
    private GameObject bubble24x24Two;
    private GameObject leftButton;
    private GameObject rightButton;
    private GameObject downButton;
    private GameObject upButton;
    private GameObject leftMoveInfo;
    private GameObject rightMoveInfo;
    private GameObject downMoveInfo;
    private GameObject upMoveInfo;

    public Help(GUIStateManager GUIStateManager) {
        this.GUIStateManager = GUIStateManager;
        init();
    }

    @Override
    public void init() {
        setObjects();
        try {
            backgroundImage = ImageIO.read(backgroundFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        bubble8x8One.move();
        bubble8x8Two.move();
        bubble8x8Three.move();
        bubble8x8Four.move();
        bubble16x16One.move();
        bubble16x16Two.move();
        bubble24x24One.move();
        bubble24x24Two.move();
        leftButton.move();
        rightButton.move();
        downButton.move();
        upButton.move();
        leftMoveInfo.move();
        rightMoveInfo.move();
        downMoveInfo.move();
        upMoveInfo.move();
        transferObjects();
    }

    @Override
    public void draw(Graphics g) {
        drawGraphics(g);
        bubble8x8One.draw(g);
        bubble8x8Two.draw(g);
        bubble8x8Three.draw(g);
        bubble8x8Four.draw(g);
        drawHelpInformation(g);
        leftButton.draw(g);
        rightButton.draw(g);
        downButton.draw(g);
        upButton.draw(g);
        leftMoveInfo.draw(g);
        rightMoveInfo.draw(g);
        downMoveInfo.draw(g);
        upMoveInfo.draw(g);
        drawMenuOption(g);
        bubble16x16One.draw(g);
        bubble16x16Two.draw(g);
        bubble24x24One.draw(g);
        bubble24x24Two.draw(g);
    }

    @Override
    public void onKeyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            sounds.setChoiceSound();
            try {
                Thread.sleep(260);
            } catch (Exception e) {
                e.printStackTrace();
            }
            selectMenuOption();
        }
    }

    @Override
    public void onKeyReleased(KeyEvent key) {

    }

    private void selectMenuOption() {
        leftButton.setVerticalPosition(450);
        rightButton.setVerticalPosition(450);
        upButton.setVerticalPosition(450);
        downButton.setVerticalPosition(450);
        leftMoveInfo.setVerticalPosition(450);
        rightMoveInfo.setVerticalPosition(450);
        downMoveInfo.setVerticalPosition(450);
        upMoveInfo.setVerticalPosition(450);
        GUIStateManager.setStates(GUIStateManager.MENU);
    }

    private void drawGraphics(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }

    private void drawHelpInformation(Graphics g) {

    }

    private void drawMenuOption(Graphics g) {
        g.setColor(new Color(248,174,71));
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 42));
        g.drawString("BACK", (GameEngine.WIDTH / 2) - 52, GameEngine.HEIGHT - 20);
    }

    private void setObjects() {
        bubble8x8One = new GameObject(3, BUBBLE_8X8, UP, 0, 0, 123, 423, 8, 8);
        bubble8x8Two = new GameObject(3, BUBBLE_8X8, UP, 0, 0, 213, 431, 8, 8);
        bubble8x8Three = new GameObject(3, BUBBLE_8X8, UP, 0, 0, 437, 411, 8, 8);
        bubble8x8Four = new GameObject(3, BUBBLE_8X8, UP, 0, 0, 692, 452, 8, 8);
        bubble16x16One = new GameObject(2, BUBBLE_16X16, UP, 0, 0, 246, 462, 16, 16);
        bubble16x16Two = new GameObject(2, BUBBLE_16X16, UP, 0, 0, 576, 452, 16, 16);
        bubble24x24One = new GameObject(2, BUBBLE_24X24, UP, 0, 0, 146, 572, 16, 16);
        bubble24x24Two = new GameObject(2, BUBBLE_16X16, UP, 0, 0, 746, 523, 16, 16);
        leftButton = new GameObject(2, LEFT_BUTTON, LEFT, 0, 0, -50, 150, 50, 50);
        rightButton = new GameObject(2, RIGHT_BUTTON, RIGHT, 0, 0, 800, 150, 50, 50);
        downButton = new GameObject(2, DOWN_BUTTON, UP, 0,0, GameEngine.WIDTH / 2 - 25, 480, 50, 50);
        upButton = new GameObject(2, UP_BUTTON, DOWN, 0,0, GameEngine.WIDTH / 2 - 25, -250, 50, 50);
        leftMoveInfo = new GameObject(1, LEFT_INFO, LEFT, 0,0, -100, 190, 50, 50);
        rightMoveInfo = new GameObject(1, RIGHT_INFO, RIGHT, 0,0, 820, 190, 50, 50);
        downMoveInfo = new GameObject(1, DOWN_INFO, ANY_DIRECTION, 1,1, 0, -100, 50, 50);
        upMoveInfo = new GameObject(1, UP_INFO, DOWN, 0,0, GameEngine.WIDTH / 2 - 24, -276, 50, 50);
    }

    private void transferObjects() {
        if (bubble8x8One.getVerticalPos() <= VERTICAL_MINUS_THIRTY_TWO) {
            bubble8x8One.setVerticalPosition(442);
            bubble8x8One.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble8x8Two.getVerticalPos() <= VERTICAL_MINUS_THIRTY_TWO) {
            bubble8x8Two.setVerticalPosition(412);
            bubble8x8Two.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble8x8Three.getVerticalPos() <= VERTICAL_MINUS_THIRTY_TWO) {
            bubble8x8Three.setVerticalPosition(482);
            bubble8x8Three.setHorizontalPosition(random.nextInt(400) + 390);
        }
        if (bubble8x8Four.getVerticalPos() <= VERTICAL_MINUS_THIRTY_TWO) {
            bubble8x8Four.setVerticalPosition(452);
            bubble8x8Four.setHorizontalPosition(random.nextInt(400) + 390);
        }
        if (bubble16x16One.getVerticalPos() <= VERTICAL_MINUS_THIRTY_TWO) {
            bubble16x16One.setVerticalPosition(450);
            bubble16x16One.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble16x16Two.getVerticalPos() <= VERTICAL_MINUS_THIRTY_TWO) {
            bubble16x16Two.setVerticalPosition(random.nextInt(400) + 480);
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
        if (leftButton.getHorizontalPos() >= GameEngine.WIDTH / 2 - 100) {
            leftButton.setHorizontalVelocity(0);
        }
        if (rightButton.getHorizontalPos() <= GameEngine.WIDTH / 2 + 50) {
            rightButton.setHorizontalVelocity(0);
        }
        if (upButton.getVerticalPos() >= 80) {
            upButton.setVerticalVelocity(0);
        }
        if (downButton.getVerticalPos() <= 150) {
            downButton.setVerticalVelocity(0);
        }
        if (leftMoveInfo.getHorizontalPos() >= GameEngine.WIDTH / 4) {
            leftMoveInfo.setHorizontalVelocity(0);
        }
        if (rightMoveInfo.getHorizontalPos() <= GameEngine.WIDTH / 4 * 3 - 80) {
            rightMoveInfo.setHorizontalVelocity(0);
        }
        if (downMoveInfo.getVerticalPos() >= 246) {
            downMoveInfo.setVerticalVelocity(0);
            downMoveInfo.setHorizontalVelocity(0);
        }
        if (upMoveInfo.getVerticalPos() >= 64) {
            upMoveInfo.setVerticalVelocity(0);
        }
    }
}
