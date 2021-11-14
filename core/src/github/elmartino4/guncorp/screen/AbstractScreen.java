package github.elmartino4.guncorp.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import github.elmartino4.guncorp.GameData;

public abstract class AbstractScreen {
    protected GameData data;

    public AbstractScreen (GameData data) {
        this.data = data;
    }

    public abstract void render();

    public void begin () {}

    public void create () {}

    public void dispose () {}

    public String getDebugText(){
        return "";
    }
}
