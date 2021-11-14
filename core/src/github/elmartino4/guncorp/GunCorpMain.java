package github.elmartino4.guncorp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import github.elmartino4.guncorp.map.MapScreen;
import github.elmartino4.guncorp.menu.AbstractMenu;
import github.elmartino4.guncorp.menu.AreaMenu;
import github.elmartino4.guncorp.menu.EscapeMenu;
import github.elmartino4.guncorp.menu.MenuData;
import github.elmartino4.guncorp.screen.AbstractScreen;

public class GunCorpMain extends ApplicationAdapter {
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font;

    public GameData gameData = new GameData(this::onMenuData);

    private float fps = 60;

	public int currentScreen = 0;

	private final AbstractScreen[] SCREENS = {
			new MapScreen(gameData)
	};

	public GunCorpMain(ConfigChangeCallback configChangeCallback) {
		this.gameData.menus = new AbstractMenu[] { new EscapeMenu(gameData), new AreaMenu(gameData) };

		UserConfig.configChangeCallback = configChangeCallback;
	}

    @Override
    public void create() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("ShareTechMono-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;

        this.gameData.batch = new SpriteBatch();
        this.gameData.shapeRenderer = new ShapeRenderer();

        font = generator.generateFont(parameter);


        //Camera
        this.gameData.camera = new OrthographicCamera();

        //Viewport
        this.gameData.viewport = new ExtendViewport(1500, 800, this.gameData.camera);

        for (AbstractScreen screen : SCREENS) {
            screen.create();
        }


        for (AbstractMenu menu : gameData.menus) {
            menu.create();
        }
    }

    @Override
    public void render() {
        fps *= 0.99;
        fps += 1D / Gdx.graphics.getDeltaTime() * 0.01;
        String text = String.format("%.2f FPS\n", fps);

        ScreenUtils.clear(1, 0, 0, 1);

        SCREENS[currentScreen].render();

        text += SCREENS[currentScreen].getDebugText();

        if (gameData.getCurrentMenu() != -1)
            gameData.menus[gameData.getCurrentMenu()].render();

        this.gameData.batch.begin();

        font.draw(this.gameData.batch, text, 10, Gdx.graphics.getHeight() - 10);


        this.gameData.batch.end();
    }

    @Override
    public void dispose() {
        this.gameData.batch.dispose();
        this.gameData.shapeRenderer.dispose();
        font.dispose();
        generator.dispose();
    }

    @Override
    public void resize(int width, int height) {
        this.gameData.viewport.setMinWorldWidth(width);
        this.gameData.viewport.setMinWorldHeight(height);
        this.gameData.viewport.update(width, height, true);

        this.gameData.batch.setProjectionMatrix(this.gameData.camera.combined);
        this.gameData.shapeRenderer.setProjectionMatrix(this.gameData.camera.combined);

        if (this.gameData.getCurrentMenu() == 1) this.gameData.setCurrentMenu(-1);
    }

    public void onMenuData(MenuData data) {
        if (data.equals(MenuData.QUIT)) Gdx.app.exit();
        if (data.equals(MenuData.PEDIA)) Gdx.app.exit();
        if (data.equals(MenuData.MY_CORP)) Gdx.app.exit();
    }
}
