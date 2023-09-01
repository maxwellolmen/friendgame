package fr.cermak.engine;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class Sprite {

    public class Pixel {
        int offsetX;
        int offsetY;
        Color color;

        public Pixel(int offsetX, int offsetY, Color color) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.color = color;
        }
    }

    private List<Pixel> pixels;

    private int height;
    private int width;

    private int x;
    private int y;

    private int oldX;
    private int oldY;

    private double velocityX;
    private double velocityY;

    private boolean gravity;

    public Sprite(String filename, int scale) {
        this.x = this.oldX = 200;
        this.y = this.oldY = 100;
        this.velocityX = 0;
        this.velocityY = 0;
        this.gravity = true;

        InputStream is = getClass().getClassLoader().getResourceAsStream(filename + ".sprite");

        if (is == null) {
            System.err.println("SPRITE NOT LOADING CORRECTLY!");
            is = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("sprites/blob.sprite")); // default sprite image
        }

        pixels = new ArrayList<>();

        try {
            loadPixels(is, scale);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadPixels(InputStream is, int scale) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while (reader.ready()) {
            String line = reader.readLine();

            String[] split = line.split(",");

            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            Color color = new Color(Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]));

            if (x + 1 > width) {
                width = x + 1;
            }

            if (y + 1 > height) {
                height = y + 1;
            }

            for (int i = 0; i < scale; i++) {
                for (int j = 0; j < scale; j++) {
                    Pixel pixel = new Pixel(x * scale - i, y * scale - j, color);
                    pixels.add(pixel);
                }
            }
        }

        width *= scale;
        height *= scale;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public List<Pixel> getPixels() {
        return pixels;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public boolean hasGravity() {
        return gravity;
    }

    public void addVelocityX(double v) {
        velocityX += v;
    }

    public void addVelocityY(double v) {
        velocityY += v;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void addX(int x) {
        this.x += x;
    }

    public void addY(int y) {
        this.y += y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}