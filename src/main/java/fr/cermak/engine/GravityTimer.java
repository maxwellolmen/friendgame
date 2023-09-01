package fr.cermak.engine;

import javax.swing.*;

public class GravityTimer extends Timer {

    private static Game frame;

    public GravityTimer(Game frame) {
        super(10, e -> force());

        setInitialDelay(0);

        GravityTimer.frame = frame;
    }

    public static void force() {
        frame.getPanel().getWorld().force();
        frame.update();
    }
}
