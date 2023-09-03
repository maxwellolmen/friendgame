package fr.cermak.friendgame.screen.welcome;

import fr.cermak.engine.Button;
import fr.cermak.engine.Screen;
import fr.cermak.engine.Sprite;
import fr.cermak.friendgame.screen.main.MainScreen;
import fr.cermak.friendgame.screen.settings.SettingsScreen;

import java.awt.event.WindowEvent;

public class WelcomeScreen extends Screen {

    private Button campaign, online, settings;

    public WelcomeScreen() {
        super(800, 500, "welcome");

        campaign = new Button("button-campaign", 1);
        online = new Button("button-online", 1);
        settings = new Button("button-settings", 1);

        campaign.setLocation(234, 213);
        online.setLocation(234, 285);
        settings.setLocation(231, 355);

        addSprites(campaign, online, settings);
    }

    @Override
    public void onClick(int x, int y, boolean spriteClicked) {}

    @Override
    public void onClick(Sprite sprite) {
        if (sprite == campaign) {
            close();

            Screen screen = new MainScreen();
            screen.setVisible(true);
        } else if (sprite == settings) {
            close();

            Screen screen = new SettingsScreen();
            screen.setVisible(true);
        }
    }


}