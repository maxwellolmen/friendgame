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
    public void onKeyPressed(char c) {
        if (c == ' ' || c == 'w' || c == VK_UP) {
            if (sprite.isGrounded()) {
                sprite.setVelocityY(-17);
            }
        } else if (c == VK_LEFT || c == 'a' && !isRightPressed()) {
            sprite.setVelocityX(-5);
        } else if (c == VK_RIGHT || c == 'd' && !isLeftPressed()) {
            sprite.setVelocityX(5);
        } else if (c == 'l') {
            sprite.setVelocityX(0);

            int index = world.getSprites().indexOf(sprite);
            do {
                index++;

                if (index == world.getSprites().size()) {
                    index = 0;
                }
            } while (!world.getSprites().get(index).isPlayable());

            sprite = world.getSprites().get(index);
        }
    }

    @Override
    public void onKeyReleased(char c) {
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

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
