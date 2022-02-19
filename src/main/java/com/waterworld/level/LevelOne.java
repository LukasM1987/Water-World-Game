package com.waterworld.level;

import com.waterworld.game_engine.GUIState;
import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_objects.*;
import com.waterworld.menu.MainMenu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class LevelOne extends GUIState {

    private static final Point point = new Point("level one");
    private static final LevelBubbles levelBubbles = new LevelBubbles();
    private static final LevelSeaweed levelSeaweed = new LevelSeaweed();
    private static final Sounds sounds = new Sounds();
    private static final Random random = new Random();
    private static final File background = new File("src/main/resources/game_objects/level_objects/level_one_800x400.jpg");
    private static final File wormPoints = new File("src/main/resources/game_objects/points/worm points 30x30.png");
    private static final File life = new File("src/main/resources/game_objects/points/life.png");

    private static final String WORM = "worm";
    private static final String RIGHT = "right";
    private static final String ENEMY = "enemy";
    private static final int MINUS_THIRTY_TWO = -32;
    private static final int MAX_POINTS = 50;
    private static final int MINUS_ONE_HUNDRED_TWENTY = -120;
    private static final int GIVE_LIFE = 10;

    private BufferedImage backgroundImage;
    private BufferedImage wormIcon;
    private BufferedImage lifeIcon;

    private GameObject enemySkin;
    private GameObject worm;
    private Enemy enemy;

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
        levelBubbles.move();
        player.horizontalMove();
        player.verticalMove();
        worm.move();
        enemy.move();
        enemySkin.move();
        levelSeaweed.move();
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
        levelBubbles.drawBack(g);
        //Miejsce na gracza, przeciwników, elementy poziomu i punkty

        //
        levelSeaweed.drawFront(g);
        enemySkin.draw(g);
        worm.draw(g);
        player.draw(g);
        levelSeaweed.drawFront(g);
        g.drawImage(wormIcon, 10, 10, null);
        point.draw(g);
        g.drawImage(lifeIcon, 10, 44, null);
        levelBubbles.drawFront(g);
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
        levelBubbles.init();
        levelSeaweed.init();
        worm = new GameObject(1, WORM, RIGHT, 0,0, 800, 180, 27,30);
        enemy = new Enemy(3, 900, 200, 115, 124);
        enemySkin = new GameObject(3, ENEMY, RIGHT, 0,0, 900, 195, 120,131);

    }

    private void transferObjects() {
        levelBubbles.transfer();
        levelSeaweed.transfer();
        if (worm.getHorizontalPos() <= MINUS_THIRTY_TWO || worm.intersects(player.getRectangle())) {
            worm.setHorizontalPosition(random.nextInt(400) + 800);
            worm.setVerticalPosition(random.nextInt(320));
            point.gainScoresInFirstLevel();
        }
        if (enemy.intersects(player.getRectangle())) {
            point.takeLife();
            sounds.hurtPlayerSound();
            enemy.setVerticalPosition(-100);
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
        if (point.getScoresInLevel() == MAX_POINTS) {
            stopMusic();

        } else if (point.getLife() == 0) {
            stopMusic();
            GUIStateManager.setStates(GUIStateManager.STATISTICS_DEAD);
        }
    }

    private void giveLife() {
        if (gainLife == GIVE_LIFE) {
            gainLife = 0;
            sounds.givePLayerLife();
            point.giveLife();
        }
    }

    private void stopMusic() {
        MainMenu.getClip().stop();
    }
}
