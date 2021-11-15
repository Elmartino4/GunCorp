package github.elmartino4.guncorp.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import github.elmartino4.guncorp.GameData;

public class MyCorpScreen extends AbstractScreen{
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font;

    public MyCorpScreen(GameData data) {
        super(data);
    }

    @Override
    public void create() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("ShareTechMono-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;

        font = generator.generateFont(parameter);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.87F, 0.63F, 0.4F, 1);

        super.data.batch.begin();

        font.draw(super.data.batch, "fish", 50, Gdx.graphics.getHeight() - 200);

        super.data.batch.end();
    }

    @Override
    public void dispose() {
        generator.dispose();
        font.dispose();
    }
}
