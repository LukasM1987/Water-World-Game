package com.waterworld.menu;

import com.waterworld.game_engine.GUIState;
import com.waterworld.game_engine.GUIStateManager;
import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_objects.GameBubbles;
import com.waterworld.game_objects.Sounds;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Statistics extends GUIState {

    private static final List<String> scoreResults = new ArrayList<>();
    private static final Sounds sounds = new Sounds();
    private static final GameBubbles bubbles = new GameBubbles(false);
    private static final File backgroundFile = new File("src/main/resources/game_objects/menu/menu_two.jpg");
    private static final int FIVE = 5;

    private BufferedImage backgroundImage;

    public Statistics(GUIStateManager GUIStateManager) {
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
        bubbles.transfer();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        bubbles.drawBack(g);
        drawScores(g);
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
        }
    }

    @Override
    public void onKeyReleased(KeyEvent key) {

    }

    public static void readScores() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/game_objects/statistics.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                scoreResults.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawMenuOption(Graphics g) {
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 42));
        g.setColor(new Color(248, 174, 71));
        g.drawString("BACK", (GameEngine.WIDTH / 2) - 56, 334);
    }

    private void setObjects() {
        bubbles.init();
    }

    private void selectMenuOption() {
        scoreResults.clear();
        GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.HELP);
    }

    private void drawScores(Graphics g) {
        g.setColor(new Color(225, 74, 83));
        if (scoreResults.isEmpty()) {
            g.setFont(new Font("Showcard Gothic", Font.PLAIN, 80));
            g.drawString("THERE ARE NO", 120, 140);
            g.drawString("STATISTICS HERE", 70, 240);
        }
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 32));
        if (scoreResults.size() < FIVE) {
            for (int i = 0; i < scoreResults.size(); i++) {
                g.drawString((i + 1) + ".     " + scoreResults.get(i), 166, 102 + i * 38);
            }
        } else {
            for (int i = 0; i < FIVE; i++) {
                g.drawString((i + 1) + ".     " + scoreResults.get(i), 166, 102 + i * 38);
            }
        }
    }
}
