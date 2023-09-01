package fr.cermak.engine;

import javax.swing.*;

public class GravityTimer extends Timer {

    private static Game frame;

    private static KeyTracker keyTracker;

    private static int i;

    public GravityTimer(Game frame, KeyTracker keyTracker) {
        super(10, e -> force());

        setInitialDelay(0);

        GravityTimer.frame = frame;
        GravityTimer.keyTracker = keyTracker;
        GravityTimer.i = 0;
    }

    public static void force() {
        frame.getPanel().getWorld().force();
        frame.update();

        if (keyTracker != null) {
            keyTracker.stroke();
        }
    }

    public void setKeyTracker(KeyTracker keyTracker) {
        GravityTimer.keyTracker = keyTracker;
    }
}
