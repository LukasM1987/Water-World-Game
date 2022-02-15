package com.waterworld.game_engine;

import javax.swing.*;

public class Frame {

    private static final JFrame frame = new JFrame("Water World");

    public void gameFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GameEngine());
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
