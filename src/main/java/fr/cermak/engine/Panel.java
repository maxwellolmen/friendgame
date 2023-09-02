package fr.cermak.engine;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private World world;

    private int cameraX;
    private int cameraY;

    public Panel(int windowWidth, int windowHeight, String startingWorld) {
        super();

        this.cameraX = this.cameraY = 0;

        setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.world = new World(this, startingWorld);
    }

    @Override
    public void paint(Graphics g) {
        if (world.getBackground() != null) {
            g.drawImage(world.getBackground(), -cameraX, -cameraY, world.getWidth(), world.getHeight(), null);
        }

        for (Sprite sprite : world.getSprites()) {
            if (sprite.getX() + sprite.getWidth() < cameraX
                    || sprite.getX() > cameraX + getWidth()
                    || sprite.getY() + sprite.getHeight() < cameraY
                    || sprite.getY() > cameraY + getHeight()) {
                continue;
            }

            for (Sprite.Pixel pixel : sprite.getPixels()) {
                g.setColor(pixel.color);
                g.drawLine(sprite.getX() + pixel.offsetX - cameraX, sprite.getY() + pixel.offsetY - cameraY, sprite.getX() + pixel.offsetX - cameraX, sprite.getY() + pixel.offsetY - cameraY);
            }
        }
    }

    public World getWorld() {
        return world;
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public void setCameraX(int cameraX) {
        this.cameraX = cameraX;
    }

    public void setCameraY(int cameraY) {
        this.cameraY = cameraY;
    }
}
