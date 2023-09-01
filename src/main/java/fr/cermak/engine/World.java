package fr.cermak.engine;

import java.util.ArrayList;
import java.util.List;

public class World {

    private int width, height;
    private List<Sprite> sprites;

    public World(int width, int height) {
        this.width = width;
        this.height = height;

        sprites = new ArrayList<>();
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void force() {
        for (Sprite sprite : sprites) {
            sprite.setOldX(sprite.getX());
            sprite.setOldY(sprite.getY());

            if (sprite.hasGravity() && sprite.getY() < (height - sprite.getHeight())) {
                sprite.addVelocityY(1);
            }

            if (sprite.getY() < ((height - sprite.getHeight()) - (sprite.getVelocityY()))) {
                sprite.addY((int) sprite.getVelocityY());
            } else {
                sprite.setY(height - sprite.getHeight());
                sprite.setVelocityY(0);
            }

            sprite.addX((int) sprite.getVelocityX());
        }
    }
}