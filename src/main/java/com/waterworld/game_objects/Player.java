package com.waterworld.game_objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

    private static final File playerSkin = new File("src/main/resources/game_objects/player/unnamed.png");
    private static final int STOP_PLAYER = 0;
    private static final int MOVE_UP_PLAYER = -1;
    private static final int MOVE_DOWN_PLAYER_FAST = 3;
    private static final int MOVE_DOWN_PLAYER_SLOWLY = 1;

    private Rectangle rectangle;
    private int horizontalVelocity;
    private int verticalVelocity;

    private int initialPlayerSpeed;

    private BufferedImage skin;

    public Player(int initialXPosition, int initialYPosition, int WIDTH, int HEIGHT, int initialPlayerSpeed) {
        this.initialPlayerSpeed = initialPlayerSpeed;
        this.rectangle = new Rectangle(initialXPosition, initialYPosition, WIDTH, HEIGHT);
    }

    public void setXDirection(int xDirection) {
        horizontalVelocity = xDirection;
    }

    public void setYDirection(int yDirection) {
        verticalVelocity = yDirection;
    }


    public void movePlayer(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_UP) {
            setYDirection(MOVE_UP_PLAYER);
        }
        if (key.getKeyCode() == KeyEvent.VK_DOWN) {
            setYDirection(MOVE_DOWN_PLAYER_FAST);
        }

        if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
            setXDirection(initialPlayerSpeed);
        }

        if (key.getKeyCode() == KeyEvent.VK_LEFT) {
            setXDirection(-initialPlayerSpeed);
        }
    }

    public void stopPlayer(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_UP) {
            setYDirection(STOP_PLAYER);
        }

        if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
            setXDirection(STOP_PLAYER);
        }

        if (key.getKeyCode() == KeyEvent.VK_LEFT) {
            setXDirection(STOP_PLAYER);
        }
        setYDirection(MOVE_DOWN_PLAYER_SLOWLY);
    }

    public boolean intersects(Rectangle rectangle) {
        return this.rectangle.intersects(rectangle);
    }

    public void horizontalMove() {
        rectangle.x += horizontalVelocity;
    }

    public void verticalMove() {
        rectangle.y += verticalVelocity;
    }

    public void draw(Graphics g) {
        initSkin();
        g.drawImage(skin, rectangle.x, rectangle.y, null);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getHorizontalPosition() {
        return rectangle.x;
    }

    public void setHorizontalPosition(int pos) {
        rectangle.x = pos;
    }

    public int getVerticalPosition() {
        return rectangle.y;
    }

    public void setVerticalPosition(int pos) {
        rectangle.y = pos;
    }

    private void initSkin() {
        try {
            skin = ImageIO.read(playerSkin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
