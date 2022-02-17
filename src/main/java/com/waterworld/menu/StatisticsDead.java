package com.waterworld.menu;

import com.waterworld.game_engine.GUIState;
import com.waterworld.game_engine.GUIStateManager;
import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_objects.GameObject;
import com.waterworld.game_objects.Sounds;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class StatisticsDead extends GUIState {

    private static final Random random = new Random();
    private static final Sounds sounds = new Sounds();
    private static final File backgroundFile = new File("src/main/resources/game_objects/level_objects/Statistics dead background 800x403.jpg");
    private static final String[] options = {"REPEAT  GAME", "QUIT TO MENU"};
    private static final String BUBBLE_8X8 = "bubble8x8";
    private static final String BUBBLE_16X16 = "bubble16x16";
    private static final String BUBBLE_24X24 = "bubble24x24";
    private static final String UP = "up";
    private static final int VERTICAL_MINUS_THIRTY_TWO = -32;
    private static final int VERTICAL_MINUS_SIXTY_FOUR = -64;

    private static int currentChoice = 0;

    private BufferedImage background;
    private GameObject bubble8x8One;
    private GameObject bubble8x8Two;
    private GameObject bubble8x8Three;
    private GameObject bubble8x8Four;
    private GameObject bubble16x16One;
    private GameObject bubble16x16Two;
    private GameObject bubble24x24One;
    private GameObject bubble24x24Two;

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
        bubble8x8One.move();
        bubble8x8Two.move();
        bubble8x8Three.move();
        bubble8x8Four.move();
        bubble16x16One.move();
        bubble16x16Two.move();
        bubble24x24One.move();
        bubble24x24Two.move();
        transferObjects();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0,0, null);
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 42));
        drawMenuOptions(g);
        bubble8x8One.draw(g);
        bubble8x8Two.draw(g);
        bubble8x8Three.draw(g);
        bubble8x8Four.draw(g);
        //Mijesce na rybkę i informację
        bubble16x16One.draw(g);
        bubble16x16Two.draw(g);
        bubble24x24One.draw(g);
        bubble24x24Two.draw(g);
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
            GUIStateManager.setStates(GUIStateManager.LEVEL_ONE);
            MainMenu.playLevelMusic();
        }

        if (currentChoice == 1) {
            GUIStateManager.setStates(GUIStateManager.MENU);
            com.waterworld.game_engine.GUIStateManager.playMainMenuMusic();
        }
        return currentChoice;
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
    }
}
