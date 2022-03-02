package com.waterworld.menu;

import com.waterworld.game_engine.GUIState;
import com.waterworld.game_engine.GUIStateManager;
import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_engine.StringObjectValue;
import com.waterworld.game_objects.GameBubbles;
import com.waterworld.game_objects.Sounds;
import com.waterworld.game_objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Help extends GUIState {

    private static final Sounds sounds = new Sounds();
    private static final GameBubbles bubbles = new GameBubbles(false);
    private static final File backgroundFile = new File("src/main/resources/game_objects/menu/menu_two.jpg");
    private static final String[] options = {"STATISTICS","       BACK"};

    private static int currentChoice;

    private BufferedImage backgroundImage;
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
        bubbles.move();
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
        bubbles.drawBack(g);
        leftButton.draw(g);
        rightButton.draw(g);
        downButton.draw(g);
        upButton.draw(g);
        leftMoveInfo.draw(g);
        rightMoveInfo.draw(g);
        downMoveInfo.draw(g);
        upMoveInfo.draw(g);
        drawMenuOption(g);
        bubbles.drawFront(g);
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
            setCurrentChoice(0);
        }
        if (key.getKeyCode() == KeyEvent.VK_UP || key.getKeyCode() == KeyEvent.VK_W) {
            currentChoice--;
            sounds.setNavigationSound();
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }

        if (key.getKeyCode() == KeyEvent.VK_DOWN || key.getKeyCode() == KeyEvent.VK_S) {
            currentChoice++;
            sounds.setNavigationSound();
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void onKeyReleased(KeyEvent key) {

    }

    private void setCurrentChoice(int currentChoice) {
        Help.currentChoice = currentChoice;
    }

    private int selectMenuOption() {
        if (currentChoice == 0) {
            removeObjects();
            Statistics.readScores();
            GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.MENU_STATISTICS);
        }
        if (currentChoice == 1) {
            removeObjects();
            MainMenu.setCurrentChoice(0);
            GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.MENU);
        }
        return currentChoice;
    }

    private void drawGraphics(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }

    private void drawMenuOption(Graphics g) {
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 42));
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(new Color(248, 174, 71));
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], (GameEngine.WIDTH / 2) - 110, 312 + i * 38);
        }
    }

    private void setObjects() {
        bubbles.init();
        leftButton = new GameObject(2, StringObjectValue.LEFT_BUTTON.getValue(), StringObjectValue.LEFT.getValue(), 0, 0, -150, 150, 50, 50);
        rightButton = new GameObject(2, StringObjectValue.RIGHT_BUTTON.getValue(), StringObjectValue.RIGHT.getValue(), 0, 0, 900, 150, 50, 50);
        downButton = new GameObject(2, StringObjectValue.DOWN_BUTTON.getValue(), StringObjectValue.UP.getValue(), 0,0, GameEngine.WIDTH / 2 - 25, 500, 50, 50);
        upButton = new GameObject(2, StringObjectValue.UP_BUTTON.getValue(), StringObjectValue.DOWN.getValue(), 0,0, GameEngine.WIDTH / 2 - 25, -270, 50, 50);
        leftMoveInfo = new GameObject(1, StringObjectValue.LEFT_INFO.getValue(), StringObjectValue.LEFT.getValue(), 0,0, -150, 190, 50, 50);
        rightMoveInfo = new GameObject(1, StringObjectValue.RIGHT_INFO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0, 870, 190, 50, 50);
        downMoveInfo = new GameObject(1, StringObjectValue.DOWN_INFO.getValue(), StringObjectValue.ANY_DIRECTION.getValue(), 1,1, 0, -100, 50, 50);
        upMoveInfo = new GameObject(1, StringObjectValue.UP_INFO.getValue(), StringObjectValue.DOWN.getValue(), 0,0, GameEngine.WIDTH / 2 - 24, -276, 50, 50);
    }

    private void transferObjects() {
        bubbles.transfer();
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

    private void removeObjects() {
        leftButton.setVerticalPosition(450);
        rightButton.setVerticalPosition(450);
        upButton.setVerticalPosition(450);
        downButton.setVerticalPosition(450);
        leftMoveInfo.setVerticalPosition(450);
        rightMoveInfo.setVerticalPosition(450);
        downMoveInfo.setVerticalPosition(450);
        upMoveInfo.setVerticalPosition(450);
        bubbles.moveOutOfFrame();
    }
}
