package fr.cermak.friendgame;

import fr.cermak.engine.KeyTracker;
import fr.cermak.engine.Sprite;
import fr.cermak.engine.Game;

public class FriendGame extends Game {

    private KeyTracker keyTracker;

    public FriendGame(String name) {
        super(1200, 700, "intro");

        Sprite sprite = new Sprite(name, 5);
        sprite.setGravity(true);
        getPanel().getWorld().addSprite(sprite);

        keyTracker = new SpriteController(sprite, getPanel().getWorld());
        addKeyListener(keyTracker);
    }
}