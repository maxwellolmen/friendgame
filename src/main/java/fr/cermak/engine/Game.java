package fr.cermak.engine;

import javax.swing.*;

public class Game extends JFrame {

    private Panel panel;

    private GravityTimer timer;

    public Game(int windowWidth, int windowHeight, String startingWorld, KeyTracker keyTracker) {
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        this.panel = new Panel(windowWidth, windowHeight, startingWorld);
        this.setContentPane(panel);

        pack();

        timer = new GravityTimer(this, keyTracker);
        timer.start();
    }

    public Game(int windowWidth, int windowHeight, String startingWorld) {
        this(windowWidth, windowHeight, startingWorld, null);
    }

    public void update() {
        panel.repaint();
    }

    public Panel getPanel() {
        return panel;
    }

    public void setKeyTracker(KeyTracker keyTracker) {
        timer.setKeyTracker(keyTracker);
    }
}
