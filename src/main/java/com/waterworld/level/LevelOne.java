package com.waterworld.level;

import com.waterworld.game_engine.GUIState;
import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_objects.Enemy;
import com.waterworld.game_objects.GameObject;
import com.waterworld.game_objects.Player;
import com.waterworld.game_objects.Sounds;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class LevelOne extends GUIState {

    private static final Point point = new Point("level one");
    private static final Sounds sounds = new Sounds();
    private static final Random random = new Random();
    private static final File background = new File("src/main/resources/game_objects/level_objects/level_one_800x400.jpg");
    private static final File wormPoints = new File("src/main/resources/game_objects/points/worm points 30x30.png");
    private static final File life = new File("src/main/resources/game_objects/points/life.png");
    private static final String BUBBLE_8X8 = "bubble8x8";
    private static final String BUBBLE_16X16 = "bubble16x16";
    private static final String BUBBLE_24X24 = "bubble24x24";
    private static final String BUBBLE_36X36 = "bubble36x36";
    private static final String UP = "up";
    private static final String WORM = "worm";
    private static final String RIGHT = "right";
    private static final String ENEMY = "enemy";
    private static final int MINUS_SIXTEEN = -16;
    private static final int MINUS_THIRTY_TWO = -32;
    private static final int MINUS_SIXTY_FOUR = -64;
    private static final int MAX_POINTS = 50;
    private static final int MINUS_ONE_HUNDRED_TWENTY = -120;
    private static final int GIVE_LIFE = 10;

    private BufferedImage backgroundImage;
    private BufferedImage wormIcon;
    private BufferedImage lifeIcon;
    private GameObject bubble8x8One;
    private GameObject bubble8x8Two;
    private GameObject bubble8x8Three;
    private GameObject bubble8x8Four;
    private GameObject bubble16x16One;
    private GameObject bubble16x16Two;
    private GameObject bubble24x24One;
    private GameObject bubble24x24Two;
    private GameObject bubble36x36One;
    private GameObject worm;
    private Enemy enemy;
    private GameObject enemySkin;

    private Player player;
    private int gainLife;

    public LevelOne(com.waterworld.game_engine.GUIStateManager GUIStateManager) {
        this.GUIStateManager = GUIStateManager;
        this.player = new Player(150, 150, 95,80, 2);
        init();
    }

    @Override
    public void init() {
        setObjects();
        try {
            backgroundImage = ImageIO.read(background);
            wormIcon = ImageIO.read(wormPoints);
            lifeIcon = ImageIO.read(life);
        } catch (Exception e) {
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
        bubble36x36One.move();
        player.horizontalMove();
        player.verticalMove();
        worm.move();
        enemy.move();
        enemySkin.move();
        givePoint();
        giveLife();
        transferObjects();
        checkPlayerFrameCollision();
        endLevel();
    }

    @Override
    public void draw(Graphics g) {
        enemy.draw(g);
        g.drawImage(backgroundImage, 0, 0, null);
        bubble8x8One.draw(g);
        bubble8x8Two.draw(g);
        bubble8x8Three.draw(g);
        bubble8x8Four.draw(g);
        bubble16x16One.draw(g);
        bubble16x16Two.draw(g);
        //Miejsce na gracza, przeciwników, elementy poziomu i punkty

        //
        enemySkin.draw(g);
        worm.draw(g);
        player.draw(g);
        g.drawImage(wormIcon, 10, 10, null);
        point.draw(g);
        g.drawImage(lifeIcon, 10, 44, null);
        bubble24x24One.draw(g);
        bubble24x24Two.draw(g);
        bubble36x36One.draw(g);
    }

    @Override
    public void onKeyPressed(KeyEvent key) {
        player.movePlayer(key);
    }

    @Override
    public void onKeyReleased(KeyEvent key) {
        player.stopPlayer(key);
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
         bubble36x36One = new GameObject(1, BUBBLE_36X36, UP, 0, 0, 398, 532, 36, 36);
         worm = new GameObject(1, WORM, RIGHT, 0,0, 800, 180, 27,30);
         enemy = new Enemy(3, 900, 200, 115, 124);
         enemySkin = new GameObject(3, ENEMY, RIGHT, 0,0, 900, 195, 120,131);
    }

    private void transferObjects() {
        if (bubble8x8One.getVerticalPos() <= MINUS_SIXTEEN) {
            bubble8x8One.setVerticalPosition(442);
            bubble8x8One.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble8x8Two.getVerticalPos() <= MINUS_SIXTEEN) {
            bubble8x8Two.setVerticalPosition(412);
            bubble8x8Two.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble8x8Three.getVerticalPos() <= MINUS_SIXTEEN) {
            bubble8x8Three.setVerticalPosition(482);
            bubble8x8Three.setHorizontalPosition(random.nextInt(400) + 390);
        }
        if (bubble8x8Four.getVerticalPos() <= MINUS_SIXTEEN) {
            bubble8x8Four.setVerticalPosition(452);
            bubble8x8Four.setHorizontalPosition(random.nextInt(400) + 390);
        }
        if (bubble16x16One.getVerticalPos() <= MINUS_THIRTY_TWO) {
            bubble16x16One.setVerticalPosition(450);
            bubble16x16One.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble16x16Two.getVerticalPos() <= MINUS_THIRTY_TWO) {
            bubble16x16Two.setVerticalPosition(random.nextInt(400) + 480);
            bubble16x16Two.setHorizontalPosition(random.nextInt(400) + 380);
        }
        if (bubble24x24One.getVerticalPos() <= MINUS_SIXTY_FOUR) {
            bubble24x24One.setVerticalPosition(random.nextInt(400) + 460);
            bubble24x24One.setHorizontalPosition(random.nextInt(400));
        }
        if (bubble24x24Two.getVerticalPos() <= MINUS_SIXTY_FOUR) {
            bubble24x24Two.setVerticalPosition(random.nextInt(400) + 430);
            bubble24x24Two.setHorizontalPosition(random.nextInt(400) + 370);
        }
        if (bubble36x36One.getVerticalPos() <= MINUS_SIXTY_FOUR) {
            bubble36x36One.setVerticalPosition(523);
            bubble36x36One.setHorizontalPosition(random.nextInt(740));
        }
        if (worm.getHorizontalPos() <= MINUS_THIRTY_TWO || worm.intersects(player.getRectangle())) {
            worm.setHorizontalPosition(random.nextInt(400) + 800);
            worm.setVerticalPosition(random.nextInt(320));
            point.gainScoresInFirstLevel();
        }
        if (enemy.intersects(player.getRectangle())) {
            point.takeLife();
            enemy.setVerticalPosition(GameEngine.HEIGHT);
        }
        if (enemy.getHorizontalPos() <= MINUS_ONE_HUNDRED_TWENTY) {
            int yPos = random.nextInt(260);
            int xPos = random.nextInt(800) + 800;
            enemy.setVerticalPosition(yPos);
            enemy.setHorizontalPosition(xPos);
            enemySkin.setVerticalPosition(yPos - 6);
            enemySkin.setHorizontalPosition(xPos - 5);
        }
    }

    private void checkPlayerFrameCollision() {
        if (player.getVerticalPosition() <= 0) {
            player.setVerticalPosition(0);
        }

        if (player.getVerticalPosition() >= GameEngine.HEIGHT - player.getRectangle().height) {
            player.setVerticalPosition(GameEngine.HEIGHT - player.getRectangle().height);
        }

        if (player.getHorizontalPosition() <= 0) {
            player.setHorizontalPosition(0);
        }

        if (player.getHorizontalPosition() >= GameEngine.WIDTH - player.getRectangle().width) {
            player.setHorizontalPosition(GameEngine.WIDTH - player.getRectangle().width);
        }
    }

    private void givePoint() {
        if (worm.intersects(player.getRectangle())) {
            sounds.givePointSound();
            point.gainScore();
            gainLife++;
        }
    }

    private void endLevel() {
        if (point.getScoresInLevel() == MAX_POINTS || point.getLife() == 0) {
            System.exit(0);
        }
    }

    private void giveLife() {
        if (gainLife == GIVE_LIFE) {
            gainLife = 0;
            point.giveLife();
        }
    }
}
