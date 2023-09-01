package fr.cermak.engine;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private World world;

    public Panel(int windowWidth, int windowHeight, String startingWorld) {
        super();

        setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.world = new World(startingWorld);
    }

    @Override
    public void paint(Graphics g) {
        if (world.getBackground() != null) {
            g.drawImage(world.getBackground(), 0, 0, world.getWidth(), world.getHeight(), null);
        }

        for (Sprite sprite : world.getSprites()) {
            for (Sprite.Pixel pixel : sprite.getPixels()) {
                g.setColor(pixel.color);
                g.drawLine(sprite.getX() + pixel.offsetX, sprite.getY() + pixel.offsetY, sprite.getX() + pixel.offsetX, sprite.getY() + pixel.offsetY);
            }
        }
    }

    public World getWorld() {
        return world;
    }
}
