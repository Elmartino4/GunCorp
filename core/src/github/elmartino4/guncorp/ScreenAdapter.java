package github.elmartino4.guncorp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class ScreenAdapter extends ApplicationAdapter {
    public abstract void render(SpriteBatch batch, ShapeRenderer shapeRenderer);

    public String getDebugText(){
        return "";
    }
}
