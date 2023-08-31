package fr.cermak.friendgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {

    private Color color;
    private Timer timer;

    public GameFrame() {
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        color = Color.red;
        this.addKeyListener(this);

        timer = new Timer(0, e -> {
            color = Color.red;
            repaint();
            timer.stop();
        });

        timer.setInitialDelay(1000);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, 500, 500);
    }

    @Override
    public void keyTyped(KeyEvent event) {}

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyChar() == ' ') {
            color = Color.green;
            repaint();

            timer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {}
}
