package fr.cermak.friendgame;

import fr.cermak.engine.Screen;
import fr.cermak.friendgame.screen.welcome.WelcomeScreen;

public class Main {

    public static void main(String[] args) {
        Screen screen = new WelcomeScreen();

        screen.setVisible(true);
    }
}