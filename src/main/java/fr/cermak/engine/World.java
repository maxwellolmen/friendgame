package fr.cermak.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class World {

    private int width, height;
    private List<Sprite> sprites;

    private Image background;

    private Panel panel;

    public World(Panel panel, String name) {
        this.panel = panel;
        sprites = new ArrayList<>();

        try {
            loadFromFile(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("worlds/" + filename + ".world"))));

        boolean bounds = false;
        boolean bg = false;
        while (reader.ready()) {
            String line = reader.readLine();

            if (line.startsWith("//") || line.startsWith("#")) {
                continue;
            }

            if (!bounds) {
                String[] split = line.split(",");

                this.width = Integer.parseInt(split[0]);
                this.height = Integer.parseInt(split[1]);

                bounds = true;
                continue;
            }

            if (!bg) {
                if (!line.equals("none")) {
                    background = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource(line)));
                }

                bg = true;
                continue;
            }

            String[] split = line.split(",");

            String name = split[0];
            int scale = Integer.parseInt(split[1]);
            int x = Integer.parseInt(split[2]);
            int y = Integer.parseInt(split[3]);
            boolean gravity = Boolean.parseBoolean(split[4]);
            boolean blocking = Boolean.parseBoolean(split[5]);
            boolean playable = Boolean.parseBoolean(split[6]);

            Sprite sprite = new Sprite(name, scale);

            sprite.setX(x);
            sprite.setY(y);
            sprite.setGravity(gravity);
            sprite.setBlocking(blocking);
            sprite.setPlayable(playable);

            addSprite(sprite);
        }
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

            if (!sprite.isGrounded(this) && sprite.hasGravity()) {
                sprite.addVelocityY(1);
            }

            if (sprite.getY() < ((height - sprite.getHeight()) - (sprite.getVelocityY()))) {
                sprite.addY((int) sprite.getVelocityY());
            } else {
                sprite.setY(height - sprite.getHeight());
                sprite.setVelocityY(0);
            }

            if (sprite.getVelocityX() < 0) {
                if (sprite.getX() > -(sprite.getVelocityX())) {
                    sprite.addX((int) sprite.getVelocityX());
                } else {
                    sprite.setX(0);
                    sprite.setVelocityX(0);
                }
            } else if (sprite.getVelocityX() > 0) {
                if (sprite.getX() < (width - sprite.getVelocityX() - sprite.getWidth() + 1)) {
                    sprite.addX((int) sprite.getVelocityX());
                } else {
                    sprite.setX(width - sprite.getWidth() + 1);
                    sprite.setVelocityX(0);
                }
            }

            if (isColliding(sprite)) {
                sprite.setVelocityX(0);
                sprite.setVelocityY(0);
                sprite.setX(sprite.getOldX());
                sprite.setY(sprite.getOldY());

                if (!sprite.isGrounded(this)) {
                    sprite.addY(1);
                }
            }

            if (sprite.isActive()) {
                if ((sprite.getX() + sprite.getWidth()) > (panel.getCameraX() + panel.getWidth() - 100)) {
                    panel.setCameraX(sprite.getX() + sprite.getWidth() - panel.getWidth() + 100);
                } else if (sprite.getX() < panel.getCameraX() + 100) {
                    panel.setCameraX(sprite.getX() - 100);
                }

                if ((sprite.getY() + sprite.getHeight()) > (panel.getCameraY() + panel.getHeight() - 100)) {
                    panel.setCameraY(sprite.getY() + sprite.getHeight() + 100);
                } else if (sprite.getY() < panel.getCameraY() + 100) {
                    panel.setCameraY(sprite.getY() - 100);
                }

                if (panel.getCameraX() > (width - panel.getWidth())) {
                    panel.setCameraX(width - panel.getWidth());
                } else if (panel.getCameraX() < 0) {
                    panel.setCameraX(0);
                }

                if (panel.getCameraY() > (height - panel.getHeight())) {
                    panel.setCameraY(height - panel.getHeight());
                } else if (panel.getCameraY() < 0) {
                    panel.setCameraY(0);
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Image getBackground() {
        return background;
    }

    public boolean isColliding(Sprite sprite) {
        for (Sprite other : sprites) {

            if (sprite != other && other.isBlocking() && sprite.getDistance(other) < 125) {
                return sprite.isColliding(other);
            }
        }

        return false;
    }
}