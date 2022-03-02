package com.waterworld.menu;

import com.waterworld.game_engine.GUIState;
import com.waterworld.game_engine.GUIStateManager;
import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_objects.MainMenuBubbles;
import com.waterworld.game_objects.Sounds;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends GUIState {

    private static final Sounds sounds = new Sounds();
    private static final MainMenuBubbles mainMenuBubbles = new MainMenuBubbles();
    private static final String[] options = {"NEW GAME", "CONTROLS", "       QUIT"};
    private static final File background = new File("src/main/resources/game_objects/menu/menu_two.jpg");
    private static final File gameLogo = new File("src/main/resources/game_objects/menu/game_logo_450.png");
    private static final File menuMusic = new File("src/main/resources/game_objects/sounds/Level one.wav");

    private static int currentChoice;

    private BufferedImage backgroundImage;
    private BufferedImage logoImage;

    private static Clip clip;

    public MainMenu(GUIStateManager GUIStateManager) {
        this.GUIStateManager = GUIStateManager;
        init();
    }

    public void init() {
        mainMenuBubbles.setBubbles();
        try {
            backgroundImage = ImageIO.read(background);
            logoImage = ImageIO.read(gameLogo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        mainMenuBubbles.moveBubbles();
        mainMenuBubbles.transferBubbles();
    }

    public void draw(Graphics g){
        drawGraphics(g);
        drawMenuOptions(g);
        mainMenuBubbles.drawBubbles(g);
    }

    public static int setCurrentChoice(int choice) {
        return currentChoice = choice;
    }

    private void drawGraphics(Graphics g) {
        g.drawImage(backgroundImage, 0, -80, null);
        g.drawImage(logoImage, (GameEngine.WIDTH / 2) - (logoImage.getWidth() / 2), 5, null);
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 42));
    }

    private void drawMenuOptions(Graphics g) {
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(new Color(248,174,71));
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], (GameEngine.WIDTH / 2) - 100, 290 + i * 38);
        }
    }

    private int selectMenuOption(){
        if(currentChoice == 0){
            GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.LEVEL_ONE);
            GUIStateManager.getClip().stop();
            playLevelMusic();

        }

        if (currentChoice == 1) {
            GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.HELP);
        }

        if(currentChoice == 2){
            exitGame();
        }
        return currentChoice;
    }

    @Override
    public void onKeyPressed(KeyEvent key){
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            sounds.setChoiceSound();
            try {
                Thread.sleep(260);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mainMenuBubbles.transferInitialBubbles();
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

    private void exitGame() {
        System.exit(0);
    }

    public static void playLevelMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(menuMusic);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(2147483647);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public static Clip getClip() {
        return clip;
    }
}
