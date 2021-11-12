package github.elmartino4.guncorp.menu;

import github.elmartino4.guncorp.GameData;
import github.elmartino4.guncorp.screen.AbstractScreen;

public abstract class AbstractMenu {
    protected GameData data;

    public AbstractMenu (GameData data) {
        this.data = data;
    }

    public abstract void render();

    public void create () {}

    public void show () {}

    public void hide () {}

    public String getDebugText(){
        return "";
    }
}
