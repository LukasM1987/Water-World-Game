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
import java.io.File;
import java.io.IOException;

public class StatisticsDead extends GUIState {

    private static final Sounds sounds = new Sounds();
    private static final GameBubbles bubbles = new GameBubbles(false);
    private static final File backgroundFile = new File("src/main/resources/game_objects/level_objects/Statistics dead background 800x403.jpg");
    private static final String[] options = {"REPEAT  GAME", "QUIT TO MENU"};

    private static int currentChoice = 0;

    private BufferedImage background;

    public StatisticsDead(GUIStateManager GUIStateManager) {
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
        bubbles.move();
        transferObjects();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0,0, null);
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 42));
        drawMenuOptions(g);
        bubbles.drawBack(g);
        //Mijesce na rybkę i informację
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

    private void drawMenuOptions(Graphics g) {
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(new Color(248,174,71));
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], (GameEngine.WIDTH / 2) - 140, 290 + i * 38);
        }
    }

    private int selectMenuOption(){
        if(currentChoice == 0){
            GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.LEVEL_ONE);
            MainMenu.playLevelMusic();
        }

        if (currentChoice == 1) {
            GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.MENU);
            com.waterworld.game_engine.GUIStateManager.playMainMenuMusic();
        }
        return currentChoice;
    }

    private void setObjects() {
        bubbles.init();
    }

    private void transferObjects() {
        bubbles.transfer();
    }
}
