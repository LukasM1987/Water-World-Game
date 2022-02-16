package com.waterworld.game_objects;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {

    private static final File navigateOption = new File("src/main/resources/game_objects/sounds/Navi choice.wav");
    private static final File choice = new File("src/main/resources/game_objects/sounds/Choice.wav");
    private static final File point = new File("src/main/resources/game_objects/sounds/Point.wav");
    private static final File hurt = new File("src/main/resources/game_objects/sounds/Hurt.wav");
    private static final File life = new File("src/main/resources/game_objects/sounds/Life up.wav");

    private AudioInputStream audioInputStream;
    private Clip clip;

    public void setNavigationSound() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(navigateOption);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void setChoiceSound() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(choice);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void givePointSound() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(point);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void hurtPlayerSound() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(hurt);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void givePLayerLife() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(life);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
