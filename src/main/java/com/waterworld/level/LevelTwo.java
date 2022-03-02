package com.waterworld.level;

import com.waterworld.game_engine.GUIState;
import com.waterworld.game_engine.GameEngine;
import com.waterworld.game_engine.StringObjectValue;
import com.waterworld.game_objects.*;
import com.waterworld.menu.LevelStatistics;
import com.waterworld.menu.MainMenu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class LevelTwo extends GUIState {

    private static final Point point = new Point(StringObjectValue.LEVEL_TWO.getValue());
    private static final GameBubbles levelBubbles = new GameBubbles(true);
    private static final Sounds sounds = new Sounds();
    private static final Random random = new Random();
    private static final File background = new File("src/main/resources/game_objects/level_objects/level_two_800x450.jpg");
    private static final File wormPoints = new File("src/main/resources/game_objects/points/worm points 30x30.png");
    private static final File life = new File("src/main/resources/game_objects/points/life.png");

    private static final int MINUS_THIRTY_TWO = -32;
    private static final int MAX_POINTS = 2;
    private static final int MINUS_ONE_HUNDRED_TWENTY = -120;
    private static final int GIVE_LIFE = 10;
    private static final int INITIAL_PLAYER_LIFE = 3;

    private static boolean win;
    private static int levelTwo;

    private BufferedImage backgroundImage;
    private BufferedImage wormIcon;
    private BufferedImage lifeIcon;

    private static GameObject enemyOneSkin;
    private static GameObject enemyTwoSkin;
    private static GameObject worm;
    private static GameObject rockOne;
    private static GameObject rockTwo;
    private static GameObject orangeSeaweed;
    private static Enemy enemyOne;
    private static Enemy enemyTwo;

    private static Player player;
    private int gainLife;

    public LevelTwo(com.waterworld.game_engine.GUIStateManager GUIStateManager) {
        this.GUIStateManager = GUIStateManager;
        init();
    }

    @Override
    public void init() {
        setObjects();
        newPlayer();
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
        enemyOne.move();
        enemyOneSkin.move();
        enemyTwo.move();
        enemyTwoSkin.move();
        rockOne.move();
        orangeSeaweed.move();
        givePoint();
        giveLife();
        transferObjects();
        checkPlayerFrameCollision();
        endLevel();
    }

    @Override
    public void draw(Graphics g) {
        enemyOne.draw(g);
        enemyTwo.draw(g);
        g.drawImage(backgroundImage, 0, 0, null);
        levelBubbles.drawBack(g);
        rockTwo.draw(g);
        rockOne.draw(g);
        orangeSeaweed.draw(g);
        enemyOneSkin.draw(g);
        enemyTwoSkin.draw(g);
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

    public static boolean isWin() {
        return win;
    }

    public static int getLevelTwo() {
        return levelTwo;
    }

    public static void setLevelTwo(int levelTwo) {
        LevelTwo.levelTwo = levelTwo;
    }

    private void removeObjects() {
        enemyOne.setHorizontalPosition(GameEngine.WIDTH + 200);
        enemyOneSkin.setHorizontalPosition(GameEngine.WIDTH + 200);
        enemyTwoSkin.setHorizontalPosition(GameEngine.WIDTH + 200);
        enemyTwo.setHorizontalPosition(GameEngine.WIDTH + 200);
        worm.setHorizontalPosition(GameEngine.WIDTH + 200);
        player.setHorizontalPosition(150);
        player.setVerticalPosition(150);
        player.setXDirection(0);
        player.setYDirection(0);
        levelBubbles.moveOutOfFrame();
        rockOne.setVerticalPosition(GameEngine.HEIGHT);
        rockTwo.setVerticalPosition(GameEngine.HEIGHT);
        orangeSeaweed.setVerticalPosition(GameEngine.HEIGHT);
    }

    private void setObjects() {
        levelBubbles.init();
        worm = new GameObject(1, StringObjectValue.WORM.getValue(), StringObjectValue.RIGHT.getValue(), 0,0, 840, 180, 27,30);
        enemyOne = new Enemy(2, 1335, 30, 80, 90);
        enemyOneSkin = new GameObject(2, StringObjectValue.ENEMY_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0, 1300, -15, 120,131);
        enemyTwo = new Enemy(4, 1035, 240, 80, 90);
        enemyTwoSkin = new GameObject(4, StringObjectValue.ENEMY_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0,0, 1000, 195, 120,131);
        rockOne = new GameObject(1, StringObjectValue.ROCK_ONE.getValue(), StringObjectValue.RIGHT.getValue(), 0, 0, GameEngine.WIDTH + 30, 354, 153, 71);
        rockTwo = new GameObject(1, StringObjectValue.ROCK_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0, 0, GameEngine.WIDTH + 396, GameEngine.HEIGHT - 136, 174, 225);
        orangeSeaweed = new GameObject(1, StringObjectValue.ORANGE_SEAWEED.getValue(), StringObjectValue.RIGHT.getValue(), 0, 0, GameEngine.WIDTH + 190, GameEngine.HEIGHT - 70, 100, 100);
    }

    private void transferObjects() {
        levelBubbles.transfer();
        if (worm.getHorizontalPos() <= MINUS_THIRTY_TWO || worm.intersects(player.getRectangle())) {
            worm.setHorizontalPosition(random.nextInt(400) + 800);
            worm.setVerticalPosition(random.nextInt(320));
            point.gainScoresInFirstLevel();
        }
        if (enemyOne.intersects(player.getRectangle())) {
            point.takeLife();
            sounds.hurtPlayerSound();
            enemyOne.setVerticalPosition(-200);
        }
        if (enemyTwo.intersects(player.getRectangle())) {
            point.takeLife();
            sounds.hurtPlayerSound();
            enemyTwo.setVerticalPosition(-200);
        }
        if (enemyOne.getHorizontalPos() <= MINUS_ONE_HUNDRED_TWENTY) {
            int yPos = random.nextInt(230) + 20;
            int xPos = random.nextInt(800) + 800;
            enemyOne.setVerticalPosition(yPos);
            enemyOne.setHorizontalPosition(xPos);
            enemyOneSkin.setVerticalPosition(yPos - 46);
            enemyOneSkin.setHorizontalPosition(xPos - 35);
        }
        if (enemyTwo.getHorizontalPos() <= MINUS_ONE_HUNDRED_TWENTY) {
            int yPos = random.nextInt(230) + 20;
            int xPos = random.nextInt(800) + 800;
            enemyTwo.setVerticalPosition(yPos);
            enemyTwo.setHorizontalPosition(xPos);
            enemyTwoSkin.setVerticalPosition(yPos - 46);
            enemyTwoSkin.setHorizontalPosition(xPos - 35);
        }
        if (rockOne.getHorizontalPos() <= MINUS_ONE_HUNDRED_TWENTY) {
            rockOne.setHorizontalPosition(random.nextInt(50) + 800);
        }
        if (rockTwo.getHorizontalPos() <= -200) {
            rockTwo.setHorizontalPosition(random.nextInt(800) + 974);
        }
        if (orangeSeaweed.getHorizontalPos() <= MINUS_ONE_HUNDRED_TWENTY) {
            orangeSeaweed.setHorizontalPosition(random.nextInt(200) + 800);
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
            zeroLife();
            win = true;
            Point.getPoints().add(point.getScore());
            removeObjects();
            newPlayer();
            levelTwo = 2;
            LevelStatistics.setCurrentChoice(0);
            GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.LEVEL_STATISTICS);

        } else if (Point.getLife() == 0) {
            win = false;
            zeroLife();
            stopMusic();
            Point.setLife(INITIAL_PLAYER_LIFE);
            Point.getPoints().add(point.getScore());
            removeObjects();
            newPlayer();
            levelTwo = 2;
            LevelStatistics.setCurrentChoice(0);
            GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.LEVEL_STATISTICS);

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

    private void newPlayer() {
        player = new Player(0,150, 150, 95,80, 2);
    }
}
