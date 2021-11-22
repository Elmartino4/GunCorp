package github.elmartino4.guncorp.gui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import github.elmartino4.guncorp.GameData;
import github.elmartino4.guncorp.config.Keybindings;
import github.elmartino4.guncorp.corporation.CorpData;

public class MyCorpScreen extends AbstractScreen{
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font;
    GlyphLayout layout;
    CorpData corpData;

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

        corpData = new CorpData();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.08F, 0.08F, 0.05F, 1);

        if (Keybindings.isKeyJustPressed("Exit menu")) {
            super.data.setCurrentMenu((super.data.getCurrentMenu() != -1) ? -1 : 0);
        }

        super.data.batch.begin();

        font.draw(super.data.batch, String.format("%s - $%.2f", corpData.name, corpData.value), Gdx.graphics.getWidth() / 2F,
                Gdx.graphics.getHeight() - 80, 0, Align.center, false);

        super.data.batch.end();
    }

    @Override
    public void dispose() {
        generator.dispose();
        font.dispose();
    }
}
