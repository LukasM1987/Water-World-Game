package com.waterworld.level;

import com.waterworld.game_engine.GameEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Point {

    private static List<Integer> points = new ArrayList<>();

    private String level;
    private int score;
    private int scoresInFirstLevel;
    private int life = 3;

    public Point(String level) {
        this.level = level;
    }

    public void draw(Graphics g) {
        if (level.equals("level one")) {
            g.setColor(new Color(225, 74, 83));
            g.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
            g.drawString(score / 10 + String.valueOf(score % 10) + "/50", (GameEngine.WIDTH / 16), 36);
        } else if (level.equals("level two")) {
            g.setColor(new Color(225, 74, 83));
            g.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
            g.drawString(score / 10 + String.valueOf(score % 10) + "/100", (GameEngine.WIDTH / 16), 36);
        } else if (level.equals("level three")) {
            g.setColor(new Color(225, 74, 83));
            g.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
            g.drawString(score / 10 + String.valueOf(score % 10) + "/200", (GameEngine.WIDTH / 16), 36);
        }
        g.setColor(new Color(225, 74, 83));
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
        g.drawString(String.valueOf(life), 50, 70);
    }

    public int gainScore() {
        return score++;
    }

    public int getScore() {
        return score;
    }

    public int gainScoresInFirstLevel() {
        return scoresInFirstLevel++;
    }

    public int getScoresInLevel() {
        return scoresInFirstLevel;
    }

    public int takeLife() {
        return life--;
    }

    public int getLife() {
        return life;
    }

    public int giveLife() {
        return life++;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static List<Integer> getPoints() {
        return points;
    }

    public void setScoresInFirstLevel(int scoresInFirstLevel) {
        this.scoresInFirstLevel = scoresInFirstLevel;
    }
}

