package fr.cermak.friendgame;

import fr.cermak.engine.KeyTracker;
import fr.cermak.engine.Game;

public class FriendGame extends Game {

    private KeyTracker keyTracker;

    public FriendGame() {
        super(1200, 700, "intro");

        keyTracker = new SpriteController(getPanel().getWorld().getSprites().get(0), getPanel().getWorld());
        addKeyListener(keyTracker);

        setKeyTracker(keyTracker);
    }
}