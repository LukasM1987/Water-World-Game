package com.waterworld.game_engine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frame {

    private static final File frameIcon = new File("src/main/resources/game_objects/bubbles/bubble8x8.png");
    private static final JFrame frame = new JFrame("Water World");
    private BufferedImage icon;

    public void gameFrame() {
        readIcon();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon);
        frame.setContentPane(new GameEngine());
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void readIcon() {
        try {
            icon = ImageIO.read(frameIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
