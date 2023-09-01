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

    /*public World(int width, int height) {
        this.width = width;
        this.height = height;

        sprites = new ArrayList<>();
    }*/

    public World(String name) {
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

            Sprite sprite = new Sprite(name, scale);

            sprite.setX(x);
            sprite.setY(y);
            sprite.setGravity(gravity);

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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Image getBackground() {
        return background;
    }
}