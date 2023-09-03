package fr.cermak.friendgame.screen.settings;

import fr.cermak.engine.Button;
import fr.cermak.engine.Screen;
import fr.cermak.engine.Sprite;
import fr.cermak.friendgame.screen.main.MainScreen;
import fr.cermak.friendgame.screen.welcome.WelcomeScreen;

public class SettingsScreen extends Screen {

    private Button back;

    public SettingsScreen() {
        super(800, 500, "settings");

        back = new Button("button-back", 1);

        back.setLocation(350, 400);

        addSprite(back);
    }

    @Override
    public void onClick(int x, int y, boolean spriteClicked) {

    }

    @Override
    public void onClick(Sprite sprite) {
        if (sprite == back) {
            close();

            Screen screen = new WelcomeScreen();
            screen.setVisible(true);
        }
    }
}
