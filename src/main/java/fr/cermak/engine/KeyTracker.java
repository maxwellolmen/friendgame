package fr.cermak.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import static java.awt.event.KeyEvent.*;

public abstract class KeyTracker implements KeyListener {

    private Set<Integer> last;
    private Set<Integer> pressed;

    public KeyTracker() {
        last = new HashSet<>();
        pressed = new HashSet<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());

        instKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());

        instKeyRelease(e.getKeyCode());
    }

    public void stroke() {
        for (int key : pressed) {
            repKeyPress(key);
            last.remove(key);
        }

        for (int key : last) {
            repKeyRelease(key);
        }

        last.clear();
        last.addAll(pressed);
    }

    public abstract void repKeyPress(int key);

    public abstract void repKeyRelease(int key);

    public abstract void instKeyPress(int key);

    public abstract void instKeyRelease(int key);

    public boolean isPressed(int key) {
        return pressed.contains(key);
    }

    public boolean isHorizPressed() {
        return isLeftPressed() || isRightPressed();
    }

    public boolean isLeftPressed() {
        return pressed.contains(VK_A) || pressed.contains(VK_LEFT) || pressed.contains(VK_KP_LEFT);
    }

    public boolean isRightPressed() {
        return pressed.contains(VK_D) || pressed.contains(VK_RIGHT) || pressed.contains(VK_KP_RIGHT);
    }
}