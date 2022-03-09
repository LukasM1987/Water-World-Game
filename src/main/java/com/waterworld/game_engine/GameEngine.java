package com.waterworld.game_engine;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameEngine extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;

    private static final File backgroundFile = new File("src/main/resources/game_objects/menu/loading.jpg");

    private Thread thread;
    private boolean running;

    private ImageIcon imageIcon;
    private JLabel label;
    private BufferedImage image;
    private Graphics g;

    private GUIStateManager GUIStateManager;

    public GameEngine(){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        drawLoadingScreen();
    }
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        init();

        int fps = 0;
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double delta = 0;
        int FPS = 60;
        double ns = 1000000000.0 / FPS;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                draw();
                drawToScreen();
                fps++;
                delta--;
            }
            if(System.currentTimeMillis() - timer >= 1000){
                System.out.println("FPS: " + fps + " per second");
                fps = 0;
                timer += 1000;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        GUIStateManager.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        GUIStateManager.keyReleased(e);
    }

    private void update() {
        GUIStateManager.update();
    }

    private void draw() {
        GUIStateManager.draw(g);
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();
        running = true;
        GUIStateManager = new GUIStateManager();
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }

    private void drawLoadingScreen() {
        imageIcon = new ImageIcon((backgroundFile.getPath()));
        label = new JLabel(imageIcon);
        label.setBounds(0,-40,WIDTH, HEIGHT + 80);
        setLayout(null);
        add(label);
    }
}
