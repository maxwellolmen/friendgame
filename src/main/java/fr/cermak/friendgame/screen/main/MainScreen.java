package fr.cermak.friendgame.screen.main;

import fr.cermak.engine.KeyTracker;
import fr.cermak.engine.Screen;
import fr.cermak.engine.Sprite;

public class MainScreen extends Screen {

    private KeyTracker keyTracker;

    public MainScreen() {
        super(1200, 700, "main");

        keyTracker = new MainController(getPanel().getWorld().getSprites().get("maxwell"), getPanel().getWorld());
        addKeyListener(keyTracker);
        setKeyTracker(keyTracker);
    }

    @Override
    public void onClick(int x, int y, boolean spriteClicked) {}

    @Override
    public void onClick(Sprite sprite) {}

    @Override
    public void onHover(Sprite sprite) {

    }

    @Override
    public void onNoHover(Sprite sprite) {

    }
}