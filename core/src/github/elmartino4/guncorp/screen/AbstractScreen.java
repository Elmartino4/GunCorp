package github.elmartino4.guncorp.screen;

import github.elmartino4.guncorp.GameData;

public abstract class AbstractScreen {
    protected GameData data;

    public AbstractScreen (GameData data) {
        this.data = data;
    }

    public abstract void render();

    public void show () {}

    public void hide () {}

    public void create () {}

    public void dispose () {}
}
