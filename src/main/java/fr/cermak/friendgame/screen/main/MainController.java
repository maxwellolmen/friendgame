package fr.cermak.friendgame.screen.main;

import fr.cermak.engine.KeyTracker;
import fr.cermak.engine.Sprite;
import fr.cermak.engine.World;

import static java.awt.event.KeyEvent.*;

public class MainController extends KeyTracker {

    private Sprite sprite;
    private World world;

    public MainController(Sprite sprite, World world) {
        this.sprite = sprite;
        sprite.setActive(true);
        this.world = world;
    }

    @Override
    public void repKeyPress(int key) {
        if (key == VK_SPACE || key == VK_W || key == VK_UP || key == VK_KP_UP) {
            if (sprite.isGrounded(world)) {
                sprite.setVelocityY(-17);
            }
        } else if (key == VK_LEFT || key == VK_KP_LEFT || key == VK_A && !isRightPressed()) {
            sprite.setVelocityX(-5);
        } else if (key == VK_RIGHT || key == VK_KP_RIGHT || key == VK_D && !isLeftPressed()) {
            sprite.setVelocityX(5);
        }
    }

    @Override
    public void repKeyRelease(int key) {
        if (key == VK_LEFT || key == VK_RIGHT || key == VK_KP_LEFT || key == VK_KP_RIGHT || key == VK_A || key == VK_D) {
            if (!isHorizPressed()) {
                sprite.setVelocityX(0);
            } else if (isLeftPressed()) {
                sprite.setVelocityX(-5);
            } else if (isRightPressed()) {
                sprite.setVelocityX(5);
            }
        }
    }

    @Override
    public void instKeyPress(int key) {
        /*if (key == VK_L) {
            sprite.setVelocityX(0);

            int index = world.getSprites().indexOf(sprite);
            do {
                index++;

                if (index == world.getSprites().size()) {
                    index = 0;
                }
            } while (!world.getSprites().get(index).isPlayable());

            sprite.setActive(false);
            sprite = world.getSprites().get(index);
            sprite.setActive(true);
        }*/
    }

    @Override
    public void instKeyRelease(int key) {

    }

    public void setSprite(Sprite sprite) {
        this.sprite.setActive(false);
        this.sprite = sprite;
        this.sprite.setActive(true);
    }
}
