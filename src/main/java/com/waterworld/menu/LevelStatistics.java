package com.waterworld.menu;

import com.waterworld.game_engine.GUIState;
import com.waterworld.game_engine.GUIStateManager;
import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_engine.StringObjectValue;
import com.waterworld.game_objects.GameBubbles;
import com.waterworld.game_objects.GameObject;
import com.waterworld.game_objects.Sounds;
import com.waterworld.level.LevelOne;
import com.waterworld.level.LevelThree;
import com.waterworld.level.LevelTwo;
import com.waterworld.level.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelStatistics extends GUIState {

    private static final Sounds sounds = new Sounds();
    private static final GameBubbles bubbles = new GameBubbles(false);
    private static final File backgroundFile = new File("src/main/resources/game_objects/level_objects/Level statistics 800x403.jpg");
    private static final String[] optionsDead = {"REPEAT  GAME", "QUIT TO MENU"};
    private static final String[] optionsWin = {"   NEXT  LEVEL", "QUIT TO MENU"};
    private static final int INITIAL_PLAYER_LIFE = 3;

    private static int currentChoice;

    private BufferedImage background;
    private GameObject deadFish;
    private GameObject fish;

    public LevelStatistics(GUIStateManager GUIStateManager) {
        this.GUIStateManager = GUIStateManager;
        init();
    }

    @Override
    public void init() {
        setObjects();
        try {
            background = ImageIO.read(backgroundFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        moveFish();
        bubbles.move();
        transferObjects();
        if (LevelOne.getLevelOne() == 1) {
            if (LevelOne.isWin()) {
                setFishMove();
            } else {
                setDeadFishMove();
            }
        }
        if (LevelTwo.getLevelTwo() == 2) {
            if (LevelTwo.isWin()) {
                setFishMove();
            } else {
                setDeadFishMove();
            }
        }
        if (LevelThree.getLevelThree() == 3) {
            setDeadFishMove();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0,0, null);
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 42));
        bubbles.drawBack(g);
        drawMenuOptions(g);
        drawFish(g);
        g.setColor(new Color(225, 74, 83));
        g.drawString("YOUR TOTAL SCORES", 190, 50);
        if (LevelOne.getLevelOne() == 1) {
            g.drawString("IN FIRST LEVEL" , 254, 100);
            g.setFont(new Font("Showcard Gothic", Font.PLAIN, 80));
            g.drawString(String.valueOf(Point.getPoints().get(0)), 380, 210);
        } else if (LevelTwo.getLevelTwo() == 2) {
            g.drawString("IN SECOND LEVEL" , 254, 100);
            g.setFont(new Font("Showcard Gothic", Font.PLAIN, 80));
            g.drawString(String.valueOf(Point.getPoints().get(1)), 380, 210);
        } else if (LevelThree.getLevelThree() == 3) {
            g.drawString("IN THIRD LEVEL" , 254, 100);
            g.setFont(new Font("Showcard Gothic", Font.PLAIN, 80));
            g.drawString(String.valueOf(Point.getPoints().get(2)), 380, 210);
        }
        bubbles.drawFront(g);
    }

    @Override
    public void onKeyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            sounds.setChoiceSound();
            selectMenuOption();
        }

        if (key.getKeyCode() == KeyEvent.VK_UP || key.getKeyCode() == KeyEvent.VK_W) {
            currentChoice--;
            sounds.setNavigationSound();
            if (currentChoice == -1) {
                currentChoice = optionsDead.length - 1;
            }
        }

        if (key.getKeyCode() == KeyEvent.VK_DOWN || key.getKeyCode() == KeyEvent.VK_S) {
            currentChoice++;
            sounds.setNavigationSound();
            if (currentChoice == optionsDead.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void onKeyReleased(KeyEvent key) {

    }

    public static void setCurrentChoice(int currentChoice) {
        LevelStatistics.currentChoice = currentChoice;
    }

    private void drawMenuOptions(Graphics g) {
        if (LevelOne.getLevelOne() == 1) {
            if (LevelOne.isWin()) {
                optionWin(g);
            } else {
                optionLoose(g);
            }
        }
        if (LevelTwo.getLevelTwo() == 2) {
            if (LevelTwo.isWin()) {
                optionWin(g);
            } else {
                optionLoose(g);
            }
        }
        if (LevelThree.getLevelThree() == 3) {
            optionLoose(g);
        }
    }

    private int selectMenuOption() {
        if (LevelOne.getLevelOne() == 1) {
            if(currentChoice == 0){
                if (LevelOne.isWin()) {
                    Point.zeroScore();
                    Point.zeroScoreInLevel();
                    LevelOne.setLevelOne(0);
                    LevelTwo.setLevelTwo(0);
                    LevelThree.setLevelThree(0);
                    MainMenu.playLevelMusic();
                    GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.LEVEL_TWO);
                } else {
                    Point.zeroScore();
                    Point.zeroScoreInLevel();
                    LevelOne.setLevelOne(0);
                    LevelTwo.setLevelTwo(0);
                    LevelThree.setLevelThree(0);
                    Point.setLife(INITIAL_PLAYER_LIFE);
                    Point.getPoints().remove(0);
                    MainMenu.playLevelMusic();
                    GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.LEVEL_ONE);
                }
            }
            if (currentChoice == 1) {
                backToMainMenu();
            }
        }
        if (LevelTwo.getLevelTwo() == 2) {
            if(currentChoice == 0){
                if (LevelTwo.isWin()) {
                    Point.zeroScore();
                    Point.zeroScoreInLevel();
                    LevelOne.setLevelOne(0);
                    LevelTwo.setLevelTwo(0);
                    LevelThree.setLevelThree(0);
                    GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.LEVEL_THREE);
                    MainMenu.playLevelMusic();
                } else {
                    Point.zeroScore();
                    Point.zeroScoreInLevel();
                    Point.setLife(INITIAL_PLAYER_LIFE);
                    Point.getPoints().remove(1);
                    LevelOne.setLevelOne(0);
                    LevelTwo.setLevelTwo(0);
                    LevelThree.setLevelThree(0);
                    MainMenu.playLevelMusic();
                    GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.LEVEL_TWO);
                }
            }
            if (currentChoice == 1) {
                backToMainMenu();
            }
        }
        if (LevelThree.getLevelThree() == 3) {
            if(currentChoice == 0){
                Point.zeroScore();
                Point.zeroScoreInLevel();
                Point.setLife(INITIAL_PLAYER_LIFE);
                Point.getPoints().remove(2);
                LevelOne.setLevelOne(0);
                LevelTwo.setLevelTwo(0);
                LevelThree.setLevelThree(0);
                MainMenu.playLevelMusic();
                GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.LEVEL_THREE);
            }
            if (currentChoice == 1) {
                backToMainMenu();
            }
        }
        return currentChoice;
    }

    private void setObjects() {
        bubbles.init();
        deadFish = new GameObject(1, StringObjectValue.DEAD_FISH.getValue(), StringObjectValue.UP.getValue(), 0, 0, 250, 150,90, 65);
        fish = new GameObject(1, StringObjectValue.FISH.getValue(), StringObjectValue.UP.getValue(), 0, 0, 250, 150,90, 65);
    }

    private void transferObjects() {
        bubbles.transfer();
    }

    private void setDeadFishMove() {
        if (deadFish.getVerticalPos() <= 100) {
            deadFish.setYDirection(-deadFish.getYVelocity());
        }
        if (deadFish.getVerticalPos() >= 190) {
            deadFish.setYDirection(-deadFish.getYVelocity());
        }
    }

    private void setFishMove() {
        if (fish.getVerticalPos() <= 100) {
            fish.setYDirection(-fish.getYVelocity());
        }
        if (fish.getVerticalPos() >= 180) {
            fish.setYDirection(-fish.getYVelocity());
        }
    }

    private void drawFish(Graphics g) {
        if (LevelOne.getLevelOne() == 1) {
            if (LevelOne.isWin()) {
                fish.draw(g);
            } else {
                deadFish.draw(g);
            }
        }
        if (LevelTwo.getLevelTwo() == 2) {
            if (LevelTwo.isWin()) {
                fish.draw(g);
            } else {
                deadFish.draw(g);
            }
        }
        if (LevelThree.getLevelThree() == 3) {
            deadFish.draw(g);
        }
    }

    private void moveFish() {
        if (LevelOne.getLevelOne() == 1) {
            if (LevelOne.isWin()) {
                fish.move();
            } else {
                deadFish.move();
            }
        }
        if (LevelTwo.getLevelTwo() == 2) {
            if (LevelTwo.isWin()) {
                fish.move();
            } else {
                deadFish.move();
            }
        }
        if (LevelThree.getLevelThree() == 3) {
            deadFish.move();
        }
    }

    private void optionWin(Graphics g) {
        for (int i = 0; i < optionsWin.length; i++) {
            if (i == currentChoice) {
                g.setColor(new Color(248, 174, 71));
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(optionsWin[i], (GameEngine.WIDTH / 2) - 140, 290 + i * 38);
        }
    }

    private void optionLoose(Graphics g) {
        for (int i = 0; i < optionsDead.length; i++) {
            if (i == currentChoice) {
                g.setColor(new Color(248, 174, 71));
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(optionsDead[i], (GameEngine.WIDTH / 2) - 140, 290 + i * 38);
        }
    }

    private void backToMainMenu() {
        Point.zeroScore();
        Point.zeroScoreInLevel();
        Point.getPoints().clear();
        LevelOne.setLevelOne(0);
        LevelTwo.setLevelTwo(0);
        Point.setLife(INITIAL_PLAYER_LIFE);
        GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.MENU);
        com.waterworld.game_engine.GUIStateManager.playMainMenuMusic();
    }
}
