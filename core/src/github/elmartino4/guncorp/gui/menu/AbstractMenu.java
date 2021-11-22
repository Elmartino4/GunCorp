package github.elmartino4.guncorp.gui.menu;

import github.elmartino4.guncorp.GameData;

public abstract class AbstractMenu {
    protected GameData data;

    public AbstractMenu (GameData data) {
        this.data = data;
    }

    public abstract void render();

    public void create () {}

    public void dispose () {}

    public void show () {}

    public void hide () {}

    public String getDebugText(){
        return "";
    }
}
