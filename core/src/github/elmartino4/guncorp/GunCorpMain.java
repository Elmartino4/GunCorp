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
import github.elmartino4.guncorp.menu.MenuData;

public class GunCorpMain extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;

	FreeTypeFontGenerator generator;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	OrthographicCamera camera;
	ExtendViewport viewport;
	BitmapFont font;

	private float fps = 60;

	private final ScreenAdapter[] SCREENS = {
			new MapScreen(this::onMenuData)
	};

	int currentScreen = 0;

	@Override
	public void create () {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("ShareTechMono-Regular.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 32;

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		font = generator.generateFont(parameter);

		//Camera
		camera = new OrthographicCamera();

		//Viewport
		viewport = new ExtendViewport(1500, 800, camera);

		for (ApplicationAdapter screen : SCREENS) {
			screen.create();
		}
	}

	@Override
	public void render () {
		fps *= 0.99;
		fps += 1D/Gdx.graphics.getDeltaTime() * 0.01;
		String text = String.format("%.2f FPS\n", fps);

		ScreenUtils.clear(1, 0, 0, 1);

		SCREENS[currentScreen].render(batch, shapeRenderer);

		text += SCREENS[currentScreen].getDebugText();

		batch.begin();

		font.draw(batch, text, 10, Gdx.graphics.getHeight() - 10);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		font.dispose();
		generator.dispose();

		for (ApplicationAdapter screen : SCREENS) {
			screen.dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		viewport.setMinWorldWidth(width);
		viewport.setMinWorldHeight(height);
		viewport.update(width, height, true);

		batch.setProjectionMatrix(camera.combined);

		for (ApplicationAdapter screen : SCREENS) {
			screen.resize(width, height);
		}
	}

	public void onMenuData(MenuData data){
		if(data.equals(MenuData.QUIT))		Gdx.app.exit();
		if(data.equals(MenuData.PEDIA))		Gdx.app.exit();
		if(data.equals(MenuData.MY_CORP))	Gdx.app.exit();
	}
}
