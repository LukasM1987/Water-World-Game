package com.waterworld.game_engine;

import com.waterworld.level.LevelOne;
import com.waterworld.level.LevelThree;
import com.waterworld.level.LevelTwo;
import com.waterworld.menu.FinalGameStatistics;
import com.waterworld.menu.Help;
import com.waterworld.menu.MainMenu;
import com.waterworld.menu.LevelStatistics;

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
    public static final int LEVEL_STATISTICS = 3;
    public static final int LEVEL_TWO = 4;
    public static final int LEVEL_THREE = 5;
    public static final int FINAL_GAME_STATISTICS = 6;

    private int currentState;
    private static Clip clip;

    public GUIStateManager() {
        currentState = MENU;
        playMainMenuMusic();
        gamesStates.add(new MainMenu(this));
        gamesStates.add(new Help(this));
        gamesStates.add(new LevelOne(this));
        gamesStates.add(new LevelStatistics(this));
        gamesStates.add(new LevelTwo(this));
        gamesStates.add(new LevelThree(this));
        gamesStates.add(new FinalGameStatistics(this));
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
