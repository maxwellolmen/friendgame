package fr.cermak.engine;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Panel extends JPanel implements MouseListener {

    private Screen screen;
    private World world;

    private int cameraX;
    private int cameraY;

    public Panel(int windowWidth, int windowHeight, String startingWorld, Screen screen) {
        super();

        this.screen = screen;

        this.cameraX = this.cameraY = 0;

        setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.world = new World(this, startingWorld);

        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        if (world.getBackground() != null) {
            g.drawImage(world.getBackground(), -cameraX, -cameraY, world.getWidth(), world.getHeight(), null);
        }

        for (Sprite sprite : world.getSprites().values()) {
            if (sprite.getX() + sprite.getWidth() < cameraX
                    || sprite.getX() > cameraX + getWidth()
                    || sprite.getY() + sprite.getHeight() < cameraY
                    || sprite.getY() > cameraY + getHeight()) {
                continue;
            }

            List<Sprite.Pixel> pixels = sprite.getPixels();

            if (pixels == null) {
                g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), null);
            } else {
                for (Sprite.Pixel pixel : sprite.getPixels()) {
                    g.setColor(pixel.color);
                    g.drawLine(sprite.getX() + pixel.offsetX - cameraX, sprite.getY() + pixel.offsetY - cameraY, sprite.getX() + pixel.offsetX - cameraX, sprite.getY() + pixel.offsetY - cameraY);
                }
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

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        boolean spriteClicked = false;
        for (Sprite sprite : world.getSprites().values()) {
            if (sprite.isOn(e.getX(), e.getY())) {
                spriteClicked = true;
                screen.onClick(sprite);
            }
        }

        screen.onClick(e.getX(), e.getY(), spriteClicked);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
