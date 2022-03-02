package com.waterworld.game_objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameObject {

    private static final File bubble8x8File = new File("src/main/resources/game_objects/bubbles/bubble8x8.png");
    private static final File bubble16x16File = new File("src/main/resources/game_objects/bubbles/bubble16x16.png");
    private static final File bubble24x24File = new File("src/main/resources/game_objects/bubbles/bubble24x24.png");
    private static final File bubble36x36File = new File("src/main/resources/game_objects/bubbles/bubble36x36.png");
    private static final File leftButton = new File("src/main/resources/game_objects/menu/left_button.png");
    private static final File rightButton = new File("src/main/resources/game_objects/menu/right button.png");
    private static final File downButton = new File("src/main/resources/game_objects/menu/down button.png");
    private static final File upButton = new File("src/main/resources/game_objects/menu/up button.png");
    private static final File worm = new File("src/main/resources/game_objects/points/worm 27x30.png");
    private static final File enemyOne = new File("src/main/resources/game_objects/enemies/Enemy one.png");
    private static final File enemyTwo = new File("src/main/resources/game_objects/enemies/Enemy two.png");
    private static final File seaWeedOne = new File("src/main/resources/game_objects/level_objects/seaweed 1.png");
    private static final File seaWeedTwo = new File("src/main/resources/game_objects/level_objects/seaweed 2.png");
    private static final File seaWeedOrange = new File("src/main/resources/game_objects/level_objects/seaweed_orange_100x100.png");
    private static final File seaWeedGreen = new File("src/main/resources/game_objects/level_objects/seaweed_green_100x100.png");
    private static final File seaWeedBlue = new File("src/main/resources/game_objects/level_objects/seaweed_blue_100x100.png");
    private static final File deadFish = new File("src/main/resources/game_objects/level_objects/dead fish.png");
    private static final File rockOne = new File("src/main/resources/game_objects/level_objects/rock 1.png");
    private static final File rockTwo = new File("src/main/resources/game_objects/level_objects/rock 2.png");
    private static final File fish = new File("src/main/resources/game_objects/player/player right.png");


    private int initialSpeed;

    private int horizontalVelocity;
    private int verticalVelocity;
    private int directionX;
    private int directionY;

    private String objectName;
    private String direction;

    private Rectangle rectangle;
    private BufferedImage bubble8x8;
    private BufferedImage bubble16x16;
    private BufferedImage bubble24x24;
    private BufferedImage bubble36x36;
    private BufferedImage left;
    private BufferedImage right;
    private BufferedImage down;
    private BufferedImage up;
    private BufferedImage wormIcon;
    private BufferedImage enemyOneIcon;
    private BufferedImage enemyTwoIcon;
    private BufferedImage seaWeedOneIcon;
    private BufferedImage seaWeedTwoIcon;
    private BufferedImage deadFishIcon;
    private BufferedImage rockOneImg;
    private BufferedImage rockTwoImg;
    private BufferedImage fishIcon;
    private BufferedImage orangeIcon;
    private BufferedImage greenIcon;
    private BufferedImage blueIcon;

    public GameObject(int initialSpeed, String objectName, String direction, int directionX, int directionY, int xPosition, int yPosition, int xSize, int ySize) {
        this.rectangle = new Rectangle(xPosition, yPosition, xSize, ySize);
        this.objectName = objectName;
        this.directionX = directionX;
        this.directionY = directionY;
        this.initialSpeed = initialSpeed;
        this.direction = direction;
        init();
    }

    public void init() {
        try {
            bubble8x8 = ImageIO.read(bubble8x8File);
            bubble16x16 = ImageIO.read(bubble16x16File);
            bubble24x24 = ImageIO.read(bubble24x24File);
            bubble36x36 = ImageIO.read(bubble36x36File);
            left = ImageIO.read(leftButton);
            right = ImageIO.read(rightButton);
            down = ImageIO.read(downButton);
            up = ImageIO.read(upButton);
            wormIcon = ImageIO.read(worm);
            enemyOneIcon = ImageIO.read(enemyOne);
            enemyTwoIcon = ImageIO.read(enemyTwo);
            seaWeedOneIcon = ImageIO.read(seaWeedOne);
            seaWeedTwoIcon = ImageIO.read(seaWeedTwo);
            deadFishIcon = ImageIO.read(deadFish);
            rockOneImg = ImageIO.read(rockOne);
            rockTwoImg = ImageIO.read(rockTwo);
            fishIcon = ImageIO.read(fish);
            orangeIcon = ImageIO.read(seaWeedOrange);
            greenIcon = ImageIO.read(seaWeedGreen);
            blueIcon = ImageIO.read(seaWeedBlue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int horizontalDirection = directionX;
        int verticalDirection = directionY;
        if (direction.equals("any direction")) {

            if (horizontalDirection == 0) {
                horizontalDirection--;
            }
            setXDirection(horizontalDirection * initialSpeed);
            if (verticalDirection == 0) {
                verticalDirection--;
            }
            setYDirection(verticalDirection * initialSpeed);
        }
        if (direction.equals("up and down")) {

        }

        if (direction.equals("up")) {
            verticalDirection = directionY;
            verticalDirection--;
            setYDirection(verticalDirection * initialSpeed);
        }

        if (direction.equals("down")) {
            verticalDirection = directionY;
            verticalDirection++;
            setYDirection(verticalDirection * initialSpeed);
        }

        if (direction.equals("left")) {
            horizontalDirection = directionX;
            horizontalDirection++;
            setXDirection(horizontalDirection * initialSpeed);
        }

        if (direction.equals("right")) {
            horizontalDirection = directionX;
            horizontalDirection--;
            setXDirection(horizontalDirection * initialSpeed);
        }
    }

    public void setXDirection(int xDirection) {
        horizontalVelocity = xDirection;
    }

    public void setYDirection(int xDirection) {
        verticalVelocity = xDirection;
    }

    public void move() {
        rectangle.x += horizontalVelocity;
        rectangle.y += verticalVelocity;
    }

    public void draw(Graphics g) {
       if (objectName.equals("bubble8x8")) {
           g.drawImage(bubble8x8, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("bubble16x16")) {
           g.drawImage(bubble16x16, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("bubble24x24")) {
           g.drawImage(bubble24x24, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("bubble36x36")) {
           g.drawImage(bubble36x36, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("left button")) {
           g.drawImage(left, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("right button")) {
           g.drawImage(right, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("down button")) {
           g.drawImage(down, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("up button")) {
           g.drawImage(up, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("left info")) {
           controlInfoFont(g);
           g.drawString("LEFT", rectangle.x, rectangle.y);
       } else if (objectName.equals("right info")) {
           controlInfoFont(g);
           g.drawString("RIGHT", rectangle.x, rectangle.y);
       } else if (objectName.equals("up info")) {
           controlInfoFont(g);
           g.drawString("UP", rectangle.x, rectangle.y);
       } else if (objectName.equals("down info")) {
           controlInfoFont(g);
           g.drawString("DOWN", rectangle.x, rectangle.y);
       } else if (objectName.equals("worm")) {
           g.drawImage(wormIcon, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("enemy")) {
           g.drawImage(enemyOneIcon, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("seaweed one")) {
           g.drawImage(seaWeedOneIcon, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("seaweed two")) {
           g.drawImage(seaWeedTwoIcon, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("dead fish")) {
           g.drawImage(deadFishIcon, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("rock one")) {
           g.drawImage(rockOneImg, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("rock two")) {
           g.drawImage(rockTwoImg, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("fish")) {
           g.drawImage(fishIcon, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("enemy two")) {
           g.drawImage(enemyTwoIcon, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("orange seaweed")) {
           g.drawImage(orangeIcon, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("green seaweed")) {
           g.drawImage(greenIcon, rectangle.x, rectangle.y, null);
       } else if (objectName.equals("blue seaweed")) {
           g.drawImage(blueIcon, rectangle.x, rectangle.y, null);
       }
    }

    public boolean intersects(Rectangle rectangle) {
        return this.rectangle.intersects(rectangle);
    }

    public int getVerticalPos() {
        return rectangle.y;
    }

    public int getHorizontalPos() {
        return rectangle.x;
    }

    public int getYVelocity() {
        return verticalVelocity;
    }

    public int getXVelocity() {
        return horizontalVelocity;
    }

    public void setHorizontalVelocity(int horizontalVelocity) {
        this.horizontalVelocity = horizontalVelocity;
    }

    public void setVerticalVelocity(int verticalVelocity) {
        this.verticalVelocity = verticalVelocity;
    }

    public void increaseHorizontalVelocity() {
        horizontalVelocity++;
    }

    public void increaseVerticalVelocity() {
        verticalVelocity++;
    }

    public void reduceVerticalVelocity() {
        verticalVelocity--;
    }

    public void setVerticalPosition(int verticalPos) {
        rectangle.y = verticalPos;
    }

    public void setHorizontalPosition(int horizontalPos) {
        rectangle.x = horizontalPos;
    }

    private void controlInfoFont(Graphics g) {
        g.setColor(new Color(225,74,83));
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 36));
    }
}
