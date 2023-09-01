package fr.cermak.engine;

import javax.swing.*;

public class Game extends JFrame {

    private Panel panel;

    public Game(int windowWidth, int windowHeight, String startingWorld) {
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        this.panel = new Panel(windowWidth, windowHeight, startingWorld);
        this.setContentPane(panel);

        pack();

        GravityTimer gravity = new GravityTimer(this);
        gravity.start();
    }

    public void update() {
        panel.repaint();
    }

    public Panel getPanel() {
        return panel;
    }
}
