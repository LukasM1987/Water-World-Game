package com.waterworld.level;

import com.waterworld.game_engine.GUIState;
import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_engine.StringObjectValue;
import com.waterworld.game_objects.*;
import com.waterworld.menu.MainMenu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class LevelOne extends GUIState {

    private static final Point point = new Point(StringObjectValue.LEVEL_ONE.getValue());
    private static final GameBubbles levelBubbles = new GameBubbles(true);
    private static final LevelSeaweed levelSeaweed = new LevelSeaweed();
    private static final Sounds sounds = new Sounds();
    private static final Random random = new Random();
    private static final File background = new File("src/main/resources/game_objects/level_objects/level_one_800x400.jpg");
    private static final File wormPoints = new File("src/main/resources/game_objects/points/worm points 30x30.png");
    private static final File life = new File("src/main/resources/game_objects/points/life.png");

    private static final int MINUS_THIRTY_TWO = -32;
    private static final int MAX_POINTS = 50;
    private static final int MINUS_ONE_HUNDRED_TWENTY = -120;
    private static final int GIVE_LIFE = 10;

    private BufferedImage backgroundImage;
    private BufferedImage wormIcon;
    private BufferedImage lifeIcon;

    private GameObject enemySkin;
    private GameObject worm;
    private GameObject rockOne;
    private GameObject rockTwo;
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
        rockTwo.move();
        worm.move();
        enemy.move();
        enemySkin.move();
        levelSeaweed.move();
        rockOne.move();
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
        rockTwo.draw(g);
        levelSeaweed.drawFront(g);
        rockOne.draw(g);
        enemySkin.draw(g);
        worm.draw(g);
        player.draw(g);
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
        worm = new GameObject(1, StringObjectValue.WORM.getValue(), StringObjectValue.RIGHT.getValue(), 0,0, 840, 180, 27,30);
        enemy = new Enemy(3, 1000, 200, 115, 124);
        enemySkin = new GameObject(3, StringObjectValue.ENEMY.getValue(), StringObjectValue.RIGHT.getValue(), 0,0, 1000, 195, 120,131);
        rockOne = new GameObject(1, StringObjectValue.ROCK_ONE.getValue(), StringObjectValue.RIGHT.getValue(), 0, 0, GameEngine.WIDTH + 30, 350, 153, 71);
        rockTwo = new GameObject(1, StringObjectValue.ROCK_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0, 0, GameEngine.WIDTH + 300, GameEngine.HEIGHT - 140, 174, 225);

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
            enemy.setVerticalPosition(-200);
        }
        if (enemy.getHorizontalPos() <= MINUS_ONE_HUNDRED_TWENTY) {
            int yPos = random.nextInt(260);
            int xPos = random.nextInt(800) + 800;
            enemy.setVerticalPosition(yPos);
            enemy.setHorizontalPosition(xPos);
            enemySkin.setVerticalPosition(yPos - 6);
            enemySkin.setHorizontalPosition(xPos - 5);
        }
        if (rockTwo.getHorizontalPos() <= -200) {
            rockTwo.setHorizontalPosition(random.nextInt(800) + 974);
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
            point.getPoints().add(point.getScoresInLevel());
            point.setScoresInFirstLevel(0);

        } else if (point.getLife() == 0) {
            zeroLife();
            stopMusic();
            point.setLife(3);
            point.setScore(0);
            point.getPoints().add(point.getScoresInLevel());
            enemySkin.setHorizontalPosition(900);
            enemy.setHorizontalPosition(900);
            worm.setHorizontalPosition(900);
            player.setHorizontalPosition(150);
            player.setVerticalPosition(150);
            player.setXDirection(0);
            player.setYDirection(0);
            levelSeaweed.moveOutOfFrame();
            levelBubbles.moveOutOfFrame();
            rockTwo.setVerticalPosition(GameEngine.HEIGHT);
            GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.STATISTICS_DEAD);
        }
    }

    private void giveLife() {
        if (gainLife == GIVE_LIFE) {
            zeroLife();
            sounds.givePLayerLife();
            point.giveLife();
        }
    }

    private void stopMusic() {
        MainMenu.getClip().stop();
    }

    private int zeroLife() {
        gainLife = 0;
        return gainLife;
    }
}
