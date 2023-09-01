package fr.cermak.friendgame;

import fr.cermak.engine.KeyTracker;
import fr.cermak.engine.Sprite;
import fr.cermak.engine.World;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class SpriteController extends KeyTracker {

    private Sprite sprite;
    private World world;

    public SpriteController(Sprite sprite, World world) {
        this.sprite = sprite;
        this.world = world;
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        char c = e.getKeyChar();

        if (c == ' ' || c == 'w' || c == VK_UP) {
            if (sprite.getY() == (world.getHeight() - sprite.getHeight())) sprite.setVelocityY(-17);
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
