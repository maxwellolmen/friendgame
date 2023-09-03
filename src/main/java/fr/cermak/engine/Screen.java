package fr.cermak.engine;

import fr.cermak.friendgame.screen.welcome.WelcomeScreen;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.Collection;

public abstract class Screen extends JFrame {

    private Panel panel;

    private GravityTimer timer;

    private Sprite hover;

    public Screen(int windowWidth, int windowHeight, String startingWorld) {
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        this.panel = new Panel(windowWidth, windowHeight, startingWorld, this);
        this.setContentPane(panel);

        pack();

        timer = new GravityTimer(this, null);
        timer.start();

        this.hover = null;
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

    public abstract void onClick(int x, int y, boolean spriteClicked);

    public abstract void onClick(Sprite sprite);

    public void onMove(int x, int y) {
        for (Sprite sprite : panel.getWorld().getSprites().values()) {
            if (sprite.isOn(x, y)) {
                hover = sprite;
                onHover(sprite);
            } else if (hover == sprite) {
                hover = null;
                onNoHover(sprite);
            }
        }
    }

    public void onHover(Sprite sprite) {
        if (sprite instanceof Button) {
            ((Button) sprite).onHover();
        }
    }

    public void onNoHover(Sprite sprite) {
        if (sprite instanceof Button) {
            ((Button) sprite).onNoHover();
        }
    }

    public void close() {
        timer.stop();
        dispose();
        //dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSED));
    }

    public void addSprite(Sprite sprite) {
        panel.getWorld().addSprite(sprite);
    }

    public void addSprites(Sprite... sprites) {
        for (Sprite sprite : sprites) {
            addSprite(sprite);
        }
    }
}
