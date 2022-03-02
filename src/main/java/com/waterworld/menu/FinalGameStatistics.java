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
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FinalGameStatistics extends GUIState {

    private static final List<String> scores = new ArrayList<>();
    private static final Sounds sounds = new Sounds();
    private static final GameBubbles bubbles = new GameBubbles(false);
    private static final File backgroundFile = new File("src/main/resources/game_objects/level_objects/Final statistics 800x405.jpg");
    private static final String[] options = {"   REPEAT  GAME","SAVE STATISTICS", "   QUIT TO MENU"};
    private static final int INITIAL_PLAYER_LIFE = 3;

    private static int currentChoice;

    private BufferedImage background;
    private FileWriter statisticWriter;
    private GameObject fish;

    public FinalGameStatistics(GUIStateManager GuiStateManager) {
        this.GUIStateManager = GuiStateManager;
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
        bubbles.move();
        fish.move();
        setFishMove();
        transferObjects();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0,0, null);
        bubbles.drawBack(g);
        fish.draw(g);
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 42));
        g.setColor(new Color(225, 74, 83));
        g.drawString("YOUR TOTAL SCORES", 190, 50);
        g.drawString("IN THE GAME" , 268, 100);
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 80));
        g.drawString(String.valueOf(Point.getPoints().get(0) + Point.getPoints().get(1) + Point.getPoints().get(2)), 380, 210);
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 42));
        drawMenuOptions(g);
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

    private void setObjects() {
        bubbles.init();
        fish = new GameObject(1, StringObjectValue.FISH.getValue(), StringObjectValue.UP.getValue(), 0, 0, 250, 150,90, 65);
    }

    private void setFishMove() {
        if (fish.getVerticalPos() <= 100) {
            fish.setYDirection(-fish.getYVelocity());
        }
        if (fish.getVerticalPos() >= 180) {
            fish.setYDirection(-fish.getYVelocity());
        }
    }

    private void transferObjects() {
        bubbles.transfer();
    }

    private int selectMenuOption() {
        if(currentChoice == 0){
            Point.zeroScore();
            Point.zeroScoreInLevel();
            LevelOne.setLevelOne(0);
            LevelTwo.setLevelTwo(0);
            LevelThree.setLevelThree(0);
            setCurrentChoice(0);
            MainMenu.playLevelMusic();
            GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.LEVEL_THREE);
        }
        if (currentChoice == 1) {
            setCurrentChoice(0);
            backToMainMenuSave();
        }

        if (currentChoice == 2) {
            setCurrentChoice(0);
            backToMainMenuNoSave();
        }
        return currentChoice;
    }

    private void backToMainMenuNoSave() {
        Point.zeroScore();
        Point.zeroScoreInLevel();
        Point.getPoints().clear();
        LevelOne.setLevelOne(0);
        LevelTwo.setLevelTwo(0);
        LevelThree.setLevelThree(0);
        Point.setLife(INITIAL_PLAYER_LIFE);
        GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.MENU);
        com.waterworld.game_engine.GUIStateManager.playMainMenuMusic();
    }

    private void backToMainMenuSave() {
        readFile();
        int totalScores = Point.getPoints().get(0) + Point.getPoints().get(1) + Point.getPoints().get(2);
        try {
            statisticWriter = new FileWriter("src/main/resources/game_objects/statistics.txt");
            statisticWriter.write(
                    LocalDateTime.now().getDayOfMonth()
                            + "/" + LocalDateTime.now().getMonth()
                            + "/" + LocalDateTime.now().getYear()
                            + "     " + LocalDateTime.now().getHour()
                            + ":" + LocalDateTime.now().getMinute()
                            + "   -   " + totalScores + " p.");
            for (String item : scores) {
                statisticWriter.write(item);
            }
            statisticWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        backToMainMenuNoSave();
    }

    private void drawMenuOptions(Graphics g) {
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(new Color(248, 174, 71));
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], (GameEngine.WIDTH / 2) - 174, 292 + i * 38);
        }
    }

    private void setCurrentChoice(int currentChoice) {
        FinalGameStatistics.currentChoice = currentChoice;
    }

    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/game_objects/statistics.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add("\n" + line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
