package github.elmartino4.guncorp;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import github.elmartino4.guncorp.menu.AbstractMenu;
import github.elmartino4.guncorp.menu.MenuData;

import java.util.function.Consumer;

public class GameData {
    public ShapeRenderer shapeRenderer;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public ExtendViewport viewport;
    public AbstractMenu[] menus;
    protected int currentMenu = -1;
    public Consumer<MenuData> menuDataConsumer;

    public GameData (Consumer<MenuData> menuDataConsumer) {
        this.menuDataConsumer = menuDataConsumer;
    }

    public void renderMenu () {
        if(currentMenu != -1) menus[currentMenu].render();
    }

    public void setCurrentMenu (int val) {
        if (val != currentMenu) {
            if (currentMenu != -1) menus[currentMenu].hide();
            if (val != -1) menus[val].show();
        }

        currentMenu = val;
    }

    public int getCurrentMenu () {
        return currentMenu;
    }

    public void onClick (MenuData menuData) {
        menuDataConsumer.accept(menuData);
    }
}
