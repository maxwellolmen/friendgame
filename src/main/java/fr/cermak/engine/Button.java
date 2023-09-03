package fr.cermak.engine;

public class Button extends Sprite {

    public Button(String filename, int scale) {
        super(filename, scale);

        setPhase("no-hover");
        setGravity(false);
    }

    public void onHover() {
        setPhase("hover");
    }

    public void onNoHover() {
        setPhase("no-hover");
    }
}