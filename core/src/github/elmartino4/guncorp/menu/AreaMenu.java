package github.elmartino4.guncorp.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import github.elmartino4.guncorp.Menu;

import java.util.function.Consumer;

public class AreaMenu extends Menu {
    Consumer<MenuData> menuDataConsumer;
    public ContextMenuData contextMenuData = new ContextMenuData();
    int x, y;
    boolean isAbove = false, isRight = false;
    public static final Color BORDER = new Color(0.08F, 0.08F, 0.08F, 1);
    public static final Color INNER = new Color(0.3F, 0.3F, 0.3F, 1);

    public AreaMenu(Consumer<MenuData> consumer){
        menuDataConsumer = consumer;
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, contextMenuData.getSize()[0], contextMenuData.getSize()[1],
                INNER, INNER, INNER, INNER);
        shapeRenderer.rect(x + 2, y + 2, contextMenuData.getSize()[0] - 4,
                contextMenuData.getSize()[1] - 4,
                BORDER, BORDER, BORDER, BORDER);

        shapeRenderer.end();
    }

    public void setMenuLocation(int x, int y){
        this.x = x - ((x > Gdx.graphics.getWidth() * 0.8) ? contextMenuData.getSize()[0] : 0);
        this.y = y - ((y < Gdx.graphics.getHeight() * 0.8) ? contextMenuData.getSize()[1] : 0);
    }
}
