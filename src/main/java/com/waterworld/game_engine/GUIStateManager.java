package com.waterworld.game_engine;

import com.waterworld.level.LevelOne;
import com.waterworld.level.LevelTwo;
import com.waterworld.menu.Help;
import com.waterworld.menu.MainMenu;
import com.waterworld.menu.StatisticsLevelOne;
import com.waterworld.menu.StatisticsLevelTwo;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUIStateManager {

    private final List<GUIState> gamesStates = new ArrayList<>();

    private static final File menuMusic = new File("src/main/resources/game_objects/sounds/Main theme.wav");

    public static final int MENU = 0;
    public static final int HELP = 1;
    public static final int LEVEL_ONE = 2;
    public static final int STATISTICS_LEVEL_ONE = 3;
    public static final int LEVEL_TWO = 4;
    public static final int STATISTICS_LEVEL_TWO = 5;
    public static final int LEVEL_THREE = 6;

    private int currentState;
    private static Clip clip;

    public GUIStateManager() {
        currentState = MENU;
        playMainMenuMusic();
        gamesStates.add(new MainMenu(this));
        gamesStates.add(new Help(this));
        gamesStates.add(new LevelOne(this));
        gamesStates.add(new StatisticsLevelOne(this));
        gamesStates.add(new LevelTwo(this));
        gamesStates.add(new StatisticsLevelTwo(this));
    }

    public void setStates(int state) {
        setCurrentState(state);
        gamesStates.get(getCurrentState()).init();
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public void update() {
        gamesStates.get(currentState).update();
    }


    public void draw(Graphics g) {
        gamesStates.get(getCurrentState()).draw(g);
    }

    public void keyPressed(KeyEvent key) {
        gamesStates.get(getCurrentState()).onKeyPressed(key);
    }


    public void keyReleased(KeyEvent key) {
        gamesStates.get(getCurrentState()).onKeyReleased(key);
    }

    public static void playMainMenuMusic() {
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

    public Clip getClip() {
        return clip;
    }
}
