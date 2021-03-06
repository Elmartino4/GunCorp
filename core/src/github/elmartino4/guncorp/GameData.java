package github.elmartino4.guncorp;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import github.elmartino4.guncorp.gui.menu.AbstractMenu;
import github.elmartino4.guncorp.gui.menu.MenuData;
import github.elmartino4.guncorp.save.DataSaveFile;
import github.elmartino4.guncorp.save.WorldSaveFile;
import github.elmartino4.guncorp.gui.screen.AbstractScreen;

import java.util.function.Consumer;

public class GameData {
    public ShapeRenderer shapeRenderer;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public ExtendViewport viewport;
    public AbstractMenu[] menus;
    public AbstractScreen[] screens;
    protected int currentMenu = -1;
    protected int currentScreen = 0;
    public Consumer<MenuData> menuDataConsumer;
    public WorldSaveFile saveFile;
    public DataSaveFile dataFile;

    public GameData(Consumer<MenuData> menuDataConsumer) {
        this.menuDataConsumer = menuDataConsumer;
        this.saveFile = new WorldSaveFile("world-save.dat");
        this.dataFile = new DataSaveFile("data-save.sql");
    }

    public void setCurrentMenu(int val) {
        if (currentMenu != -1) menus[currentMenu].hide();
        if (val != -1) menus[val].show();

        currentMenu = val;
    }

    public int getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentScreen(int currentScreen, boolean closeMenus) {
        if (closeMenus) setCurrentMenu(-1);

        if (this.currentScreen != -1) screens[this.currentScreen].hide();
        if (currentScreen != -1) screens[currentScreen].show();

        this.currentScreen = currentScreen;
    }

    public int getCurrentScreen() {
        return currentScreen;
    }

    public void onClick(MenuData menuData) {
        menuDataConsumer.accept(menuData);
    }
}
