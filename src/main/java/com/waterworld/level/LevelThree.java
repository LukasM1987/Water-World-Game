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

public class LevelThree extends GUIState {

    private static final Point point = new Point(StringObjectValue.LEVEL_THREE.getValue());
    private static final GameBubbles levelBubbles = new GameBubbles(true);
    private static final Sounds sounds = new Sounds();
    private static final Random random = new Random();
    private static final File background = new File("src/main/resources/game_objects/level_objects/level_three_800x450.jpg");
    private static final File wormPoints = new File("src/main/resources/game_objects/points/worm points 30x30.png");
    private static final File life = new File("src/main/resources/game_objects/points/life.png");

    private static final int MINUS_THIRTY_TWO = -32;
    private static final int MAX_POINTS = 100;
    private static final int MINUS_ONE_HUNDRED_TWENTY = -120;
    private static final int GIVE_LIFE = 10;
    private static final int INITIAL_PLAYER_LIFE = 3;

    private static boolean win;
    private static int levelThree;

    private BufferedImage backgroundImage;
    private BufferedImage wormIcon;
    private BufferedImage lifeIcon;

    private GameObject enemyOneSkin;
    private GameObject worm;
    private GameObject rockOne;
    private GameObject rockTwo;
    private GameObject rockThree;
    private Enemy enemyOne;

    private Player player;
    private int gainLife;

    public LevelThree(com.waterworld.game_engine.GUIStateManager GUIStateManager) {
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
        rockOne.move();
        rockTwo.move();
        rockThree.move();
        worm.move();
        enemyOne.move();
        enemyOneSkin.move();
        givePoint();
        giveLife();
        transferObjects();
        checkPlayerFrameCollision();
        endLevel();
    }

    @Override
    public void draw(Graphics g) {
        enemyOne.draw(g);
        g.drawImage(backgroundImage, 0, 0, null);
        levelBubbles.drawBack(g);
        rockTwo.draw(g);
        rockOne.draw(g);
        rockThree.draw(g);
        enemyOneSkin.draw(g);
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

    public static int getLevelThree() {
        return levelThree;
    }

    public static void setLevelThree(int levelThree) {
        LevelThree.levelThree = levelThree;
    }

    private void setObjects() {
        levelBubbles.init();
        worm = new GameObject(1, StringObjectValue.WORM.getValue(), StringObjectValue.RIGHT.getValue(), 0,0, 840, 180, 27,30);
        enemyOne = new Enemy(3, 1000, 200, 115, 124);
        enemyOneSkin = new GameObject(3, StringObjectValue.ENEMY.getValue(), StringObjectValue.RIGHT.getValue(), 0,0, 1000, 195, 120,131);

        rockOne = new GameObject(1, StringObjectValue.ROCK_ONE.getValue(), StringObjectValue.RIGHT.getValue(), 0, 0, GameEngine.WIDTH + 30, 354, 153, 71);
        rockTwo = new GameObject(1, StringObjectValue.ROCK_TWO.getValue(), StringObjectValue.RIGHT.getValue(), 0, 0, GameEngine.WIDTH + 356, GameEngine.HEIGHT - 136, 174, 225);
        rockThree = new GameObject(1, StringObjectValue.ROCK_ONE.getValue(), StringObjectValue.RIGHT.getValue(), 0, 0, GameEngine.WIDTH + 739, 354, 174, 225);
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
        if (enemyOne.getHorizontalPos() <= MINUS_ONE_HUNDRED_TWENTY) {
            int yPos = random.nextInt(260);
            int xPos = random.nextInt(800) + 800;
            enemyOne.setVerticalPosition(yPos);
            enemyOne.setHorizontalPosition(xPos);
            enemyOneSkin.setVerticalPosition(yPos - 6);
            enemyOneSkin.setHorizontalPosition(xPos - 5);
        }
        if (rockOne.getHorizontalPos() <= MINUS_ONE_HUNDRED_TWENTY) {
            rockOne.setHorizontalPosition(random.nextInt(50) + 800);
        }
        if (rockTwo.getHorizontalPos() <= -200) {
            rockTwo.setHorizontalPosition(random.nextInt(800) + 974);
        }
        if (rockThree.getHorizontalPos() <= MINUS_ONE_HUNDRED_TWENTY) {
            rockThree.setHorizontalPosition(random.nextInt(150) + 800);
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
            win = true;
            Point.getPoints().add(point.getScore());
            zeroLife();
            removeObjects();
            newPlayer();
            levelThree = 3;
            GUIStateManager.setStates(com.waterworld.game_engine.GUIStateManager.LEVEL_STATISTICS);
        } else if (Point.getLife() == 0) {
            win = false;
            zeroLife();
            stopMusic();
            Point.getPoints().add(point.getScore());
            Point.setLife(INITIAL_PLAYER_LIFE);
            removeObjects();
            newPlayer();
            levelThree = 3;
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

    private void removeObjects() {
        enemyOneSkin.setHorizontalPosition(900);
        enemyOne.setHorizontalPosition(900);
        worm.setHorizontalPosition(900);
        player.setHorizontalPosition(150);
        player.setVerticalPosition(150);
        player.setXDirection(0);
        player.setYDirection(0);
        levelBubbles.moveOutOfFrame();
        rockTwo.setVerticalPosition(GameEngine.HEIGHT);
    }

    private void newPlayer() {
        player = new Player(0,150, 150, 95,80, 2);
    }
}
