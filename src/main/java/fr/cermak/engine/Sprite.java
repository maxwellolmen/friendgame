package fr.cermak.engine;

import java.net.URISyntaxException;
import java.nio.Buffer;
import java.util.*;
import java.awt.*;
import java.io.*;
import java.util.List;

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

        public boolean isWithin(List<Pixel> pixels, int curX, int curY, int otherX, int otherY) {
            //System.out.println(curX + " " + curY + ", " + otherX + " " + otherY + "\n");

            for (Pixel pixel : pixels) {
                //System.out.println((offsetX + curX) + " " + (offsetY + curY) + ", " + (pixel.offsetX + otherX) + " " + (pixel.offsetY + otherY));

                if ((pixel.offsetX + otherX) == (offsetX + curX) && (pixel.offsetY + otherY) == (offsetY + curY)) return true;
            }

            return false;
        }
    }

    private Map<String, List<Pixel>> phases;
    private String phase;

    private int height;
    private int width;

    private int x;
    private int y;

    private int oldX;
    private int oldY;

    private double velocityX;
    private double velocityY;

    private boolean gravity;
    private boolean blocking;
    private boolean playable;

    private boolean grounded;
    private boolean active;

    public Sprite(String filename, int scale) {
        this.x = this.oldX = 200;
        this.y = this.oldY = 100;
        this.velocityX = 0;
        this.velocityY = 0;
        this.gravity = true;
        this.blocking = true;
        this.playable = false;
        this.grounded = false;
        this.active = false;

        phases = new TreeMap<>();
        phase = "default";

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("sprites/" + filename + "/phases.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            String phaseLine = reader.readLine();
            String[] split = phaseLine.split(",");

            for (String phaseString : split) {
                InputStream is2 = getClass().getClassLoader().getResourceAsStream("sprites/" + filename + "/" + phaseString + ".sprite");

                loadPixels(is2, scale, phaseString);
                Objects.requireNonNull(is2).close();
            }

            reader.close();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadPixels(InputStream is, int scale, String phase) throws IOException {
        List<Pixel> pixels = new ArrayList<>();
        phases.put(phase, pixels);

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
                    Pixel pixel = new Pixel(x * scale + i, y * scale + j, color);
                    pixels.add(pixel);
                }
            }
        }

        width *= scale;
        height *= scale;

        reader.close();
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
        return phases.get(phase);
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

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public boolean isBlocking() {
        return blocking;
    }

    public void setBlocking(boolean blocking) {
        this.blocking = blocking;
    }

    public boolean isColliding(Sprite other) {
        if (x < (other.getX() + other.getWidth())
                && x + width > other.getX()
                && y < (other.getY() + other.getHeight())
                && y + height > other.getY()) {
            return true;
        }

        return false;
    }

    public double getDistance(Sprite other) {
        return Math.sqrt(Math.pow(other.getX() - x, 2.0) + Math.pow(other.getY() - y, 2.0));
    }

    public boolean isGrounded(World world) {
        if (y == (world.getHeight() - height)) {
            return true;
        }

        for (Sprite other : world.getSprites()) {
            if (other.getY() == (y + height)) {
                if (x < other.getX()) {
                    if (width > (other.getX() - x)) {
                        return true;
                    }
                } else if (x > other.getX()) {
                    if (other.getWidth() > (x - other.getX())) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}