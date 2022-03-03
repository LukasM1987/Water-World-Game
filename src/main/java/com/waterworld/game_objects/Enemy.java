package com.waterworld.game_objects;

import java.awt.*;

public class Enemy {

    private int initialSpeed;

    private int horizontalVelocity;

    private Rectangle rectangle;

    public Enemy(int initialSpeed, int xPosition, int yPosition, int xSize, int ySize) {
        this.rectangle = new Rectangle(xPosition, yPosition, xSize, ySize);
        this.initialSpeed = initialSpeed;
        init(initialSpeed);
    }

    public void move() {
        rectangle.x -= horizontalVelocity;
    }

    public void init(int xDirection) {
        horizontalVelocity = xDirection;
    }

    public boolean intersects(Rectangle rectangle) {
        return this.rectangle.intersects(rectangle);
    }

    public int getHorizontalPos() {
        return rectangle.x;
    }

    public void draw(Graphics g) {
        g.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void setVerticalPosition(int verticalPos) {
        rectangle.y = verticalPos;
    }

    public void setHorizontalPosition(int horizontalPos) {
        rectangle.x = horizontalPos;
    }
}
