package com.waterworld.level;

import com.waterworld.game_engine.GameEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Point {

    private static List<Integer> points = new ArrayList<>();

    private String level;
    private static int score;
    private static int scoresInFirstLevel;
    private static int life;

    public Point(String level) {
        this.level = level;
    }

    public void draw(Graphics g) {
        if (level.equals("level one")) {
            g.setColor(new Color(225, 74, 83));
            g.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
            g.drawString(score / 10 + String.valueOf(score % 10) + "/25", (GameEngine.WIDTH / 16), 36);
        } else if (level.equals("level two")) {
            g.setColor(new Color(225, 74, 83));
            g.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
            g.drawString(score / 10 + String.valueOf(score % 10) + "/50", (GameEngine.WIDTH / 16), 36);
        } else if (level.equals("level three")) {
            g.setColor(new Color(225, 74, 83));
            g.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
            g.drawString(score / 10 + String.valueOf(score % 10) + "/75", (GameEngine.WIDTH / 16), 36);
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

    public static int zeroScore() {
        score = 0;
        return score;
    }

    public static int zeroScoreInLevel() {
        scoresInFirstLevel = 0;
        return scoresInFirstLevel;
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

    public static int getLife() {
        return life;
    }

    public int giveLife() {
        return life++;
    }

    public static void setLife(int life) {
        Point.life = life;
    }

    public static List<Integer> getPoints() {
        return points;
    }
}

