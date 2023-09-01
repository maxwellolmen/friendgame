package fr.cermak.friendgame;

import fr.cermak.engine.KeyTracker;
import fr.cermak.engine.Sprite;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class SpriteController extends KeyTracker {

    private Sprite sprite;

    public SpriteController(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        char c = e.getKeyChar();

        if (c == ' ' || c == 'w' || c == VK_UP) {
            if (sprite.getY() == 400) sprite.setVelocityY(-17);
        } else if (c == VK_LEFT || c == 'a' && !isRightPressed()) {
            sprite.setVelocityX(-5);
        } else if (c == VK_RIGHT || c == 'd' && !isLeftPressed()) {
            sprite.setVelocityX(5);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        char c = e.getKeyChar();

        if (c == VK_LEFT || c == VK_RIGHT || c == 'a' || c == 'd') {
            if (!isHorizPressed()) {
                sprite.setVelocityX(0);
            } else if (isLeftPressed()) {
                sprite.setVelocityX(-5);
            } else if (isRightPressed()) {
                sprite.setVelocityX(5);
            }
        }
    }
}
