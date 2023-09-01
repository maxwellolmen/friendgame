package fr.cermak.engine;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private World world;

    public Panel(int worldWidth, int worldHeight, int windowWidth, int windowHeight) {
        super();

        setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.world = new World(worldWidth, worldHeight);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(85, 239, 246));
        g.fillRect(0, 0, 500, 500);

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
