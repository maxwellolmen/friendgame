package fr.cermak.engine;

import javax.swing.*;
import java.awt.*;

public class GravityTimer extends Timer {

    private static Screen frame;

    private static KeyTracker keyTracker;

    public GravityTimer(Screen frame, KeyTracker keyTracker) {
        super(10, e -> force());

        setInitialDelay(0);

        GravityTimer.frame = frame;
        GravityTimer.keyTracker = keyTracker;
    }

    public static void force() {
        frame.getPanel().getWorld().force();
        frame.update();

        if (keyTracker != null) {
            keyTracker.stroke();
        }

        try {
            int x = (int) (MouseInfo.getPointerInfo().getLocation().getX() - frame.getLocationOnScreen().getX());
            int y = (int) (MouseInfo.getPointerInfo().getLocation().getY() - frame.getLocationOnScreen().getY() - 27);

            frame.onMove(x, y);
        } catch (IllegalComponentStateException e) {}
    }

    public void setKeyTracker(KeyTracker keyTracker) {
        GravityTimer.keyTracker = keyTracker;
    }
}
