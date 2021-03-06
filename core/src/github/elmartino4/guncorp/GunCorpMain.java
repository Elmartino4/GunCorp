package github.elmartino4.guncorp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import github.elmartino4.guncorp.config.ConfigChangeCallback;
import github.elmartino4.guncorp.config.Keybindings;
import github.elmartino4.guncorp.config.UserConfig;
import github.elmartino4.guncorp.gui.menu.*;
import github.elmartino4.guncorp.gui.screen.AbstractScreen;
import github.elmartino4.guncorp.gui.screen.CorpopediaScreen;
import github.elmartino4.guncorp.gui.screen.MapScreen;
import github.elmartino4.guncorp.gui.screen.MyCorpScreen;

public class GunCorpMain extends ApplicationAdapter {
    public GameData gameData = new GameData(this::onMenuData);

    private int titleTimer = 0;

    public GunCorpMain(ConfigChangeCallback configChangeCallback) {
        this.gameData.menus = new AbstractMenu[] { new EscapeMenu(gameData), new AreaMenu(gameData), new BuildingMenu(gameData)};
        this.gameData.screens = new AbstractScreen[] { new MapScreen(gameData), new CorpopediaScreen(gameData),
                new MyCorpScreen(gameData) };
        UserConfig.configChangeCallback = configChangeCallback;
    }

    @Override
    public void create() {
        this.gameData.batch = new SpriteBatch();
        this.gameData.shapeRenderer = new ShapeRenderer();

        // Camera
        this.gameData.camera = new OrthographicCamera();

        // Viewport
        this.gameData.viewport = new ExtendViewport(1500, 800, this.gameData.camera);

        for (AbstractScreen screen : gameData.screens) {
            screen.create();
        }

        for (AbstractMenu menu : gameData.menus) {
            menu.create();
        }

        UserConfig.generate();
        Keybindings.generate();

        try {
            this.gameData.saveFile.begin();
            this.gameData.dataFile.begin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        if (titleTimer < 0) {
            if (UserConfig.prefs.getBoolean("debug")) {
                Gdx.graphics.setTitle(String.format("GunCorp - %dfps\n", Gdx.graphics.getFramesPerSecond()));
            }
            titleTimer = (int) (Math.min(Gdx.graphics.getFramesPerSecond() / 2F, 1000));
        } else {
            titleTimer--;
        }

        if (Keybindings.isKeyJustPressed("Toggle debug")) {
            UserConfig.prefs.putBoolean("debug", !UserConfig.prefs.getBoolean("debug"));
            UserConfig.prefs.flush();
            if (!UserConfig.prefs.getBoolean("debug")) {
                Gdx.graphics.setTitle("GunCorp");
            } else {
                Gdx.graphics.setTitle(String.format("GunCorp - %dfps\n", Gdx.graphics.getFramesPerSecond()));
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);

        gameData.screens[gameData.getCurrentScreen()].render();

        if (gameData.getCurrentMenu() != -1)
            gameData.menus[gameData.getCurrentMenu()].render();
    }

    @Override
    public void dispose() {
        this.gameData.batch.dispose();
        this.gameData.shapeRenderer.dispose();
        try {
            this.gameData.saveFile.save();
            this.gameData.dataFile.save();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (AbstractMenu menu : gameData.menus) {
            menu.dispose();
        }

        for (AbstractScreen screen : gameData.screens) {
            screen.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        this.gameData.viewport.setMinWorldWidth(width);
        this.gameData.viewport.setMinWorldHeight(height);
        this.gameData.viewport.update(width, height, true);

        this.gameData.batch.setProjectionMatrix(this.gameData.camera.combined);
        this.gameData.shapeRenderer.setProjectionMatrix(this.gameData.camera.combined);

        if (this.gameData.getCurrentMenu() == 1)
            this.gameData.setCurrentMenu(-1);
    }

    public void onMenuData(MenuData data) {
        if (data.equals(MenuData.QUIT))
            Gdx.app.exit();
        if (data.equals(MenuData.PEDIA))
            gameData.setCurrentScreen(1, true);
        if (data.equals(MenuData.MY_CORP))
            gameData.setCurrentScreen(2, true);
    }
}
