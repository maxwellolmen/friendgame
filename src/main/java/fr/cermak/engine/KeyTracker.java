package fr.cermak.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;

public abstract class KeyTracker implements KeyListener {

    private Set<Character> last;
    private Set<Character> pressed;

    public KeyTracker() {
        last = new HashSet<>();
        pressed = new HashSet<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyChar());

        instKeyPress(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyChar());

        instKeyRelease(e.getKeyChar());
    }

    public void stroke() {
        for (char c : pressed) {
            repKeyPress(c);
            last.remove(c);
        }

        for (char c : last) {
            repKeyRelease(c);
        }

        last.clear();
        last.addAll(pressed);
    }

    public abstract void repKeyPress(char c);

    public abstract void repKeyRelease(char c);

    public abstract void instKeyPress(char c);

    public abstract void instKeyRelease(char c);

    public boolean isPressed(char c) {
        return pressed.contains(c);
    }

    public boolean isHorizPressed() {
        return isLeftPressed() || isRightPressed();
    }

    public boolean isLeftPressed() {
        return pressed.contains('a') || pressed.contains(VK_LEFT);
    }

    public boolean isRightPressed() {
        return pressed.contains('d') || pressed.contains(VK_RIGHT);
    }
}