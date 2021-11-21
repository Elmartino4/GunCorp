package github.elmartino4.guncorp.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import github.elmartino4.guncorp.GameData;

public class MyCorpScreen extends AbstractScreen{
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font;
    GlyphLayout layout;

    public MyCorpScreen(GameData data) {
        super(data);
    }

    @Override
    public void create() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("ShareTechMono-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;

        font = generator.generateFont(parameter);

        layout = new GlyphLayout();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.87F, 0.63F, 0.4F, 1);

        super.data.batch.begin();

        font.draw(super.data.batch, "fish", Gdx.graphics.getWidth() / 2F, Gdx.graphics.getHeight() - 80,
                0, Align.center, false);

        super.data.batch.end();
    }

    @Override
    public void dispose() {
        generator.dispose();
        font.dispose();
    }
}
