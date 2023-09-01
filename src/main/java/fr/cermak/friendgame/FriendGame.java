package fr.cermak.friendgame;

import fr.cermak.engine.KeyTracker;
import fr.cermak.engine.Sprite;
import fr.cermak.engine.Game;

public class FriendGame extends Game {

    private KeyTracker keyTracker;

    public FriendGame() {
        super(500, 500, 500, 500);

        Sprite sprite = new Sprite("maxwell", 5);
        sprite.setGravity(true);
        getPanel().getWorld().addSprite(sprite);

        keyTracker = new SpriteController(sprite);
        addKeyListener(keyTracker);
    }
}
